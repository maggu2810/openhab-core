/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.smarthome.io.rest.sse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.OutboundSseEvent.Builder;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.auth.Role;
import org.eclipse.smarthome.core.events.Event;
import org.eclipse.smarthome.io.rest.RESTConstants;
import org.eclipse.smarthome.io.rest.SseBroadcaster;
import org.eclipse.smarthome.io.rest.sse.internal.SsePublisher;
import org.eclipse.smarthome.io.rest.sse.internal.SseSinkInfo;
import org.eclipse.smarthome.io.rest.sse.internal.dto.EventDTO;
import org.eclipse.smarthome.io.rest.sse.internal.util.SseUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsApplicationSelect;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsName;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * SSE Resource for pushing events to currently listening clients.
 *
 * @author Ivan Iliev - Initial contribution
 * @author Yordan Zhelev - Added Swagger annotations
 * @author Markus Rathgeb - Drop Glassfish dependency and use API only
 */
@Component(service = SsePublisher.class/* , scope = ServiceScope.PROTOTYPE */)
@JaxrsResource
@JaxrsName("events")
@JaxrsApplicationSelect("(" + JaxrsWhiteboardConstants.JAX_RS_NAME + "=" + RESTConstants.JAX_RS_NAME + ")")
@JSONRequired
@Path("/events")
@RolesAllowed({ Role.USER })
@Singleton
@Api(value = "events", hidden = true)
@NonNullByDefault
public class SseResource implements SsePublisher {

    private static final String X_ACCEL_BUFFERING_HEADER = "X-Accel-Buffering";

    private final Logger logger = LoggerFactory.getLogger(SseResource.class);

    private @NonNullByDefault({}) Builder eventBuilder;

    private final SseBroadcaster<SseSinkInfo> broadcaster = new SseBroadcaster<>();

    private final ExecutorService executorService;

    @Context
    public void setSse(final Sse sse) {
        this.eventBuilder = sse.newEventBuilder();
    }

    @Activate
    public SseResource() {
        this.executorService = Executors.newSingleThreadExecutor();
    }

    @Deactivate
    public void deactivate() {
        broadcaster.close();
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @ApiOperation(value = "Get all events.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Topic is empty or contains invalid characters") })
    public void listen(@Context final SseEventSink sseEventSink, @Context final HttpServletResponse response,
            @QueryParam("topics") @ApiParam(value = "topics") String eventFilter) {
        if (!SseUtil.isValidTopicFilter(eventFilter)) {
            response.setStatus(Status.BAD_REQUEST.getStatusCode());
            return;
        }

        broadcaster.add(sseEventSink, new SseSinkInfo(eventFilter));

        // Disables proxy buffering when using an nginx http server proxy for this response.
        // This allows you to not disable proxy buffering in nginx and still have working sse
        response.addHeader(X_ACCEL_BUFFERING_HEADER, "no");

        // We want to make sure that the response is not compressed and buffered so that the client receives server sent
        // events at the moment of sending them.
        response.addHeader(HttpHeaders.CONTENT_ENCODING, "identity");
        try {
            response.flushBuffer();
        } catch (final IOException ex) {
            logger.trace("flush buffer failed", ex);
        }
    }

    private void handleEventBroadcast(Event event) {
        final Builder eventBuilder = this.eventBuilder;
        if (eventBuilder == null) {
            logger.trace("broadcast skipped, event builder unknown (no one listened since activation)");
            return;
        }

        final EventDTO eventDTO = SseUtil.buildDTO(event);
        final OutboundSseEvent sseEvent = SseUtil.buildEvent(eventBuilder, eventDTO);

        broadcaster.sendIf(sseEvent, info -> info.matchesTopic(eventDTO.topic));
    }

    @Override
    public void broadcast(Event event) {
        executorService.execute(() -> handleEventBroadcast(event));
    }
}

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
package org.eclipse.smarthome.storage.mapdb.internal.migration;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Exception to identify a migration error.
 *
 * @author Markus Rathgeb - Initial contribution and API
 */
@NonNullByDefault
public class MigrationException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     *
     * @param cause the cause
     */
    public MigrationException(final Throwable cause) {
        super(cause);
    }

}

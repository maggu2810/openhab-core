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

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Migration handler for rules.
 *
 * @author Markus Rathgeb - Initial contribution and API
 */
@NonNullByDefault
public class RuleMigrationHandler implements MigrationHandler {

    private final Type type = new TypeToken<Map<String, Object>>() {
    }.getType();

    private final Gson mapper;

    /**
     * Constructor.
     *
     * @param mapper the Gson mapper
     */
    public RuleMigrationHandler(final Gson mapper) {
        this.mapper = mapper;
    }

    @Override
    public String getTypeNameOld() {
        return "org.eclipse.smarthome.automation.Rule";
    }

    @Override
    public String getTypeNameNew() {
        return "org.eclipse.smarthome.automation.dto.RuleDTO";
    }

    @Override
    public String migrate(final String value) throws MigrationException {
        try {
            final Map<String, Object> myMap = mapper.fromJson(value, type);
            if (myMap != null) {
                mergePropertiesIntoConfiguration(myMap);
                mergePropertiesIntoConfiguration(myMap.get("triggers"));
                mergePropertiesIntoConfiguration(myMap.get("actions"));
                mergePropertiesIntoConfiguration(myMap.get("conditions"));
                return mapper.toJson(myMap);
            } else {
                return value;
            }
        } catch (final JsonSyntaxException ex) {
            throw new MigrationException(ex);
        }
    }

    private void mergePropertiesIntoConfiguration(final @Nullable Object parentOfConfiguration) {
        if (parentOfConfiguration instanceof Map) {
            mergePropertiesIntoConfigurationOfMap((Map<?, ?>) parentOfConfiguration);
        } else if (parentOfConfiguration instanceof Collection) {
            mergePropertiesIntoConfigurationOfCollection((Collection<?>) parentOfConfiguration);
        }
    }

    private void mergePropertiesIntoConfigurationOfCollection(final Collection<?> parentOfConfigurations) {
        for (final Object parentOfConfiguration : parentOfConfigurations) {
            mergePropertiesIntoConfiguration(parentOfConfiguration);
        }
    }

    private void mergePropertiesIntoConfigurationOfMap(final Map<?, ?> parentOfConfiguration) {
        Object tmp;
        tmp = parentOfConfiguration.get("configuration");
        if (tmp instanceof Map) {
            final Map<?, ?> cfg = (Map<?, ?>) tmp;
            tmp = cfg.remove("properties");
            if (tmp instanceof Map) {
                final Map<?, ?> props = (Map<?, ?>) tmp;
                putAll(props, cfg);
            }
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static void putAll(final Map<?, ?> source, final Map<?, ?> destination) {
        ((Map) destination).putAll(source);
    }

}

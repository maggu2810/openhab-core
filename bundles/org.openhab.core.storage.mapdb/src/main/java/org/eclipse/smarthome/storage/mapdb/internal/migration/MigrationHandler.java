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
 * Definition of the {@code MigrationHandler} interface.
 *
 * @author Markus Rathgeb - Initial contribution and API
 */
@NonNullByDefault
public interface MigrationHandler {

    /**
     * Gets the old type name.
     *
     * @return old type name
     */
    String getTypeNameOld();

    /**
     * Gets the new type name.
     *
     * @return new type name
     */
    String getTypeNameNew();

    /**
     * Migrate the old data to the new one.
     *
     * @param value the old data
     * @return the new data
     * @throws MigrationException on error
     */
    String migrate(String value) throws MigrationException;
}

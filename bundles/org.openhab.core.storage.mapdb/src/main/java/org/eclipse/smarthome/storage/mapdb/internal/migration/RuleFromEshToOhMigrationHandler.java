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
 * Migration handler for rules.
 *
 * @author Markus Rathgeb - Initial contribution and API
 */
@NonNullByDefault
public class RuleFromEshToOhMigrationHandler implements MigrationHandler {

    @Override
    public String getTypeNameOld() {
        return "org.eclipse.smarthome.automation.dto.RuleDTO";
    }

    @Override
    public String getTypeNameNew() {
        return "org.openhab.core.automation.dto.RuleDTO";
    }

    @Override
    public String migrate(final String value) throws MigrationException {
        return value;
    }

}

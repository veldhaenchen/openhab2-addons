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
package org.openhab.binding.zoneminder.internal;

import java.util.Set;

import org.eclipse.smarthome.core.thing.Bridge;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingTypeUID;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandlerFactory;
import org.eclipse.smarthome.core.thing.binding.ThingHandler;
import org.openhab.binding.zoneminder.ZoneMinderConstants;
import org.openhab.binding.zoneminder.handler.ZoneMinderServerBridgeHandler;
import org.openhab.binding.zoneminder.handler.ZoneMinderThingMonitorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;

/**
 * The {@link ZoneMinderHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Martin S. Eskildsen - Initial contribution
 *
 */
public class ZoneMinderHandlerFactory extends BaseThingHandlerFactory {

    private Logger logger = LoggerFactory.getLogger(ZoneMinderHandlerFactory.class);

    public static final Set<ThingTypeUID> SUPPORTED_THING_TYPES = Sets.union(
            ZoneMinderServerBridgeHandler.SUPPORTED_THING_TYPES, ZoneMinderThingMonitorHandler.SUPPORTED_THING_TYPES);

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {

        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(ZoneMinderConstants.THING_TYPE_BRIDGE_ZONEMINDER_SERVER)) {

            logger.debug("[FACTORY]: creating handler for bridge thing '{}'", thing);
            ZoneMinderServerBridgeHandler bridge = new ZoneMinderServerBridgeHandler((Bridge) thing);

            return bridge;
        } else if (thingTypeUID.equals(ZoneMinderConstants.THING_TYPE_THING_ZONEMINDER_MONITOR)) {
            return new ZoneMinderThingMonitorHandler(thing);
        }

        return null;
    }

}

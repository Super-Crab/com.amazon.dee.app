package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.AccessoryTransport;
/* loaded from: classes.dex */
public final class DeviceUtils {
    private DeviceUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static int getDeviceTransport(AccessoryTransport.Type type) {
        Preconditions.notNull(type, "type");
        if (type == AccessoryTransport.Type.GATT) {
            return 0;
        }
        if (type == AccessoryTransport.Type.RFCOMM) {
            return 1;
        }
        throw new IllegalArgumentException("Unsupported accessory transport " + type);
    }
}

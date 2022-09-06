package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import java.util.EnumMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bc {
    private static final String gM = "com.amazon.identity.auth.device.bc";
    private static final bc gN = new bc();
    private final EnumMap<DeviceAttribute, Object> gO = new EnumMap<>(DeviceAttribute.class);

    private bc() {
    }

    private synchronized Object b(Context context, DeviceAttribute deviceAttribute) {
        Object fetchValue = deviceAttribute.fetchValue(context);
        if (!(fetchValue instanceof String) || !TextUtils.isEmpty((String) fetchValue)) {
            io.i(gM, String.format("Setting device attribute %s to %s", deviceAttribute.toString(), fetchValue));
            this.gO.put((EnumMap<DeviceAttribute, Object>) deviceAttribute, (DeviceAttribute) fetchValue);
            return fetchValue;
        }
        return fetchValue;
    }

    public static bc bm() {
        return gN;
    }

    public synchronized Object a(Context context, DeviceAttribute deviceAttribute) {
        Object obj = this.gO.get(deviceAttribute);
        if (obj != null) {
            return obj;
        }
        return b(context, deviceAttribute);
    }

    public synchronized void clearCache() {
        this.gO.clear();
    }
}

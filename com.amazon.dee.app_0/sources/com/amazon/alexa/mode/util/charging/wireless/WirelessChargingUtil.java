package com.amazon.alexa.mode.util.charging.wireless;

import com.amazon.alexa.mode.model.WirelessChargingEventModel;
import com.google.gson.GsonBuilder;
/* loaded from: classes9.dex */
public final class WirelessChargingUtil {
    private WirelessChargingUtil() {
        throw new IllegalStateException("No instances!");
    }

    public static String getWirelessChargingEventJson(boolean z) {
        return new GsonBuilder().create().toJson(new WirelessChargingEventModel(z));
    }
}

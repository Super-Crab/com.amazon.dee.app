package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class StaticDeviceDetailsDecorator implements DeviceDetails {
    private static final String UNKNOWN = "Unknown";
    private static HashMap<String, String> staticDeviceDetailsMap = new HashMap<>();
    @NonNull
    private DeviceDetails deviceDetails;

    /* loaded from: classes6.dex */
    public enum StaticDeviceDetailKeys {
        DSN,
        DEVICE_ID,
        SOFTWARE_VERSION,
        HARDWARE,
        BUILD_TYPE,
        PLATFORM,
        MODEL
    }

    static {
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.DSN.toString(), "Unknown");
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.DEVICE_ID.toString(), Build.ID);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.SOFTWARE_VERSION.toString(), Build.VERSION.RELEASE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.HARDWARE.toString(), Build.HARDWARE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.BUILD_TYPE.toString(), Build.TYPE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.PLATFORM.toString(), Build.DEVICE);
        staticDeviceDetailsMap.put(StaticDeviceDetailKeys.MODEL.toString(), Build.MODEL);
    }

    public StaticDeviceDetailsDecorator(@NonNull DeviceDetails deviceDetails) {
        if (deviceDetails != null) {
            this.deviceDetails = deviceDetails;
            return;
        }
        throw new IllegalArgumentException("deviceDetails is null");
    }

    public static synchronized void putIntoStaticDeviceDetailsMap(String str, String str2) {
        synchronized (StaticDeviceDetailsDecorator.class) {
            staticDeviceDetailsMap.put(str, str2);
        }
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.deviceDetails.DeviceDetails
    public Map<String, String> asMap() {
        Map<String, String> asMap = this.deviceDetails.asMap();
        synchronized (StaticDeviceDetailsDecorator.class) {
            asMap.putAll(staticDeviceDetailsMap);
        }
        return asMap;
    }
}

package com.amazonaws.mobileconnectors.remoteconfiguration.clientcontextdecorator;

import android.content.Context;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStore;
/* loaded from: classes13.dex */
public class MapR5Wrapper {
    private String deviceType;
    private String dsn;

    public MapR5Wrapper(Context context) {
        if (!isMapR5Present()) {
            return;
        }
        DeviceDataStore deviceDataStore = DeviceDataStore.getInstance(context);
        try {
            this.deviceType = deviceDataStore.getValue("DeviceType");
        } catch (Exception unused) {
            this.deviceType = null;
        }
        try {
            this.dsn = deviceDataStore.getValue(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
        } catch (Exception unused2) {
            this.dsn = null;
        }
    }

    public String getDeviceSerialNumber() {
        return this.dsn;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public boolean isMapR5Present() {
        try {
            Class.forName("com.amazon.identity.auth.device.api.DeviceDataStore");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}

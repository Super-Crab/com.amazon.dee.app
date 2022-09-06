package com.amazon.alexa.mode.util;

import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import java.util.List;
/* loaded from: classes9.dex */
public class CatapultTtsDeviceMonitor {
    private static final String MUFFIN_DEVICE_TYPE = "A303PJF6ISQ7IC";
    private static final String TAG = "CatapultTtsDeviceMonitor";
    private boolean hasConnectedTtsCapableDevice;
    private boolean isSessionRightAfterSetup;

    public boolean isTtsDeviceJustRegistered() {
        return this.isSessionRightAfterSetup && this.hasConnectedTtsCapableDevice;
    }

    public void resetSetupStatus() {
        this.isSessionRightAfterSetup = false;
    }

    public void setSetupStatus() {
        this.isSessionRightAfterSetup = true;
    }

    public void updateConnectedDevice(List<DeviceGroup> list) {
        boolean z = false;
        for (DeviceGroup deviceGroup : list) {
            Device device = deviceGroup.getDevice();
            if (device != null && "A303PJF6ISQ7IC".equals(device.getType())) {
                z = true;
            }
        }
        this.hasConnectedTtsCapableDevice = z;
    }
}

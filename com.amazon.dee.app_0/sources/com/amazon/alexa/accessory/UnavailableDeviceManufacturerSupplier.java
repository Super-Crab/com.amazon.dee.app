package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public class UnavailableDeviceManufacturerSupplier implements DeviceManufacturerSupplier {
    @Override // com.amazon.alexa.accessory.DeviceManufacturerSupplier
    public boolean isFirstParty(String str) {
        return false;
    }

    @Override // com.amazon.alexa.accessory.DeviceManufacturerSupplier
    public boolean isFwUpdateSupported(String str) {
        return true;
    }
}

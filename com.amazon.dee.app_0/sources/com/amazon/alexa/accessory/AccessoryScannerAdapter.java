package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public class AccessoryScannerAdapter implements AccessoryScannerListener {
    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanCancelled() {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanCompleted() {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanFailed(int i) {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanStarted() {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onBleAccessoryFoundNearby(Accessory accessory, AccessoryScanRecord accessoryScanRecord, int i) {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onBleDataBeaconFoundNearby(Accessory accessory, AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i) {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onConnectedAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onConnectedAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
    }
}

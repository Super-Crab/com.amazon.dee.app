package com.amazon.alexa.accessory;
/* loaded from: classes.dex */
public interface AccessoryScannerListener {
    void onAccessoryScanCancelled();

    void onAccessoryScanCompleted();

    void onAccessoryScanFailed(int i);

    void onAccessoryScanStarted();

    void onBleAccessoryFoundNearby(Accessory accessory, AccessoryScanRecord accessoryScanRecord, int i);

    void onBleDataBeaconFoundNearby(Accessory accessory, AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i);

    void onConnectedAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord);

    void onConnectedAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord);
}

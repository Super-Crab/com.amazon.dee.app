package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryDataBeaconRecord;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.AccessoryScannerListener;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
/* loaded from: classes.dex */
class DelegateScannerAdapter implements AccessoryScannerListener {
    private final AccessoryScannerListener listener;

    public DelegateScannerAdapter(AccessoryScannerListener accessoryScannerListener) {
        Preconditions.notNull(accessoryScannerListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = accessoryScannerListener;
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanCancelled() {
        this.listener.onAccessoryScanCancelled();
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanCompleted() {
        this.listener.onAccessoryScanCompleted();
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanFailed(int i) {
        this.listener.onAccessoryScanFailed(i);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanStarted() {
        this.listener.onAccessoryScanStarted();
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onBleAccessoryFoundNearby(Accessory accessory, AccessoryScanRecord accessoryScanRecord, int i) {
        this.listener.onBleAccessoryFoundNearby(accessory, accessoryScanRecord, i);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onBleDataBeaconFoundNearby(Accessory accessory, AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i) {
        this.listener.onBleDataBeaconFoundNearby(accessory, accessoryDataBeaconRecord, i);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onConnectedAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        this.listener.onConnectedAccessoryFound(accessory, accessoryInquiryRecord);
    }

    @Override // com.amazon.alexa.accessory.AccessoryScannerListener
    public void onConnectedAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        this.listener.onConnectedAccessoryLost(accessory, accessoryInquiryRecord);
    }
}

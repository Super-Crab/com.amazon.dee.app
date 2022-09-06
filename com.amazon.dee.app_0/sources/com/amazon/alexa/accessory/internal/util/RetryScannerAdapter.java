package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryDataBeaconRecord;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessoryScanRecord;
import com.amazon.alexa.accessory.AccessoryScannerListener;
/* loaded from: classes.dex */
public final class RetryScannerAdapter extends DelegateScannerAdapter {
    private final int retryCount;
    private int retryNumber;
    private final Runnable scanRequest;

    public RetryScannerAdapter(int i, Runnable runnable, AccessoryScannerListener accessoryScannerListener) {
        super(accessoryScannerListener);
        Preconditions.notNull(runnable, "scanRequest");
        this.retryCount = i;
        this.scanRequest = runnable;
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onAccessoryScanCancelled() {
        super.onAccessoryScanCancelled();
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onAccessoryScanCompleted() {
        super.onAccessoryScanCompleted();
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public void onAccessoryScanFailed(int i) {
        int i2 = this.retryNumber;
        this.retryNumber = i2 + 1;
        if (i2 == this.retryCount) {
            super.onAccessoryScanFailed(i);
            return;
        }
        Logger.d("Failed to scan for accessories %d. Retrying [%d out of %d]...", Integer.valueOf(i), Integer.valueOf(this.retryNumber), Integer.valueOf(this.retryCount));
        this.scanRequest.run();
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onAccessoryScanStarted() {
        super.onAccessoryScanStarted();
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onBleAccessoryFoundNearby(Accessory accessory, AccessoryScanRecord accessoryScanRecord, int i) {
        super.onBleAccessoryFoundNearby(accessory, accessoryScanRecord, i);
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onBleDataBeaconFoundNearby(Accessory accessory, AccessoryDataBeaconRecord accessoryDataBeaconRecord, int i) {
        super.onBleDataBeaconFoundNearby(accessory, accessoryDataBeaconRecord, i);
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onConnectedAccessoryFound(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        super.onConnectedAccessoryFound(accessory, accessoryInquiryRecord);
    }

    @Override // com.amazon.alexa.accessory.internal.util.DelegateScannerAdapter, com.amazon.alexa.accessory.AccessoryScannerListener
    public /* bridge */ /* synthetic */ void onConnectedAccessoryLost(Accessory accessory, AccessoryInquiryRecord accessoryInquiryRecord) {
        super.onConnectedAccessoryLost(accessory, accessoryInquiryRecord);
    }
}

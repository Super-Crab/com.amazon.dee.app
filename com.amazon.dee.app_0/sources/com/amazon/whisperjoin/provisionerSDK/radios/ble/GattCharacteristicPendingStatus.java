package com.amazon.whisperjoin.provisionerSDK.radios.ble;
/* loaded from: classes13.dex */
class GattCharacteristicPendingStatus {
    private final GattCharacteristicPendingUpdate mAddtionalUpdates;
    private final boolean mHandled;

    public GattCharacteristicPendingStatus(boolean z, GattCharacteristicPendingUpdate gattCharacteristicPendingUpdate) {
        this.mHandled = z;
        this.mAddtionalUpdates = gattCharacteristicPendingUpdate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GattCharacteristicPendingUpdate additionalUpdates() {
        return this.mAddtionalUpdates;
    }

    public boolean handled() {
        return this.mHandled;
    }
}

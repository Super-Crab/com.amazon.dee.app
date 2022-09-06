package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ble.BleGattCharacteristic;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public interface GattCharacteristicPendingUpdate {
    GattCharacteristicPendingStatus notify(BleGattCharacteristic bleGattCharacteristic);
}

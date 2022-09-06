package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.whisperbridge.ble.BleGattClient;
/* loaded from: classes13.dex */
interface BLEClients {
    BleGattClient getGattClient();

    GattCharacteristicClient getServiceClient();
}

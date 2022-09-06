package com.amazon.whisperbridge.ble;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class GattClientModule {
    private static final String TAG = "GattClientModule";

    public GattClientModule() {
        WJLog.i(TAG, "Creating Android BLE module");
    }

    @Provides
    @Singleton
    public BluetoothGattFactory provideBluetoothGattFactory() {
        return new AndroidBleGattClientFactory();
    }
}

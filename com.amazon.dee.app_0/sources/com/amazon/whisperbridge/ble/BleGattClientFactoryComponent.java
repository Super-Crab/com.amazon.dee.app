package com.amazon.whisperbridge.ble;

import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {GattClientModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface BleGattClientFactoryComponent {
    void inject(BleGattClientFactory bleGattClientFactory);
}

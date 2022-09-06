package com.amazon.whisperbridge.ble;

import com.amazon.whisperbridge.ble.command.BleCommandExecutorModule;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {BleCommandExecutorModule.class})
@Singleton
/* loaded from: classes13.dex */
public interface BleGattClientComponent {
    void inject(BleGattClient bleGattClient);
}

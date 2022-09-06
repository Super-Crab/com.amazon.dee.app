package com.amazon.whisperbridge.ble.command;

import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes13.dex */
public class BleCommandExecutorModule {
    private static BleCommandExecutor sCommandExecutor;

    public static void setComponent(BleCommandExecutor bleCommandExecutor) {
        if (bleCommandExecutor != null) {
            sCommandExecutor = bleCommandExecutor;
            return;
        }
        throw new IllegalArgumentException("BleCommandExecutor cannot be null");
    }

    @Provides
    @Singleton
    public BleCommandExecutor provideCommandExecutor() {
        if (sCommandExecutor == null) {
            sCommandExecutor = new BleCommandExecutor();
        }
        return sCommandExecutor;
    }
}

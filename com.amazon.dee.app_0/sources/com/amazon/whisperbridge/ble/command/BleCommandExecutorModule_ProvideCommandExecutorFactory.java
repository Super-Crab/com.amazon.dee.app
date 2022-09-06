package com.amazon.whisperbridge.ble.command;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class BleCommandExecutorModule_ProvideCommandExecutorFactory implements Factory<BleCommandExecutor> {
    private final BleCommandExecutorModule module;

    public BleCommandExecutorModule_ProvideCommandExecutorFactory(BleCommandExecutorModule bleCommandExecutorModule) {
        this.module = bleCommandExecutorModule;
    }

    public static BleCommandExecutorModule_ProvideCommandExecutorFactory create(BleCommandExecutorModule bleCommandExecutorModule) {
        return new BleCommandExecutorModule_ProvideCommandExecutorFactory(bleCommandExecutorModule);
    }

    public static BleCommandExecutor provideInstance(BleCommandExecutorModule bleCommandExecutorModule) {
        return proxyProvideCommandExecutor(bleCommandExecutorModule);
    }

    public static BleCommandExecutor proxyProvideCommandExecutor(BleCommandExecutorModule bleCommandExecutorModule) {
        return (BleCommandExecutor) Preconditions.checkNotNull(bleCommandExecutorModule.provideCommandExecutor(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BleCommandExecutor mo10268get() {
        return provideInstance(this.module);
    }
}

package com.amazon.whisperbridge.ble;

import com.amazon.whisperbridge.ble.command.BleCommandExecutor;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class BleGattClient_MembersInjector implements MembersInjector<BleGattClient> {
    private final Provider<BleCommandExecutor> mClientCommandExecutorProvider;

    public BleGattClient_MembersInjector(Provider<BleCommandExecutor> provider) {
        this.mClientCommandExecutorProvider = provider;
    }

    public static MembersInjector<BleGattClient> create(Provider<BleCommandExecutor> provider) {
        return new BleGattClient_MembersInjector(provider);
    }

    public static void injectMClientCommandExecutor(BleGattClient bleGattClient, BleCommandExecutor bleCommandExecutor) {
        bleGattClient.mClientCommandExecutor = bleCommandExecutor;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BleGattClient bleGattClient) {
        injectMClientCommandExecutor(bleGattClient, this.mClientCommandExecutorProvider.mo10268get());
    }
}

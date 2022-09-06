package com.amazon.whisperbridge.ble;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class BleGattClientFactory_MembersInjector implements MembersInjector<BleGattClientFactory> {
    private final Provider<BluetoothGattFactory> mBluetoothGattFactoryProvider;

    public BleGattClientFactory_MembersInjector(Provider<BluetoothGattFactory> provider) {
        this.mBluetoothGattFactoryProvider = provider;
    }

    public static MembersInjector<BleGattClientFactory> create(Provider<BluetoothGattFactory> provider) {
        return new BleGattClientFactory_MembersInjector(provider);
    }

    public static void injectMBluetoothGattFactory(BleGattClientFactory bleGattClientFactory, BluetoothGattFactory bluetoothGattFactory) {
        bleGattClientFactory.mBluetoothGattFactory = bluetoothGattFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BleGattClientFactory bleGattClientFactory) {
        injectMBluetoothGattFactory(bleGattClientFactory, this.mBluetoothGattFactoryProvider.mo10268get());
    }
}

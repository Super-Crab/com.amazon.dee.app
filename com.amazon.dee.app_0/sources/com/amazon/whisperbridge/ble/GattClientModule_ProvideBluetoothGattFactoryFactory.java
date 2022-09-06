package com.amazon.whisperbridge.ble;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class GattClientModule_ProvideBluetoothGattFactoryFactory implements Factory<BluetoothGattFactory> {
    private final GattClientModule module;

    public GattClientModule_ProvideBluetoothGattFactoryFactory(GattClientModule gattClientModule) {
        this.module = gattClientModule;
    }

    public static GattClientModule_ProvideBluetoothGattFactoryFactory create(GattClientModule gattClientModule) {
        return new GattClientModule_ProvideBluetoothGattFactoryFactory(gattClientModule);
    }

    public static BluetoothGattFactory provideInstance(GattClientModule gattClientModule) {
        return proxyProvideBluetoothGattFactory(gattClientModule);
    }

    public static BluetoothGattFactory proxyProvideBluetoothGattFactory(GattClientModule gattClientModule) {
        return (BluetoothGattFactory) Preconditions.checkNotNull(gattClientModule.provideBluetoothGattFactory(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothGattFactory mo10268get() {
        return provideInstance(this.module);
    }
}

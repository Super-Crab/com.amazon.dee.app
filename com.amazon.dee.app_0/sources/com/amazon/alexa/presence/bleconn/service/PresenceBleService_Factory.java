package com.amazon.alexa.presence.bleconn.service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceBleService_Factory implements Factory<PresenceBleService> {
    private final Provider<BleConnServiceComponent> bleServiceComponentProvider;
    private final Provider<BluetoothAdapter> bluetoothAdapterProvider;
    private final Provider<BluetoothManager> bluetoothManagerProvider;
    private final Provider<Context> contextProvider;

    public PresenceBleService_Factory(Provider<Context> provider, Provider<BluetoothManager> provider2, Provider<BluetoothAdapter> provider3, Provider<BleConnServiceComponent> provider4) {
        this.contextProvider = provider;
        this.bluetoothManagerProvider = provider2;
        this.bluetoothAdapterProvider = provider3;
        this.bleServiceComponentProvider = provider4;
    }

    public static PresenceBleService_Factory create(Provider<Context> provider, Provider<BluetoothManager> provider2, Provider<BluetoothAdapter> provider3, Provider<BleConnServiceComponent> provider4) {
        return new PresenceBleService_Factory(provider, provider2, provider3, provider4);
    }

    public static PresenceBleService newPresenceBleService(Context context, BluetoothManager bluetoothManager, BluetoothAdapter bluetoothAdapter, Object obj) {
        return new PresenceBleService(context, bluetoothManager, bluetoothAdapter, (BleConnServiceComponent) obj);
    }

    public static PresenceBleService provideInstance(Provider<Context> provider, Provider<BluetoothManager> provider2, Provider<BluetoothAdapter> provider3, Provider<BleConnServiceComponent> provider4) {
        return new PresenceBleService(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public PresenceBleService mo10268get() {
        return provideInstance(this.contextProvider, this.bluetoothManagerProvider, this.bluetoothAdapterProvider, this.bleServiceComponentProvider);
    }
}

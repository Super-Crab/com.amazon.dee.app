package com.amazon.alexa.presence.utils;

import dagger.internal.Factory;
/* loaded from: classes9.dex */
public final class BluetoothHelper_Factory implements Factory<BluetoothHelper> {
    private static final BluetoothHelper_Factory INSTANCE = new BluetoothHelper_Factory();

    public static BluetoothHelper_Factory create() {
        return INSTANCE;
    }

    public static BluetoothHelper newBluetoothHelper() {
        return new BluetoothHelper();
    }

    public static BluetoothHelper provideInstance() {
        return new BluetoothHelper();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothHelper mo10268get() {
        return provideInstance();
    }
}

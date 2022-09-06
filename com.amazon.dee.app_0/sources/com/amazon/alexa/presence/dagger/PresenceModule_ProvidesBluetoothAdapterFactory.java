package com.amazon.alexa.presence.dagger;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import dagger.internal.Factory;
import javax.annotation.Nullable;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvidesBluetoothAdapterFactory implements Factory<BluetoothAdapter> {
    private final Provider<Context> contextProvider;
    private final PresenceModule module;

    public PresenceModule_ProvidesBluetoothAdapterFactory(PresenceModule presenceModule, Provider<Context> provider) {
        this.module = presenceModule;
        this.contextProvider = provider;
    }

    public static PresenceModule_ProvidesBluetoothAdapterFactory create(PresenceModule presenceModule, Provider<Context> provider) {
        return new PresenceModule_ProvidesBluetoothAdapterFactory(presenceModule, provider);
    }

    @Nullable
    public static BluetoothAdapter provideInstance(PresenceModule presenceModule, Provider<Context> provider) {
        return proxyProvidesBluetoothAdapter(presenceModule, provider.mo10268get());
    }

    @Nullable
    public static BluetoothAdapter proxyProvidesBluetoothAdapter(PresenceModule presenceModule, Context context) {
        return presenceModule.providesBluetoothAdapter(context);
    }

    @Override // javax.inject.Provider
    @Nullable
    /* renamed from: get */
    public BluetoothAdapter mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}

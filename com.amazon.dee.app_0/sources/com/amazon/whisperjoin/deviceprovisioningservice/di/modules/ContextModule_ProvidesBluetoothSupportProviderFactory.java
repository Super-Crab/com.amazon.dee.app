package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import android.content.Context;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BluetoothSupportProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class ContextModule_ProvidesBluetoothSupportProviderFactory implements Factory<BluetoothSupportProvider> {
    private final Provider<Context> contextProvider;
    private final ContextModule module;

    public ContextModule_ProvidesBluetoothSupportProviderFactory(ContextModule contextModule, Provider<Context> provider) {
        this.module = contextModule;
        this.contextProvider = provider;
    }

    public static ContextModule_ProvidesBluetoothSupportProviderFactory create(ContextModule contextModule, Provider<Context> provider) {
        return new ContextModule_ProvidesBluetoothSupportProviderFactory(contextModule, provider);
    }

    public static BluetoothSupportProvider provideInstance(ContextModule contextModule, Provider<Context> provider) {
        return proxyProvidesBluetoothSupportProvider(contextModule, provider.mo10268get());
    }

    public static BluetoothSupportProvider proxyProvidesBluetoothSupportProvider(ContextModule contextModule, Context context) {
        return (BluetoothSupportProvider) Preconditions.checkNotNull(contextModule.providesBluetoothSupportProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BluetoothSupportProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}

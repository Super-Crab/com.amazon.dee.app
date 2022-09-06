package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes11.dex */
public final class FalcoDevicesModule_AmpdInformationProviderFactory implements Factory<AMPDInformationProvider> {
    private final Provider<Context> contextProvider;
    private final FalcoDevicesModule module;

    public FalcoDevicesModule_AmpdInformationProviderFactory(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        this.module = falcoDevicesModule;
        this.contextProvider = provider;
    }

    public static FalcoDevicesModule_AmpdInformationProviderFactory create(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        return new FalcoDevicesModule_AmpdInformationProviderFactory(falcoDevicesModule, provider);
    }

    public static AMPDInformationProvider provideInstance(FalcoDevicesModule falcoDevicesModule, Provider<Context> provider) {
        return proxyAmpdInformationProvider(falcoDevicesModule, provider.mo10268get());
    }

    public static AMPDInformationProvider proxyAmpdInformationProvider(FalcoDevicesModule falcoDevicesModule, Context context) {
        return (AMPDInformationProvider) Preconditions.checkNotNull(falcoDevicesModule.ampdInformationProvider(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AMPDInformationProvider mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}

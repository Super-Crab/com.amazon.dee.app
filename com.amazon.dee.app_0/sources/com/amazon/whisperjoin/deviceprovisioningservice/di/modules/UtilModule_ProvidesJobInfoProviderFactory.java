package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.util.JobInfoProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class UtilModule_ProvidesJobInfoProviderFactory implements Factory<JobInfoProvider> {
    private final UtilModule module;

    public UtilModule_ProvidesJobInfoProviderFactory(UtilModule utilModule) {
        this.module = utilModule;
    }

    public static UtilModule_ProvidesJobInfoProviderFactory create(UtilModule utilModule) {
        return new UtilModule_ProvidesJobInfoProviderFactory(utilModule);
    }

    public static JobInfoProvider provideInstance(UtilModule utilModule) {
        return proxyProvidesJobInfoProvider(utilModule);
    }

    public static JobInfoProvider proxyProvidesJobInfoProvider(UtilModule utilModule) {
        return (JobInfoProvider) Preconditions.checkNotNull(utilModule.providesJobInfoProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public JobInfoProvider mo10268get() {
        return provideInstance(this.module);
    }
}

package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.crashreporting.api.CrashMetadata;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes6.dex */
public final class ApplicationModule_ProvideCrashMetadataFactory implements Factory<CrashMetadata> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideCrashMetadataFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideCrashMetadataFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideCrashMetadataFactory(applicationModule);
    }

    public static CrashMetadata provideInstance(ApplicationModule applicationModule) {
        return proxyProvideCrashMetadata(applicationModule);
    }

    public static CrashMetadata proxyProvideCrashMetadata(ApplicationModule applicationModule) {
        return (CrashMetadata) Preconditions.checkNotNull(applicationModule.provideCrashMetadata(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CrashMetadata mo10268get() {
        return provideInstance(this.module);
    }
}

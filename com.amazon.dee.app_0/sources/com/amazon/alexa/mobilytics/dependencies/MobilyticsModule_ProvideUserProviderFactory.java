package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideUserProviderFactory implements Factory<MobilyticsUserProvider> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideUserProviderFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideUserProviderFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideUserProviderFactory(mobilyticsModule);
    }

    public static MobilyticsUserProvider provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideUserProvider(mobilyticsModule);
    }

    public static MobilyticsUserProvider proxyProvideUserProvider(MobilyticsModule mobilyticsModule) {
        return (MobilyticsUserProvider) Preconditions.checkNotNull(mobilyticsModule.provideUserProvider(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsUserProvider mo10268get() {
        return provideInstance(this.module);
    }
}

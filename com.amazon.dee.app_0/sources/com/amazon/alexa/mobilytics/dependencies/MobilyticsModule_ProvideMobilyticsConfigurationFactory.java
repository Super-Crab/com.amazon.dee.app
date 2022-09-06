package com.amazon.alexa.mobilytics.dependencies;

import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes9.dex */
public final class MobilyticsModule_ProvideMobilyticsConfigurationFactory implements Factory<MobilyticsConfiguration> {
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMobilyticsConfigurationFactory(MobilyticsModule mobilyticsModule) {
        this.module = mobilyticsModule;
    }

    public static MobilyticsModule_ProvideMobilyticsConfigurationFactory create(MobilyticsModule mobilyticsModule) {
        return new MobilyticsModule_ProvideMobilyticsConfigurationFactory(mobilyticsModule);
    }

    public static MobilyticsConfiguration provideInstance(MobilyticsModule mobilyticsModule) {
        return proxyProvideMobilyticsConfiguration(mobilyticsModule);
    }

    public static MobilyticsConfiguration proxyProvideMobilyticsConfiguration(MobilyticsModule mobilyticsModule) {
        return (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsModule.provideMobilyticsConfiguration(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsConfiguration mo10268get() {
        return provideInstance(this.module);
    }
}

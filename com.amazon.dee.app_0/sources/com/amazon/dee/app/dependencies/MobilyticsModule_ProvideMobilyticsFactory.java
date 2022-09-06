package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MobilyticsModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private final Provider<MobilyticsConfiguration> configProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMobilyticsFactory(MobilyticsModule mobilyticsModule, Provider<MobilyticsConfiguration> provider) {
        this.module = mobilyticsModule;
        this.configProvider = provider;
    }

    public static MobilyticsModule_ProvideMobilyticsFactory create(MobilyticsModule mobilyticsModule, Provider<MobilyticsConfiguration> provider) {
        return new MobilyticsModule_ProvideMobilyticsFactory(mobilyticsModule, provider);
    }

    public static Mobilytics provideInstance(MobilyticsModule mobilyticsModule, Provider<MobilyticsConfiguration> provider) {
        return proxyProvideMobilytics(mobilyticsModule, provider.mo10268get());
    }

    public static Mobilytics proxyProvideMobilytics(MobilyticsModule mobilyticsModule, MobilyticsConfiguration mobilyticsConfiguration) {
        return (Mobilytics) Preconditions.checkNotNull(mobilyticsModule.provideMobilytics(mobilyticsConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module, this.configProvider);
    }
}

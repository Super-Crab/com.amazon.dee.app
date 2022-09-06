package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsClientModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private final Provider<MobilyticsConfiguration> configProvider;
    private final MobilyticsClientModule module;

    public MobilyticsClientModule_ProvideMobilyticsFactory(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsConfiguration> provider) {
        this.module = mobilyticsClientModule;
        this.configProvider = provider;
    }

    public static MobilyticsClientModule_ProvideMobilyticsFactory create(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsConfiguration> provider) {
        return new MobilyticsClientModule_ProvideMobilyticsFactory(mobilyticsClientModule, provider);
    }

    public static Mobilytics provideInstance(MobilyticsClientModule mobilyticsClientModule, Provider<MobilyticsConfiguration> provider) {
        return proxyProvideMobilytics(mobilyticsClientModule, provider.mo10268get());
    }

    public static Mobilytics proxyProvideMobilytics(MobilyticsClientModule mobilyticsClientModule, MobilyticsConfiguration mobilyticsConfiguration) {
        return (Mobilytics) Preconditions.checkNotNull(mobilyticsClientModule.provideMobilytics(mobilyticsConfiguration), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance(this.module, this.configProvider);
    }
}

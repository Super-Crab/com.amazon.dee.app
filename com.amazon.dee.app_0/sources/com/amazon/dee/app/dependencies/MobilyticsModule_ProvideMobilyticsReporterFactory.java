package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsReporter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MobilyticsModule_ProvideMobilyticsReporterFactory implements Factory<MobilyticsReporter> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MobilyticsModule module;

    public MobilyticsModule_ProvideMobilyticsReporterFactory(MobilyticsModule mobilyticsModule, Provider<Mobilytics> provider) {
        this.module = mobilyticsModule;
        this.mobilyticsProvider = provider;
    }

    public static MobilyticsModule_ProvideMobilyticsReporterFactory create(MobilyticsModule mobilyticsModule, Provider<Mobilytics> provider) {
        return new MobilyticsModule_ProvideMobilyticsReporterFactory(mobilyticsModule, provider);
    }

    public static MobilyticsReporter provideInstance(MobilyticsModule mobilyticsModule, Provider<Mobilytics> provider) {
        return proxyProvideMobilyticsReporter(mobilyticsModule, provider.mo10268get());
    }

    public static MobilyticsReporter proxyProvideMobilyticsReporter(MobilyticsModule mobilyticsModule, Mobilytics mobilytics) {
        return (MobilyticsReporter) Preconditions.checkNotNull(mobilyticsModule.provideMobilyticsReporter(mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsReporter mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}

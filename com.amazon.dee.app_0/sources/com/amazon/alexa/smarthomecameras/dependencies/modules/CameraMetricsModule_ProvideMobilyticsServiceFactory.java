package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class CameraMetricsModule_ProvideMobilyticsServiceFactory implements Factory<CamerasMobilyticsService> {
    private final Provider<Mobilytics> mobilyticsProvider;

    public CameraMetricsModule_ProvideMobilyticsServiceFactory(Provider<Mobilytics> provider) {
        this.mobilyticsProvider = provider;
    }

    public static CameraMetricsModule_ProvideMobilyticsServiceFactory create(Provider<Mobilytics> provider) {
        return new CameraMetricsModule_ProvideMobilyticsServiceFactory(provider);
    }

    public static CamerasMobilyticsService provideInstance(Provider<Mobilytics> provider) {
        return proxyProvideMobilyticsService(provider.mo10268get());
    }

    public static CamerasMobilyticsService proxyProvideMobilyticsService(Mobilytics mobilytics) {
        return (CamerasMobilyticsService) Preconditions.checkNotNull(CameraMetricsModule.provideMobilyticsService(mobilytics), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CamerasMobilyticsService mo10268get() {
        return provideInstance(this.mobilyticsProvider);
    }
}

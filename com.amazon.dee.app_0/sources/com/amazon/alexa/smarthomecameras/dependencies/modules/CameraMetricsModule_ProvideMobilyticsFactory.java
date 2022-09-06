package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.mobilytics.Mobilytics;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class CameraMetricsModule_ProvideMobilyticsFactory implements Factory<Mobilytics> {
    private static final CameraMetricsModule_ProvideMobilyticsFactory INSTANCE = new CameraMetricsModule_ProvideMobilyticsFactory();

    public static CameraMetricsModule_ProvideMobilyticsFactory create() {
        return INSTANCE;
    }

    public static Mobilytics provideInstance() {
        return proxyProvideMobilytics();
    }

    public static Mobilytics proxyProvideMobilytics() {
        return (Mobilytics) Preconditions.checkNotNull(CameraMetricsModule.provideMobilytics(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Mobilytics mo10268get() {
        return provideInstance();
    }
}

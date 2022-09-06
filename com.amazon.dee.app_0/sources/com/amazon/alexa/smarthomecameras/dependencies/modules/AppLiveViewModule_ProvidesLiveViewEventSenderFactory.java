package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesLiveViewEventSenderFactory implements Factory<LiveViewEventSender> {
    private final Provider<AlexaMobileFrameworkApis> amfApisProvider;
    private final Provider<CamerasMobilyticsService> mobilyticsServiceProvider;

    public AppLiveViewModule_ProvidesLiveViewEventSenderFactory(Provider<AlexaMobileFrameworkApis> provider, Provider<CamerasMobilyticsService> provider2) {
        this.amfApisProvider = provider;
        this.mobilyticsServiceProvider = provider2;
    }

    public static AppLiveViewModule_ProvidesLiveViewEventSenderFactory create(Provider<AlexaMobileFrameworkApis> provider, Provider<CamerasMobilyticsService> provider2) {
        return new AppLiveViewModule_ProvidesLiveViewEventSenderFactory(provider, provider2);
    }

    public static LiveViewEventSender provideInstance(Provider<AlexaMobileFrameworkApis> provider, Provider<CamerasMobilyticsService> provider2) {
        return proxyProvidesLiveViewEventSender(provider.mo10268get(), provider2.mo10268get());
    }

    public static LiveViewEventSender proxyProvidesLiveViewEventSender(AlexaMobileFrameworkApis alexaMobileFrameworkApis, CamerasMobilyticsService camerasMobilyticsService) {
        return (LiveViewEventSender) Preconditions.checkNotNull(AppLiveViewModule.providesLiveViewEventSender(alexaMobileFrameworkApis, camerasMobilyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LiveViewEventSender mo10268get() {
        return provideInstance(this.amfApisProvider, this.mobilyticsServiceProvider);
    }
}

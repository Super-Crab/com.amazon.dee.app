package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class AppLiveViewModule_ProvidesCameraSessionManagerFactory implements Factory<CameraSessionManager> {
    private final Provider<RtcscAppInfo> appInfoProvider;
    private final Provider<Context> contextProvider;
    private final Provider<LiveViewEventSender> eventSenderProvider;
    private final Provider<ScheduledExecutorService> executorServiceProvider;
    private final Provider<CamerasMobilyticsService> mobilyticsServiceProvider;
    private final Provider<CamerasAppClient> rtcscAppClientProvider;

    public AppLiveViewModule_ProvidesCameraSessionManagerFactory(Provider<Context> provider, Provider<RtcscAppInfo> provider2, Provider<CamerasAppClient> provider3, Provider<LiveViewEventSender> provider4, Provider<ScheduledExecutorService> provider5, Provider<CamerasMobilyticsService> provider6) {
        this.contextProvider = provider;
        this.appInfoProvider = provider2;
        this.rtcscAppClientProvider = provider3;
        this.eventSenderProvider = provider4;
        this.executorServiceProvider = provider5;
        this.mobilyticsServiceProvider = provider6;
    }

    public static AppLiveViewModule_ProvidesCameraSessionManagerFactory create(Provider<Context> provider, Provider<RtcscAppInfo> provider2, Provider<CamerasAppClient> provider3, Provider<LiveViewEventSender> provider4, Provider<ScheduledExecutorService> provider5, Provider<CamerasMobilyticsService> provider6) {
        return new AppLiveViewModule_ProvidesCameraSessionManagerFactory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static CameraSessionManager provideInstance(Provider<Context> provider, Provider<RtcscAppInfo> provider2, Provider<CamerasAppClient> provider3, Provider<LiveViewEventSender> provider4, Provider<ScheduledExecutorService> provider5, Provider<CamerasMobilyticsService> provider6) {
        return proxyProvidesCameraSessionManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get());
    }

    public static CameraSessionManager proxyProvidesCameraSessionManager(Context context, RtcscAppInfo rtcscAppInfo, CamerasAppClient camerasAppClient, LiveViewEventSender liveViewEventSender, ScheduledExecutorService scheduledExecutorService, CamerasMobilyticsService camerasMobilyticsService) {
        return (CameraSessionManager) Preconditions.checkNotNull(AppLiveViewModule.providesCameraSessionManager(context, rtcscAppInfo, camerasAppClient, liveViewEventSender, scheduledExecutorService, camerasMobilyticsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CameraSessionManager mo10268get() {
        return provideInstance(this.contextProvider, this.appInfoProvider, this.rtcscAppClientProvider, this.eventSenderProvider, this.executorServiceProvider, this.mobilyticsServiceProvider);
    }
}

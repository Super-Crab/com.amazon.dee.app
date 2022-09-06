package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class LiveViewActivity_MembersInjector implements MembersInjector<LiveViewActivity> {
    private final Provider<LandscapeCameraView> cameraViewProvider;
    private final Provider<CamerasMobilyticsService> mobilyticsServiceProvider;
    private final Provider<ActivityOrientationListener> orientationEventListenerProvider;

    public LiveViewActivity_MembersInjector(Provider<LandscapeCameraView> provider, Provider<ActivityOrientationListener> provider2, Provider<CamerasMobilyticsService> provider3) {
        this.cameraViewProvider = provider;
        this.orientationEventListenerProvider = provider2;
        this.mobilyticsServiceProvider = provider3;
    }

    public static MembersInjector<LiveViewActivity> create(Provider<LandscapeCameraView> provider, Provider<ActivityOrientationListener> provider2, Provider<CamerasMobilyticsService> provider3) {
        return new LiveViewActivity_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCameraView(LiveViewActivity liveViewActivity, LandscapeCameraView landscapeCameraView) {
        liveViewActivity.cameraView = landscapeCameraView;
    }

    public static void injectMobilyticsService(LiveViewActivity liveViewActivity, CamerasMobilyticsService camerasMobilyticsService) {
        liveViewActivity.mobilyticsService = camerasMobilyticsService;
    }

    public static void injectOrientationEventListener(LiveViewActivity liveViewActivity, ActivityOrientationListener activityOrientationListener) {
        liveViewActivity.orientationEventListener = activityOrientationListener;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(LiveViewActivity liveViewActivity) {
        injectCameraView(liveViewActivity, this.cameraViewProvider.mo10268get());
        injectOrientationEventListener(liveViewActivity, this.orientationEventListenerProvider.mo10268get());
        injectMobilyticsService(liveViewActivity, this.mobilyticsServiceProvider.mo10268get());
    }
}

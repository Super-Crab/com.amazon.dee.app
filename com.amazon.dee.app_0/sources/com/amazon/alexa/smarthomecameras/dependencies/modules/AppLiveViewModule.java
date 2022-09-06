package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.capabilityagent.AmfLiveViewEventSender;
import com.amazon.alexa.smarthomecameras.capabilityagent.LiveViewEventSender;
import com.amazon.alexa.smarthomecameras.model.CameraViewModel;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.rtcsc.CamerasAppClient;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.session.RtcscCameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.amazon.rtcsc.interfaces.RtcscAppInfo;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Named;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public abstract class AppLiveViewModule {
    private static final String CAMERA_SESSION_MANAGER_EXECUTOR = "camera_session_manager_executor";

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static AppLifecycleOwner providesAppLifecycleOwner() {
        return new AppLifecycleOwner();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static CameraSessionManager providesCameraSessionManager(Context context, RtcscAppInfo rtcscAppInfo, CamerasAppClient camerasAppClient, LiveViewEventSender liveViewEventSender, @Named("camera_session_manager_executor") ScheduledExecutorService scheduledExecutorService, CamerasMobilyticsService camerasMobilyticsService) {
        return new RtcscCameraSessionManager(context, rtcscAppInfo, camerasAppClient, liveViewEventSender, scheduledExecutorService, camerasMobilyticsService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static CamerasAppClient providesCamerasAppClient(Context context) {
        return new CamerasAppClient(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Named(CAMERA_SESSION_MANAGER_EXECUTOR)
    public static ScheduledExecutorService providesExecutorService() {
        return ExecutorFactory.newSingleThreadScheduledExecutor(CAMERA_SESSION_MANAGER_EXECUTOR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static LiveViewEventSender providesLiveViewEventSender(AlexaMobileFrameworkApis alexaMobileFrameworkApis, CamerasMobilyticsService camerasMobilyticsService) {
        return new AmfLiveViewEventSender(alexaMobileFrameworkApis, camerasMobilyticsService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static CameraViewContract.Model providesModel() {
        return new CameraViewModel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static PtzListenerFactory providesPtzListenerFactory() {
        return new PtzListenerFactory();
    }
}

package com.amazon.alexa.smarthomecameras.dependencies.components;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AlexaServicesModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AppLiveViewModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.AudioManagerModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraMetricsModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.ContextModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CoralServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.EventBusModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.FeatureServiceV2Module;
import com.amazon.alexa.smarthomecameras.dependencies.modules.NetworkServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingRegistryModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RoutingServiceModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.RtcscModule;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.dee.app.http.CoralService;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {AlexaServicesModule.class, AudioManagerModule.class, AppLiveViewModule.class, ContextModule.class, RoutingServiceModule.class, RtcscModule.class, CameraMetricsModule.class, NetworkServiceModule.class, FeatureServiceV2Module.class, RoutingRegistryModule.class, EventBusModule.class, CoralServiceModule.class})
@Singleton
/* loaded from: classes10.dex */
public interface CameraComponent {
    AppLifecycleOwner appLifeycleOwner();

    AudioManager audioManager();

    CameraViewContract.Model cameraModel();

    CameraSessionManager cameraSessionManager();

    CamerasMobilyticsService camerasMobilyticsService();

    Context context();

    CoralService coralService();

    EventBus eventBus();

    FeatureServiceV2 featureServiceV2();

    NetworkService networkService();

    PtzListenerFactory ptzListenerFactory();

    RoutingRegistry routingRegistry();

    RoutingService routingService();
}

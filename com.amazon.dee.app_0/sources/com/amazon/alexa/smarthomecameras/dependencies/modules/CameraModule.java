package com.amazon.alexa.smarthomecameras.dependencies.modules;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.presenter.CameraViewPresenter;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.dee.app.http.CoralService;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes10.dex */
public class CameraModule {
    private CameraLabel cameraLabel;
    private DevicePayload devicePayload;
    private EntityId entityId;

    public CameraModule(EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload) {
        Preconditions.checkNotNull(entityId, "EntityId cannot be null");
        Preconditions.checkNotNull(entityId, "CameraLabel cannot be null");
        this.entityId = entityId;
        this.cameraLabel = cameraLabel;
        this.devicePayload = devicePayload;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CameraLabel providesCameraLabel() {
        return this.cameraLabel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public DevicePayload providesDevicePayload() {
        return this.devicePayload;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public EntityId providesEntityId() {
        return this.entityId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public CameraViewContract.Presenter providesPresenter(EntityId entityId, CameraViewContract.Model model, CameraSessionManager cameraSessionManager, AudioManager audioManager, AppLifecycleOwner appLifecycleOwner, CamerasMobilyticsService camerasMobilyticsService, RoutingService routingService, FeatureServiceV2 featureServiceV2, DevicePayload devicePayload, CoralService coralService) {
        return new CameraViewPresenter(entityId, model, cameraSessionManager, audioManager, appLifecycleOwner, camerasMobilyticsService, routingService, featureServiceV2, devicePayload, coralService);
    }
}

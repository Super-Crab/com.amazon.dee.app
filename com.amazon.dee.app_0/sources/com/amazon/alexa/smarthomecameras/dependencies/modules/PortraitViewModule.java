package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.view.PortraitCameraView;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes10.dex */
public abstract class PortraitViewModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static PortraitCameraView providesPortraitCameraView(Context context, EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, NetworkService networkService, RoutingRegistry routingRegistry, FeatureServiceV2 featureServiceV2) {
        Preconditions.checkNotNull(context, "Context cannot be null");
        Preconditions.checkNotNull(entityId, "EntityId cannot be null");
        Preconditions.checkNotNull(cameraLabel, "CameraLabel cannot be null");
        Preconditions.checkNotNull(devicePayload, "DevicePayload cannot be null");
        Preconditions.checkNotNull(presenter, "Presenter cannot be null");
        Preconditions.checkNotNull(ptzListenerFactory, "PtzListenerFactory cannot be null");
        Preconditions.checkNotNull(networkService, "NetworkService cannot be null");
        Preconditions.checkNotNull(featureServiceV2, "FeatureServiceV2 cannot be null");
        return new PortraitCameraView(context, entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, networkService, routingRegistry, featureServiceV2);
    }
}

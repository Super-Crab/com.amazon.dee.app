package com.amazon.alexa.smarthomecameras.dependencies.modules;

import android.app.Activity;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.model.CameraLabel;
import com.amazon.alexa.smarthomecameras.model.DevicePayload;
import com.amazon.alexa.smarthomecameras.model.EntityId;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.view.ActivityOrientationListener;
import com.amazon.alexa.smarthomecameras.view.LandscapeCameraView;
import com.google.common.base.Preconditions;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes10.dex */
public class LandscapeViewModule {
    private Activity activity;

    public LandscapeViewModule(Activity activity) {
        Preconditions.checkNotNull(activity, "Activity cannot be null");
        this.activity = activity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public LandscapeCameraView providesLandscapeCameraView(EntityId entityId, CameraLabel cameraLabel, DevicePayload devicePayload, CameraViewContract.Presenter presenter, PtzListenerFactory ptzListenerFactory, CamerasMobilyticsService camerasMobilyticsService, NetworkService networkService, FeatureServiceV2 featureServiceV2) {
        Preconditions.checkNotNull(entityId, "EntityId cannot be null");
        Preconditions.checkNotNull(cameraLabel, "CameraLabel cannot be null");
        Preconditions.checkNotNull(presenter, "Presenter cannot be null");
        Preconditions.checkNotNull(ptzListenerFactory, "PtzListenerFactory cannot be null");
        Preconditions.checkNotNull(camerasMobilyticsService, "CamerasMobilyticsService cannot be null");
        Preconditions.checkNotNull(networkService, "NetworkService cannot be null");
        Preconditions.checkNotNull(featureServiceV2, "FeatureServiceV2 cannot be null");
        return new LandscapeCameraView(this.activity, entityId, cameraLabel, devicePayload, presenter, ptzListenerFactory, camerasMobilyticsService, networkService, featureServiceV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public ActivityOrientationListener providesOrientationListener() {
        return new ActivityOrientationListener(this.activity);
    }
}

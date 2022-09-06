package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesCameraLabelFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesDevicePayloadFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesEntityIdFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesPresenterFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.LandscapeViewModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.LandscapeViewModule_ProvidesLandscapeCameraViewFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.LandscapeViewModule_ProvidesOrientationListenerFactory;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.alexa.smarthomecameras.view.LiveViewActivity;
import com.dee.app.http.CoralService;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class DaggerLiveViewActivity_Injector implements LiveViewActivity.Injector {
    private CameraComponent cameraComponent;
    private CameraModule cameraModule;
    private LandscapeViewModule landscapeViewModule;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private CameraComponent cameraComponent;
        private CameraModule cameraModule;
        private LandscapeViewModule landscapeViewModule;

        public LiveViewActivity.Injector build() {
            Preconditions.checkBuilderRequirement(this.landscapeViewModule, LandscapeViewModule.class);
            Preconditions.checkBuilderRequirement(this.cameraModule, CameraModule.class);
            Preconditions.checkBuilderRequirement(this.cameraComponent, CameraComponent.class);
            return new DaggerLiveViewActivity_Injector(this);
        }

        public Builder cameraComponent(CameraComponent cameraComponent) {
            this.cameraComponent = (CameraComponent) Preconditions.checkNotNull(cameraComponent);
            return this;
        }

        public Builder cameraModule(CameraModule cameraModule) {
            this.cameraModule = (CameraModule) Preconditions.checkNotNull(cameraModule);
            return this;
        }

        public Builder landscapeViewModule(LandscapeViewModule landscapeViewModule) {
            this.landscapeViewModule = (LandscapeViewModule) Preconditions.checkNotNull(landscapeViewModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private LandscapeCameraView getLandscapeCameraView() {
        return LandscapeViewModule_ProvidesLandscapeCameraViewFactory.proxyProvidesLandscapeCameraView(this.landscapeViewModule, CameraModule_ProvidesEntityIdFactory.proxyProvidesEntityId(this.cameraModule), CameraModule_ProvidesCameraLabelFactory.proxyProvidesCameraLabel(this.cameraModule), CameraModule_ProvidesDevicePayloadFactory.proxyProvidesDevicePayload(this.cameraModule), getPresenter(), (PtzListenerFactory) Preconditions.checkNotNull(this.cameraComponent.ptzListenerFactory(), "Cannot return null from a non-@Nullable component method"), (CamerasMobilyticsService) Preconditions.checkNotNull(this.cameraComponent.camerasMobilyticsService(), "Cannot return null from a non-@Nullable component method"), (NetworkService) Preconditions.checkNotNull(this.cameraComponent.networkService(), "Cannot return null from a non-@Nullable component method"), (FeatureServiceV2) Preconditions.checkNotNull(this.cameraComponent.featureServiceV2(), "Cannot return null from a non-@Nullable component method"));
    }

    private CameraViewContract.Presenter getPresenter() {
        CameraModule cameraModule = this.cameraModule;
        return CameraModule_ProvidesPresenterFactory.proxyProvidesPresenter(cameraModule, CameraModule_ProvidesEntityIdFactory.proxyProvidesEntityId(cameraModule), (CameraViewContract.Model) Preconditions.checkNotNull(this.cameraComponent.cameraModel(), "Cannot return null from a non-@Nullable component method"), (CameraSessionManager) Preconditions.checkNotNull(this.cameraComponent.cameraSessionManager(), "Cannot return null from a non-@Nullable component method"), (AudioManager) Preconditions.checkNotNull(this.cameraComponent.audioManager(), "Cannot return null from a non-@Nullable component method"), (AppLifecycleOwner) Preconditions.checkNotNull(this.cameraComponent.appLifeycleOwner(), "Cannot return null from a non-@Nullable component method"), (CamerasMobilyticsService) Preconditions.checkNotNull(this.cameraComponent.camerasMobilyticsService(), "Cannot return null from a non-@Nullable component method"), (RoutingService) Preconditions.checkNotNull(this.cameraComponent.routingService(), "Cannot return null from a non-@Nullable component method"), (FeatureServiceV2) Preconditions.checkNotNull(this.cameraComponent.featureServiceV2(), "Cannot return null from a non-@Nullable component method"), CameraModule_ProvidesDevicePayloadFactory.proxyProvidesDevicePayload(this.cameraModule), (CoralService) Preconditions.checkNotNull(this.cameraComponent.coralService(), "Cannot return null from a non-@Nullable component method"));
    }

    @CanIgnoreReturnValue
    private LiveViewActivity injectLiveViewActivity(LiveViewActivity liveViewActivity) {
        LiveViewActivity_MembersInjector.injectCameraView(liveViewActivity, getLandscapeCameraView());
        LiveViewActivity_MembersInjector.injectOrientationEventListener(liveViewActivity, LandscapeViewModule_ProvidesOrientationListenerFactory.proxyProvidesOrientationListener(this.landscapeViewModule));
        LiveViewActivity_MembersInjector.injectMobilyticsService(liveViewActivity, (CamerasMobilyticsService) Preconditions.checkNotNull(this.cameraComponent.camerasMobilyticsService(), "Cannot return null from a non-@Nullable component method"));
        return liveViewActivity;
    }

    @Override // com.amazon.alexa.smarthomecameras.view.LiveViewActivity.Injector
    public void inject(LiveViewActivity liveViewActivity) {
        injectLiveViewActivity(liveViewActivity);
    }

    private DaggerLiveViewActivity_Injector(Builder builder) {
        this.cameraModule = builder.cameraModule;
        this.cameraComponent = builder.cameraComponent;
        this.landscapeViewModule = builder.landscapeViewModule;
    }
}

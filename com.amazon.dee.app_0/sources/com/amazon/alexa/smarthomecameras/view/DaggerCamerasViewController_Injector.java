package com.amazon.alexa.smarthomecameras.view;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.routing.api.RoutingRegistry;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.dependencies.components.CameraComponent;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesCameraLabelFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesDevicePayloadFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesEntityIdFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.CameraModule_ProvidesPresenterFactory;
import com.amazon.alexa.smarthomecameras.dependencies.modules.PortraitViewModule_ProvidesPortraitCameraViewFactory;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.service.CamerasMobilyticsService;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import com.amazon.alexa.smarthomecameras.view.CamerasViewController;
import com.dee.app.http.CoralService;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class DaggerCamerasViewController_Injector implements CamerasViewController.Injector {
    private CameraComponent cameraComponent;
    private CameraModule cameraModule;

    /* loaded from: classes10.dex */
    public static final class Builder {
        private CameraComponent cameraComponent;
        private CameraModule cameraModule;

        public CamerasViewController.Injector build() {
            Preconditions.checkBuilderRequirement(this.cameraModule, CameraModule.class);
            Preconditions.checkBuilderRequirement(this.cameraComponent, CameraComponent.class);
            return new DaggerCamerasViewController_Injector(this);
        }

        public Builder cameraComponent(CameraComponent cameraComponent) {
            this.cameraComponent = (CameraComponent) Preconditions.checkNotNull(cameraComponent);
            return this;
        }

        public Builder cameraModule(CameraModule cameraModule) {
            this.cameraModule = (CameraModule) Preconditions.checkNotNull(cameraModule);
            return this;
        }

        private Builder() {
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private PortraitCameraView getPortraitCameraView() {
        return PortraitViewModule_ProvidesPortraitCameraViewFactory.proxyProvidesPortraitCameraView((Context) Preconditions.checkNotNull(this.cameraComponent.context(), "Cannot return null from a non-@Nullable component method"), CameraModule_ProvidesEntityIdFactory.proxyProvidesEntityId(this.cameraModule), CameraModule_ProvidesCameraLabelFactory.proxyProvidesCameraLabel(this.cameraModule), CameraModule_ProvidesDevicePayloadFactory.proxyProvidesDevicePayload(this.cameraModule), getPresenter(), (PtzListenerFactory) Preconditions.checkNotNull(this.cameraComponent.ptzListenerFactory(), "Cannot return null from a non-@Nullable component method"), (NetworkService) Preconditions.checkNotNull(this.cameraComponent.networkService(), "Cannot return null from a non-@Nullable component method"), (RoutingRegistry) Preconditions.checkNotNull(this.cameraComponent.routingRegistry(), "Cannot return null from a non-@Nullable component method"), (FeatureServiceV2) Preconditions.checkNotNull(this.cameraComponent.featureServiceV2(), "Cannot return null from a non-@Nullable component method"));
    }

    private CameraViewContract.Presenter getPresenter() {
        CameraModule cameraModule = this.cameraModule;
        return CameraModule_ProvidesPresenterFactory.proxyProvidesPresenter(cameraModule, CameraModule_ProvidesEntityIdFactory.proxyProvidesEntityId(cameraModule), (CameraViewContract.Model) Preconditions.checkNotNull(this.cameraComponent.cameraModel(), "Cannot return null from a non-@Nullable component method"), (CameraSessionManager) Preconditions.checkNotNull(this.cameraComponent.cameraSessionManager(), "Cannot return null from a non-@Nullable component method"), (AudioManager) Preconditions.checkNotNull(this.cameraComponent.audioManager(), "Cannot return null from a non-@Nullable component method"), (AppLifecycleOwner) Preconditions.checkNotNull(this.cameraComponent.appLifeycleOwner(), "Cannot return null from a non-@Nullable component method"), (CamerasMobilyticsService) Preconditions.checkNotNull(this.cameraComponent.camerasMobilyticsService(), "Cannot return null from a non-@Nullable component method"), (RoutingService) Preconditions.checkNotNull(this.cameraComponent.routingService(), "Cannot return null from a non-@Nullable component method"), (FeatureServiceV2) Preconditions.checkNotNull(this.cameraComponent.featureServiceV2(), "Cannot return null from a non-@Nullable component method"), CameraModule_ProvidesDevicePayloadFactory.proxyProvidesDevicePayload(this.cameraModule), (CoralService) Preconditions.checkNotNull(this.cameraComponent.coralService(), "Cannot return null from a non-@Nullable component method"));
    }

    @CanIgnoreReturnValue
    private CamerasViewController injectCamerasViewController(CamerasViewController camerasViewController) {
        CamerasViewController_MembersInjector.injectAudioManager(camerasViewController, (AudioManager) Preconditions.checkNotNull(this.cameraComponent.audioManager(), "Cannot return null from a non-@Nullable component method"));
        CamerasViewController_MembersInjector.injectCameraSessionManager(camerasViewController, (CameraSessionManager) Preconditions.checkNotNull(this.cameraComponent.cameraSessionManager(), "Cannot return null from a non-@Nullable component method"));
        CamerasViewController_MembersInjector.injectPtzListenerFactory(camerasViewController, (PtzListenerFactory) Preconditions.checkNotNull(this.cameraComponent.ptzListenerFactory(), "Cannot return null from a non-@Nullable component method"));
        CamerasViewController_MembersInjector.injectPortraitCameraView(camerasViewController, getPortraitCameraView());
        CamerasViewController_MembersInjector.injectAppLifecycleOwner(camerasViewController, (AppLifecycleOwner) Preconditions.checkNotNull(this.cameraComponent.appLifeycleOwner(), "Cannot return null from a non-@Nullable component method"));
        return camerasViewController;
    }

    @Override // com.amazon.alexa.smarthomecameras.view.CamerasViewController.Injector
    public void inject(CamerasViewController camerasViewController) {
        injectCamerasViewController(camerasViewController);
    }

    private DaggerCamerasViewController_Injector(Builder builder) {
        this.cameraComponent = builder.cameraComponent;
        this.cameraModule = builder.cameraModule;
    }
}

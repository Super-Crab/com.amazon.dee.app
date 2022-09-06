package com.amazon.alexa.smarthomecameras.view;

import com.amazon.alexa.smarthomecameras.audio.AudioManager;
import com.amazon.alexa.smarthomecameras.ptz.PtzListenerFactory;
import com.amazon.alexa.smarthomecameras.session.CameraSessionManager;
import com.amazon.alexa.smarthomecameras.util.AppLifecycleOwner;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class CamerasViewController_MembersInjector implements MembersInjector<CamerasViewController> {
    private final Provider<AppLifecycleOwner> appLifecycleOwnerProvider;
    private final Provider<AudioManager> audioManagerProvider;
    private final Provider<CameraSessionManager> cameraSessionManagerProvider;
    private final Provider<PortraitCameraView> portraitCameraViewProvider;
    private final Provider<PtzListenerFactory> ptzListenerFactoryProvider;

    public CamerasViewController_MembersInjector(Provider<AudioManager> provider, Provider<CameraSessionManager> provider2, Provider<PtzListenerFactory> provider3, Provider<PortraitCameraView> provider4, Provider<AppLifecycleOwner> provider5) {
        this.audioManagerProvider = provider;
        this.cameraSessionManagerProvider = provider2;
        this.ptzListenerFactoryProvider = provider3;
        this.portraitCameraViewProvider = provider4;
        this.appLifecycleOwnerProvider = provider5;
    }

    public static MembersInjector<CamerasViewController> create(Provider<AudioManager> provider, Provider<CameraSessionManager> provider2, Provider<PtzListenerFactory> provider3, Provider<PortraitCameraView> provider4, Provider<AppLifecycleOwner> provider5) {
        return new CamerasViewController_MembersInjector(provider, provider2, provider3, provider4, provider5);
    }

    public static void injectAppLifecycleOwner(CamerasViewController camerasViewController, AppLifecycleOwner appLifecycleOwner) {
        camerasViewController.appLifecycleOwner = appLifecycleOwner;
    }

    public static void injectAudioManager(CamerasViewController camerasViewController, AudioManager audioManager) {
        camerasViewController.audioManager = audioManager;
    }

    public static void injectCameraSessionManager(CamerasViewController camerasViewController, CameraSessionManager cameraSessionManager) {
        camerasViewController.cameraSessionManager = cameraSessionManager;
    }

    public static void injectPortraitCameraView(CamerasViewController camerasViewController, PortraitCameraView portraitCameraView) {
        camerasViewController.portraitCameraView = portraitCameraView;
    }

    public static void injectPtzListenerFactory(CamerasViewController camerasViewController, PtzListenerFactory ptzListenerFactory) {
        camerasViewController.ptzListenerFactory = ptzListenerFactory;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CamerasViewController camerasViewController) {
        injectAudioManager(camerasViewController, this.audioManagerProvider.mo10268get());
        injectCameraSessionManager(camerasViewController, this.cameraSessionManagerProvider.mo10268get());
        injectPtzListenerFactory(camerasViewController, this.ptzListenerFactoryProvider.mo10268get());
        injectPortraitCameraView(camerasViewController, this.portraitCameraViewProvider.mo10268get());
        injectAppLifecycleOwner(camerasViewController, this.appLifecycleOwnerProvider.mo10268get());
    }
}

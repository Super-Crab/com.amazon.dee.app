package com.amazon.alexa.smarthomecameras;
/* loaded from: classes10.dex */
public interface CamerasContract {

    /* loaded from: classes10.dex */
    public interface Interactor {
        void onCameraSettingsClicked(String str);
    }

    /* loaded from: classes10.dex */
    public interface Presenter {
        void onCameraSettingsClicked(String str);

        void start();
    }
}

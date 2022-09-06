package com.amazon.alexa.smarthomecameras.dependencies.components;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class CameraComponentProvider {
    private static CameraComponent cameraComponent;

    @Nullable
    public static synchronized CameraComponent getCameraComponent() {
        CameraComponent cameraComponent2;
        synchronized (CameraComponentProvider.class) {
            cameraComponent2 = cameraComponent;
        }
        return cameraComponent2;
    }

    public static synchronized void reset() {
        synchronized (CameraComponentProvider.class) {
            cameraComponent = null;
        }
    }

    public static synchronized void setCameraComponent(CameraComponent cameraComponent2) {
        synchronized (CameraComponentProvider.class) {
            Preconditions.checkNotNull(cameraComponent2, "CameraComponent cannot be null");
            cameraComponent = cameraComponent2;
        }
    }
}

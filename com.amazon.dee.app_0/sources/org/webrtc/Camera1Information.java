package org.webrtc;

import android.hardware.Camera;
/* loaded from: classes5.dex */
public class Camera1Information implements CameraInformation {
    private final Camera.Parameters parameters;
    private boolean enable = false;
    private float transitionTime = 0.0f;
    private float strength = 0.0f;
    private boolean enableGpuPath = false;

    public Camera1Information(Camera.Parameters parameters) {
        this.parameters = parameters;
    }

    @Override // org.webrtc.CameraInformation
    public void disableVideoEffect() {
        this.enable = false;
        this.transitionTime = 0.0f;
        this.strength = 0.0f;
        this.enableGpuPath = true;
    }

    @Override // org.webrtc.CameraInformation
    public void enableVideoEffect(float f, float f2) {
        if (f >= 0.0f) {
            if (f2 >= 0.0f && f2 <= 1.0f) {
                this.enable = true;
                this.transitionTime = f;
                this.strength = f2;
                this.enableGpuPath = true;
                return;
            }
            throw new IllegalArgumentException("Strength must be between 0.0 and 1.0 inclusive.");
        }
        throw new IllegalArgumentException("TransitionTime can not be negative.");
    }

    @Override // org.webrtc.CameraInformation
    /* renamed from: getAttributes */
    public CameraAttributes mo13073getAttributes() {
        return new Camera1Attributes(this.parameters, this.enable, this.transitionTime, this.strength, this.enableGpuPath);
    }

    public Camera.Parameters getParameters() {
        return this.parameters;
    }
}

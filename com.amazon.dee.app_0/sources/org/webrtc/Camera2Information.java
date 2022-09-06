package org.webrtc;

import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes5.dex */
public class Camera2Information implements CameraInformation {
    private List<CaptureRequest.Key<?>> captureRequestKeys;
    private boolean enable = false;
    private float transitionTime = 0.0f;
    private float strength = 0.0f;
    private int captureWidth = 0;
    private int captureHeight = 0;
    private Surface followMeSurface = null;
    private FollowMeCameraSessionFrameUpdater followMeCameraFrameUpdater = null;
    private HashMap<String, Map.Entry<CaptureRequest.Key<Object>, Object>> customCaptureRequestKeys = new HashMap<>();

    public Camera2Information(List<CaptureRequest.Key<?>> list) {
        this.captureRequestKeys = new ArrayList();
        this.captureRequestKeys = list;
    }

    public void addCustomCaptureRequest(CaptureRequest.Key<Object> key, Object obj) {
        this.customCaptureRequestKeys.put(key.getName(), new AbstractMap.SimpleEntry(key, obj));
    }

    @Override // org.webrtc.CameraInformation
    public void disableVideoEffect() {
        this.enable = false;
        this.transitionTime = 0.0f;
        this.strength = 0.0f;
    }

    @Override // org.webrtc.CameraInformation
    public void enableVideoEffect(float f, float f2) {
        if (f >= 0.0f) {
            if (f2 >= 0.0f && f2 <= 1.0f) {
                this.enable = true;
                this.transitionTime = f;
                this.strength = f2;
                return;
            }
            throw new IllegalArgumentException("Strength must be between 0.0 and 1.0 inclusive.");
        }
        throw new IllegalArgumentException("TransitionTime can not be negative.");
    }

    public int getCaptureHeight() {
        return this.captureHeight;
    }

    public List<CaptureRequest.Key<?>> getCaptureRequestKeys() {
        return this.captureRequestKeys;
    }

    public int getCaptureWidth() {
        return this.captureWidth;
    }

    public void setCaptureHeight(int i) {
        this.captureHeight = i;
    }

    public void setCaptureWidth(int i) {
        this.captureWidth = i;
    }

    public void setFollowMeCameraSessionFrameUpdater(FollowMeCameraSessionFrameUpdater followMeCameraSessionFrameUpdater) {
        this.followMeCameraFrameUpdater = followMeCameraSessionFrameUpdater;
    }

    public void setFollowMeSurface(Surface surface) {
        this.followMeSurface = surface;
    }

    @Override // org.webrtc.CameraInformation
    /* renamed from: getAttributes */
    public Camera2Attributes mo13073getAttributes() {
        return new Camera2Attributes(this.enable, this.transitionTime, this.strength, this.customCaptureRequestKeys, this.followMeSurface, this.followMeCameraFrameUpdater);
    }
}

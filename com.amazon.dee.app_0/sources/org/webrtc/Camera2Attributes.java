package org.webrtc;

import android.hardware.camera2.CaptureRequest;
import android.view.Surface;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class Camera2Attributes implements CameraAttributes {
    private static final int DEFAULT_RADIUS = 128;
    private static final String TAG = "Camera2Attributes";
    private HashMap<String, Map.Entry<CaptureRequest.Key<Object>, Object>> customCaptureRequestKeys;
    private boolean enable;
    private FollowMeCameraSessionFrameUpdater followMeCameraFrameUpdater;
    private Surface followMeSurface;
    private float strength;
    private float transitionTime;

    public Camera2Attributes() {
        this.enable = false;
        this.transitionTime = 0.0f;
        this.strength = 0.0f;
        this.followMeSurface = null;
        this.followMeCameraFrameUpdater = null;
        this.customCaptureRequestKeys = new HashMap<>();
    }

    public String getCurrentVideoEffectStatus() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[{Format:1.0}, {name:FROST, enable:");
        outline107.append(this.enable);
        outline107.append("}, {FROST: CURRENT:");
        return GeneratedOutlineSupport1.outline91(outline107, this.strength < 0.01f ? "0.0" : "1.0", "}]");
    }

    public Map.Entry<CaptureRequest.Key<Object>, Object> getCustomCaptureRequestKey(String str) {
        return this.customCaptureRequestKeys.getOrDefault(str, null);
    }

    public HashMap<String, Map.Entry<CaptureRequest.Key<Object>, Object>> getCustomCaptureRequestKeys() {
        return this.customCaptureRequestKeys;
    }

    public boolean getEnableVideoEffect() {
        return this.enable;
    }

    public FollowMeCameraSessionFrameUpdater getFollowMeCameraSessionFrameUpdater() {
        return this.followMeCameraFrameUpdater;
    }

    public Surface getFollowMeSurface() {
        return this.followMeSurface;
    }

    public float getStrength() {
        return this.strength;
    }

    public float getTransitionTime() {
        return this.transitionTime;
    }

    public int getVideoEffectRadius(long j) {
        if (j > 0 && Float.compare(this.transitionTime, 0.0f) > 0) {
            this.strength = 1.0f - ((float) ((TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - j) / 1000.0d) / this.transitionTime));
            if (this.strength < 0.01f) {
                this.strength = 0.0f;
                return 0;
            }
        } else {
            this.strength = 1.0f;
        }
        return (int) (this.strength * 128.0f);
    }

    public String toString() {
        return getCurrentVideoEffectStatus();
    }

    public Camera2Attributes(boolean z, float f, float f2, HashMap<String, Map.Entry<CaptureRequest.Key<Object>, Object>> hashMap, Surface surface, FollowMeCameraSessionFrameUpdater followMeCameraSessionFrameUpdater) {
        this.enable = false;
        this.transitionTime = 0.0f;
        this.strength = 0.0f;
        this.followMeSurface = null;
        this.followMeCameraFrameUpdater = null;
        this.customCaptureRequestKeys = new HashMap<>();
        this.customCaptureRequestKeys.putAll(hashMap);
        this.enable = z;
        this.transitionTime = f;
        this.strength = f2;
        this.followMeSurface = surface;
        this.followMeCameraFrameUpdater = followMeCameraSessionFrameUpdater;
    }
}

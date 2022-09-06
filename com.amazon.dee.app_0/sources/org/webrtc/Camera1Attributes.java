package org.webrtc;

import android.hardware.Camera;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public class Camera1Attributes implements CameraAttributes {
    private static final int DEFAULT_RADIUS = 128;
    private boolean enable;
    private boolean enableGpuPath;
    private final String str;
    private float strength;
    private float transitionTime;

    public Camera1Attributes(Camera.Parameters parameters, boolean z, float f, float f2, boolean z2) {
        this.enable = false;
        this.transitionTime = 0.0f;
        this.strength = 0.0f;
        this.enableGpuPath = false;
        this.str = parameters.flatten();
        this.enable = z;
        this.transitionTime = f;
        this.strength = f2;
        this.enableGpuPath = z2;
    }

    public String getCameraParametersString() {
        return this.str;
    }

    public String getCurrentVideoEffectStatus() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[{Format:1.0}, {name:FROST, enable:");
        outline107.append(this.enable);
        outline107.append("}, {FROST: CURRENT:");
        return GeneratedOutlineSupport1.outline91(outline107, this.strength < 0.01f ? "0.0" : "1.0", "}]");
    }

    public boolean getEnableGpuPath() {
        return this.enableGpuPath;
    }

    public boolean getEnableVideoEffect() {
        return this.enable;
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
}

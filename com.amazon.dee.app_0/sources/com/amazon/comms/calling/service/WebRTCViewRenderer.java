package com.amazon.comms.calling.service;

import android.view.SurfaceView;
import android.view.View;
/* loaded from: classes11.dex */
public interface WebRTCViewRenderer {

    /* loaded from: classes11.dex */
    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    /* loaded from: classes11.dex */
    public enum SurfaceViewShape {
        CIRCLE,
        RECTANGLE,
        ROUNDED_RECTANGLE;

        public static SurfaceViewShape getValue(String str) {
            if (str == null) {
                return RECTANGLE;
            }
            return valueOf(str);
        }
    }

    boolean firstFrameReceived();

    boolean firstFrameRendered();

    @Deprecated
    SurfaceView getSurfaceView();

    <T extends View> T getView(Class<T> cls);

    void setMirror(boolean z);

    void setScalingType(ScalingType scalingType);

    void setSurfaceViewShape(SurfaceViewShape surfaceViewShape, int i);
}

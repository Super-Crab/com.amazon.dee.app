package com.amazon.rtcsc.appclient.surfaceview;
/* loaded from: classes13.dex */
public interface RTCSurfaceViewInterface {

    /* loaded from: classes13.dex */
    public enum ScalingType {
        SCALE_ASPECT_FIT,
        SCALE_ASPECT_FILL,
        SCALE_ASPECT_BALANCED
    }

    /* loaded from: classes13.dex */
    public enum ViewDirection {
        LOCAL_VIEW,
        REMOTE_VIEW
    }

    void clearImage();

    void init(String str, ViewDirection viewDirection);

    void release();

    void setEnableHardwareScaler(boolean z);

    void setMirror(boolean z);

    void setScalingType(ScalingType scalingType);
}

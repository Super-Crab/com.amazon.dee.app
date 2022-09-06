package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public final class MediaParams {
    private final LastFrameClearOption localLastFrameClearOption;
    private final LastFrameClearOption remoteLastFrameClearOption;
    private final float surfaceViewBorderRadius;

    /* loaded from: classes11.dex */
    public enum LastFrameClearOption {
        NONE,
        RENDER_BLACK,
        REINITIALIZE_SURFACE
    }

    public MediaParams(LastFrameClearOption lastFrameClearOption, LastFrameClearOption lastFrameClearOption2, float f) {
        this.localLastFrameClearOption = lastFrameClearOption;
        this.remoteLastFrameClearOption = lastFrameClearOption2;
        this.surfaceViewBorderRadius = f;
    }

    public LastFrameClearOption getLocalLastFrameClearOption() {
        return this.localLastFrameClearOption;
    }

    public LastFrameClearOption getRemoteLastFrameClearOption() {
        return this.remoteLastFrameClearOption;
    }

    public float getSurfaceViewBorderRadius() {
        return this.surfaceViewBorderRadius;
    }
}

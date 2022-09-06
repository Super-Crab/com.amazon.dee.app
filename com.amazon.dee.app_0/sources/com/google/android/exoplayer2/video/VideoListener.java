package com.google.android.exoplayer2.video;
/* loaded from: classes2.dex */
public interface VideoListener {
    default void onRenderedFirstFrame() {
    }

    default void onSurfaceSizeChanged(int i, int i2) {
    }

    default void onVideoSizeChanged(int i, int i2, int i3, float f) {
    }
}

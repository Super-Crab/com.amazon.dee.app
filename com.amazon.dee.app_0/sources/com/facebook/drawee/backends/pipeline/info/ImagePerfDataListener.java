package com.facebook.drawee.backends.pipeline.info;
/* loaded from: classes2.dex */
public interface ImagePerfDataListener {
    void onImageLoadStatusUpdated(ImagePerfData imagePerfData, int imageLoadStatus);

    void onImageVisibilityUpdated(ImagePerfData imagePerfData, int visibilityState);
}

package com.facebook.drawee.backends.pipeline.debug;

import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class DebugOverlayImageOriginListener implements ImageOriginListener {
    private int mImageOrigin = 1;

    public int getImageOrigin() {
        return this.mImageOrigin;
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImageOriginListener
    public void onImageLoaded(String controllerId, int imageOrigin, boolean successful, @Nullable String ultimateProducerName) {
        this.mImageOrigin = imageOrigin;
    }
}

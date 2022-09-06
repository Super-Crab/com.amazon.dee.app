package com.facebook.drawee.backends.pipeline.info;

import com.facebook.common.logging.FLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class ForwardingImageOriginListener implements ImageOriginListener {
    private static final String TAG = "ForwardingImageOriginListener";
    private final List<ImageOriginListener> mImageOriginListeners;

    public ForwardingImageOriginListener(Set<ImageOriginListener> imageOriginListeners) {
        this.mImageOriginListeners = new ArrayList(imageOriginListeners);
    }

    public synchronized void addImageOriginListener(ImageOriginListener listener) {
        this.mImageOriginListeners.add(listener);
    }

    @Override // com.facebook.drawee.backends.pipeline.info.ImageOriginListener
    public synchronized void onImageLoaded(String controllerId, int imageOrigin, boolean successful, @Nullable String ultimateProducerName) {
        int size = this.mImageOriginListeners.size();
        for (int i = 0; i < size; i++) {
            ImageOriginListener imageOriginListener = this.mImageOriginListeners.get(i);
            if (imageOriginListener != null) {
                try {
                    imageOriginListener.onImageLoaded(controllerId, imageOrigin, successful, ultimateProducerName);
                } catch (Exception e) {
                    FLog.e(TAG, "InternalListener exception in onImageLoaded", e);
                }
            }
        }
    }

    public synchronized void removeImageOriginListener(ImageOriginListener listener) {
        this.mImageOriginListeners.remove(listener);
    }

    public ForwardingImageOriginListener(ImageOriginListener... imageOriginListeners) {
        this.mImageOriginListeners = new ArrayList(imageOriginListeners.length);
        Collections.addAll(this.mImageOriginListeners, imageOriginListeners);
    }
}

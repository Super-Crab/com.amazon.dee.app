package com.facebook.drawee.interfaces;

import android.graphics.drawable.Drawable;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public interface SettableDraweeHierarchy extends DraweeHierarchy {
    void reset();

    void setControllerOverlay(Drawable drawable);

    void setFailure(Throwable throwable);

    void setImage(Drawable drawable, float progress, boolean immediate);

    void setProgress(float progress, boolean immediate);

    void setRetry(Throwable throwable);
}

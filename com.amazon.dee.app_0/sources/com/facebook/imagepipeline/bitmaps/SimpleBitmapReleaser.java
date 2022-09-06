package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;
/* loaded from: classes2.dex */
public class SimpleBitmapReleaser implements ResourceReleaser<Bitmap> {
    private static SimpleBitmapReleaser sInstance;

    private SimpleBitmapReleaser() {
    }

    public static SimpleBitmapReleaser getInstance() {
        if (sInstance == null) {
            sInstance = new SimpleBitmapReleaser();
        }
        return sInstance;
    }

    @Override // com.facebook.common.references.ResourceReleaser
    public void release(Bitmap value) {
        value.recycle();
    }
}

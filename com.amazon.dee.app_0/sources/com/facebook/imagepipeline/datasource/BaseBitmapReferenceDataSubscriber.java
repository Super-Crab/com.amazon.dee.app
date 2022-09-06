package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class BaseBitmapReferenceDataSubscriber extends BaseDataSubscriber<CloseableReference<CloseableImage>> {
    protected abstract void onNewResultImpl(@Nullable CloseableReference<Bitmap> bitmapReference);

    @Override // com.facebook.datasource.BaseDataSubscriber
    public void onNewResultImpl(@Nonnull DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (!dataSource.isFinished()) {
            return;
        }
        CloseableReference<CloseableImage> mo6898getResult = dataSource.mo6898getResult();
        CloseableReference<Bitmap> closeableReference = null;
        if (mo6898getResult != null && (mo6898getResult.get() instanceof CloseableStaticBitmap)) {
            closeableReference = ((CloseableStaticBitmap) mo6898getResult.get()).cloneUnderlyingBitmapReference();
        }
        try {
            onNewResultImpl(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
            CloseableReference.closeSafely(mo6898getResult);
        }
    }
}

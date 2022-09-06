package com.facebook.imagepipeline.datasource;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class BaseListBitmapDataSubscriber extends BaseDataSubscriber<List<CloseableReference<CloseableImage>>> {
    @Override // com.facebook.datasource.BaseDataSubscriber
    public void onNewResultImpl(DataSource<List<CloseableReference<CloseableImage>>> dataSource) {
        if (!dataSource.isFinished()) {
            return;
        }
        List<CloseableReference<CloseableImage>> mo6898getResult = dataSource.mo6898getResult();
        if (mo6898getResult == null) {
            onNewResultListImpl(null);
            return;
        }
        try {
            ArrayList arrayList = new ArrayList(mo6898getResult.size());
            for (CloseableReference<CloseableImage> closeableReference : mo6898getResult) {
                if (closeableReference != null && (closeableReference.get() instanceof CloseableBitmap)) {
                    arrayList.add(((CloseableBitmap) closeableReference.get()).getUnderlyingBitmap());
                } else {
                    arrayList.add(null);
                }
            }
            onNewResultListImpl(arrayList);
        } finally {
            for (CloseableReference<CloseableImage> next : mo6898getResult) {
                CloseableReference.closeSafely(next);
            }
        }
    }

    protected abstract void onNewResultListImpl(@Nullable List<Bitmap> bitmapList);
}

package com.facebook.imagepipeline.transformation;

import android.graphics.Bitmap;
import android.os.Build;
import com.facebook.common.references.CloseableReference;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public final class TransformationUtils {
    public static boolean maybeApplyTransformation(@Nullable BitmapTransformation transformation, @Nullable CloseableReference<Bitmap> bitmapReference) {
        if (transformation == null || bitmapReference == null) {
            return false;
        }
        Bitmap bitmap = bitmapReference.get();
        int i = Build.VERSION.SDK_INT;
        if (transformation.modifiesTransparency()) {
            bitmap.setHasAlpha(true);
        }
        transformation.transform(bitmap);
        return true;
    }
}

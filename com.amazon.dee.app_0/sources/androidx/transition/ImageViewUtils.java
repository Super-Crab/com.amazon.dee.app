package androidx.transition;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
class ImageViewUtils {
    private static final String TAG = "ImageViewUtils";
    private static Method sAnimateTransformMethod;
    private static boolean sAnimateTransformMethodFetched;
    private static Field sDrawMatrixField;
    private static boolean sDrawMatrixFieldFetched;

    private ImageViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void animateTransform(ImageView imageView, Matrix matrix) {
        if (matrix == null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.setBounds(0, 0, (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight(), (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom());
            imageView.invalidate();
            return;
        }
        int i = Build.VERSION.SDK_INT;
        fetchAnimateTransformMethod();
        Method method = sAnimateTransformMethod;
        if (method == null) {
            return;
        }
        try {
            method.invoke(imageView, matrix);
        } catch (IllegalAccessException unused) {
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    private static void fetchAnimateTransformMethod() {
        if (!sAnimateTransformMethodFetched) {
            try {
                sAnimateTransformMethod = ImageView.class.getDeclaredMethod("animateTransform", Matrix.class);
                sAnimateTransformMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i(TAG, "Failed to retrieve animateTransform method", e);
            }
            sAnimateTransformMethodFetched = true;
        }
    }

    private static void fetchDrawMatrixField() {
        if (!sDrawMatrixFieldFetched) {
            try {
                sDrawMatrixField = ImageView.class.getDeclaredField("mDrawMatrix");
                sDrawMatrixField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
            }
            sDrawMatrixFieldFetched = true;
        }
    }
}

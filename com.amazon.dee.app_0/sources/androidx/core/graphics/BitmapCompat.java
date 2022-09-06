package androidx.core.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class BitmapCompat {
    private BitmapCompat() {
    }

    public static int getAllocationByteCount(@NonNull Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return bitmap.getAllocationByteCount();
    }

    public static boolean hasMipMap(@NonNull Bitmap bitmap) {
        int i = Build.VERSION.SDK_INT;
        return bitmap.hasMipMap();
    }

    public static void setHasMipMap(@NonNull Bitmap bitmap, boolean z) {
        int i = Build.VERSION.SDK_INT;
        bitmap.setHasMipMap(z);
    }
}

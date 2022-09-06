package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class ImageViewCompat {
    private ImageViewCompat() {
    }

    @Nullable
    public static ColorStateList getImageTintList(@NonNull ImageView imageView) {
        int i = Build.VERSION.SDK_INT;
        return imageView.getImageTintList();
    }

    @Nullable
    public static PorterDuff.Mode getImageTintMode(@NonNull ImageView imageView) {
        int i = Build.VERSION.SDK_INT;
        return imageView.getImageTintMode();
    }

    public static void setImageTintList(@NonNull ImageView imageView, @Nullable ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintList(colorStateList);
        int i2 = Build.VERSION.SDK_INT;
    }

    public static void setImageTintMode(@NonNull ImageView imageView, @Nullable PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        imageView.setImageTintMode(mode);
        int i2 = Build.VERSION.SDK_INT;
    }
}

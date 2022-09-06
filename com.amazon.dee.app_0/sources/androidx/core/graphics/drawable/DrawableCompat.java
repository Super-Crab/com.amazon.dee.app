package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* loaded from: classes.dex */
public final class DrawableCompat {
    private static final String TAG = "DrawableCompat";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;

    private DrawableCompat() {
    }

    public static void applyTheme(@NonNull Drawable drawable, @NonNull Resources.Theme theme) {
        int i = Build.VERSION.SDK_INT;
        drawable.applyTheme(theme);
    }

    public static boolean canApplyTheme(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.canApplyTheme();
    }

    public static void clearColorFilter(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        drawable.clearColorFilter();
    }

    public static int getAlpha(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    public static ColorFilter getColorFilter(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getColorFilter();
    }

    public static int getLayoutDirection(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.getLayoutDirection();
    }

    public static void inflate(@NonNull Drawable drawable, @NonNull Resources resources, @NonNull XmlPullParser xmlPullParser, @NonNull AttributeSet attributeSet, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        int i = Build.VERSION.SDK_INT;
        drawable.inflate(resources, xmlPullParser, attributeSet, theme);
    }

    public static boolean isAutoMirrored(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable.isAutoMirrored();
    }

    @Deprecated
    public static void jumpToCurrentState(@NonNull Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    public static void setAutoMirrored(@NonNull Drawable drawable, boolean z) {
        int i = Build.VERSION.SDK_INT;
        drawable.setAutoMirrored(z);
    }

    public static void setHotspot(@NonNull Drawable drawable, float f, float f2) {
        int i = Build.VERSION.SDK_INT;
        drawable.setHotspot(f, f2);
    }

    public static void setHotspotBounds(@NonNull Drawable drawable, int i, int i2, int i3, int i4) {
        int i5 = Build.VERSION.SDK_INT;
        drawable.setHotspotBounds(i, i2, i3, i4);
    }

    public static boolean setLayoutDirection(@NonNull Drawable drawable, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return drawable.setLayoutDirection(i);
    }

    public static void setTint(@NonNull Drawable drawable, @ColorInt int i) {
        int i2 = Build.VERSION.SDK_INT;
        drawable.setTint(i);
    }

    public static void setTintList(@NonNull Drawable drawable, @Nullable ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintList(colorStateList);
    }

    public static void setTintMode(@NonNull Drawable drawable, @NonNull PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        drawable.setTintMode(mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T extends Drawable> T unwrap(@NonNull Drawable drawable) {
        return drawable instanceof WrappedDrawable ? (T) ((WrappedDrawable) drawable).getWrappedDrawable() : drawable;
    }

    public static Drawable wrap(@NonNull Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        return drawable;
    }
}

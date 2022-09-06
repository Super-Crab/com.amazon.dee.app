package androidx.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;
/* loaded from: classes.dex */
class PropertyValuesHolderUtils {
    private PropertyValuesHolderUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        int i = Build.VERSION.SDK_INT;
        return PropertyValuesHolder.ofObject(property, (TypeConverter) null, path);
    }
}

package androidx.core.util;

import android.os.Build;
import androidx.annotation.Nullable;
import java.util.Objects;
/* loaded from: classes.dex */
public class ObjectsCompat {
    private ObjectsCompat() {
    }

    public static boolean equals(@Nullable Object obj, @Nullable Object obj2) {
        int i = Build.VERSION.SDK_INT;
        return Objects.equals(obj, obj2);
    }

    public static int hash(@Nullable Object... objArr) {
        int i = Build.VERSION.SDK_INT;
        return Objects.hash(objArr);
    }

    public static int hashCode(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}

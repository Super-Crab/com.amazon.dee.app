package androidx.databinding.adapters;

import android.os.Build;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class ListenerUtil {
    private static final SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray<>();

    public static <T> T getListener(View view, int i) {
        int i2 = Build.VERSION.SDK_INT;
        return (T) view.getTag(i);
    }

    public static <T> T trackListener(View view, T t, int i) {
        int i2 = Build.VERSION.SDK_INT;
        T t2 = (T) view.getTag(i);
        view.setTag(i, t);
        return t2;
    }
}

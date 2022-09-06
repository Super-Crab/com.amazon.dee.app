package androidx.transition;

import android.graphics.Canvas;
import android.os.Build;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
class CanvasUtils {
    private static Method sInorderBarrierMethod;
    private static boolean sOrderMethodsFetched;
    private static Method sReorderBarrierMethod;

    private CanvasUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void enableZ(@NonNull Canvas canvas, boolean z) {
        if (Build.VERSION.SDK_INT < 28) {
            if (!sOrderMethodsFetched) {
                try {
                    sReorderBarrierMethod = Canvas.class.getDeclaredMethod("insertReorderBarrier", new Class[0]);
                    sReorderBarrierMethod.setAccessible(true);
                    sInorderBarrierMethod = Canvas.class.getDeclaredMethod("insertInorderBarrier", new Class[0]);
                    sInorderBarrierMethod.setAccessible(true);
                } catch (NoSuchMethodException unused) {
                }
                sOrderMethodsFetched = true;
            }
            if (z) {
                try {
                    if (sReorderBarrierMethod != null) {
                        sReorderBarrierMethod.invoke(canvas, new Object[0]);
                    }
                } catch (IllegalAccessException unused2) {
                    return;
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e.getCause());
                }
            }
            if (z || sInorderBarrierMethod == null) {
                return;
            }
            sInorderBarrierMethod.invoke(canvas, new Object[0]);
            return;
        }
        throw new IllegalStateException("This method doesn't work on Pie!");
    }
}

package androidx.core.os;

import android.os.Build;
import android.os.Trace;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class TraceCompat {
    private TraceCompat() {
    }

    public static void beginSection(@NonNull String str) {
        int i = Build.VERSION.SDK_INT;
        Trace.beginSection(str);
    }

    public static void endSection() {
        int i = Build.VERSION.SDK_INT;
        Trace.endSection();
    }
}

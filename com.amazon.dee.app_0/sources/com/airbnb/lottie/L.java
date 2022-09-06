package com.airbnb.lottie;

import androidx.annotation.RestrictTo;
import androidx.core.os.TraceCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class L {
    public static boolean DBG = false;
    private static final int MAX_DEPTH = 20;
    public static final String TAG = "LOTTIE";
    private static int depthPastMaxDepth = 0;
    private static String[] sections = null;
    private static long[] startTimeNs = null;
    private static int traceDepth = 0;
    private static boolean traceEnabled = false;

    public static void beginSection(String str) {
        if (!traceEnabled) {
            return;
        }
        int i = traceDepth;
        if (i == 20) {
            depthPastMaxDepth++;
            return;
        }
        sections[i] = str;
        startTimeNs[i] = System.nanoTime();
        TraceCompat.beginSection(str);
        traceDepth++;
    }

    public static float endSection(String str) {
        int i = depthPastMaxDepth;
        if (i > 0) {
            depthPastMaxDepth = i - 1;
            return 0.0f;
        } else if (!traceEnabled) {
            return 0.0f;
        } else {
            traceDepth--;
            int i2 = traceDepth;
            if (i2 != -1) {
                if (str.equals(sections[i2])) {
                    TraceCompat.endSection();
                    return ((float) (System.nanoTime() - startTimeNs[traceDepth])) / 1000000.0f;
                }
                throw new IllegalStateException(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline115("Unbalanced trace call ", str, ". Expected "), sections[traceDepth], "."));
            }
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
    }

    public static void setTraceEnabled(boolean z) {
        if (traceEnabled == z) {
            return;
        }
        traceEnabled = z;
        if (!traceEnabled) {
            return;
        }
        sections = new String[20];
        startTimeNs = new long[20];
    }
}

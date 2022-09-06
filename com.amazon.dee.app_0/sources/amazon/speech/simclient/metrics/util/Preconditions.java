package amazon.speech.simclient.metrics.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public final class Preconditions {
    public static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72(str, " cannot be null"));
    }
}

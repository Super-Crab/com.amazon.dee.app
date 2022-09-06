package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class dl {
    private static ea jE;

    public static synchronized ea C(Context context) {
        ea eaVar;
        synchronized (dl.class) {
            if (jE == null) {
                jE = cj.x(context);
            }
            eaVar = jE;
        }
        return eaVar;
    }

    public static void generateNewInstance(Context context) {
        jE = cj.x(context);
    }
}

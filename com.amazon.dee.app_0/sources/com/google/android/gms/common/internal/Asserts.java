package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes2.dex */
public final class Asserts {
    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForSdk
    public static void checkMainThread(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }
        String valueOf = String.valueOf(Thread.currentThread());
        String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + 57, "checkMainThread: current thread ", valueOf, " IS NOT the main thread ", valueOf2);
        outline106.append("!");
        Log.e("Asserts", outline106.toString());
        throw new IllegalStateException(str);
    }

    @KeepForSdk
    public static void checkNotMainThread(String str) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return;
        }
        String valueOf = String.valueOf(Thread.currentThread());
        String valueOf2 = String.valueOf(Looper.getMainLooper().getThread());
        StringBuilder outline106 = GeneratedOutlineSupport1.outline106(valueOf2.length() + valueOf.length() + 56, "checkNotMainThread: current thread ", valueOf, " IS the main thread ", valueOf2);
        outline106.append("!");
        Log.e("Asserts", outline106.toString());
        throw new IllegalStateException(str);
    }

    @KeepForSdk
    public static void checkNotNull(Object obj) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException("null reference");
    }

    @KeepForSdk
    public static void checkNull(Object obj) {
        if (obj == null) {
            return;
        }
        throw new IllegalArgumentException("non-null reference");
    }

    @KeepForSdk
    public static void checkState(boolean z) {
        if (z) {
            return;
        }
        throw new IllegalStateException();
    }

    @KeepForSdk
    public static void checkNotNull(Object obj, Object obj2) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(String.valueOf(obj2));
    }

    @KeepForSdk
    public static void checkState(boolean z, Object obj) {
        if (z) {
            return;
        }
        throw new IllegalStateException(String.valueOf(obj));
    }
}

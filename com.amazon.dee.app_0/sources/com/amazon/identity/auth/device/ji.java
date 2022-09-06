package com.amazon.identity.auth.device;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ji {
    private static Thread rG;
    private static Handler rH;
    public static final Executor rF = new ir("MAPCommonThreadPool");
    private static final Object[] aM = new Object[0];

    public static void b(Runnable runnable) {
        rF.execute(runnable);
    }

    public static void c(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    public static void d(Runnable runnable) {
        if (!gP()) {
            runnable.run();
        } else {
            b(runnable);
        }
    }

    public static is dG(String str) {
        return new is(str);
    }

    public static boolean gP() {
        return Looper.getMainLooper() != null && Looper.getMainLooper() == Looper.myLooper();
    }

    public static Handler gQ() {
        synchronized (aM) {
            if (rH != null) {
                return rH;
            }
            Thread thread = new Thread() { // from class: com.amazon.identity.auth.device.ji.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    Looper.prepare();
                    Handler unused = ji.rH = new Handler();
                    Looper.loop();
                }
            };
            rG = thread;
            thread.start();
            return rH;
        }
    }
}

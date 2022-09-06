package io.reactivex.rxjava3.android.schedulers;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
/* loaded from: classes3.dex */
public final class AndroidSchedulers {
    private static final Scheduler MAIN_THREAD = RxAndroidPlugins.initMainThreadScheduler($$Lambda$AndroidSchedulers$2crO82bOkOgbwToxlZgj1sEtdZQ.INSTANCE);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static final class MainHolder {
        static final Scheduler DEFAULT = new HandlerScheduler(new Handler(Looper.getMainLooper()), true);

        private MainHolder() {
        }
    }

    private AndroidSchedulers() {
        throw new AssertionError("No instances.");
    }

    public static Scheduler from(Looper looper) {
        return from(looper, true);
    }

    public static Scheduler mainThread() {
        return RxAndroidPlugins.onMainThreadScheduler(MAIN_THREAD);
    }

    @SuppressLint({"NewApi"})
    public static Scheduler from(Looper looper, boolean z) {
        if (looper != null) {
            int i = Build.VERSION.SDK_INT;
            return new HandlerScheduler(new Handler(looper), z);
        }
        throw new NullPointerException("looper == null");
    }
}

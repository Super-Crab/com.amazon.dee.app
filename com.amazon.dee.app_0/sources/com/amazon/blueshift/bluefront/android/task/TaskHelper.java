package com.amazon.blueshift.bluefront.android.task;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
/* loaded from: classes11.dex */
public final class TaskHelper {
    private TaskHelper() {
    }

    public static <P, T extends AsyncTask<P, ?, ?>> void execute(T t) {
        execute(t, null);
    }

    @SuppressLint({"NewApi"})
    public static <P, T extends AsyncTask<P, ?, ?>> void execute(T t, P... pArr) {
        int i = Build.VERSION.SDK_INT;
        t.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, pArr);
    }
}

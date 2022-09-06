package com.squareup.leakcanary;

import android.app.Application;
import android.content.Context;
/* loaded from: classes3.dex */
public final class LeakCanary {
    private LeakCanary() {
        throw new AssertionError();
    }

    public static RefWatcher install(Application application) {
        return RefWatcher.DISABLED;
    }

    public static RefWatcher installedRefWatcher() {
        return RefWatcher.DISABLED;
    }

    public static boolean isInAnalyzerProcess(Context context) {
        return false;
    }
}

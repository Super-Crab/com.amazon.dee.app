package com.amazon.tarazed.core.session;

import android.app.ActivityManager;
import kotlin.Metadata;
/* compiled from: AppBackgroundChecker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/core/session/AppBackgroundChecker;", "", "()V", "isAppInBackground", "", "()Z", "isAppInForeground", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class AppBackgroundChecker {
    public static final AppBackgroundChecker INSTANCE = new AppBackgroundChecker();

    private AppBackgroundChecker() {
    }

    public final boolean isAppInBackground() {
        return !isAppInForeground();
    }

    public final boolean isAppInForeground() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo.importance <= 100;
    }
}

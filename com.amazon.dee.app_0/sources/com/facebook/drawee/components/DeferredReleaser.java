package com.facebook.drawee.components;

import android.os.Looper;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class DeferredReleaser {
    @Nullable
    private static DeferredReleaser sInstance;

    /* loaded from: classes2.dex */
    public interface Releasable {
        void release();
    }

    public static synchronized DeferredReleaser getInstance() {
        DeferredReleaser deferredReleaser;
        synchronized (DeferredReleaser.class) {
            if (sInstance == null) {
                sInstance = new DeferredReleaserConcurrentImpl();
            }
            deferredReleaser = sInstance;
        }
        return deferredReleaser;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public abstract void cancelDeferredRelease(Releasable releasable);

    public abstract void scheduleDeferredRelease(Releasable releasable);
}

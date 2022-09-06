package io.reactivex.rxjava3.internal.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.schedulers.NonBlockingThread;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.CountDownLatch;
/* loaded from: classes3.dex */
public final class BlockingHelper {
    private BlockingHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static void awaitForComplete(CountDownLatch latch, Disposable subscription) {
        if (latch.getCount() == 0) {
            return;
        }
        try {
            verifyNonBlocking();
            latch.await();
        } catch (InterruptedException e) {
            subscription.dispose();
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for subscription to complete.", e);
        }
    }

    public static void verifyNonBlocking() {
        if (RxJavaPlugins.isFailOnNonBlockingScheduler()) {
            if (!(Thread.currentThread() instanceof NonBlockingThread) && !RxJavaPlugins.onBeforeBlocking()) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempt to block on a Scheduler ");
            outline107.append(Thread.currentThread().getName());
            outline107.append(" that doesn't support blocking operators as they may lead to deadlock");
            throw new IllegalStateException(outline107.toString());
        }
    }
}

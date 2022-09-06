package com.amazon.identity.auth.device;

import android.os.Handler;
import java.util.concurrent.CountDownLatch;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ig {
    private static final String TAG = "com.amazon.identity.auth.device.ig";

    private ig() {
    }

    public static void a(Handler handler, final Runnable runnable) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        handler.post(new Runnable() { // from class: com.amazon.identity.auth.device.ig.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                } finally {
                    countDownLatch.countDown();
                }
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            io.e(TAG, "Latch was interrupted.", e);
        }
    }
}

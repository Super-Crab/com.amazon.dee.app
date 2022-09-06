package com.amazon.alexa;

import java.util.concurrent.CountDownLatch;
/* compiled from: ExoplayerEventWaiter.java */
/* loaded from: classes.dex */
public class JTh {
    public static final String zZm = "JTh";
    public final Object BIo = new Object();
    public CountDownLatch zQM;

    public void BIo() {
        synchronized (this.BIo) {
            this.zQM = new CountDownLatch(1);
        }
    }

    public void zZm() {
        synchronized (this.BIo) {
            if (this.zQM != null) {
                this.zQM.countDown();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r1.await(r3, r5) != false) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean zZm(long r3, java.util.concurrent.TimeUnit r5) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.BIo     // Catch: java.lang.InterruptedException -> L19
            monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L19
            java.util.concurrent.CountDownLatch r1 = r2.zQM     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto La
            java.util.concurrent.CountDownLatch r1 = r2.zQM     // Catch: java.lang.Throwable -> L16
            goto Lb
        La:
            r1 = 0
        Lb:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L16
            if (r1 == 0) goto L14
            boolean r3 = r1.await(r3, r5)     // Catch: java.lang.InterruptedException -> L19
            if (r3 == 0) goto L19
        L14:
            r3 = 1
            goto L1a
        L16:
            r3 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L16
            throw r3     // Catch: java.lang.InterruptedException -> L19
        L19:
            r3 = 0
        L1a:
            if (r3 != 0) goto L23
            java.lang.String r4 = com.amazon.alexa.JTh.zZm
            java.lang.String r5 = "Timeout to send an event expired before event was sent"
            android.util.Log.e(r4, r5)
        L23:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.JTh.zZm(long, java.util.concurrent.TimeUnit):boolean");
    }
}

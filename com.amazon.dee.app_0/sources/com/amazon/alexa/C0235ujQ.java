package com.amazon.alexa;

import java.util.concurrent.ExecutorService;
/* compiled from: InteractionLifecycle.java */
/* renamed from: com.amazon.alexa.ujQ  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0235ujQ {
    public ndD BIo;
    public final ExecutorService zQM;
    public zZm zZm = zZm.UNINITIALIZED;

    /* compiled from: InteractionLifecycle.java */
    /* renamed from: com.amazon.alexa.ujQ$BIo */
    /* loaded from: classes.dex */
    protected class BIo implements Runnable {
        public BIo() {
        }

        @Override // java.lang.Runnable
        public void run() {
            C0235ujQ.this.BIo.zQM();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: InteractionLifecycle.java */
    /* renamed from: com.amazon.alexa.ujQ$zZm */
    /* loaded from: classes.dex */
    public enum zZm {
        UNINITIALIZED,
        FOREGROUND,
        BACKGROUND,
        SCHEDULED,
        PAUSED,
        STOPPED
    }

    public C0235ujQ(ndD ndd, ExecutorService executorService) {
        this.BIo = ndd;
        this.zQM = executorService;
    }

    public boolean BIo() {
        return this.zZm == zZm.STOPPED;
    }

    public void zQM() {
        if (!BIo()) {
            this.zZm = zZm.STOPPED;
            zZm(new BIo());
        }
    }

    public dnp zZm() {
        return this.BIo.BIo();
    }

    public void zZm(Runnable runnable) {
        ExecutorService executorService = this.zQM;
        if (executorService != null) {
            executorService.submit(runnable);
        } else {
            runnable.run();
        }
    }
}

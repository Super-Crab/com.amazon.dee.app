package com.amazon.alexa;

import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* compiled from: AudioPlayerProgressReporter.java */
/* loaded from: classes.dex */
public class Ygi {
    public static final AtomicInteger BIo = new AtomicInteger(0);
    public static final String zZm = "Ygi";
    public long JTe;
    public long LPk;
    public ScheduledFuture<?> Mlj;
    public ScheduledExecutorService Qle;
    public final Runnable jiA;
    public ScheduledFuture<?> yPL;
    public final VIX zQM;
    public final Runnable zyO;
    public boolean zzR = false;

    public Ygi(Runnable runnable, Runnable runnable2, VIX vix) {
        this.zyO = runnable;
        this.jiA = runnable2;
        this.zQM = vix;
    }

    public synchronized void BIo() {
        this.zzR = false;
        zZm();
        this.JTe = 0L;
        this.LPk = 0L;
        ScheduledExecutorService scheduledExecutorService = this.Qle;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
        }
    }

    public synchronized void zQM() {
        zZm();
        if (!this.zzR) {
            return;
        }
        long Qle = this.zQM.Qle();
        long j = this.JTe - Qle;
        if (j > 0) {
            String str = "Scheduling ProgressReportDelayElapsed event in " + j + " ms";
            this.yPL = this.Qle.schedule(this.zyO, j, TimeUnit.MILLISECONDS);
        }
        long j2 = this.LPk;
        long j3 = j2 == 0 ? 0L : j2 - (Qle % j2);
        if (j3 > 0) {
            long j4 = this.LPk;
            String str2 = "Scheduling ProgressReportIntervalElapsed event in " + j3 + " ms that will repeat every " + j4 + " ms";
            this.Mlj = this.Qle.scheduleAtFixedRate(this.jiA, j3, j4, TimeUnit.MILLISECONDS);
        }
    }

    public synchronized void zZm(CiJ ciJ) {
        String str = "Progress report setup: " + ciJ;
        zZm();
        this.JTe = ((SlJ) ciJ).zZm;
        this.LPk = ((SlJ) ciJ).BIo;
        StringBuilder zZm2 = C0179Pya.zZm("audio-player-progress-reporter-");
        zZm2.append(BIo.getAndIncrement());
        this.Qle = ManagedExecutorFactory.newSingleThreadScheduledExecutor(zZm2.toString());
        this.zzR = true;
    }

    public synchronized void zyO() {
        zZm();
    }

    public final void zZm() {
        ScheduledFuture<?> scheduledFuture = this.yPL;
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            this.yPL.cancel(false);
        }
        ScheduledFuture<?> scheduledFuture2 = this.Mlj;
        if (scheduledFuture2 == null || scheduledFuture2.isDone()) {
            return;
        }
        this.Mlj.cancel(false);
    }
}

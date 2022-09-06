package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: WakeWordTracker.java */
/* loaded from: classes.dex */
public class wry implements OyE, Runnable {
    public static final String zZm = "wry";
    public final ScheduledExecutorService BIo;
    public final cIy Qle;
    public final ILi jiA;
    public ScheduledFuture yPL;
    public final dDK zQM;
    public final AlexaClientEventBus zyO;
    public final Object JTe = new Object();
    public final List<Long> Mlj = new ArrayList();
    public final AtomicBoolean LPk = new AtomicBoolean(false);

    public wry(dDK ddk, AlexaClientEventBus alexaClientEventBus, ILi iLi, cIy ciy) {
        this.zQM = ddk;
        this.Qle = ciy;
        this.jiA = iLi;
        StringBuilder zZm2 = C0179Pya.zZm("wakeword-tracker-");
        zZm2.append(ciy.getValue());
        this.BIo = ManagedExecutorFactory.newSingleThreadScheduledExecutor(zZm2.toString());
        this.zyO = alexaClientEventBus;
    }

    public final long BIo() {
        synchronized (this.JTe) {
            if (!this.Mlj.isEmpty()) {
                return this.Mlj.get(0).longValue();
            }
            return Long.MIN_VALUE;
        }
    }

    public final boolean jiA() {
        boolean z;
        synchronized (this.JTe) {
            z = !this.Mlj.isEmpty();
        }
        return z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (jiA()) {
            if (this.LPk.get()) {
                Log.i(zZm, "Resume wakeword detection as wakeword is completed");
                long BIo = BIo() + 600;
                GeneratedOutlineSupport1.outline153("Current wakeword Resume position : ", BIo);
                if (this.zQM.zZm() <= BIo) {
                    return;
                }
                Log.i(zZm, "Delaying resume if wakeword occurrences are closer");
                long BIo2 = BIo() + 600;
                while (jiA()) {
                    long zZm2 = zZm();
                    long j = zZm2 - BIo2;
                    if (zZm2 == Long.MIN_VALUE || j > 100) {
                        break;
                    }
                    BIo2 = zZm2 + 600;
                    zQM();
                }
                if (this.zQM.zZm() <= BIo2) {
                    return;
                }
                String str = "Resuming wakeword detection at " + BIo2;
                zyO();
                zQM();
                return;
            }
            Log.i(zZm, "Pause wakeword detection");
            long BIo3 = BIo();
            if (BIo3 == Long.MIN_VALUE) {
                return;
            }
            GeneratedOutlineSupport1.outline153("occurrence found at ", BIo3);
            if (BIo3 - this.zQM.zZm() > 100) {
                return;
            }
            if (!this.LPk.getAndSet(true)) {
                this.zyO.zyO(new CDa());
            }
            StringBuilder zZm3 = C0179Pya.zZm("Pausing at Exoplayer pos : ");
            zZm3.append(this.zQM.zZm());
            zZm3.toString();
        }
    }

    public final void zQM() {
        synchronized (this.JTe) {
            if (!this.Mlj.isEmpty()) {
                this.Mlj.remove(0);
            }
        }
    }

    @Override // com.amazon.alexa.OyE
    public void zZm(long j) {
        synchronized (this.JTe) {
            this.Mlj.add(Long.valueOf(j - 100));
        }
    }

    public final void zyO() {
        if (this.LPk.getAndSet(false)) {
            this.zyO.zyO(new MOH());
        }
    }

    public final long zZm() {
        synchronized (this.JTe) {
            if (this.Mlj.isEmpty() || this.Mlj.size() < 2) {
                return Long.MIN_VALUE;
            }
            return this.Mlj.get(1).longValue();
        }
    }
}

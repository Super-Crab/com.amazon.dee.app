package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.C0235ujQ;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import java.util.concurrent.ExecutorService;
/* compiled from: AudioInteractionLifecycle.java */
/* loaded from: classes.dex */
public class tYL extends C0235ujQ {
    public static final String zyO = "tYL";
    public final DialogRequestIdentifier JTe;
    public final ZIZ LPk;
    public final PJz Qle;
    public final jDH jiA;

    /* compiled from: AudioInteractionLifecycle.java */
    /* loaded from: classes.dex */
    private class BIo implements Runnable {
        public /* synthetic */ BIo(lMm lmm) {
        }

        @Override // java.lang.Runnable
        public void run() {
            tYL.this.jiA.jiA();
        }
    }

    /* compiled from: AudioInteractionLifecycle.java */
    /* loaded from: classes.dex */
    private class zQM implements Runnable {
        public /* synthetic */ zQM(lMm lmm) {
        }

        @Override // java.lang.Runnable
        public void run() {
            tYL.this.jiA.Qle();
        }
    }

    /* compiled from: AudioInteractionLifecycle.java */
    /* loaded from: classes.dex */
    private class zZm implements Runnable {
        public /* synthetic */ zZm(lMm lmm) {
        }

        @Override // java.lang.Runnable
        public void run() {
            tYL.this.jiA.zyO();
        }
    }

    /* compiled from: AudioInteractionLifecycle.java */
    /* loaded from: classes.dex */
    private class zyO implements Runnable {
        public /* synthetic */ zyO(lMm lmm) {
        }

        @Override // java.lang.Runnable
        public void run() {
            tYL.this.jiA.zZm(tYL.this.LPk.zZm(tYL.this.Qle, tYL.this.JTe));
        }
    }

    public tYL(jDH jdh, PJz pJz, DialogRequestIdentifier dialogRequestIdentifier, ExecutorService executorService, ZIZ ziz) {
        super(jdh, executorService);
        this.jiA = jdh;
        this.Qle = pJz;
        this.JTe = dialogRequestIdentifier;
        this.LPk = ziz;
    }

    public boolean JTe() {
        return this.jiA instanceof MSk;
    }

    public boolean LPk() {
        return this.zZm == C0235ujQ.zZm.BACKGROUND;
    }

    public void Qle() {
        if (this.zZm == C0235ujQ.zZm.UNINITIALIZED) {
            this.zZm = C0235ujQ.zZm.SCHEDULED;
            zZm(new zyO(null));
            return;
        }
        Log.w(zyO, "Tried to enter scheduled state from outside uninitialized state");
    }

    public void jiA() {
        if (!BIo()) {
            if (this.zZm == C0235ujQ.zZm.FOREGROUND) {
                return;
            }
            this.zZm = C0235ujQ.zZm.FOREGROUND;
            zZm(new BIo(null));
        }
    }

    public void yPL() {
        if (!BIo()) {
            if (this.zZm == C0235ujQ.zZm.PAUSED) {
                return;
            }
            this.zZm = C0235ujQ.zZm.PAUSED;
            zZm(new zQM(null));
        }
    }

    public void zyO() {
        if (BIo() || LPk()) {
            return;
        }
        this.zZm = C0235ujQ.zZm.BACKGROUND;
        zZm(new zZm(null));
    }
}

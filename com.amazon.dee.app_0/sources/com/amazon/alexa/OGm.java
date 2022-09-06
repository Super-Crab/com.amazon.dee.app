package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.UserPerceivedLatencyData;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.fuM;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DialogTurn.java */
/* loaded from: classes.dex */
public class OGm {
    public static final String zZm = "OGm";
    public final AlexaClientEventBus BIo;
    public Future<?> HvC;
    public final AlexaDialogTurnMetricsCallback JTe;
    public final ScheduledExecutorService LPk;
    public esV Mlj;
    public final AlexaDialogTurnStopCallback Qle;
    public boolean dMe;
    public final LjN jiA;
    public AlexaAudioMetadata lOf;
    public volatile boolean noQ;
    public zZm uzr;
    public volatile boolean vkx;
    public volatile boolean wDP;
    public final AtomicReference<zQM> yPL;
    public final XWx zQM;
    public final Lzl zyO;
    public DialogRequestIdentifier zzR;

    /* compiled from: DialogTurn.java */
    /* loaded from: classes.dex */
    private static class BIo implements Runnable {
        public final OGm zZm;

        public /* synthetic */ BIo(OGm oGm, YhZ yhZ) {
            this.zZm = oGm;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.zZm) {
                if (this.zZm.yPL()) {
                    this.zZm.zZm(fuM.zyO.OTHER);
                }
            }
        }
    }

    /* compiled from: DialogTurn.java */
    /* loaded from: classes.dex */
    public interface zQM {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DialogTurn.java */
    /* loaded from: classes.dex */
    public enum zZm {
        UNKNOWN,
        CREATED,
        STARTED,
        FINISHED
    }

    public OGm(AlexaClientEventBus alexaClientEventBus, esV esv, Lzl lzl, LjN ljN, XWx xWx, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ExecutorFactory.newSingleThreadScheduledExecutor("recording-timeout-" + xWx);
        this.uzr = zZm.UNKNOWN;
        this.BIo = alexaClientEventBus;
        this.Mlj = esv;
        this.zQM = xWx;
        this.zyO = lzl;
        this.jiA = ljN;
        this.Qle = alexaDialogTurnStopCallback;
        this.JTe = alexaDialogTurnMetricsCallback;
        this.uzr = zZm.CREATED;
        this.dMe = true;
        this.LPk = newSingleThreadScheduledExecutor;
        this.zzR = DialogRequestIdentifier.NONE;
        this.yPL = new AtomicReference<>();
    }

    public synchronized void BIo(@Nullable zQM zqm) {
        StringBuilder zZm2 = C0179Pya.zZm("finish turn ");
        zZm2.append(Qle());
        zZm2.toString();
        if (this.vkx) {
            this.wDP = true;
            this.yPL.set(zqm);
        } else {
            zZm(zqm);
        }
    }

    public synchronized esV JTe() {
        return this.Mlj;
    }

    public synchronized boolean LPk() {
        return this.uzr == zZm.FINISHED;
    }

    public synchronized boolean Mlj() {
        return this.uzr == zZm.STARTED;
    }

    public synchronized XWx Qle() {
        return this.zQM;
    }

    public synchronized Lzl jiA() {
        return this.zyO;
    }

    public synchronized boolean yPL() {
        return this.dMe;
    }

    public synchronized cIy zQM() {
        return this.zyO.jiA();
    }

    public synchronized void zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        if (this.uzr == zZm.CREATED) {
            this.zzR = dialogRequestIdentifier;
            Lzl lzl = this.zyO;
            if (!(lzl != null && lzl.JTe())) {
                this.BIo.zyO(new ghu(this.zQM, dialogRequestIdentifier, null));
                this.HvC = this.LPk.schedule(new BIo(this, null), 50L, TimeUnit.SECONDS);
            } else {
                this.BIo.zyO(new tai(this.zQM, dialogRequestIdentifier, null));
            }
            this.jiA.BIo(this.zQM);
            this.uzr = zZm.STARTED;
        } else {
            String str = zZm;
            StringBuilder zZm2 = C0179Pya.zZm("Attempting to start a dialog turn when in state: ");
            zZm2.append(this.uzr);
            zZm2.append(". Turn: ");
            zZm2.append(Qle());
            Log.e(str, zZm2.toString());
        }
    }

    public synchronized DialogRequestIdentifier zyO() {
        return this.zzR;
    }

    public synchronized cIy BIo() {
        return this.zyO.zyO();
    }

    public synchronized void zZm(fuM.zyO zyo) {
        if (this.dMe) {
            this.Qle.stopRecording();
            Future<?> future = this.HvC;
            if (future != null) {
                future.cancel(true);
            }
            this.zyO.zQM();
            this.dMe = false;
            this.BIo.zyO(new C0214kwy(zyo, this.zzR));
        }
    }

    public synchronized void zZm() {
        BIo(null);
    }

    public synchronized void zZm(UserPerceivedLatencyData userPerceivedLatencyData) {
        StringBuilder zZm2 = C0179Pya.zZm("setUserPerceivedLatencyData ");
        zZm2.append(userPerceivedLatencyData.getUserPerceivedLatency());
        zZm2.append(" for turn ");
        zZm2.append(Qle());
        zZm2.toString();
        AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback = this.JTe;
        if (alexaDialogTurnMetricsCallback != null) {
            alexaDialogTurnMetricsCallback.onUserPerceivedLatencyData(userPerceivedLatencyData);
            this.noQ = true;
        }
        this.vkx = false;
        if (this.wDP) {
            this.wDP = false;
            zZm(this.yPL.getAndSet(null));
        }
    }

    public final void zZm(@Nullable zQM zqm) {
        if (Mlj()) {
            zZm(fuM.zyO.OTHER);
            this.jiA.zZm(this.zQM);
            this.zyO.zQM();
        } else {
            this.jiA.BIo(this.zQM);
            zZm(fuM.zyO.OTHER);
            this.jiA.zZm(this.zQM);
            this.zyO.BIo();
        }
        this.LPk.shutdownNow();
        this.uzr = zZm.FINISHED;
        if (zqm != null) {
            NEe.zZm(((UjQ) zqm).zZm);
        }
        StringBuilder zZm2 = C0179Pya.zZm("turn finished: ");
        zZm2.append(Qle());
        zZm2.toString();
    }
}

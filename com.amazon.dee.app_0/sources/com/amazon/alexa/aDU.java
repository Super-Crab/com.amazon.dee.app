package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.utils.TimeProvider;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* compiled from: AudioSpeechInteraction.java */
/* loaded from: classes.dex */
public class aDU extends WvJ {
    public static final long lOf = TimeUnit.SECONDS.toMillis(2);
    public static final String zzR = "aDU";
    public final Map<bqj, MessageProcessingCallbacks> HvC;
    public final Object NXS;
    public final ILi Qgh;
    public final HashMap<cIy, wry> Tbw;
    public final Object XWf;
    public final Queue<bqj> dMe;
    public final TimeProvider noQ;
    public final dDK uzr;
    public final JTh vkx;
    public final Map<bqj, nLZ> wDP;

    public aDU(dDK ddk, AlexaClientEventBus alexaClientEventBus, QeM qeM, vkx vkxVar, shl shlVar, TimeProvider timeProvider, Wyh wyh, @Nullable NEe nEe, MessageMetadata messageMetadata, ILi iLi) {
        super(alexaClientEventBus, qeM, vkxVar, shlVar, wyh, nEe, messageMetadata);
        this.dMe = new LinkedList();
        this.Tbw = new HashMap<>();
        this.XWf = new Object();
        this.NXS = new Object();
        this.vkx = new JTh();
        this.uzr = ddk;
        this.HvC = new HashMap();
        this.wDP = new HashMap();
        this.noQ = timeProvider;
        this.Qgh = iLi;
        ((VIX) ddk.zZm).JTe();
    }

    @Override // com.amazon.alexa.WvJ, com.amazon.alexa.xkq
    public void BIo(bqj bqjVar) {
        this.Qle.BIo(bqjVar);
        this.BIo.zyO(psG.zZm(this.wDP.remove(bqjVar)));
        this.vkx.zZm();
        jiA(bqjVar);
    }

    public final void dMe() {
        synchronized (this.NXS) {
            dDK ddk = this.uzr;
            if (!ddk.BIo) {
                ((VIX) ddk.zZm).dMe();
                ddk.BIo = true;
            }
        }
    }

    @Override // com.amazon.alexa.zJO, com.amazon.alexa.jDH
    public void jiA() {
        synchronized (this.NXS) {
            synchronized (this.XWf) {
                bqj peek = this.dMe.peek();
                if (peek != null) {
                    this.wDP.get(peek).zyO();
                    this.uzr.zZm(peek, this);
                } else {
                    Log.e(zzR, "Could not play speech. Speech queue is empty");
                }
            }
        }
    }

    public final void lOf() {
        synchronized (this.NXS) {
            ((VIX) this.uzr.zZm).lOf();
            synchronized (this.XWf) {
                zZm((List) this.dMe);
                this.dMe.clear();
                JTe();
            }
        }
    }

    @Override // com.amazon.alexa.WvJ
    public boolean yPL() {
        boolean z;
        synchronized (this.XWf) {
            z = !this.dMe.isEmpty();
        }
        return z;
    }

    @Override // com.amazon.alexa.WvJ, com.amazon.alexa.xkq
    public void zQM(bqj bqjVar) {
        if (bqjVar.zQM()) {
            cIy zZm = bqjVar.zZm();
            if (!this.Tbw.containsKey(zZm)) {
                wry wryVar = new wry(this.uzr, this.BIo, this.Qgh, zZm);
                this.Tbw.put(zZm, wryVar);
                Log.i(wry.zZm, "startWakeWordTracker");
                wryVar.jiA.zZm(wryVar, wryVar.Qle);
                if (wryVar.yPL == null) {
                    wryVar.yPL = wryVar.BIo.scheduleAtFixedRate(wryVar, 0L, 100L, TimeUnit.MILLISECONDS);
                } else {
                    String str = wry.zZm;
                }
            } else {
                C0179Pya.zZm("Already tracking wakeword for attachment:", (Object) zZm);
            }
        }
        this.vkx.BIo();
        super.zQM(bqjVar);
        nLZ nlz = this.wDP.get(bqjVar);
        nlz.Qle();
        nlz.jiA();
        this.BIo.zyO(new C0240yDN(nlz));
        this.BIo.zyO(zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechStarted.zZm, bqjVar.mo947BIo()));
    }

    @Override // com.amazon.alexa.WvJ
    public void zZm(bqj bqjVar, MessageProcessingCallbacks messageProcessingCallbacks) {
        synchronized (this.XWf) {
            if (this.dMe.offer(bqjVar)) {
                this.wDP.put(bqjVar, new nLZ(bqjVar, 0L, this.noQ));
                this.HvC.put(bqjVar, messageProcessingCallbacks);
            }
        }
    }

    @Override // com.amazon.alexa.WvJ, com.amazon.alexa.zJO, com.amazon.alexa.jDH
    public void zyO() {
        Mlj();
    }

    public final void zzR() {
        synchronized (this.XWf) {
            for (MessageProcessingCallbacks messageProcessingCallbacks : this.HvC.values()) {
                messageProcessingCallbacks.onStopped();
            }
            this.HvC.clear();
        }
    }

    @Override // com.amazon.alexa.xkq
    public void zyO(bqj bqjVar) {
        this.Qle.zyO(bqjVar);
        this.BIo.zyO(zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechFinished.zZm, bqjVar.mo947BIo()));
        this.vkx.zZm();
        synchronized (this.NXS) {
            synchronized (this.XWf) {
                this.dMe.poll();
                this.BIo.zyO(psG.zZm(this.wDP.remove(bqjVar)));
                if (this.dMe.isEmpty()) {
                    zZm(false);
                } else {
                    bqj peek = this.dMe.peek();
                    this.wDP.get(peek).zyO();
                    this.uzr.zZm(peek, this);
                }
                if (this.HvC.containsKey(bqjVar)) {
                    this.HvC.remove(bqjVar).onFinished();
                }
            }
        }
        jiA(bqjVar);
    }

    @Override // com.amazon.alexa.WvJ
    public void zZm(boolean z) {
        StringBuilder zZm = C0179Pya.zZm("Stopping Interaction. Speech Finished? ");
        zZm.append(!z);
        zZm.toString();
        synchronized (this.NXS) {
            if (((VIX) this.uzr.zZm).yPL()) {
                dMe();
            }
            if (z) {
                zzR();
                this.vkx.zZm(lOf, TimeUnit.MILLISECONDS);
            }
            lOf();
        }
    }

    public final void jiA(bqj bqjVar) {
        cIy zZm;
        wry wryVar;
        if (!bqjVar.zQM() || (wryVar = this.Tbw.get((zZm = bqjVar.zZm()))) == null) {
            return;
        }
        Log.i(wry.zZm, "Stopping wakeword tracker");
        ScheduledFuture scheduledFuture = wryVar.yPL;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            wryVar.yPL = null;
        }
        ScheduledExecutorService scheduledExecutorService = wryVar.BIo;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
        wryVar.zyO();
        wryVar.jiA.BIo.remove(wryVar.Qle);
        this.Tbw.remove(zZm);
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar, Exception exc) {
        this.Qle.zZm(bqjVar, exc);
        synchronized (this.XWf) {
            for (MessageProcessingCallbacks messageProcessingCallbacks : this.HvC.values()) {
                messageProcessingCallbacks.onError();
            }
            this.HvC.clear();
            this.vkx.zZm();
            zZm(false);
        }
        jiA(bqjVar);
    }

    @Override // com.amazon.alexa.jDH
    public void zZm(IkF ikF) {
        synchronized (this.NXS) {
            ((VIX) this.uzr.zZm).zZm(ikF);
        }
    }

    @Override // com.amazon.alexa.WvJ, com.amazon.alexa.xkq
    public void zZm(bqj bqjVar) {
        this.wDP.get(bqjVar).zQM();
    }
}

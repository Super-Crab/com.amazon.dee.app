package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.MessageMetadata;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.utils.TimeProvider;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* compiled from: NoAudioSpeechInteraction.java */
/* loaded from: classes.dex */
public class Xew extends WvJ {
    public static final String zzR = "Xew";
    public volatile bqj HvC;
    public final Map<bqj, MessageProcessingCallbacks> dMe;
    public final Queue<bqj> lOf;
    public final ScheduledExecutorService noQ;
    public final Object uzr;
    public volatile ScheduledFuture<?> vkx;
    public final TimeProvider wDP;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NoAudioSpeechInteraction.java */
    /* loaded from: classes.dex */
    public class BIo implements Runnable {
        public final bqj zZm;

        public /* synthetic */ BIo(bqj bqjVar, CgP cgP) {
            this.zZm = bqjVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Xew.this.zQM(this.zZm);
            Xew xew = Xew.this;
            xew.zZm(this.zZm, xew.wDP.elapsedRealTime());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NoAudioSpeechInteraction.java */
    /* loaded from: classes.dex */
    public class zZm implements Runnable {
        public final bqj zZm;

        public /* synthetic */ zZm(bqj bqjVar, CgP cgP) {
            this.zZm = bqjVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Xew xew = Xew.this;
            xew.Qle.BIo(this.zZm);
            Xew.this.zyO(this.zZm);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Xew(AlexaClientEventBus alexaClientEventBus, vkx vkxVar, QeM qeM, shl shlVar, TimeProvider timeProvider, Wyh wyh, @Nullable NEe nEe, MessageMetadata messageMetadata) {
        super(alexaClientEventBus, qeM, vkxVar, shlVar, wyh, nEe, messageMetadata);
        ScheduledExecutorService newSingleThreadScheduledExecutor = ExecutorFactory.newSingleThreadScheduledExecutor("NoAudioSpeechInteraction");
        this.lOf = new LinkedList();
        this.uzr = new Object();
        this.dMe = new HashMap();
        this.wDP = timeProvider;
        this.noQ = newSingleThreadScheduledExecutor;
    }

    public final void lOf() {
        synchronized (this.uzr) {
            zZm((List) this.lOf);
            this.lOf.clear();
            JTe();
        }
    }

    @Override // com.amazon.alexa.WvJ
    public boolean yPL() {
        boolean z;
        synchronized (this.uzr) {
            z = !this.lOf.isEmpty();
        }
        return z;
    }

    @Override // com.amazon.alexa.WvJ, com.amazon.alexa.xkq
    public void zQM(bqj bqjVar) {
        super.zQM(bqjVar);
        this.BIo.zyO(zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechStarted.zZm, bqjVar.mo947BIo()));
        ScheduledExecutorService scheduledExecutorService = this.noQ;
        zZm zzm = new zZm(bqjVar, null);
        String str = bqjVar.yPL;
        this.vkx = scheduledExecutorService.schedule(zzm, Math.min(Math.max(str != null ? str.trim().length() * 80 : 0L, 1000L), 50000L), TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.alexa.xkq
    public void zyO(bqj bqjVar) {
        this.Qle.zyO(bqjVar);
        this.vkx = null;
        this.BIo.zyO(zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechFinished.zZm, bqjVar.mo947BIo()));
        synchronized (this.uzr) {
            this.lOf.poll();
            if (this.lOf.isEmpty()) {
                zZm(false);
            } else {
                bqj peek = this.lOf.peek();
                this.HvC = peek;
                this.noQ.execute(new BIo(peek, null));
            }
            if (this.dMe.containsKey(bqjVar)) {
                this.dMe.remove(bqjVar).onFinished();
            }
        }
    }

    public final void zzR() {
        synchronized (this.uzr) {
            for (MessageProcessingCallbacks messageProcessingCallbacks : this.dMe.values()) {
                messageProcessingCallbacks.onStopped();
            }
            this.dMe.clear();
        }
    }

    @Override // com.amazon.alexa.WvJ
    public void zZm(bqj bqjVar, MessageProcessingCallbacks messageProcessingCallbacks) {
        synchronized (this.uzr) {
            if (this.lOf.offer(bqjVar)) {
                this.dMe.put(bqjVar, messageProcessingCallbacks);
            }
            bqj peek = this.lOf.peek();
            if (peek != null && this.HvC == null) {
                this.HvC = peek;
                this.noQ.execute(new BIo(peek, null));
            } else {
                Log.e(zzR, "Could not start speech metrics. Speech queue is empty");
            }
        }
    }

    @Override // com.amazon.alexa.xkq
    public void zZm(bqj bqjVar, Exception exc) {
        this.Qle.zZm(bqjVar, exc);
        synchronized (this.uzr) {
            for (MessageProcessingCallbacks messageProcessingCallbacks : this.dMe.values()) {
                messageProcessingCallbacks.onError();
            }
            this.dMe.clear();
            zZm(false);
        }
    }

    @Override // com.amazon.alexa.WvJ
    public void zZm(boolean z) {
        StringBuilder zZm2 = C0179Pya.zZm("Stopping Interaction. Speech Finished? ");
        zZm2.append(!z);
        zZm2.toString();
        if (this.vkx != null && !this.vkx.isCancelled()) {
            ScheduledFuture<?> scheduledFuture = this.vkx;
            this.vkx = null;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(true);
            }
        }
        if (z) {
            zzR();
        }
        lOf();
    }
}

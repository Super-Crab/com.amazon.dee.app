package com.amazon.alexa;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: TextResponseSpeechStateTracker.java */
@Singleton
/* loaded from: classes.dex */
public class tAW {
    @VisibleForTesting
    public static final PJz BIo = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.NO_AUDIOFOCUS);
    public static final String zZm = "tAW";
    public ScheduledFuture<?> JTe;
    public jiA LPk;
    public final Map<MessageIdentifier, Message> Qle;
    public final ScheduledExecutorService jiA;
    public final AlexaClientEventBus zQM;
    public final QeM zyO;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextResponseSpeechStateTracker.java */
    /* loaded from: classes.dex */
    public class BIo implements Runnable {
        public final MessageProcessingCallbacks BIo;
        public final Message zZm;

        public /* synthetic */ BIo(Message message, MessageProcessingCallbacks messageProcessingCallbacks, HjI hjI) {
            this.zZm = message;
            this.BIo = messageProcessingCallbacks;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = tAW.zZm;
            Message message = this.zZm;
            MessageProcessingCallbacks messageProcessingCallbacks = this.BIo;
            UqQ uqQ = (UqQ) message.getPayload();
            Lzb lzb = new Lzb(uqQ.BIo, message.getMessageMetadata(), bqj.zZm(uqQ.BIo, uqQ.zZm, message.getDialogRequestIdentifier(), uqQ.zyO), message.getMessageIdentifier(), messageProcessingCallbacks);
            tAW.this.Qle.put(this.zZm.getMessageIdentifier(), this.zZm);
            if (tAW.this.LPk == null) {
                tAW.this.zZm(this.zZm);
            }
            tAW.BIo(tAW.this, lzb);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextResponseSpeechStateTracker.java */
    /* loaded from: classes.dex */
    public static class jiA extends jDH {
        public /* synthetic */ jiA(HjI hjI) {
        }

        @Override // com.amazon.alexa.ndD
        public dnp BIo() {
            return AvsApiConstants.SpeechSynthesizer.zQM;
        }

        @Override // com.amazon.alexa.jDH
        public void Qle() {
        }

        @Override // com.amazon.alexa.jDH
        public void jiA() {
        }

        @Override // com.amazon.alexa.ndD
        public void zQM() {
        }

        @Override // com.amazon.alexa.jDH
        public void zyO() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextResponseSpeechStateTracker.java */
    /* loaded from: classes.dex */
    public class zQM implements Runnable {
        public final Lzb zZm;

        public /* synthetic */ zQM(Lzb lzb, HjI hjI) {
            this.zZm = lzb;
        }

        @Override // java.lang.Runnable
        public void run() {
            tAW.zZm(tAW.this, this.zZm);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextResponseSpeechStateTracker.java */
    /* loaded from: classes.dex */
    public class zZm implements Runnable {
        public final MessageIdentifier zZm;

        public /* synthetic */ zZm(MessageIdentifier messageIdentifier, HjI hjI) {
            this.zZm = messageIdentifier;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = tAW.zZm;
            StringBuilder zZm = C0179Pya.zZm("Cancelling message: ");
            zZm.append(this.zZm);
            zZm.toString();
            if (tAW.this.Qle.containsKey(this.zZm)) {
                tAW taw = tAW.this;
                taw.jiA.execute(new zyO(null));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextResponseSpeechStateTracker.java */
    /* loaded from: classes.dex */
    public class zyO implements Runnable {
        public /* synthetic */ zyO(HjI hjI) {
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = tAW.zZm;
            tAW.zZm(tAW.this);
            tAW.this.Qle.clear();
            tAW.this.zZm();
        }
    }

    @Inject
    public tAW(AlexaClientEventBus alexaClientEventBus, QeM qeM) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = ExecutorFactory.newSingleThreadScheduledExecutor("TextResponseSpeechStateTracker");
        this.zQM = alexaClientEventBus;
        this.zyO = qeM;
        this.jiA = newSingleThreadScheduledExecutor;
        this.Qle = new HashMap();
    }

    public static /* synthetic */ void BIo(tAW taw, Lzb lzb) {
        taw.zyO.zQM(lzb.BIo());
        C0179Pya.zZm("Send speech started event ", (Object) lzb);
        taw.zQM.zyO(taw.zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechStarted.zZm, lzb));
        taw.JTe = taw.jiA.schedule(new zQM(lzb, null), 100L, TimeUnit.MILLISECONDS);
    }

    public static /* synthetic */ void zZm(tAW taw, Lzb lzb) {
        if (taw.Qle.containsKey(lzb.zZm())) {
            taw.Qle.remove(lzb.zZm());
            taw.zyO.zyO(lzb.BIo());
            C0179Pya.zZm("Sending speech finished event: ", (Object) lzb);
            taw.zQM.zyO(taw.zZm(AvsApiConstants.SpeechSynthesizer.Events.SpeechFinished.zZm, lzb));
            if (taw.Qle.isEmpty()) {
                taw.zZm();
            }
            lzb.jiA.onFinished();
        }
    }

    public static /* synthetic */ void zZm(tAW taw) {
        ScheduledFuture<?> scheduledFuture = taw.JTe;
        if (scheduledFuture != null) {
            taw.JTe = null;
            scheduledFuture.cancel(true);
        }
    }

    @VisibleForTesting
    public JjI zZm(Name name, Lzb lzb) {
        return JjI.BIo().zZm(Message.create(Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setName(name).setNamespace(AvsApiConstants.SpeechSynthesizer.zZm).build(), yXU.zZm(lzb.zZm), lzb.BIo)).zZm();
    }

    public final void zZm(Message message) {
        this.LPk = new jiA(null);
        this.zQM.zyO(mZe.zZm(aVo.DIALOG, this.LPk, BIo, message.getOriginatingDialogRequestIdentifier()));
    }

    public final void zZm() {
        jiA jia = this.LPk;
        if (jia != null) {
            this.zQM.zyO(LBB.zZm(jia.zZm));
            this.LPk = null;
        }
    }
}

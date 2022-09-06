package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.api.AlexaCaptionListener;
import com.amazon.alexa.api.CaptionResponse;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: SpeechSynthesizerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class fjm extends BaseCapabilityAgent {
    public static final String zZm = "fjm";
    public final dDK BIo;
    public final AlexaClientEventBus JTe;
    public final jSO LPk;
    public final QeM Mlj;
    public final Map<MessageIdentifier, Message> Qle;
    public lUQ dMe;
    public final vkx jiA;
    public WvJ lOf;
    public boolean uzr;
    public final Wyh yPL;
    public final iHK zQM;
    public final yqV zyO;
    public final Shr<AlexaCaptionListener> zzR;

    @Inject
    public fjm(dDK ddk, AlexaClientEventBus alexaClientEventBus, iHK ihk, yqV yqv, vkx vkxVar, QeM qeM, jSO jso, Wyh wyh) {
        super(Capability.create(AvsApiConstants.SpeechSynthesizer.BIo, "1.0"));
        this.BIo = ddk;
        this.JTe = alexaClientEventBus;
        this.zQM = ihk;
        this.zyO = yqv;
        this.LPk = jso;
        this.yPL = wyh;
        this.jiA = vkxVar;
        this.Mlj = qeM;
        this.Qle = new HashMap();
        this.uzr = false;
        this.zzR = new Shr<>();
        alexaClientEventBus.zZm(this);
    }

    public final boolean BIo() {
        WvJ wvJ = this.lOf;
        if (wvJ != null) {
            wvJ.Mlj();
            this.lOf = null;
            return true;
        }
        return false;
    }

    @Subscribe
    public synchronized void on(xZV xzv) {
        this.zzR.BIo(((uyC) xzv).BIo);
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        MessageIdentifier messageIdentifier = message.getMessageIdentifier();
        C0179Pya.zZm("Cancelling message: ", (Object) messageIdentifier);
        if (this.Qle.containsKey(messageIdentifier)) {
            this.Qle.clear();
            if (!BIo()) {
                Log.i(zZm, "Could not cancel message. SpeechInteraction was null");
            }
            lUQ luq = this.dMe;
            if (luq != null) {
                luq.yPL();
                this.dMe = null;
            }
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        C0179Pya.zZm("Preprocessing message: ", (Object) message);
        if (AvsApiConstants.SpeechSynthesizer.Directives.Speak.zZm.equals(message.getHeader().getName())) {
            WvJ wvJ = this.lOf;
            if (wvJ == null || !wvJ.yPL()) {
                DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
                boolean zZm2 = zZm(dialogRequestIdentifier);
                if (this.LPk.BIo(dialogRequestIdentifier) && this.dMe == null && !zZm2) {
                    this.jiA.zZm(wSq.PREPARING_TO_SPEAK);
                    this.dMe = new lUQ(this.JTe, this.jiA);
                    this.JTe.zyO(mZe.zZm(aVo.DIALOG, this.dMe, this.zyO.zZm(), message.getOriginatingDialogRequestIdentifier()));
                }
                this.lOf = this.zQM.zZm(this.BIo, this.JTe, this.Mlj, message.getMessageMetadata(), zZm2);
                this.jiA.zZm(wSq.SPEAKING);
                this.uzr = false;
            }
            this.Qle.put(message.getMessageIdentifier(), message);
            DEe dEe = (DEe) message.getPayload();
            message.getDialogRequestIdentifier();
            UqQ uqQ = (UqQ) dEe;
            CaptionResponse captionResponse = new CaptionResponse(uqQ.zyO, CaptionResponse.CaptionFormat.RAW);
            Iterator<AlexaCaptionListener> it2 = this.zzR.iterator();
            while (it2.hasNext()) {
                it2.next().onReceivedCaption(captionResponse);
            }
            C0179Pya.zZm("Received speak for payload: ", (Object) dEe);
            this.lOf.zZm(bqj.zZm(uqQ.BIo, uqQ.zZm, message.getDialogRequestIdentifier(), uqQ.zyO), messageProcessingCallbacks);
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public synchronized void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        MessageIdentifier messageIdentifier = message.getMessageIdentifier();
        C0179Pya.zZm("Processing message: ", (Object) messageIdentifier);
        if (this.Qle.containsKey(messageIdentifier)) {
            if (this.lOf != null && !this.uzr) {
                yqV yqv = this.zyO;
                NEe zZm2 = this.yPL.zZm(message.getDialogRequestIdentifier());
                this.JTe.zyO(mZe.zZm(aVo.DIALOG, this.lOf, yqv.zZm(zZm2 != null ? zZm2.jiA().suppressSpeechResponse() : false), message.getOriginatingDialogRequestIdentifier()));
                lUQ luq = this.dMe;
                if (luq != null) {
                    luq.yPL();
                    this.dMe = null;
                }
                this.uzr = true;
            } else if (this.lOf == null) {
                throw new IllegalStateException("Speech interaction should never be null when process() is called");
            }
        } else {
            String str = zZm;
            Log.e(str, "Message " + messageIdentifier + " is not known and cannot be processed");
        }
    }

    public synchronized void zQM() {
        BIo();
        ((VIX) this.BIo.zZm).lOf();
    }

    public synchronized void zZm(ExtendedClient extendedClient, AlexaCaptionListener alexaCaptionListener) {
        this.zzR.zZm(extendedClient, alexaCaptionListener);
    }

    public synchronized void zZm(AlexaCaptionListener alexaCaptionListener) {
        this.zzR.remove(alexaCaptionListener);
    }

    public synchronized void zZm() {
        BIo();
        this.Mlj.BIo();
    }

    public final boolean zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        NEe zZm2 = this.yPL.zZm(dialogRequestIdentifier);
        if (zZm2 != null) {
            return zZm2.jiA().suppressSpeechResponse();
        }
        return false;
    }
}

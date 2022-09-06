package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.EnumSet;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: InteractionModelCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class zMV extends BaseCapabilityAgent {
    public static final EnumSet<wSq> BIo = EnumSet.of(wSq.LISTENING, wSq.THINKING);
    public static final String zZm = "zMV";
    public final vkx jiA;
    public final jSO zQM;
    public final AlexaClientEventBus zyO;

    @Inject
    public zMV(jSO jso, AlexaClientEventBus alexaClientEventBus, vkx vkxVar) {
        super(Capability.create(AvsApiConstants.InteractionModel.BIo, "1.1"));
        this.zQM = jso;
        this.zyO = alexaClientEventBus;
        this.jiA = vkxVar;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onCancel(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (AvsApiConstants.InteractionModel.Directives.RequestProcessingStarted.zZm.equals(message.getHeader().getName())) {
            this.zyO.zyO(new ISm(message.getDialogRequestIdentifier()));
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onPreprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (AvsApiConstants.InteractionModel.Directives.RequestProcessingStarted.zZm.equals(message.getHeader().getName())) {
            if (!zZm(message, messageProcessingCallbacks)) {
                String str = zZm;
                Log.e(str, "Invalid Message: " + message);
            } else {
                DialogRequestIdentifier dialogRequestIdentifier = message.getDialogRequestIdentifier();
                wSq zyO = this.jiA.zyO();
                if (!BIo.contains(zyO)) {
                    String str2 = zZm;
                    Log.w(str2, "Skipping entering Request Processing due to invalid state: " + zyO);
                } else {
                    PJz zZm2 = PJz.zZm(PJz.zQM.MUSIC, PJz.BIo.TRANSIENT_EXCLUSIVE);
                    this.zyO.zyO(mZe.zZm(aVo.DIALOG, new TIo(this.zyO, this.jiA, dialogRequestIdentifier), zZm2, dialogRequestIdentifier));
                    this.jiA.zZm(wSq.REQUEST_PROCESSING);
                }
                this.zyO.zyO(new VTh(dialogRequestIdentifier));
            }
            messageProcessingCallbacks.onFinished();
        }
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        Name name = message.getHeader().getName();
        if (AvsApiConstants.InteractionModel.Directives.NewDialogRequest.zZm.equals(name)) {
            DialogRequestIdentifier dialogRequestIdentifier = ((xfe) message.getPayload()).zZm;
            if (dialogRequestIdentifier != null && !DialogRequestIdentifier.NONE.equals(dialogRequestIdentifier)) {
                this.zQM.zZm(dialogRequestIdentifier);
            }
        } else if (AvsApiConstants.InteractionModel.Directives.RequestProcessingCompleted.zZm.equals(name)) {
            if (!zZm(message, messageProcessingCallbacks)) {
                String str = zZm;
                Log.e(str, "Invalid Message: " + message);
            } else {
                this.zyO.zyO(BQL.zZm(message.getDialogRequestIdentifier()));
            }
        } else {
            messageProcessingCallbacks.onError();
            return;
        }
        messageProcessingCallbacks.onFinished();
    }

    public final boolean zZm(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (!message.hasDialogRequestIdentifier()) {
            messageProcessingCallbacks.onError();
            return false;
        }
        return true;
    }
}

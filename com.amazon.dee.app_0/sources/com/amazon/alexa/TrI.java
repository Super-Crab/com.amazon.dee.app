package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: ApplicationManagerCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class TrI extends BaseCapabilityAgent {
    public final vYS zZm;

    @Inject
    public TrI(vYS vys) {
        super(new Capability[0]);
        this.zZm = vys;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.BaseCapabilityAgent
    public void onProcess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (AvsApiConstants.ApplicationManager.Directives.Navigation.zZm.equals(message.getHeader().getName())) {
            this.zZm.zZm(((zEh) message.getPayload()).BIo);
            messageProcessingCallbacks.onFinished();
            return;
        }
        messageProcessingCallbacks.onError();
    }
}

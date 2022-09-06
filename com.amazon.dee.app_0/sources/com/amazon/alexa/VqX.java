package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.Collections;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: UnsupportedMessageCapabilityAgent.java */
@Singleton
/* loaded from: classes.dex */
public class VqX implements CapabilityAgent {
    public static final String zZm = "VqX";
    public final AlexaClientEventBus BIo;

    @Inject
    public VqX(AlexaClientEventBus alexaClientEventBus) {
        this.BIo = alexaClientEventBus;
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void cancel(MessageIdentifier messageIdentifier) {
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public Set<Capability> getCapabilities() {
        return Collections.emptySet();
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void preprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        String unparsedMessage = message.getMessageMetadata().getUnparsedMessage();
        Log.e(zZm, "Received an unsupported message");
        this.BIo.zyO(TTH.zZm((String) null, unparsedMessage));
        messageProcessingCallbacks.onFinished();
    }

    @Override // com.amazon.alexa.client.core.capabilities.agents.CapabilityAgent
    public void process(MessageIdentifier messageIdentifier) {
    }
}

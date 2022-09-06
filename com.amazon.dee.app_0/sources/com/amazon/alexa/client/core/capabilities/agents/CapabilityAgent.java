package com.amazon.alexa.client.core.capabilities.agents;

import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import java.util.Set;
/* loaded from: classes6.dex */
public interface CapabilityAgent {
    void cancel(MessageIdentifier messageIdentifier);

    Set<Capability> getCapabilities();

    void preprocess(Message message, MessageProcessingCallbacks messageProcessingCallbacks);

    void process(MessageIdentifier messageIdentifier);
}

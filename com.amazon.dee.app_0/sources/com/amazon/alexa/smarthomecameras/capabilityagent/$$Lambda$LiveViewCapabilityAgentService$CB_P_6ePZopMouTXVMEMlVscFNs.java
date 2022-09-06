package com.amazon.alexa.smarthomecameras.capabilityagent;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.smarthomecameras.capabilityagent.-$$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs implements MessageFilter {
    public static final /* synthetic */ $$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs INSTANCE = new $$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs();

    private /* synthetic */ $$Lambda$LiveViewCapabilityAgentService$CB_P_6ePZopMouTXVMEMlVscFNs() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = LiveViewCapabilityAgentConstants.LIVE_VIEW_CONTROLLER_EVENT.equals(message.getEventType());
        return equals;
    }
}

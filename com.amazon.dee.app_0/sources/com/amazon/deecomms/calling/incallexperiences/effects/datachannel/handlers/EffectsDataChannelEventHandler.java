package com.amazon.deecomms.calling.incallexperiences.effects.datachannel.handlers;

import com.amazon.deecomms.calling.datachannel.CommsDataChannelEvent;
import com.amazon.deecomms.calling.incallexperiences.effects.datachannel.listeners.EffectsDataChannelEventListener;
/* loaded from: classes12.dex */
public interface EffectsDataChannelEventHandler {
    void apply(String str);

    boolean processAckEvent(CommsDataChannelEvent commsDataChannelEvent);

    void processBeginEffectsSessionEvent(CommsDataChannelEvent commsDataChannelEvent);

    void processCurrentStatusEvent(CommsDataChannelEvent commsDataChannelEvent);

    void registerEffectsDataChannelEventListener(EffectsDataChannelEventListener effectsDataChannelEventListener);

    void remove(String str);

    void sendMetricEvent(String str, Long l, String str2);

    void unregisterEffectsDataChannelEventListener();
}

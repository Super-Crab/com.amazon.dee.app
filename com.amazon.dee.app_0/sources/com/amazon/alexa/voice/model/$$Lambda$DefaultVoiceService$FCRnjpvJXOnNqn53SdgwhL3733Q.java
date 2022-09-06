package com.amazon.alexa.voice.model;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.model.-$$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q implements MessageFilter {
    public static final /* synthetic */ $$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q INSTANCE = new $$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q();

    private /* synthetic */ $$Lambda$DefaultVoiceService$FCRnjpvJXOnNqn53SdgwhL3733Q() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return "vox::stop".equals(message.getEventType());
    }
}

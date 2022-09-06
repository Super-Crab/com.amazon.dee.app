package com.amazon.alexa.voice.wakeword;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.voice.wakeword.-$$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA  reason: invalid class name */
/* loaded from: classes11.dex */
public final /* synthetic */ class $$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA implements MessageFilter {
    public static final /* synthetic */ $$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA INSTANCE = new $$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA();

    private /* synthetic */ $$Lambda$WakeWordEventHandler$jVD3krKsm2pmklVzvTVj57pc7nA() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return message.getEventType().contentEquals("voice::wakewordSuppression");
    }
}

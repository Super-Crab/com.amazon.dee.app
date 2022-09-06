package com.amazon.alexa.sharing.media.transmitter;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.sharing.media.transmitter.-$$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI implements MessageFilter {
    public static final /* synthetic */ $$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI INSTANCE = new $$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI();

    private /* synthetic */ $$Lambda$FileTransmitter$B3PvRSun7znJUfZnR0382altvWI() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}

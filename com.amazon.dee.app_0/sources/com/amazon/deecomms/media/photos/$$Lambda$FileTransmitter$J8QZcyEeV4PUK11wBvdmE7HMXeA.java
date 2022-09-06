package com.amazon.deecomms.media.photos;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.media.photos.-$$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA implements MessageFilter {
    public static final /* synthetic */ $$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA INSTANCE = new $$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA();

    private /* synthetic */ $$Lambda$FileTransmitter$J8QZcyEeV4PUK11wBvdmE7HMXeA() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}

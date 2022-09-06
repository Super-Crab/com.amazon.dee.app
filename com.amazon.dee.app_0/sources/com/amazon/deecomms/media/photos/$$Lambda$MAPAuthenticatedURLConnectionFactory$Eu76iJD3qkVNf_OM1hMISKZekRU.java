package com.amazon.deecomms.media.photos;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.media.photos.-$$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU implements MessageFilter {
    public static final /* synthetic */ $$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU INSTANCE = new $$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU();

    private /* synthetic */ $$Lambda$MAPAuthenticatedURLConnectionFactory$Eu76iJD3qkVNf_OM1hMISKZekRU() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        return IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
    }
}

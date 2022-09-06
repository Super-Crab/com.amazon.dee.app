package com.amazon.deecomms.media.photos;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.media.photos.-$$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0 implements MessageFilter {
    public static final /* synthetic */ $$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0 INSTANCE = new $$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0();

    private /* synthetic */ $$Lambda$SharedPreferenceEndpointsCache$03xjREqgdL7fcg04mebtGwhu7c0() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}

package com.amazon.alexa.sharing.media.clouddrive;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.sharing.media.clouddrive.-$$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws implements MessageFilter {
    public static final /* synthetic */ $$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws INSTANCE = new $$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws();

    private /* synthetic */ $$Lambda$SharedPreferenceEndpointsCache$rhf5VOQF1Xq_kYRTLbIooeGvsws() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = IdentityEvent.IDENTITY_CHANGED.equals(message.getEventType());
        return equals;
    }
}

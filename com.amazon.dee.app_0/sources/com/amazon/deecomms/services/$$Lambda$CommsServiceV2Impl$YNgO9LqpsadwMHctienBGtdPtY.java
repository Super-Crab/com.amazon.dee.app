package com.amazon.deecomms.services;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
import com.amazon.deecomms.common.EventBusEventType;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.services.-$$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPt-Y  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPtY implements MessageFilter {
    public static final /* synthetic */ $$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPtY INSTANCE = new $$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPtY();

    private /* synthetic */ $$Lambda$CommsServiceV2Impl$YNgO9LqpsadwMHctienBGtdPtY() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = EventBusEventType.REMOVE_EXPIRED_MEDIA_FROM_CACHE.toString().equals(message.getEventType());
        return equals;
    }
}

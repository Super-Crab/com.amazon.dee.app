package com.amazon.alexa.location;

import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageFilter;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y implements MessageFilter {
    public static final /* synthetic */ $$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y INSTANCE = new $$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y();

    private /* synthetic */ $$Lambda$GeofenceEventHandler$5OGCF1BaD5jeo58MTwxPfJ63n6Y() {
    }

    @Override // com.amazon.alexa.eventbus.api.MessageFilter
    public final boolean isMatch(Message message) {
        boolean equals;
        equals = "geofence::sync".equals(message.getEventType());
        return equals;
    }
}

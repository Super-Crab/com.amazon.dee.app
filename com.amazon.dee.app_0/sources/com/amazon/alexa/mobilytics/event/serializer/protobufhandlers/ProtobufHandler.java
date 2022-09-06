package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
/* loaded from: classes9.dex */
public interface ProtobufHandler {
    @Nullable
    MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent);
}

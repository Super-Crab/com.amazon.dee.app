package com.amazon.alexa.mobilytics.event.serializer;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
/* loaded from: classes9.dex */
public interface ProtobufSerializer {
    MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent);

    MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull ProtobufHandler... protobufHandlerArr);
}

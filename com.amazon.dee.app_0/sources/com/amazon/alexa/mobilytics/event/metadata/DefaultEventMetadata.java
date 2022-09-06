package com.amazon.alexa.mobilytics.event.metadata;

import androidx.annotation.NonNull;
import com.amazon.alexa.mobilytics.protobuf.EventDetailsProto;
/* loaded from: classes9.dex */
public interface DefaultEventMetadata extends EventMetadata {
    @NonNull
    EventDetailsProto.Metadata serialize();
}

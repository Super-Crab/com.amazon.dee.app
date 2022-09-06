package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.protobuf.EventProto;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.util.Utils;
import com.google.common.base.Preconditions;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class EventProtobufHandler implements ProtobufHandler {
    private final MobilyticsConfiguration mobilyticsConfiguration;
    private final String serviceName;

    @Inject
    public EventProtobufHandler(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        this.mobilyticsConfiguration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mobilyticsConfiguration.serviceName());
        if (this.mobilyticsConfiguration.isDebug()) {
            sb.append("_debug");
        }
        this.serviceName = sb.toString();
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        EventProto.Builder newBuilder = EventProto.newBuilder();
        newBuilder.setEventId(UUID.randomUUID().toString()).setSourceName(this.serviceName).setEventType(mobilyticsEvent.getEventType()).setEventTimestamp(mobilyticsEvent.getEventTimestamp()).setEventDetails(((DefaultMobilyticsEvent) mobilyticsEvent).serialize()).setApplicationForegrounded(Utils.isAppOnForeground(this.mobilyticsConfiguration.context()));
        return MobilyticsMessageProto.newBuilder().setEvent(newBuilder).mo10084build();
    }
}

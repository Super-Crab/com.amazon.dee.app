package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface EventProtoOrBuilder extends MessageOrBuilder {
    boolean getApplicationForegrounded();

    String getDebugUrl();

    ByteString getDebugUrlBytes();

    EventDetailsProto getEventDetails();

    EventDetailsProtoOrBuilder getEventDetailsOrBuilder();

    String getEventId();

    ByteString getEventIdBytes();

    long getEventTimestamp();

    String getEventType();

    ByteString getEventTypeBytes();

    String getSourceName();

    ByteString getSourceNameBytes();

    boolean hasEventDetails();
}

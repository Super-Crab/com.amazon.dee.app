package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface SessionProtoOrBuilder extends MessageOrBuilder {
    long getDuration();

    String getSessionId();

    ByteString getSessionIdBytes();

    long getStartTimestamp();

    long getStopTimestamp();
}

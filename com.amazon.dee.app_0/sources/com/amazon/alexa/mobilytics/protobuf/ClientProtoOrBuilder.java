package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface ClientProtoOrBuilder extends MessageOrBuilder {
    String getClientId();

    ByteString getClientIdBytes();

    String getCognitoId();

    ByteString getCognitoIdBytes();
}

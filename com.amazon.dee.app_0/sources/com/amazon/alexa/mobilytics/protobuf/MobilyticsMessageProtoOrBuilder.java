package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface MobilyticsMessageProtoOrBuilder extends MessageOrBuilder {
    ApplicationProto getApplication();

    ApplicationProtoOrBuilder getApplicationOrBuilder();

    ClientProto getClient();

    ClientProtoOrBuilder getClientOrBuilder();

    CustomerProto getCustomer();

    CustomerProtoOrBuilder getCustomerOrBuilder();

    DeviceProto getDevice();

    DeviceProtoOrBuilder getDeviceOrBuilder();

    EventProto getEvent();

    EventProtoOrBuilder getEventOrBuilder();

    String getPivots();

    ByteString getPivotsBytes();

    SessionProto getSession();

    SessionProtoOrBuilder getSessionOrBuilder();

    boolean hasApplication();

    boolean hasClient();

    boolean hasCustomer();

    boolean hasDevice();

    boolean hasEvent();

    boolean hasSession();
}

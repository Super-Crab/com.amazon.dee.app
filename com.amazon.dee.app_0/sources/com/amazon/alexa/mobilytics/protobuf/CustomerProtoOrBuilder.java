package com.amazon.alexa.mobilytics.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface CustomerProtoOrBuilder extends MessageOrBuilder {
    String getCountryOfResidence();

    ByteString getCountryOfResidenceBytes();

    String getDirectedId();

    ByteString getDirectedIdBytes();

    String getHashedCommsId();

    ByteString getHashedCommsIdBytes();

    String getHouseholdId();

    ByteString getHouseholdIdBytes();

    String getMarketplaceId();

    ByteString getMarketplaceIdBytes();

    String getPersonId();

    ByteString getPersonIdBytes();

    String getPersonIdv2();

    ByteString getPersonIdv2Bytes();
}

package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.alexa.mobilytics.protobuf.ApplicationProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface ApplicationProtoOrBuilder extends MessageOrBuilder {
    String getAppId();

    ByteString getAppIdBytes();

    String getCognitoIdentityPoolId();

    ByteString getCognitoIdentityPoolIdBytes();

    String getPackageName();

    ByteString getPackageNameBytes();

    ApplicationProto.Sdk getSdk();

    ApplicationProto.SdkOrBuilder getSdkOrBuilder();

    String getTitle();

    ByteString getTitleBytes();

    String getVersionCode();

    ByteString getVersionCodeBytes();

    String getVersionName();

    ByteString getVersionNameBytes();

    boolean hasSdk();
}

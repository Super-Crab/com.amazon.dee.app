package com.amazon.alexa.mobilytics.protobuf;

import com.amazon.alexa.mobilytics.protobuf.DeviceProto;
import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;
/* loaded from: classes9.dex */
public interface DeviceProtoOrBuilder extends MessageOrBuilder {
    String getCarrier();

    ByteString getCarrierBytes();

    String getDeviceId();

    ByteString getDeviceIdBytes();

    String getDeviceType();

    ByteString getDeviceTypeBytes();

    DeviceProto.Locale getLocale();

    DeviceProto.LocaleOrBuilder getLocaleOrBuilder();

    String getMake();

    ByteString getMakeBytes();

    String getModel();

    ByteString getModelBytes();

    String getNetworkType();

    ByteString getNetworkTypeBytes();

    DeviceProto.Platform getPlatform();

    DeviceProto.PlatformOrBuilder getPlatformOrBuilder();

    DeviceProto.Screen getScreen();

    DeviceProto.ScreenOrBuilder getScreenOrBuilder();

    String getTimezone();

    ByteString getTimezoneBytes();

    boolean hasLocale();

    boolean hasPlatform();

    boolean hasScreen();
}

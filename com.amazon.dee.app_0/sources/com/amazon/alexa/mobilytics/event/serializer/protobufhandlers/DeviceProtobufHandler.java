package com.amazon.alexa.mobilytics.event.serializer.protobufhandlers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.DeviceConfiguration;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.protobuf.DeviceProto;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.util.Log;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes9.dex */
public class DeviceProtobufHandler implements ProtobufHandler {
    private static final String TAG = Log.tag(DeviceProtobufHandler.class);
    private final DeviceConfiguration deviceConfiguration;

    @Inject
    public DeviceProtobufHandler(@NonNull DeviceConfiguration deviceConfiguration) {
        this.deviceConfiguration = (DeviceConfiguration) Preconditions.checkNotNull(deviceConfiguration);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
    @Nullable
    public MobilyticsMessageProto serialize(@NonNull MobilyticsEvent mobilyticsEvent) {
        DeviceProto.Builder newBuilder = DeviceProto.newBuilder();
        if (this.deviceConfiguration.deviceSerialNumber() != null) {
            newBuilder.setDeviceId(this.deviceConfiguration.deviceSerialNumber());
        }
        if (this.deviceConfiguration.deviceType() != null) {
            newBuilder.setDeviceType(this.deviceConfiguration.deviceType());
        }
        if (this.deviceConfiguration.timezone() != null) {
            newBuilder.setTimezone(this.deviceConfiguration.timezone());
        }
        newBuilder.setPlatform(DeviceProto.Platform.newBuilder().setName(this.deviceConfiguration.operatingSystemType()).setVersion(this.deviceConfiguration.operatingSystemVersion()));
        newBuilder.setMake(this.deviceConfiguration.manufacturer()).setModel(this.deviceConfiguration.model()).setScreen(DeviceProto.Screen.newBuilder().setScreenWidth(this.deviceConfiguration.screenWidth()).setScreenHeight(this.deviceConfiguration.screenHeight()).setScreenDensity(this.deviceConfiguration.screenDensity())).setNetworkType(this.deviceConfiguration.networkType()).setLocale(DeviceProto.Locale.newBuilder().setCountry(this.deviceConfiguration.country()).setLanguage(this.deviceConfiguration.language()));
        return MobilyticsMessageProto.newBuilder().setDevice(newBuilder).mo10084build();
    }
}

package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AutoValue_DeviceInfo;
import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes.dex */
public abstract class DeviceInfo {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract DeviceInfo build();

        public abstract Builder setDeviceSerialNumber(String str);

        public abstract Builder setDeviceType(String str);
    }

    public static Builder builder() {
        return new AutoValue_DeviceInfo.Builder();
    }

    public abstract String getDeviceSerialNumber();

    public abstract String getDeviceType();
}

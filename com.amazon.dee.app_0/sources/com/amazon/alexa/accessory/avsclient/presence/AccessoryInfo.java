package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AutoValue_AccessoryInfo;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;
@AutoValue
/* loaded from: classes.dex */
public abstract class AccessoryInfo {

    @AutoValue.Builder
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract AccessoryInfo build();

        public abstract Builder setClusterDeviceInfo(DeviceInfo deviceInfo);

        public abstract Builder setDeviceInfo(DeviceInfo deviceInfo);

        public abstract Builder setState(State state);

        public abstract Builder setTimestamp(String str);
    }

    public static Builder builder() {
        return new AutoValue_AccessoryInfo.Builder();
    }

    @Nullable
    public abstract DeviceInfo getClusterDeviceInfo();

    @Nullable
    public abstract DeviceInfo getDeviceInfo();

    public abstract State getState();

    public abstract String getTimestamp();
}

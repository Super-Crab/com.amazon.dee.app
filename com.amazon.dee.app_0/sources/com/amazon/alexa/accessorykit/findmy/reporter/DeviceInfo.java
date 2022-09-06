package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.AutoValue_DeviceInfo;
import com.amazon.alexa.accessorykit.findmy.reporter.C$AutoValue_DeviceInfo;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes6.dex */
public abstract class DeviceInfo {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract DeviceInfo build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDeviceSerialNumber(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDeviceType(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_DeviceInfo.Builder();
    }

    public static TypeAdapter<DeviceInfo> typeAdapter(Gson gson) {
        return new AutoValue_DeviceInfo.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getDeviceSerialNumber();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getDeviceType();
}

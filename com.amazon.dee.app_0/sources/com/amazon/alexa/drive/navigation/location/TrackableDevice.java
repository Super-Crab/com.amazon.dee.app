package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.navigation.location.AutoValue_TrackableDevice;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_TrackableDevice;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
@AutoValue
/* loaded from: classes7.dex */
public abstract class TrackableDevice {

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract TrackableDevice build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setCause(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setDeviceInfo(DeviceInfo deviceInfo);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setEstimatedProximityDistance(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_TrackableDevice.Builder();
    }

    public static TypeAdapter<TrackableDevice> typeAdapter(Gson gson) {
        return new AutoValue_TrackableDevice.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getCause();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract DeviceInfo getDeviceInfo();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getEstimatedProximityDistance();
}

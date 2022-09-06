package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.navigation.location.AutoValue_TrackableDevicesLocation;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_TrackableDevicesLocation;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import java.util.List;
@AutoValue
/* loaded from: classes7.dex */
public abstract class TrackableDevicesLocation {
    static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @AutoValue.Builder
    /* loaded from: classes7.dex */
    static abstract class Builder {
        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract TrackableDevicesLocation build();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setGeolocation(Geolocation geolocation);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setTimestamp(String str);

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Builder setTrackableDevices(List<TrackableDevice> list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder() {
        return new C$AutoValue_TrackableDevicesLocation.Builder();
    }

    public static TypeAdapter<TrackableDevicesLocation> typeAdapter(Gson gson) {
        return new AutoValue_TrackableDevicesLocation.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Geolocation getGeolocation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getTimestamp();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List<TrackableDevice> getTrackableDevices();
}

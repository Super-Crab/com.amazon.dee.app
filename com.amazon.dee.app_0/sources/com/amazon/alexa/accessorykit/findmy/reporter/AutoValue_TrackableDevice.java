package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_TrackableDevice extends C$AutoValue_TrackableDevice {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<TrackableDevice> {
        private volatile TypeAdapter<DeviceInfo> deviceInfo_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("deviceInfo", "estimatedProximityDistance", "cause");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_TrackableDevice.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public TrackableDevice mo8353read(JsonReader jsonReader) throws IOException {
            DeviceInfo deviceInfo = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("deviceInfo").equals(nextName)) {
                        TypeAdapter<DeviceInfo> typeAdapter = this.deviceInfo_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(DeviceInfo.class);
                            this.deviceInfo_adapter = typeAdapter;
                        }
                        deviceInfo = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("estimatedProximityDistance").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("cause").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str2 = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_TrackableDevice(deviceInfo, str, str2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, TrackableDevice trackableDevice) throws IOException {
            if (trackableDevice == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("deviceInfo"));
            if (trackableDevice.getDeviceInfo() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DeviceInfo> typeAdapter = this.deviceInfo_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(DeviceInfo.class);
                    this.deviceInfo_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, trackableDevice.getDeviceInfo());
            }
            jsonWriter.name(this.realFieldNames.get("estimatedProximityDistance"));
            if (trackableDevice.getEstimatedProximityDistance() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, trackableDevice.getEstimatedProximityDistance());
            }
            jsonWriter.name(this.realFieldNames.get("cause"));
            if (trackableDevice.getCause() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, trackableDevice.getCause());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TrackableDevice(final DeviceInfo deviceInfo, final String str, final String str2) {
        new TrackableDevice(deviceInfo, str, str2) { // from class: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_TrackableDevice
            private final String cause;
            private final DeviceInfo deviceInfo;
            private final String estimatedProximityDistance;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_TrackableDevice$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends TrackableDevice.Builder {
                private String cause;
                private DeviceInfo deviceInfo;
                private String estimatedProximityDistance;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice.Builder
                public TrackableDevice build() {
                    String str = "";
                    if (this.deviceInfo == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceInfo");
                    }
                    if (this.estimatedProximityDistance == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " estimatedProximityDistance");
                    }
                    if (this.cause == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " cause");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_TrackableDevice(this.deviceInfo, this.estimatedProximityDistance, this.cause);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice.Builder
                public TrackableDevice.Builder setCause(String str) {
                    if (str != null) {
                        this.cause = str;
                        return this;
                    }
                    throw new NullPointerException("Null cause");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice.Builder
                public TrackableDevice.Builder setDeviceInfo(DeviceInfo deviceInfo) {
                    if (deviceInfo != null) {
                        this.deviceInfo = deviceInfo;
                        return this;
                    }
                    throw new NullPointerException("Null deviceInfo");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice.Builder
                public TrackableDevice.Builder setEstimatedProximityDistance(String str) {
                    if (str != null) {
                        this.estimatedProximityDistance = str;
                        return this;
                    }
                    throw new NullPointerException("Null estimatedProximityDistance");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (deviceInfo != null) {
                    this.deviceInfo = deviceInfo;
                    if (str != null) {
                        this.estimatedProximityDistance = str;
                        if (str2 != null) {
                            this.cause = str2;
                            return;
                        }
                        throw new NullPointerException("Null cause");
                    }
                    throw new NullPointerException("Null estimatedProximityDistance");
                }
                throw new NullPointerException("Null deviceInfo");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof TrackableDevice)) {
                    return false;
                }
                TrackableDevice trackableDevice = (TrackableDevice) obj;
                return this.deviceInfo.equals(trackableDevice.getDeviceInfo()) && this.estimatedProximityDistance.equals(trackableDevice.getEstimatedProximityDistance()) && this.cause.equals(trackableDevice.getCause());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice
            public String getCause() {
                return this.cause;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice
            public DeviceInfo getDeviceInfo() {
                return this.deviceInfo;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevice
            public String getEstimatedProximityDistance() {
                return this.estimatedProximityDistance;
            }

            public int hashCode() {
                return ((((this.deviceInfo.hashCode() ^ 1000003) * 1000003) ^ this.estimatedProximityDistance.hashCode()) * 1000003) ^ this.cause.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrackableDevice{deviceInfo=");
                outline107.append(this.deviceInfo);
                outline107.append(", estimatedProximityDistance=");
                outline107.append(this.estimatedProximityDistance);
                outline107.append(", cause=");
                return GeneratedOutlineSupport1.outline91(outline107, this.cause, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

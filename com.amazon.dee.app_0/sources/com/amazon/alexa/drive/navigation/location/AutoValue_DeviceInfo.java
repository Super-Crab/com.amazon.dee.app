package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.location.DeviceInfo;
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
/* loaded from: classes7.dex */
public final class AutoValue_DeviceInfo extends C$AutoValue_DeviceInfo {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DeviceInfo> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("deviceSerialNumber", "deviceType");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_DeviceInfo.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DeviceInfo mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("deviceSerialNumber").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("deviceType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DeviceInfo(str, str2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, DeviceInfo deviceInfo) throws IOException {
            if (deviceInfo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("deviceSerialNumber"));
            if (deviceInfo.getDeviceSerialNumber() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, deviceInfo.getDeviceSerialNumber());
            }
            jsonWriter.name(this.realFieldNames.get("deviceType"));
            if (deviceInfo.getDeviceType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, deviceInfo.getDeviceType());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_DeviceInfo(final String str, final String str2) {
        new DeviceInfo(str, str2) { // from class: com.amazon.alexa.drive.navigation.location.$AutoValue_DeviceInfo
            private final String deviceSerialNumber;
            private final String deviceType;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.location.$AutoValue_DeviceInfo$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends DeviceInfo.Builder {
                private String deviceSerialNumber;
                private String deviceType;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.DeviceInfo.Builder
                public DeviceInfo build() {
                    String str = "";
                    if (this.deviceSerialNumber == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceSerialNumber");
                    }
                    if (this.deviceType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_DeviceInfo(this.deviceSerialNumber, this.deviceType);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.DeviceInfo.Builder
                public DeviceInfo.Builder setDeviceSerialNumber(String str) {
                    if (str != null) {
                        this.deviceSerialNumber = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceSerialNumber");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.DeviceInfo.Builder
                public DeviceInfo.Builder setDeviceType(String str) {
                    if (str != null) {
                        this.deviceType = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceType");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.deviceSerialNumber = str;
                    if (str2 != null) {
                        this.deviceType = str2;
                        return;
                    }
                    throw new NullPointerException("Null deviceType");
                }
                throw new NullPointerException("Null deviceSerialNumber");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof DeviceInfo)) {
                    return false;
                }
                DeviceInfo deviceInfo = (DeviceInfo) obj;
                return this.deviceSerialNumber.equals(deviceInfo.getDeviceSerialNumber()) && this.deviceType.equals(deviceInfo.getDeviceType());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.DeviceInfo
            public String getDeviceSerialNumber() {
                return this.deviceSerialNumber;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.DeviceInfo
            public String getDeviceType() {
                return this.deviceType;
            }

            public int hashCode() {
                return ((this.deviceSerialNumber.hashCode() ^ 1000003) * 1000003) ^ this.deviceType.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceInfo{deviceSerialNumber=");
                outline107.append(this.deviceSerialNumber);
                outline107.append(", deviceType=");
                return GeneratedOutlineSupport1.outline91(outline107, this.deviceType, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

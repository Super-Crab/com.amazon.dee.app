package com.amazon.alexa.accessory.kota;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Locale;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class UpdateRequest implements Parcelable, JsonObjectSerializable {
    public static final Parcelable.Creator<UpdateRequest> CREATOR = new Parcelable.Creator<UpdateRequest>() { // from class: com.amazon.alexa.accessory.kota.UpdateRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public UpdateRequest mo321createFromParcel(Parcel parcel) {
            return new UpdateRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public UpdateRequest[] mo322newArray(int i) {
            return new UpdateRequest[i];
        }
    };
    private static final String JSON_BUILD_DIMENSION = "buildDimension";
    private static final String JSON_COMPONENT_ID = "componentId";
    private static final String JSON_COMPONENT_VERSION = "componentVersion";
    private static final String JSON_DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    private static final String JSON_DEVICE_TYPE = "deviceType";
    private static final String URL = "https://softwareupdates.amazon.com/software/software/inventory/companion";
    private final String buildDimension;
    private final String componentId;
    private final int componentVersion;
    private final String deviceSerialNumber;
    private final String deviceType;
    private final String url;

    /* loaded from: classes.dex */
    public static final class Builder {
        String buildDimension;
        String componentId;
        int componentVersion;
        String deviceSerialNumber;
        String deviceType;
        String url;

        public UpdateRequest build() {
            Preconditions.notNull(this.buildDimension, UpdateRequest.JSON_BUILD_DIMENSION);
            Preconditions.notNull(this.deviceSerialNumber, "deviceSerialNumber");
            Preconditions.notNull(this.componentId, UpdateRequest.JSON_COMPONENT_ID);
            return new UpdateRequest(this);
        }

        public Builder buildDimension(String str) {
            this.buildDimension = str;
            return this;
        }

        public Builder componentId(String str) {
            this.componentId = str;
            return this;
        }

        public Builder componentVersion(int i) {
            this.componentVersion = i;
            return this;
        }

        public Builder deviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder deviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder url(String str) {
            this.url = str;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class JsonBuilder implements JsonObjectSerializable.Factory<UpdateRequest> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public UpdateRequest mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().buildDimension(jSONObject.getString(UpdateRequest.JSON_BUILD_DIMENSION)).deviceSerialNumber(jSONObject.getString("deviceSerialNumber")).deviceType(jSONObject.getString("deviceType")).componentId(jSONObject.getString(UpdateRequest.JSON_COMPONENT_ID)).componentVersion(jSONObject.getInt(UpdateRequest.JSON_COMPONENT_VERSION)).build();
        }
    }

    UpdateRequest(Builder builder) {
        this.buildDimension = builder.buildDimension;
        this.deviceSerialNumber = builder.deviceSerialNumber;
        this.deviceType = builder.deviceType;
        this.componentId = builder.componentId;
        this.componentVersion = builder.componentVersion;
        String str = builder.url;
        this.url = str == null ? URL : str;
    }

    public static String generateComponentId(String str, String str2) {
        Preconditions.notNull(str, MetricsConstants.Session.FIRMWARE_NAME);
        Preconditions.notNull(str2, "locale");
        return String.format(Locale.US, "com.amazon.%s.firmware.%s", str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || UpdateRequest.class != obj.getClass()) {
            return false;
        }
        UpdateRequest updateRequest = (UpdateRequest) obj;
        return this.componentVersion == updateRequest.componentVersion && Objects.equals(this.buildDimension, updateRequest.buildDimension) && Objects.equals(this.deviceSerialNumber, updateRequest.deviceSerialNumber) && Objects.equals(this.deviceType, updateRequest.deviceType) && Objects.equals(this.componentId, updateRequest.componentId) && Objects.equals(this.url, updateRequest.url);
    }

    public String getBuildDimension() {
        return this.buildDimension;
    }

    public String getComponentId() {
        return this.componentId;
    }

    public int getComponentVersion() {
        return this.componentVersion;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getUrl() {
        return this.url;
    }

    public int hashCode() {
        return Objects.hash(this.buildDimension, this.deviceSerialNumber, this.deviceType, this.componentId, Integer.valueOf(this.componentVersion), this.url);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(JSON_BUILD_DIMENSION, this.buildDimension).put("deviceSerialNumber", this.deviceSerialNumber).put("deviceType", this.deviceType).put(JSON_COMPONENT_ID, this.componentId).put(JSON_COMPONENT_VERSION, this.componentVersion);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateRequest{, buildDimension='");
        GeneratedOutlineSupport1.outline176(outline107, this.buildDimension, Chars.QUOTE, ", deviceSerialNumber='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceSerialNumber, Chars.QUOTE, ", deviceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceType, Chars.QUOTE, ", componentId='");
        GeneratedOutlineSupport1.outline176(outline107, this.componentId, Chars.QUOTE, ", componentVersion=");
        outline107.append(this.componentVersion);
        outline107.append(", url='");
        return GeneratedOutlineSupport1.outline90(outline107, this.url, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.buildDimension);
        parcel.writeString(this.deviceSerialNumber);
        parcel.writeString(this.deviceType);
        parcel.writeString(this.componentId);
        parcel.writeInt(this.componentVersion);
        parcel.writeString(this.url);
    }

    UpdateRequest(Parcel parcel) {
        this.buildDimension = parcel.readString();
        this.deviceSerialNumber = parcel.readString();
        this.deviceType = parcel.readString();
        this.componentId = parcel.readString();
        this.componentVersion = parcel.readInt();
        this.url = parcel.readString();
    }
}

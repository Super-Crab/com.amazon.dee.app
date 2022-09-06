package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class CompanionDeviceId implements JsonObjectSerializable {
    private final String deviceSerialNumber;
    private final String deviceType;

    /* loaded from: classes.dex */
    public static final class Builder {
        String deviceSerialNumber;
        String deviceType;

        public CompanionDeviceId build() {
            Preconditions.notNull(this.deviceSerialNumber, "deviceSerialNumber");
            Preconditions.notNull(this.deviceType, "deviceType");
            return new CompanionDeviceId(this);
        }

        public Builder deviceSerialNumber(String str) {
            this.deviceSerialNumber = str;
            return this;
        }

        public Builder deviceType(String str) {
            this.deviceType = str;
            return this;
        }
    }

    CompanionDeviceId(Builder builder) {
        this.deviceSerialNumber = builder.deviceSerialNumber;
        this.deviceType = builder.deviceType;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CompanionDeviceId.class != obj.getClass()) {
            return false;
        }
        CompanionDeviceId companionDeviceId = (CompanionDeviceId) obj;
        if (this.deviceSerialNumber.equals(companionDeviceId.deviceSerialNumber)) {
            return this.deviceType.equals(companionDeviceId.deviceType);
        }
        return false;
    }

    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return this.deviceType.hashCode() + (this.deviceSerialNumber.hashCode() * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("deviceSerialNumber", this.deviceSerialNumber).put("deviceType", this.deviceType);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CompanionDeviceId{deviceSerialNumber='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceSerialNumber, Chars.QUOTE, ", deviceType='");
        return GeneratedOutlineSupport1.outline90(outline107, this.deviceType, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}

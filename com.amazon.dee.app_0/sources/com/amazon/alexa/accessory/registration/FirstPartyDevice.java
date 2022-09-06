package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class FirstPartyDevice implements JsonObjectSerializable {
    private static final String DEVICE_TYPE_ID_KEY = "deviceType";
    private static final String DSN_KEY = "deviceSerialNumber";
    public static final Factory FACTORY = new Factory();
    private final String deviceTypeId;
    private final String dsn;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<FirstPartyDevice> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public FirstPartyDevice mo1239create(JSONObject jSONObject) throws JSONException {
            return new FirstPartyDevice(jSONObject.getString("deviceSerialNumber"), jSONObject.getString("deviceType"));
        }
    }

    public FirstPartyDevice(String str, String str2) {
        Preconditions.notNull(str, "dsn");
        Preconditions.notNull(str2, "deviceTypeId");
        this.dsn = str;
        this.deviceTypeId = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FirstPartyDevice.class != obj.getClass()) {
            return false;
        }
        FirstPartyDevice firstPartyDevice = (FirstPartyDevice) obj;
        if (this.dsn.equals(firstPartyDevice.dsn)) {
            return this.deviceTypeId.equals(firstPartyDevice.deviceTypeId);
        }
        return false;
    }

    public String getDeviceType() {
        return this.deviceTypeId;
    }

    public String getDsn() {
        return this.dsn;
    }

    public int hashCode() {
        return this.deviceTypeId.hashCode() + (this.dsn.hashCode() * 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("deviceSerialNumber", this.dsn).put("deviceType", this.deviceTypeId);
    }
}

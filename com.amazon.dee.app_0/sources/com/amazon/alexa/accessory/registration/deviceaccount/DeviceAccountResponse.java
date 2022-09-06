package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DeviceAccountResponse implements JsonObjectSerializable {
    private static final String DEVICE_ACCOUNT_ID_KEY = "deviceAccountId";
    public static final Factory FACTORY = new Factory();
    private final String deviceAccountId;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceAccountResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceAccountResponse mo1239create(JSONObject jSONObject) throws JSONException {
            return new DeviceAccountResponse(jSONObject.getString(DeviceAccountResponse.DEVICE_ACCOUNT_ID_KEY));
        }
    }

    public DeviceAccountResponse(String str) {
        Preconditions.notNull(str, DEVICE_ACCOUNT_ID_KEY);
        this.deviceAccountId = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DeviceAccountResponse.class == obj.getClass()) {
            return Objects.equals(this.deviceAccountId, ((DeviceAccountResponse) obj).getDeviceAccountId());
        }
        return false;
    }

    public String getDeviceAccountId() {
        return this.deviceAccountId;
    }

    public int hashCode() {
        return Objects.hash(this.deviceAccountId);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(DEVICE_ACCOUNT_ID_KEY, this.deviceAccountId);
    }
}

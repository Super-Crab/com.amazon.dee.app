package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DeviceAccount implements JsonObjectSerializable {
    private static final String DEVICE_ACCOUNT_REQUEST_KEY = "deviceAccountRequest";
    private static final String DEVICE_ACCOUNT_RESPONSE_KEY = "deviceAccountResponse";
    public static final Factory FACTORY = new Factory(DeviceAccountRequest.FACTORY, DeviceAccountResponse.FACTORY);
    private final DeviceAccountRequest deviceAccountRequest;
    private final DeviceAccountResponse deviceAccountResponse;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceAccount> {
        private final JsonObjectSerializable.Factory<DeviceAccountRequest> deviceAccountRequestFactory;
        private final JsonObjectSerializable.Factory<DeviceAccountResponse> deviceAccountResponseFactory;

        public Factory(JsonObjectSerializable.Factory<DeviceAccountRequest> factory, JsonObjectSerializable.Factory<DeviceAccountResponse> factory2) {
            this.deviceAccountRequestFactory = factory;
            this.deviceAccountResponseFactory = factory2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceAccount mo1239create(JSONObject jSONObject) throws JSONException {
            return new DeviceAccount(this.deviceAccountRequestFactory.mo1239create(jSONObject.getJSONObject(DeviceAccount.DEVICE_ACCOUNT_REQUEST_KEY)), this.deviceAccountResponseFactory.mo1239create(jSONObject.getJSONObject(DeviceAccount.DEVICE_ACCOUNT_RESPONSE_KEY)));
        }
    }

    public DeviceAccount(DeviceAccountRequest deviceAccountRequest, DeviceAccountResponse deviceAccountResponse) {
        Preconditions.notNull(deviceAccountRequest, DEVICE_ACCOUNT_REQUEST_KEY);
        Preconditions.notNull(deviceAccountResponse, DEVICE_ACCOUNT_RESPONSE_KEY);
        this.deviceAccountRequest = deviceAccountRequest;
        this.deviceAccountResponse = deviceAccountResponse;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceAccount.class != obj.getClass()) {
            return false;
        }
        DeviceAccount deviceAccount = (DeviceAccount) obj;
        return Objects.equals(this.deviceAccountRequest, deviceAccount.getDeviceAccountRequest()) && Objects.equals(this.deviceAccountResponse, deviceAccount.getDeviceAccountResponse());
    }

    public DeviceAccountRequest getDeviceAccountRequest() {
        return this.deviceAccountRequest;
    }

    public DeviceAccountResponse getDeviceAccountResponse() {
        return this.deviceAccountResponse;
    }

    public int hashCode() {
        return Objects.hash(this.deviceAccountRequest, this.deviceAccountResponse);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(DEVICE_ACCOUNT_REQUEST_KEY, this.deviceAccountRequest.toJsonObject()).put(DEVICE_ACCOUNT_RESPONSE_KEY, this.deviceAccountResponse.toJsonObject());
    }
}

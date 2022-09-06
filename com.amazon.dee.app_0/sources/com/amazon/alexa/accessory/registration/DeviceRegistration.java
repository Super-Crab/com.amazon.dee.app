package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DeviceRegistration implements JsonObjectSerializable {
    private static final String DEVICE_REGISTRATION_REQUEST_KEY = "deviceRegistrationRequest";
    private static final String DEVICE_REGISTRATION_RESPONSE_KEY = "deviceRegistrationResponse";
    public static final Factory FACTORY = new Factory(DeviceRegistrationRequest.FACTORY, DeviceRegistrationResponse.FACTORY);
    private static final String TIME_OF_REGISTRATION_KEY = "timeOfRegistration";
    private final DeviceRegistrationRequest deviceRegistrationRequest;
    private final DeviceRegistrationResponse deviceRegistrationResponse;
    private final long timeOfRegistration;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceRegistration> {
        private final JsonObjectSerializable.Factory<DeviceRegistrationRequest> deviceRegistrationRequestFactory;
        private final JsonObjectSerializable.Factory<DeviceRegistrationResponse> deviceRegistrationResponseFactory;

        public Factory(JsonObjectSerializable.Factory<DeviceRegistrationRequest> factory, JsonObjectSerializable.Factory<DeviceRegistrationResponse> factory2) {
            this.deviceRegistrationRequestFactory = factory;
            this.deviceRegistrationResponseFactory = factory2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceRegistration mo1239create(JSONObject jSONObject) throws JSONException {
            return new DeviceRegistration(this.deviceRegistrationRequestFactory.mo1239create(jSONObject.getJSONObject(DeviceRegistration.DEVICE_REGISTRATION_REQUEST_KEY)), this.deviceRegistrationResponseFactory.mo1239create(jSONObject.getJSONObject(DeviceRegistration.DEVICE_REGISTRATION_RESPONSE_KEY)), jSONObject.getLong(DeviceRegistration.TIME_OF_REGISTRATION_KEY));
        }
    }

    public DeviceRegistration(DeviceRegistrationRequest deviceRegistrationRequest, DeviceRegistrationResponse deviceRegistrationResponse, long j) {
        Preconditions.notNull(deviceRegistrationRequest, DEVICE_REGISTRATION_REQUEST_KEY);
        Preconditions.notNull(deviceRegistrationResponse, DEVICE_REGISTRATION_RESPONSE_KEY);
        this.deviceRegistrationRequest = deviceRegistrationRequest;
        this.deviceRegistrationResponse = deviceRegistrationResponse;
        this.timeOfRegistration = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DeviceRegistration.class == obj.getClass()) {
            return this.deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier().equals(((DeviceRegistration) obj).deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier());
        }
        return false;
    }

    public DeviceRegistrationRequest getDeviceRegistrationRequest() {
        return this.deviceRegistrationRequest;
    }

    public DeviceRegistrationResponse getDeviceRegistrationResponse() {
        return this.deviceRegistrationResponse;
    }

    public long getTimeOfRegistration() {
        return this.timeOfRegistration;
    }

    public int hashCode() {
        return this.deviceRegistrationRequest.getDeviceRegistrationRequestIdentifier().hashCode();
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(DEVICE_REGISTRATION_REQUEST_KEY, this.deviceRegistrationRequest.toJsonObject()).put(DEVICE_REGISTRATION_RESPONSE_KEY, this.deviceRegistrationResponse.toJsonObject()).put(TIME_OF_REGISTRATION_KEY, this.timeOfRegistration);
    }
}

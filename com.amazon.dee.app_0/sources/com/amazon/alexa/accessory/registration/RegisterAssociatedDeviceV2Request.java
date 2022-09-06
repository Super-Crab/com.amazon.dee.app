package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class RegisterAssociatedDeviceV2Request implements JsonObjectSerializable {
    private final DeviceRegistrationRequest request;

    public RegisterAssociatedDeviceV2Request(DeviceRegistrationRequest deviceRegistrationRequest) {
        Preconditions.notNull(deviceRegistrationRequest, "request");
        this.request = deviceRegistrationRequest;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("parameters", this.request.toJsonObject());
    }
}

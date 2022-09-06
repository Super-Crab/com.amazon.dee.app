package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DeregisterAssociatedDeviceV2Request implements JsonObjectSerializable {
    private final DeviceDeregistrationRequest request;

    public DeregisterAssociatedDeviceV2Request(DeviceDeregistrationRequest deviceDeregistrationRequest) {
        Preconditions.notNull(deviceDeregistrationRequest, "request");
        this.request = deviceDeregistrationRequest;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("parameters", this.request.toJsonObject());
    }
}

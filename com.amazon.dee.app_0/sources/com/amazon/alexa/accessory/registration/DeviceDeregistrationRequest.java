package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DeviceDeregistrationRequest implements JsonObjectSerializable {
    private final String associatedDeviceRelationship;
    private final String associatedDeviceRole;
    private final DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier;

    public DeviceDeregistrationRequest(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, String str, String str2) {
        Preconditions.notNull(deviceRegistrationRequestIdentifier, "deviceRegistrationRequestIdentifier");
        Preconditions.notNull(str, "associatedDeviceRelationship");
        Preconditions.notNull(str2, "associatedDeviceRole");
        this.deviceRegistrationRequestIdentifier = deviceRegistrationRequestIdentifier;
        this.associatedDeviceRelationship = str;
        this.associatedDeviceRole = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceDeregistrationRequest.class != obj.getClass()) {
            return false;
        }
        DeviceDeregistrationRequest deviceDeregistrationRequest = (DeviceDeregistrationRequest) obj;
        if (!this.deviceRegistrationRequestIdentifier.equals(deviceDeregistrationRequest.deviceRegistrationRequestIdentifier) || !this.associatedDeviceRelationship.equals(deviceDeregistrationRequest.associatedDeviceRelationship)) {
            return false;
        }
        return this.associatedDeviceRole.equals(deviceDeregistrationRequest.associatedDeviceRole);
    }

    public DeviceRegistrationRequestIdentifier getDeviceRegistrationRequestIdentifier() {
        return this.deviceRegistrationRequestIdentifier;
    }

    public int hashCode() {
        return this.associatedDeviceRole.hashCode() + GeneratedOutlineSupport1.outline7(this.associatedDeviceRelationship, this.deviceRegistrationRequestIdentifier.hashCode() * 31, 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return this.deviceRegistrationRequestIdentifier.toJsonObject().put("associatedDeviceRelationship", this.associatedDeviceRelationship).put("associatedDeviceRole", this.associatedDeviceRole);
    }
}

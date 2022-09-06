package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DeviceRegistrationRequest implements JsonObjectSerializable {
    private static final String ALLOW_MULTIPLE_ACCOUNTS_KEY = "allowMultipleAccounts";
    static final String ASSOCIATED_DEVICE_RELATIONSHIP_KEY = "associatedDeviceRelationship";
    static final String ASSOCIATED_DEVICE_ROLE_KEY = "associatedDeviceRole";
    private static final String DEREGISTER_EXISTING_ACCOUNTS_KEY = "deregisterExistingAccounts";
    private static final String DEVICE_NAME_KEY = "deviceName";
    public static final Factory FACTORY = new Factory(DeviceRegistrationRequestIdentifier.FACTORY);
    private static final String SOFTWARE_VERSION_KEY = "softwareVersion";
    private final String allowMultipleAccounts;
    private final String associatedDeviceRelationship;
    private final String associatedDeviceRole;
    private final String deregisterExitingAccounts;
    private final String deviceName;
    private final DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier;
    private final String softwareVersion;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceRegistrationRequest> {
        private final JsonObjectSerializable.Factory<DeviceRegistrationRequestIdentifier> deviceRegistrationRequestIdentifierFactory;

        public Factory(JsonObjectSerializable.Factory<DeviceRegistrationRequestIdentifier> factory) {
            this.deviceRegistrationRequestIdentifierFactory = factory;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceRegistrationRequest mo1239create(JSONObject jSONObject) throws JSONException {
            return new DeviceRegistrationRequest(this.deviceRegistrationRequestIdentifierFactory.mo1239create(jSONObject), JsonUtils.getStringOrNull(jSONObject, "deviceName"), JsonUtils.getStringOrNull(jSONObject, "softwareVersion"), jSONObject.getString(DeviceRegistrationRequest.DEREGISTER_EXISTING_ACCOUNTS_KEY), jSONObject.getString(DeviceRegistrationRequest.ALLOW_MULTIPLE_ACCOUNTS_KEY), jSONObject.getString(DeviceRegistrationRequest.ASSOCIATED_DEVICE_RELATIONSHIP_KEY), jSONObject.getString(DeviceRegistrationRequest.ASSOCIATED_DEVICE_ROLE_KEY));
        }
    }

    public DeviceRegistrationRequest(DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier, String str, String str2, String str3, String str4, String str5, String str6) {
        Preconditions.notNull(deviceRegistrationRequestIdentifier, "deviceRegistrationRequestIdentifier");
        Preconditions.notNull(str3, DEREGISTER_EXISTING_ACCOUNTS_KEY);
        Preconditions.notNull(str4, ALLOW_MULTIPLE_ACCOUNTS_KEY);
        Preconditions.notNull(str5, ASSOCIATED_DEVICE_RELATIONSHIP_KEY);
        Preconditions.notNull(str6, ASSOCIATED_DEVICE_ROLE_KEY);
        Preconditions.notNull(str4, ALLOW_MULTIPLE_ACCOUNTS_KEY);
        this.deviceRegistrationRequestIdentifier = deviceRegistrationRequestIdentifier;
        this.deviceName = str;
        this.softwareVersion = str2;
        this.deregisterExitingAccounts = str3;
        this.allowMultipleAccounts = str4;
        this.associatedDeviceRelationship = str5;
        this.associatedDeviceRole = str6;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceRegistrationRequest.class != obj.getClass()) {
            return false;
        }
        DeviceRegistrationRequest deviceRegistrationRequest = (DeviceRegistrationRequest) obj;
        if (!this.deviceRegistrationRequestIdentifier.equals(deviceRegistrationRequest.deviceRegistrationRequestIdentifier)) {
            return false;
        }
        String str = this.deviceName;
        if (str == null ? deviceRegistrationRequest.deviceName != null : !str.equals(deviceRegistrationRequest.deviceName)) {
            return false;
        }
        String str2 = this.softwareVersion;
        if (str2 == null ? deviceRegistrationRequest.softwareVersion != null : !str2.equals(deviceRegistrationRequest.softwareVersion)) {
            return false;
        }
        if (!this.deregisterExitingAccounts.equals(deviceRegistrationRequest.deregisterExitingAccounts) || !this.allowMultipleAccounts.equals(deviceRegistrationRequest.allowMultipleAccounts) || !this.associatedDeviceRelationship.equals(deviceRegistrationRequest.associatedDeviceRelationship)) {
            return false;
        }
        return this.associatedDeviceRole.equals(deviceRegistrationRequest.associatedDeviceRole);
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public DeviceRegistrationRequestIdentifier getDeviceRegistrationRequestIdentifier() {
        return this.deviceRegistrationRequestIdentifier;
    }

    public String getSoftwareVersion() {
        return this.softwareVersion;
    }

    public int hashCode() {
        int hashCode = this.deviceRegistrationRequestIdentifier.hashCode() * 31;
        String str = this.deviceName;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.softwareVersion;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return this.associatedDeviceRole.hashCode() + GeneratedOutlineSupport1.outline7(this.associatedDeviceRelationship, GeneratedOutlineSupport1.outline7(this.allowMultipleAccounts, GeneratedOutlineSupport1.outline7(this.deregisterExitingAccounts, (hashCode2 + i) * 31, 31), 31), 31);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return this.deviceRegistrationRequestIdentifier.toJsonObject().put("deviceName", this.deviceName).put("softwareVersion", this.softwareVersion).put(DEREGISTER_EXISTING_ACCOUNTS_KEY, this.deregisterExitingAccounts).put(ALLOW_MULTIPLE_ACCOUNTS_KEY, this.allowMultipleAccounts).put(ASSOCIATED_DEVICE_RELATIONSHIP_KEY, this.associatedDeviceRelationship).put(ASSOCIATED_DEVICE_ROLE_KEY, this.associatedDeviceRole);
    }
}

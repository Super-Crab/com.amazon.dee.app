package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class DeviceRegistrationResponse implements JsonObjectSerializable {
    private static final String ACCOUNT_POOL_KEY = "account_pool";
    private static final String ALIAS_KEY = "alias";
    private static final String CLUSTER_DEVICE_SERIAL_NUMBER = "cluster_device_serial_number";
    private static final String CLUSTER_DEVICE_TYPE = "cluster_device_type";
    private static final String DEVICE_TYPE_KEY = "device_type";
    public static final Factory FACTORY = new Factory();
    private static final String GIVEN_NAME_KEY = "given_name";
    private static final String INTERNAL_DEVICE_SERIAL_NUMBER_KEY = "internal_device_serial_number";
    private static final String NAME_KEY = "name";
    private static final String USER_DEVICE_NAME_KEY = "user_device_name";
    private static final String USER_DIRECTED_ID_KEY = "user_directed_id";
    private final String accountPool;
    private final String alias;
    private final String clusterDeviceSerialNumber;
    private final String clusterDeviceType;
    private final String deviceType;
    private final String givenName;
    private final String internalDeviceSerialNumber;
    private final String name;
    private final String userDeviceName;
    private final String userDirectedId;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceRegistrationResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceRegistrationResponse mo1239create(JSONObject jSONObject) throws JSONException {
            return new DeviceRegistrationResponse(jSONObject.getString(DeviceRegistrationResponse.USER_DEVICE_NAME_KEY), jSONObject.getString(DeviceRegistrationResponse.GIVEN_NAME_KEY), jSONObject.getString("name"), JsonUtils.getStringOrNull(jSONObject, DeviceRegistrationResponse.ACCOUNT_POOL_KEY), jSONObject.getString(DeviceRegistrationResponse.USER_DIRECTED_ID_KEY), JsonUtils.getStringOrNull(jSONObject, "device_type"), JsonUtils.getStringOrNull(jSONObject, DeviceRegistrationResponse.ALIAS_KEY), JsonUtils.getStringOrNull(jSONObject, DeviceRegistrationResponse.INTERNAL_DEVICE_SERIAL_NUMBER_KEY), JsonUtils.getStringOrNull(jSONObject, DeviceRegistrationResponse.CLUSTER_DEVICE_SERIAL_NUMBER), JsonUtils.getStringOrNull(jSONObject, DeviceRegistrationResponse.CLUSTER_DEVICE_TYPE));
        }
    }

    public DeviceRegistrationResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        Preconditions.notNull(str, "userDeviceName");
        Preconditions.notNull(str2, "givenName");
        Preconditions.notNull(str3, "name");
        Preconditions.notNull(str5, "userDirectedId");
        this.userDeviceName = str;
        this.givenName = str2;
        this.name = str3;
        this.alias = str7;
        this.accountPool = str4;
        this.userDirectedId = str5;
        this.deviceType = str6;
        this.internalDeviceSerialNumber = str8;
        this.clusterDeviceSerialNumber = str9;
        this.clusterDeviceType = str10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceRegistrationResponse.class != obj.getClass()) {
            return false;
        }
        DeviceRegistrationResponse deviceRegistrationResponse = (DeviceRegistrationResponse) obj;
        return Objects.equals(this.userDeviceName, deviceRegistrationResponse.userDeviceName) && Objects.equals(this.givenName, deviceRegistrationResponse.givenName) && Objects.equals(this.name, deviceRegistrationResponse.name) && Objects.equals(this.alias, deviceRegistrationResponse.alias) && Objects.equals(this.accountPool, deviceRegistrationResponse.accountPool) && Objects.equals(this.userDirectedId, deviceRegistrationResponse.userDirectedId) && Objects.equals(this.deviceType, deviceRegistrationResponse.deviceType) && Objects.equals(this.internalDeviceSerialNumber, deviceRegistrationResponse.internalDeviceSerialNumber) && Objects.equals(this.clusterDeviceSerialNumber, deviceRegistrationResponse.clusterDeviceSerialNumber) && Objects.equals(this.clusterDeviceType, deviceRegistrationResponse.clusterDeviceType);
    }

    public String getClusterDeviceSerialNumber() {
        return this.clusterDeviceSerialNumber;
    }

    public String getClusterDeviceType() {
        return this.clusterDeviceType;
    }

    public String getCustomerDirectedId() {
        return this.userDirectedId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getInternalDeviceSerialNumber() {
        return this.internalDeviceSerialNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getUserDeviceName() {
        return this.userDeviceName;
    }

    public int hashCode() {
        return Objects.hash(this.userDeviceName, this.givenName, this.name, this.alias, this.accountPool, this.userDirectedId, this.deviceType, this.internalDeviceSerialNumber, this.clusterDeviceSerialNumber, this.clusterDeviceType);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(USER_DEVICE_NAME_KEY, this.userDeviceName).put(GIVEN_NAME_KEY, this.givenName).put("name", this.name).put(ACCOUNT_POOL_KEY, this.accountPool).put(USER_DIRECTED_ID_KEY, this.userDirectedId).put("device_type", this.deviceType).put(ALIAS_KEY, this.alias).put(INTERNAL_DEVICE_SERIAL_NUMBER_KEY, this.internalDeviceSerialNumber).put(CLUSTER_DEVICE_SERIAL_NUMBER, this.clusterDeviceSerialNumber).put(CLUSTER_DEVICE_TYPE, this.clusterDeviceType);
    }
}

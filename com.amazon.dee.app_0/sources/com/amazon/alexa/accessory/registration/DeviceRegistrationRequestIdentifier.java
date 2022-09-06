package com.amazon.alexa.accessory.registration;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class DeviceRegistrationRequestIdentifier implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory(FirstPartyDevice.FACTORY, FirstPartyClusterDevice.FACTORY, ThirdPartyDevice.FACTORY);
    private static final String FIRST_PARTY_CLUSTER_DEVICE_KEY = "clusterDevice";
    private static final String FIRST_PARTY_DEVICE_KEY = "device";
    private static final String THIRD_PARTY_DEVICE_KEY = "thirdPartyDevice";
    private final FirstPartyClusterDevice firstPartyClusterDevice;
    private final FirstPartyDevice firstPartyDevice;
    private final ThirdPartyDevice thirdPartyDevice;

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceRegistrationRequestIdentifier> {
        private final JsonObjectSerializable.Factory<FirstPartyClusterDevice> firstPartyClusterDeviceFactory;
        private final JsonObjectSerializable.Factory<FirstPartyDevice> firstPartyDeviceFactory;
        private final JsonObjectSerializable.Factory<ThirdPartyDevice> thirdPartyDeviceFactory;

        public Factory(JsonObjectSerializable.Factory<FirstPartyDevice> factory, JsonObjectSerializable.Factory<FirstPartyClusterDevice> factory2, JsonObjectSerializable.Factory<ThirdPartyDevice> factory3) {
            Preconditions.notNull(factory, "firstPartyDeviceFactory");
            Preconditions.notNull(factory2, "firstPartyClusterDeviceFactory");
            Preconditions.notNull(factory3, "thirdPartyDeviceFactory");
            this.firstPartyDeviceFactory = factory;
            this.firstPartyClusterDeviceFactory = factory2;
            this.thirdPartyDeviceFactory = factory3;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceRegistrationRequestIdentifier mo1239create(JSONObject jSONObject) throws JSONException {
            JSONObject jsonObjectOrNull = JsonUtils.getJsonObjectOrNull(jSONObject, "device");
            JSONObject jsonObjectOrNull2 = JsonUtils.getJsonObjectOrNull(jSONObject, DeviceRegistrationRequestIdentifier.FIRST_PARTY_CLUSTER_DEVICE_KEY);
            JSONObject jsonObjectOrNull3 = JsonUtils.getJsonObjectOrNull(jSONObject, DeviceRegistrationRequestIdentifier.THIRD_PARTY_DEVICE_KEY);
            boolean z = false;
            ArrayList arrayList = new ArrayList(Arrays.asList(jsonObjectOrNull, jsonObjectOrNull2, jsonObjectOrNull3));
            arrayList.removeAll(Collections.singleton(null));
            if (arrayList.size() == 1) {
                z = true;
            }
            Preconditions.precondition(z, "Can only provide one of: FirstPartyDevice, FirstPartyClusterDevice, or ThirdPartyDevice");
            if (jsonObjectOrNull != null) {
                return new DeviceRegistrationRequestIdentifier(this.firstPartyDeviceFactory.mo1239create(jsonObjectOrNull));
            }
            if (jsonObjectOrNull2 != null) {
                return new DeviceRegistrationRequestIdentifier(this.firstPartyClusterDeviceFactory.mo1239create(jsonObjectOrNull2));
            }
            if (jsonObjectOrNull3 != null) {
                return new DeviceRegistrationRequestIdentifier(this.thirdPartyDeviceFactory.mo1239create(jsonObjectOrNull3));
            }
            throw new JSONException("No firstPartyClusterDevice, thirdPartyDevice or firstPartyDevice available in json to construct deviceRegistrationRequestIdentifier");
        }
    }

    public DeviceRegistrationRequestIdentifier(FirstPartyDevice firstPartyDevice) {
        Preconditions.notNull(firstPartyDevice, "firstPartyDevice");
        this.firstPartyDevice = firstPartyDevice;
        this.firstPartyClusterDevice = null;
        this.thirdPartyDevice = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceRegistrationRequestIdentifier.class != obj.getClass()) {
            return false;
        }
        DeviceRegistrationRequestIdentifier deviceRegistrationRequestIdentifier = (DeviceRegistrationRequestIdentifier) obj;
        return Objects.equals(this.firstPartyDevice, deviceRegistrationRequestIdentifier.firstPartyDevice) && Objects.equals(this.firstPartyClusterDevice, deviceRegistrationRequestIdentifier.firstPartyClusterDevice) && Objects.equals(this.thirdPartyDevice, deviceRegistrationRequestIdentifier.thirdPartyDevice);
    }

    public FirstPartyClusterDevice getFirstPartyClusterDevice() {
        return this.firstPartyClusterDevice;
    }

    public FirstPartyDevice getFirstPartyDevice() {
        return this.firstPartyDevice;
    }

    public ThirdPartyDevice getThirdPartyDevice() {
        return this.thirdPartyDevice;
    }

    public int hashCode() {
        return Objects.hash(this.firstPartyDevice, this.firstPartyClusterDevice, this.thirdPartyDevice);
    }

    public boolean isFirstParty() {
        return (this.firstPartyDevice == null && this.firstPartyClusterDevice == null) ? false : true;
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        FirstPartyDevice firstPartyDevice = this.firstPartyDevice;
        if (firstPartyDevice != null) {
            jSONObject.put("device", firstPartyDevice.toJsonObject());
        } else {
            FirstPartyClusterDevice firstPartyClusterDevice = this.firstPartyClusterDevice;
            if (firstPartyClusterDevice != null) {
                jSONObject.put(FIRST_PARTY_CLUSTER_DEVICE_KEY, firstPartyClusterDevice.toJsonObject());
            } else {
                ThirdPartyDevice thirdPartyDevice = this.thirdPartyDevice;
                if (thirdPartyDevice != null) {
                    jSONObject.put(THIRD_PARTY_DEVICE_KEY, thirdPartyDevice.toJsonObject());
                } else {
                    throw new JSONException("Couldn't serialize device registration request identifier to json");
                }
            }
        }
        return jSONObject;
    }

    public DeviceRegistrationRequestIdentifier(FirstPartyClusterDevice firstPartyClusterDevice) {
        Preconditions.notNull(firstPartyClusterDevice, "firstPartyClusterDevice");
        this.firstPartyClusterDevice = firstPartyClusterDevice;
        this.firstPartyDevice = null;
        this.thirdPartyDevice = null;
    }

    public DeviceRegistrationRequestIdentifier(ThirdPartyDevice thirdPartyDevice) {
        Preconditions.notNull(thirdPartyDevice, THIRD_PARTY_DEVICE_KEY);
        this.thirdPartyDevice = thirdPartyDevice;
        this.firstPartyDevice = null;
        this.firstPartyClusterDevice = null;
    }
}

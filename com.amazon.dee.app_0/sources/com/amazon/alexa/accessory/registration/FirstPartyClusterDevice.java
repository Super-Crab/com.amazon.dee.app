package com.amazon.alexa.accessory.registration;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public final class FirstPartyClusterDevice implements JsonObjectSerializable {
    private static final String CLUSTER_DEVICE_TYPE_KEY = "clusterDeviceType";
    private static final String CONSTITUENT_DEVICES_KEY = "constituentDevices";
    public static final Factory FACTORY = new Factory();
    private final String clusterDeviceType;
    private final List<ConstituentDevice> constituentDevices;

    /* loaded from: classes6.dex */
    public static final class ConstituentDevice implements JsonObjectSerializable {
        private static final String DEVICE_SERIAL_NUMBER_KEY = "deviceSerialNumber";
        private static final String DEVICE_TYPE_KEY = "deviceType";
        public static final Factory FACTORY = new Factory();
        private static final String OS_VERSION_KEY = "osVersion";
        final String deviceSerialNumber;
        final String deviceType;
        final String osVersion;

        /* loaded from: classes6.dex */
        public static final class Factory implements JsonObjectSerializable.Factory<ConstituentDevice> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
            /* renamed from: create */
            public ConstituentDevice mo1239create(JSONObject jSONObject) throws JSONException {
                return new ConstituentDevice(JsonUtils.getStringOrNull(jSONObject, "deviceType"), jSONObject.getString("deviceSerialNumber"), JsonUtils.getStringOrNull(jSONObject, ConstituentDevice.OS_VERSION_KEY));
            }
        }

        public ConstituentDevice(String str, String str2, String str3) {
            Preconditions.notNull(str2, "deviceSerialNumber");
            this.deviceType = str;
            this.deviceSerialNumber = str2;
            this.osVersion = str3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ConstituentDevice.class != obj.getClass()) {
                return false;
            }
            ConstituentDevice constituentDevice = (ConstituentDevice) obj;
            return Objects.equals(this.deviceType, constituentDevice.deviceType) && Objects.equals(this.deviceSerialNumber, constituentDevice.deviceSerialNumber);
        }

        public String getDeviceSerialNumber() {
            return this.deviceSerialNumber;
        }

        @Nullable
        public String getDeviceType() {
            return this.deviceType;
        }

        public int hashCode() {
            return Objects.hash(this.deviceType, this.deviceSerialNumber);
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put("deviceType", this.deviceType).put("deviceSerialNumber", this.deviceSerialNumber).put(OS_VERSION_KEY, this.osVersion);
        }
    }

    /* loaded from: classes6.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<FirstPartyClusterDevice> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public FirstPartyClusterDevice mo1239create(JSONObject jSONObject) throws JSONException {
            return new FirstPartyClusterDevice(JsonUtils.fromJsonArray(jSONObject.getJSONArray(FirstPartyClusterDevice.CONSTITUENT_DEVICES_KEY), ConstituentDevice.FACTORY), JsonUtils.getStringOrNull(jSONObject, FirstPartyClusterDevice.CLUSTER_DEVICE_TYPE_KEY));
        }
    }

    public FirstPartyClusterDevice(List<ConstituentDevice> list, String str) {
        Preconditions.notNull(list, CONSTITUENT_DEVICES_KEY);
        Preconditions.precondition(list.size() <= 1 ? false : true, "Must provide more than 1 constituent devices");
        this.constituentDevices = list;
        this.clusterDeviceType = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FirstPartyClusterDevice.class != obj.getClass()) {
            return false;
        }
        FirstPartyClusterDevice firstPartyClusterDevice = (FirstPartyClusterDevice) obj;
        return Objects.equals(new HashSet(this.constituentDevices), new HashSet(firstPartyClusterDevice.constituentDevices)) && Objects.equals(this.clusterDeviceType, firstPartyClusterDevice.clusterDeviceType);
    }

    public List<ConstituentDevice> getConstituentDevices() {
        return this.constituentDevices;
    }

    public int hashCode() {
        return Objects.hash(new HashSet(this.constituentDevices), this.clusterDeviceType);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(CLUSTER_DEVICE_TYPE_KEY, this.clusterDeviceType).put(CONSTITUENT_DEVICES_KEY, JsonUtils.toJsonArray(this.constituentDevices));
    }

    public FirstPartyClusterDevice(DeviceGroup deviceGroup, Map<Integer, String> map) {
        Preconditions.precondition(deviceGroup.getDevices().size() <= 1 ? false : true, "Devices must have more than one devices");
        Preconditions.notNull(map, "deviceIdToFirmwareVersionmap");
        ArrayList arrayList = new ArrayList(3);
        for (Device device : deviceGroup.getDevices()) {
            arrayList.add(new ConstituentDevice(device.getType(), device.getSerialNumber(), map.get(device.getDeviceId())));
        }
        this.constituentDevices = arrayList;
        this.clusterDeviceType = null;
    }
}

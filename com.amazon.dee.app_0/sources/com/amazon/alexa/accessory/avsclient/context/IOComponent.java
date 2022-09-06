package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class IOComponent implements JsonObjectSerializable {
    private static final String CLUSTER_DEVICE = "clusterDevice";
    private static final String CONNECTION = "connection";
    private static final String CONNECTION_TYPE = "type";
    private static final String CONTEXT = "context";
    private static final String DEVICE_INFO = "deviceInfo";
    private static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
    private static final String DEVICE_TYPE = "deviceType";
    private static final String FIRMWARE_VERSION = "firmwareVersion";
    private static final String TYPE = "type";
    private final BatteryContext batteryContext;
    private final ClusterDevice clusterDevice;
    private final Connection connection;
    private final String deviceType;
    private final String dsn1p;
    private final String firmwareVersion;
    private final Type type;

    /* loaded from: classes.dex */
    public static final class BatteryContext implements JsonObjectSerializable {
        private final String timeOfSample;
        private final int value;

        public BatteryContext(int i, String str) {
            Preconditions.notNull(str, "timeOfSample");
            this.value = i;
            this.timeOfSample = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && BatteryContext.class == obj.getClass() && this.value == ((BatteryContext) obj).value;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.value));
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put("namespace", "Alexa.BatteryLevelSensor").put("name", "batteryLevel").put("value", this.value).put("timeOfSample", this.timeOfSample).put("uncertaintyInMilliseconds", 0);
        }
    }

    /* loaded from: classes.dex */
    public static final class ClusterDevice implements JsonObjectSerializable {
        private final String clusterDeviceSerialNumber;
        private final String clusterDeviceType;

        public ClusterDevice(String str, String str2) {
            Preconditions.notNull(str, "clusterDeviceType");
            Preconditions.notNull(str2, "clusterDeviceSerialNumber");
            this.clusterDeviceType = str;
            this.clusterDeviceSerialNumber = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || ClusterDevice.class != obj.getClass()) {
                return false;
            }
            ClusterDevice clusterDevice = (ClusterDevice) obj;
            return Objects.equals(this.clusterDeviceType, clusterDevice.clusterDeviceType) && Objects.equals(this.clusterDeviceSerialNumber, clusterDevice.clusterDeviceSerialNumber);
        }

        public int hashCode() {
            return Objects.hash(this.clusterDeviceType, this.clusterDeviceSerialNumber);
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put("clusterDeviceType", this.clusterDeviceType).put("clusterDeviceSerialNumber", this.clusterDeviceSerialNumber);
        }
    }

    /* loaded from: classes.dex */
    public enum Connection {
        BLUETOOTH_A2DP,
        BLUETOOTH_HFP,
        BLUETOOTH_LE,
        BLUETOOTH_CLASSIC,
        HDMI,
        INPUT,
        LINE_IN,
        LINE_OUT,
        MICROPHONE,
        OUTPUT,
        SPEAKER
    }

    /* loaded from: classes.dex */
    public enum Type {
        MICROPHONE,
        AUDIO_OUTPUT,
        SCREEN,
        CAMERA
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IOComponent.class != obj.getClass()) {
            return false;
        }
        IOComponent iOComponent = (IOComponent) obj;
        return Objects.equals(this.dsn1p, iOComponent.dsn1p) && Objects.equals(this.deviceType, iOComponent.deviceType) && this.type == iOComponent.type && this.connection == iOComponent.connection && Objects.equals(this.firmwareVersion, iOComponent.firmwareVersion) && Objects.equals(this.clusterDevice, iOComponent.clusterDevice) && Objects.equals(this.batteryContext, iOComponent.batteryContext);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDsn_1p() {
        return this.dsn1p;
    }

    public String getFirmwareVersion() {
        return this.firmwareVersion;
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return Objects.hash(this.dsn1p, this.deviceType, this.type, this.connection, this.firmwareVersion, this.clusterDevice, this.batteryContext);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        JSONObject put = new JSONObject().put("type", this.type).put(CONNECTION, new JSONObject().put("type", this.connection)).put(DEVICE_INFO, new JSONObject().put("deviceType", this.deviceType).put("deviceSerialNumber", this.dsn1p).put("firmwareVersion", this.firmwareVersion));
        BatteryContext batteryContext = this.batteryContext;
        JSONObject jSONObject = null;
        JSONObject put2 = put.put(CONTEXT, batteryContext == null ? null : JsonUtils.toJsonArray(Collections.singletonList(batteryContext)));
        ClusterDevice clusterDevice = this.clusterDevice;
        if (clusterDevice != null) {
            jSONObject = clusterDevice.toJsonObject();
        }
        return put2.put(CLUSTER_DEVICE, jSONObject);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOComponent{dsn1p='");
        GeneratedOutlineSupport1.outline176(outline107, this.dsn1p, Chars.QUOTE, ", deviceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.deviceType, Chars.QUOTE, ", type=");
        outline107.append(this.type);
        outline107.append(", connection=");
        outline107.append(this.connection);
        outline107.append(", firmwareVersion='");
        GeneratedOutlineSupport1.outline176(outline107, this.firmwareVersion, Chars.QUOTE, ", clusterDevice=");
        outline107.append(this.clusterDevice);
        outline107.append(", batteryContext=");
        outline107.append(this.batteryContext);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private IOComponent(Builder builder) {
        Preconditions.notNull(builder.deviceType, "deviceType");
        Preconditions.notNull(builder.dsn1p, "dsn_1p");
        Preconditions.notNull(builder.type, "type");
        Preconditions.notNull(builder.connection, CONNECTION);
        this.dsn1p = builder.dsn1p;
        this.deviceType = builder.deviceType;
        this.type = builder.type;
        this.connection = builder.connection;
        this.firmwareVersion = builder.firmwareVersion;
        this.clusterDevice = builder.clusterDevice;
        this.batteryContext = builder.batteryContext;
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private BatteryContext batteryContext;
        private ClusterDevice clusterDevice;
        private Connection connection;
        private String deviceType;
        private String dsn1p;
        private String firmwareVersion;
        private Type type;

        public Builder(IOComponent iOComponent) {
            this.dsn1p = iOComponent.dsn1p;
            this.deviceType = iOComponent.deviceType;
            this.type = iOComponent.type;
            this.connection = iOComponent.connection;
            this.firmwareVersion = iOComponent.firmwareVersion;
            this.clusterDevice = iOComponent.clusterDevice;
            this.batteryContext = iOComponent.batteryContext;
        }

        public Builder batteryContext(BatteryContext batteryContext) {
            this.batteryContext = batteryContext;
            return this;
        }

        public IOComponent build() {
            return new IOComponent(this);
        }

        public Builder clusterDevice(ClusterDevice clusterDevice) {
            this.clusterDevice = clusterDevice;
            return this;
        }

        public Builder connection(Connection connection) {
            this.connection = connection;
            return this;
        }

        public Builder deviceType(String str) {
            this.deviceType = str;
            return this;
        }

        public Builder dsn1p(String str) {
            this.dsn1p = str;
            return this;
        }

        public Builder firmwareVersion(String str) {
            this.firmwareVersion = str;
            return this;
        }

        public Builder type(Type type) {
            this.type = type;
            return this;
        }

        public Builder() {
        }
    }
}

package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class AutoValue_AccessoryInfo extends AccessoryInfo {
    private final DeviceInfo clusterDeviceInfo;
    private final DeviceInfo deviceInfo;
    private final State state;
    private final String timestamp;

    /* loaded from: classes.dex */
    static final class Builder extends AccessoryInfo.Builder {
        private DeviceInfo clusterDeviceInfo;
        private DeviceInfo deviceInfo;
        private State state;
        private String timestamp;

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo.Builder
        public AccessoryInfo build() {
            String str = "";
            if (this.state == null) {
                str = GeneratedOutlineSupport1.outline72(str, " state");
            }
            if (this.timestamp == null) {
                str = GeneratedOutlineSupport1.outline72(str, " timestamp");
            }
            if (str.isEmpty()) {
                return new AutoValue_AccessoryInfo(this.state, this.deviceInfo, this.clusterDeviceInfo, this.timestamp);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo.Builder
        public AccessoryInfo.Builder setClusterDeviceInfo(@Nullable DeviceInfo deviceInfo) {
            this.clusterDeviceInfo = deviceInfo;
            return this;
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo.Builder
        public AccessoryInfo.Builder setDeviceInfo(@Nullable DeviceInfo deviceInfo) {
            this.deviceInfo = deviceInfo;
            return this;
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo.Builder
        public AccessoryInfo.Builder setState(State state) {
            if (state != null) {
                this.state = state;
                return this;
            }
            throw new NullPointerException("Null state");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo.Builder
        public AccessoryInfo.Builder setTimestamp(String str) {
            if (str != null) {
                this.timestamp = str;
                return this;
            }
            throw new NullPointerException("Null timestamp");
        }
    }

    public boolean equals(Object obj) {
        DeviceInfo deviceInfo;
        DeviceInfo deviceInfo2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessoryInfo)) {
            return false;
        }
        AccessoryInfo accessoryInfo = (AccessoryInfo) obj;
        return this.state.equals(accessoryInfo.getState()) && ((deviceInfo = this.deviceInfo) != null ? deviceInfo.equals(accessoryInfo.getDeviceInfo()) : accessoryInfo.getDeviceInfo() == null) && ((deviceInfo2 = this.clusterDeviceInfo) != null ? deviceInfo2.equals(accessoryInfo.getClusterDeviceInfo()) : accessoryInfo.getClusterDeviceInfo() == null) && this.timestamp.equals(accessoryInfo.getTimestamp());
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo
    @Nullable
    public DeviceInfo getClusterDeviceInfo() {
        return this.clusterDeviceInfo;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo
    @Nullable
    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo
    public State getState() {
        return this.state;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryInfo
    public String getTimestamp() {
        return this.timestamp;
    }

    public int hashCode() {
        int hashCode = (this.state.hashCode() ^ 1000003) * 1000003;
        DeviceInfo deviceInfo = this.deviceInfo;
        int i = 0;
        int hashCode2 = (hashCode ^ (deviceInfo == null ? 0 : deviceInfo.hashCode())) * 1000003;
        DeviceInfo deviceInfo2 = this.clusterDeviceInfo;
        if (deviceInfo2 != null) {
            i = deviceInfo2.hashCode();
        }
        return ((hashCode2 ^ i) * 1000003) ^ this.timestamp.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryInfo{state=");
        outline107.append(this.state);
        outline107.append(", deviceInfo=");
        outline107.append(this.deviceInfo);
        outline107.append(", clusterDeviceInfo=");
        outline107.append(this.clusterDeviceInfo);
        outline107.append(", timestamp=");
        return GeneratedOutlineSupport1.outline91(outline107, this.timestamp, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_AccessoryInfo(State state, @Nullable DeviceInfo deviceInfo, @Nullable DeviceInfo deviceInfo2, String str) {
        this.state = state;
        this.deviceInfo = deviceInfo;
        this.clusterDeviceInfo = deviceInfo2;
        this.timestamp = str;
    }
}

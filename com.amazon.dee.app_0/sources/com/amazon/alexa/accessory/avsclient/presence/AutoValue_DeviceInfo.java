package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.DeviceInfo;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
final class AutoValue_DeviceInfo extends DeviceInfo {
    private final String deviceSerialNumber;
    private final String deviceType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends DeviceInfo.Builder {
        private String deviceSerialNumber;
        private String deviceType;

        @Override // com.amazon.alexa.accessory.avsclient.presence.DeviceInfo.Builder
        public DeviceInfo build() {
            String str = "";
            if (this.deviceType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceType");
            }
            if (this.deviceSerialNumber == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceSerialNumber");
            }
            if (str.isEmpty()) {
                return new AutoValue_DeviceInfo(this.deviceType, this.deviceSerialNumber);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.DeviceInfo.Builder
        public DeviceInfo.Builder setDeviceSerialNumber(String str) {
            if (str != null) {
                this.deviceSerialNumber = str;
                return this;
            }
            throw new NullPointerException("Null deviceSerialNumber");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.DeviceInfo.Builder
        public DeviceInfo.Builder setDeviceType(String str) {
            if (str != null) {
                this.deviceType = str;
                return this;
            }
            throw new NullPointerException("Null deviceType");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) obj;
        return this.deviceType.equals(deviceInfo.getDeviceType()) && this.deviceSerialNumber.equals(deviceInfo.getDeviceSerialNumber());
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.DeviceInfo
    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.DeviceInfo
    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return ((this.deviceType.hashCode() ^ 1000003) * 1000003) ^ this.deviceSerialNumber.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceInfo{deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", deviceSerialNumber=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceSerialNumber, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_DeviceInfo(String str, String str2) {
        this.deviceType = str;
        this.deviceSerialNumber = str2;
    }
}

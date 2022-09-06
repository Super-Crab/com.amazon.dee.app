package com.amazon.alexa.accessory.notificationpublisher.consumption;

import com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
final class AutoValue_GestureEventPayload extends GestureEventPayload {
    private final String deviceAddress;
    private final String deviceType;
    private final int inputAction;
    private final int inputBehavior;
    private final int inputSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends GestureEventPayload.Builder {
        private String deviceAddress;
        private String deviceType;
        private Integer inputAction;
        private Integer inputBehavior;
        private Integer inputSource;

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload build() {
            String str = "";
            if (this.inputSource == null) {
                str = GeneratedOutlineSupport1.outline72(str, " inputSource");
            }
            if (this.inputAction == null) {
                str = GeneratedOutlineSupport1.outline72(str, " inputAction");
            }
            if (this.inputBehavior == null) {
                str = GeneratedOutlineSupport1.outline72(str, " inputBehavior");
            }
            if (this.deviceType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceType");
            }
            if (this.deviceAddress == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceAddress");
            }
            if (str.isEmpty()) {
                return new AutoValue_GestureEventPayload(this.inputSource.intValue(), this.inputAction.intValue(), this.inputBehavior.intValue(), this.deviceType, this.deviceAddress);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload.Builder setDeviceAddress(String str) {
            if (str != null) {
                this.deviceAddress = str;
                return this;
            }
            throw new NullPointerException("Null deviceAddress");
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload.Builder setDeviceType(String str) {
            if (str != null) {
                this.deviceType = str;
                return this;
            }
            throw new NullPointerException("Null deviceType");
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload.Builder setInputAction(int i) {
            this.inputAction = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload.Builder setInputBehavior(int i) {
            this.inputBehavior = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload.Builder
        public GestureEventPayload.Builder setInputSource(int i) {
            this.inputSource = Integer.valueOf(i);
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GestureEventPayload)) {
            return false;
        }
        GestureEventPayload gestureEventPayload = (GestureEventPayload) obj;
        return this.inputSource == gestureEventPayload.getInputSource() && this.inputAction == gestureEventPayload.getInputAction() && this.inputBehavior == gestureEventPayload.getInputBehavior() && this.deviceType.equals(gestureEventPayload.getDeviceType()) && this.deviceAddress.equals(gestureEventPayload.getDeviceAddress());
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload
    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload, com.amazon.alexa.accessory.notificationpublisher.consumption.Payload
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload
    public int getInputAction() {
        return this.inputAction;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload
    public int getInputBehavior() {
        return this.inputBehavior;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.consumption.GestureEventPayload
    public int getInputSource() {
        return this.inputSource;
    }

    public int hashCode() {
        return ((((((((this.inputSource ^ 1000003) * 1000003) ^ this.inputAction) * 1000003) ^ this.inputBehavior) * 1000003) ^ this.deviceType.hashCode()) * 1000003) ^ this.deviceAddress.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GestureEventPayload{inputSource=");
        outline107.append(this.inputSource);
        outline107.append(", inputAction=");
        outline107.append(this.inputAction);
        outline107.append(", inputBehavior=");
        outline107.append(this.inputBehavior);
        outline107.append(", deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", deviceAddress=");
        return GeneratedOutlineSupport1.outline91(outline107, this.deviceAddress, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_GestureEventPayload(int i, int i2, int i3, String str, String str2) {
        this.inputSource = i;
        this.inputAction = i2;
        this.inputBehavior = i3;
        this.deviceType = str;
        this.deviceAddress = str2;
    }
}

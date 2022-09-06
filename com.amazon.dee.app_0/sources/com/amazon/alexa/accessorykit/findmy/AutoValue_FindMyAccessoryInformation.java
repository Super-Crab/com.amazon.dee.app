package com.amazon.alexa.accessorykit.findmy;

import com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_FindMyAccessoryInformation extends FindMyAccessoryInformation {
    private final String deviceSerialNumber;
    private final String deviceType;
    private final FindMyAccessoryInformation.EventCause eventCause;
    private final String identifier;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static final class Builder extends FindMyAccessoryInformation.Builder {
        private String deviceSerialNumber;
        private String deviceType;
        private FindMyAccessoryInformation.EventCause eventCause;
        private String identifier;

        @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation.Builder
        public FindMyAccessoryInformation build() {
            String str = "";
            if (this.deviceSerialNumber == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceSerialNumber");
            }
            if (this.deviceType == null) {
                str = GeneratedOutlineSupport1.outline72(str, " deviceType");
            }
            if (this.eventCause == null) {
                str = GeneratedOutlineSupport1.outline72(str, " eventCause");
            }
            if (this.identifier == null) {
                str = GeneratedOutlineSupport1.outline72(str, " identifier");
            }
            if (str.isEmpty()) {
                return new AutoValue_FindMyAccessoryInformation(this.deviceSerialNumber, this.deviceType, this.eventCause, this.identifier);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation.Builder
        public FindMyAccessoryInformation.Builder setDeviceSerialNumber(String str) {
            if (str != null) {
                this.deviceSerialNumber = str;
                return this;
            }
            throw new NullPointerException("Null deviceSerialNumber");
        }

        @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation.Builder
        public FindMyAccessoryInformation.Builder setDeviceType(String str) {
            if (str != null) {
                this.deviceType = str;
                return this;
            }
            throw new NullPointerException("Null deviceType");
        }

        @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation.Builder
        public FindMyAccessoryInformation.Builder setEventCause(FindMyAccessoryInformation.EventCause eventCause) {
            if (eventCause != null) {
                this.eventCause = eventCause;
                return this;
            }
            throw new NullPointerException("Null eventCause");
        }

        @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation.Builder
        public FindMyAccessoryInformation.Builder setIdentifier(String str) {
            if (str != null) {
                this.identifier = str;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FindMyAccessoryInformation)) {
            return false;
        }
        FindMyAccessoryInformation findMyAccessoryInformation = (FindMyAccessoryInformation) obj;
        return this.deviceSerialNumber.equals(findMyAccessoryInformation.getDeviceSerialNumber()) && this.deviceType.equals(findMyAccessoryInformation.getDeviceType()) && this.eventCause.equals(findMyAccessoryInformation.getEventCause()) && this.identifier.equals(findMyAccessoryInformation.getIdentifier());
    }

    @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation
    public String getDeviceSerialNumber() {
        return this.deviceSerialNumber;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation
    public FindMyAccessoryInformation.EventCause getEventCause() {
        return this.eventCause;
    }

    @Override // com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation
    public String getIdentifier() {
        return this.identifier;
    }

    public int hashCode() {
        return ((((((this.deviceSerialNumber.hashCode() ^ 1000003) * 1000003) ^ this.deviceType.hashCode()) * 1000003) ^ this.eventCause.hashCode()) * 1000003) ^ this.identifier.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FindMyAccessoryInformation{deviceSerialNumber=");
        outline107.append(this.deviceSerialNumber);
        outline107.append(", deviceType=");
        outline107.append(this.deviceType);
        outline107.append(", eventCause=");
        outline107.append(this.eventCause);
        outline107.append(", identifier=");
        return GeneratedOutlineSupport1.outline91(outline107, this.identifier, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_FindMyAccessoryInformation(String str, String str2, FindMyAccessoryInformation.EventCause eventCause, String str3) {
        this.deviceSerialNumber = str;
        this.deviceType = str2;
        this.eventCause = eventCause;
        this.identifier = str3;
    }
}

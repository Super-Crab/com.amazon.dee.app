package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class AutoValue_AccessoryStateChangeEventPayload extends AccessoryStateChangeEventPayload {
    private final List<AccessoryInfo> accessories;
    private final String eventTimestamp;
    private final PersonInfo personInfo;
    private final String token;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends AccessoryStateChangeEventPayload.Builder {
        private List<AccessoryInfo> accessories;
        private String eventTimestamp;
        private PersonInfo personInfo;
        private String token;

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload.Builder
        public AccessoryStateChangeEventPayload build() {
            String str = "";
            if (this.eventTimestamp == null) {
                str = GeneratedOutlineSupport1.outline72(str, " eventTimestamp");
            }
            if (this.accessories == null) {
                str = GeneratedOutlineSupport1.outline72(str, " accessories");
            }
            if (this.personInfo == null) {
                str = GeneratedOutlineSupport1.outline72(str, " personInfo");
            }
            if (str.isEmpty()) {
                return new AutoValue_AccessoryStateChangeEventPayload(this.eventTimestamp, this.accessories, this.personInfo, this.token);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload.Builder
        public AccessoryStateChangeEventPayload.Builder setAccessories(List<AccessoryInfo> list) {
            if (list != null) {
                this.accessories = list;
                return this;
            }
            throw new NullPointerException("Null accessories");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload.Builder
        public AccessoryStateChangeEventPayload.Builder setEventTimestamp(String str) {
            if (str != null) {
                this.eventTimestamp = str;
                return this;
            }
            throw new NullPointerException("Null eventTimestamp");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload.Builder
        public AccessoryStateChangeEventPayload.Builder setPersonInfo(PersonInfo personInfo) {
            if (personInfo != null) {
                this.personInfo = personInfo;
                return this;
            }
            throw new NullPointerException("Null personInfo");
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload.Builder
        public AccessoryStateChangeEventPayload.Builder setToken(@Nullable String str) {
            this.token = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AccessoryStateChangeEventPayload)) {
            return false;
        }
        AccessoryStateChangeEventPayload accessoryStateChangeEventPayload = (AccessoryStateChangeEventPayload) obj;
        if (this.eventTimestamp.equals(accessoryStateChangeEventPayload.getEventTimestamp()) && this.accessories.equals(accessoryStateChangeEventPayload.getAccessories()) && this.personInfo.equals(accessoryStateChangeEventPayload.getPersonInfo())) {
            String str = this.token;
            if (str == null) {
                if (accessoryStateChangeEventPayload.getToken() == null) {
                    return true;
                }
            } else if (str.equals(accessoryStateChangeEventPayload.getToken())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload
    public List<AccessoryInfo> getAccessories() {
        return this.accessories;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload
    public String getEventTimestamp() {
        return this.eventTimestamp;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload
    public PersonInfo getPersonInfo() {
        return this.personInfo;
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.AccessoryStateChangeEventPayload
    @Nullable
    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        int hashCode = (((((this.eventTimestamp.hashCode() ^ 1000003) * 1000003) ^ this.accessories.hashCode()) * 1000003) ^ this.personInfo.hashCode()) * 1000003;
        String str = this.token;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryStateChangeEventPayload{eventTimestamp=");
        outline107.append(this.eventTimestamp);
        outline107.append(", accessories=");
        outline107.append(this.accessories);
        outline107.append(", personInfo=");
        outline107.append(this.personInfo);
        outline107.append(", token=");
        return GeneratedOutlineSupport1.outline91(outline107, this.token, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_AccessoryStateChangeEventPayload(String str, List<AccessoryInfo> list, PersonInfo personInfo, @Nullable String str2) {
        this.eventTimestamp = str;
        this.accessories = list;
        this.personInfo = personInfo;
        this.token = str2;
    }
}

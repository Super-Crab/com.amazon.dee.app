package com.amazon.alexa.accessory.avsclient.presence;

import com.amazon.alexa.accessory.avsclient.presence.PersonInfo;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
final class AutoValue_PersonInfo extends PersonInfo {
    private final String activePersonId;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends PersonInfo.Builder {
        private String activePersonId;

        @Override // com.amazon.alexa.accessory.avsclient.presence.PersonInfo.Builder
        public PersonInfo build() {
            return new AutoValue_PersonInfo(this.activePersonId);
        }

        @Override // com.amazon.alexa.accessory.avsclient.presence.PersonInfo.Builder
        public PersonInfo.Builder setActivePersonId(@Nullable String str) {
            this.activePersonId = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PersonInfo)) {
            return false;
        }
        String str = this.activePersonId;
        String activePersonId = ((PersonInfo) obj).getActivePersonId();
        return str == null ? activePersonId == null : str.equals(activePersonId);
    }

    @Override // com.amazon.alexa.accessory.avsclient.presence.PersonInfo
    @Nullable
    public String getActivePersonId() {
        return this.activePersonId;
    }

    public int hashCode() {
        String str = this.activePersonId;
        return (str == null ? 0 : str.hashCode()) ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("PersonInfo{activePersonId="), this.activePersonId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    private AutoValue_PersonInfo(@Nullable String str) {
        this.activePersonId = str;
    }
}

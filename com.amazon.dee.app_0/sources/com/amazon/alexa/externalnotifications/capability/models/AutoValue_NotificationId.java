package com.amazon.alexa.externalnotifications.capability.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
final class AutoValue_NotificationId extends NotificationId {
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_NotificationId(String str) {
        if (str != null) {
            this.value = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NotificationId)) {
            return false;
        }
        return this.value.equals(((NotificationId) obj).getValue());
    }

    @Override // com.amazon.alexa.externalnotifications.capability.models.StronglyTypedString
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("NotificationId{value="), this.value, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa.alertsca;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_AlertsToken extends AlertsToken {
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertsToken(String str) {
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
        if (!(obj instanceof AlertsToken)) {
            return false;
        }
        return this.value.equals(((AlertsToken) obj).getValue());
    }

    @Override // com.amazon.alexa.alertsca.utils.StronglyTypedString
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("AlertsToken{value="), this.value, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

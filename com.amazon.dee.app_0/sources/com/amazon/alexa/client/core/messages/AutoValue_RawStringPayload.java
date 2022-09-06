package com.amazon.alexa.client.core.messages;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_RawStringPayload extends RawStringPayload {
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_RawStringPayload(String str) {
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
        if (!(obj instanceof RawStringPayload)) {
            return false;
        }
        return this.value.equals(((RawStringPayload) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.messages.RawStringPayload
    public String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode() ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("RawStringPayload{value="), this.value, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

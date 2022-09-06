package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AlexaTimeZone.java */
/* renamed from: com.amazon.alexa.tGb  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0231tGb extends SOo {
    public final String zZm;

    public AbstractC0231tGb(String str) {
        if (str != null) {
            this.zZm = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SOo)) {
            return false;
        }
        return this.zZm.equals(((SOo) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.zZm;
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("AlexaTimeZone{value="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

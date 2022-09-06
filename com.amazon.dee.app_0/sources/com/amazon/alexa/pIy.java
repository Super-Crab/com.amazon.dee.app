package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PlayerCookie.java */
/* loaded from: classes.dex */
public final class pIy extends zYH {
    public final String BIo;

    public pIy(String str) {
        if (str != null) {
            this.BIo = str;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zYH)) {
            return false;
        }
        return this.BIo.equals(((zYH) obj).getValue());
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.BIo;
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("PlayerCookie{value="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallIdentifier.java */
/* renamed from: com.amazon.alexa.YfC  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0185YfC extends MNR {
    public final String BIo;

    public C0185YfC(String str) {
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
        if (!(obj instanceof MNR)) {
            return false;
        }
        return this.BIo.equals(((C0185YfC) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("ApiCallIdentifier{value="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

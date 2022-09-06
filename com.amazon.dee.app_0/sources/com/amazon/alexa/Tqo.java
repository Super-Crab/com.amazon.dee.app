package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DialogTurnIdentifier.java */
/* loaded from: classes.dex */
public final class Tqo extends XWx {
    public final String BIo;
    public final boolean zQM;

    public Tqo(String str, boolean z) {
        if (str != null) {
            this.BIo = str;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XWx)) {
            return false;
        }
        XWx xWx = (XWx) obj;
        return this.BIo.equals(xWx.getValue()) && this.zQM == ((Tqo) xWx).zQM;
    }

    @Override // com.amazon.alexa.client.core.networking.adapters.StronglyTypedString
    public String getValue() {
        return this.BIo;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DialogTurnIdentifier{value=");
        zZm.append(this.BIo);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

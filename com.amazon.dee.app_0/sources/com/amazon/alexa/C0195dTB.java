package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_SendMessageRequest.java */
/* renamed from: com.amazon.alexa.dTB  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0195dTB extends mDr {
    public final JjI BIo;
    public final RrI zZm;

    public C0195dTB(RrI rrI, JjI jjI) {
        if (rrI != null) {
            this.zZm = rrI;
            if (jjI != null) {
                this.BIo = jjI;
                return;
            }
            throw new NullPointerException("Null event");
        }
        throw new NullPointerException("Null requestId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mDr)) {
            return false;
        }
        C0195dTB c0195dTB = (C0195dTB) obj;
        return this.zZm.equals(c0195dTB.zZm) && this.BIo.equals(c0195dTB.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SendMessageRequest{requestId=");
        zZm.append(this.zZm);
        zZm.append(", event=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

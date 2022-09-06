package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_LocationServices.java */
/* renamed from: com.amazon.alexa.eaZ  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0203eaZ extends Xdr {
    public final kNm BIo;
    public final gZN zZm;

    public AbstractC0203eaZ(gZN gzn, kNm knm) {
        if (gzn != null) {
            this.zZm = gzn;
            if (knm != null) {
                this.BIo = knm;
                return;
            }
            throw new NullPointerException("Null status");
        }
        throw new NullPointerException("Null access");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Xdr)) {
            return false;
        }
        AbstractC0203eaZ abstractC0203eaZ = (AbstractC0203eaZ) obj;
        return this.zZm.equals(abstractC0203eaZ.zZm) && this.BIo.equals(abstractC0203eaZ.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("LocationServices{access=");
        zZm.append(this.zZm);
        zZm.append(", status=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

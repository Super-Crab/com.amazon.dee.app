package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PublishCapabilitiesEvent.java */
/* loaded from: classes.dex */
public final class MXm extends rjK {
    public final CAj BIo;
    public final vHh zQM;

    public MXm(CAj cAj, vHh vhh) {
        if (cAj != null) {
            this.BIo = cAj;
            if (vhh != null) {
                this.zQM = vhh;
                return;
            }
            throw new NullPointerException("Null callback");
        }
        throw new NullPointerException("Null request");
    }

    @Override // com.amazon.alexa.rjK
    public CAj BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof rjK)) {
            return false;
        }
        MXm mXm = (MXm) obj;
        return this.BIo.equals(mXm.BIo) && this.zQM.equals(mXm.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PublishCapabilitiesEvent{request=");
        zZm.append(this.BIo);
        zZm.append(", callback=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

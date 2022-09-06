package com.amazon.alexa;

import com.amazon.alexa.AbstractC0209ibG;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_WakeWordInitiator.java */
/* loaded from: classes.dex */
public abstract class VLZ extends AbstractC0209ibG {
    public final kbU BIo;
    public final AbstractC0209ibG.zZm zZm;

    public VLZ(AbstractC0209ibG.zZm zzm, kbU kbu) {
        if (zzm != null) {
            this.zZm = zzm;
            if (kbu != null) {
                this.BIo = kbu;
                return;
            }
            throw new NullPointerException("Null payload");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0209ibG)) {
            return false;
        }
        VLZ vlz = (VLZ) obj;
        return this.zZm.equals(vlz.zZm) && this.BIo.equals(vlz.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("WakeWordInitiator{type=");
        zZm.append(this.zZm);
        zZm.append(", payload=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Alc;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SetDestinationPayload.java */
/* loaded from: classes.dex */
public abstract class PGo extends Alc {
    public final String BIo;
    public final Alc.zZm zZm;

    public PGo(Alc.zZm zzm, @Nullable String str) {
        if (zzm != null) {
            this.zZm = zzm;
            this.BIo = str;
            return;
        }
        throw new NullPointerException("Null destination");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Alc)) {
            return false;
        }
        PGo pGo = (PGo) obj;
        if (this.zZm.equals(pGo.zZm)) {
            String str = this.BIo;
            if (str == null) {
                if (pGo.BIo == null) {
                    return true;
                }
            } else if (str.equals(pGo.BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        String str = this.BIo;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SetDestinationPayload{destination=");
        zZm.append(this.zZm);
        zZm.append(", transportationMode=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

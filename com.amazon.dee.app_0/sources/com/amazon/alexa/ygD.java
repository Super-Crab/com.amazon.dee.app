package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Ims;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AuthorizeDiscoveredPlayersPayload_Player.java */
/* loaded from: classes.dex */
public abstract class ygD extends Ims.zZm {
    public final boolean BIo;
    public final Ims.zZm.AbstractC0011zZm zQM;
    public final FHI zZm;

    public ygD(FHI fhi, boolean z, @Nullable Ims.zZm.AbstractC0011zZm abstractC0011zZm) {
        if (fhi != null) {
            this.zZm = fhi;
            this.BIo = z;
            this.zQM = abstractC0011zZm;
            return;
        }
        throw new NullPointerException("Null localPlayerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ims.zZm)) {
            return false;
        }
        ygD ygd = (ygD) obj;
        if (this.zZm.equals(ygd.zZm) && this.BIo == ygd.BIo) {
            Ims.zZm.AbstractC0011zZm abstractC0011zZm = this.zQM;
            if (abstractC0011zZm == null) {
                if (ygd.zQM == null) {
                    return true;
                }
            } else if (abstractC0011zZm.equals(ygd.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237)) * 1000003;
        Ims.zZm.AbstractC0011zZm abstractC0011zZm = this.zQM;
        return hashCode ^ (abstractC0011zZm == null ? 0 : abstractC0011zZm.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Player{localPlayerId=");
        zZm.append(this.zZm);
        zZm.append(", authorized=");
        zZm.append(this.BIo);
        zZm.append(", metadata=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

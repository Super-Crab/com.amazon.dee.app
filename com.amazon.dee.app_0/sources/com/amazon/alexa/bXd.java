package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Qds;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlayerEventPayload.java */
/* loaded from: classes.dex */
public abstract class bXd extends Qds {
    public final vQe BIo;
    public final Hir zQM;
    public final String zZm;
    public final GWl zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_PlayerEventPayload.java */
    /* loaded from: classes.dex */
    public static final class zZm extends Qds.zZm {
        public vQe BIo;
        public Hir zQM;
        public String zZm;
        public GWl zyO;
    }

    public bXd(String str, vQe vqe, Hir hir, @Nullable GWl gWl) {
        if (str != null) {
            this.zZm = str;
            if (vqe != null) {
                this.BIo = vqe;
                if (hir != null) {
                    this.zQM = hir;
                    this.zyO = gWl;
                    return;
                }
                throw new NullPointerException("Null skillToken");
            }
            throw new NullPointerException("Null playerId");
        }
        throw new NullPointerException("Null eventName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Qds)) {
            return false;
        }
        bXd bxd = (bXd) obj;
        if (this.zZm.equals(bxd.zZm) && this.BIo.equals(bxd.BIo) && this.zQM.equals(bxd.zQM)) {
            GWl gWl = this.zyO;
            if (gWl == null) {
                if (bxd.zyO == null) {
                    return true;
                }
            } else if (gWl.equals(bxd.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        GWl gWl = this.zyO;
        return hashCode ^ (gWl == null ? 0 : gWl.hashCode());
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlayerEventPayload{eventName=");
        zZm2.append(this.zZm);
        zZm2.append(", playerId=");
        zZm2.append(this.BIo);
        zZm2.append(", skillToken=");
        zZm2.append(this.zQM);
        zZm2.append(", playbackSessionId=");
        return C0179Pya.BIo(zZm2, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

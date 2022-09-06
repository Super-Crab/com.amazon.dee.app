package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalMediaPlayerErrorEvent.java */
/* loaded from: classes.dex */
public final class YLU extends FXk {
    public final vQe BIo;
    public final pfe zQM;

    public YLU(@Nullable vQe vqe, pfe pfeVar) {
        this.BIo = vqe;
        if (pfeVar != null) {
            this.zQM = pfeVar;
            return;
        }
        throw new NullPointerException("Null playerError");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FXk)) {
            return false;
        }
        vQe vqe = this.BIo;
        if (vqe != null ? vqe.equals(((YLU) obj).BIo) : ((YLU) obj).BIo == null) {
            if (this.zQM.equals(((YLU) obj).zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        vQe vqe = this.BIo;
        return (((vqe == null ? 0 : vqe.hashCode()) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalMediaPlayerErrorEvent{playerId=");
        zZm.append(this.BIo);
        zZm.append(", playerError=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

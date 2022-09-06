package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AvsExceptionEncounteredEvent.java */
/* loaded from: classes.dex */
public final class xyS extends MwJ {
    public final Ufm BIo;
    public final String zQM;

    public xyS(Ufm ufm, @Nullable String str) {
        if (ufm != null) {
            this.BIo = ufm;
            this.zQM = str;
            return;
        }
        throw new NullPointerException("Null code");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MwJ)) {
            return false;
        }
        xyS xys = (xyS) obj;
        if (this.BIo.equals(xys.BIo)) {
            String str = this.zQM;
            if (str == null) {
                if (xys.zQM == null) {
                    return true;
                }
            } else if (str.equals(xys.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        String str = this.zQM;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AvsExceptionEncounteredEvent{code=");
        zZm.append(this.BIo);
        zZm.append(", description=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

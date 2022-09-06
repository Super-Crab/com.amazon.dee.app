package com.amazon.alexa;

import com.amazon.alexa.Ppr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import javax.annotation.Nullable;
/* compiled from: AutoValue_SettingsUpdateSentEvent.java */
/* loaded from: classes.dex */
public final class IlB extends Ppr {
    public final Ppr.zZm BIo;
    public final boolean zQM;
    public final Integer zyO;

    public IlB(Ppr.zZm zzm, boolean z, @Nullable Integer num) {
        if (zzm != null) {
            this.BIo = zzm;
            this.zQM = z;
            this.zyO = num;
            return;
        }
        throw new NullPointerException("Null settingsUpdateType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ppr)) {
            return false;
        }
        IlB ilB = (IlB) obj;
        if (this.BIo.equals(ilB.BIo) && this.zQM == ilB.zQM) {
            Integer num = this.zyO;
            if (num == null) {
                if (ilB.zyO == null) {
                    return true;
                }
            } else if (num.equals(ilB.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237)) * 1000003;
        Integer num = this.zyO;
        return hashCode ^ (num == null ? 0 : num.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SettingsUpdateSentEvent{settingsUpdateType=");
        zZm.append(this.BIo);
        zZm.append(", successful=");
        zZm.append(this.zQM);
        zZm.append(", responseCode=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

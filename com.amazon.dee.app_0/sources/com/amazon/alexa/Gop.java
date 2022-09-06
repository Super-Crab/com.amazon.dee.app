package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.VIE;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DeviceLockScreenStateCheckEvent.java */
/* loaded from: classes.dex */
public final class Gop extends Qqb {
    public final VIE.BIo BIo;
    public final String zQM;

    public Gop(@Nullable VIE.BIo bIo, @Nullable String str) {
        this.BIo = bIo;
        this.zQM = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Qqb)) {
            return false;
        }
        VIE.BIo bIo = this.BIo;
        if (bIo != null ? bIo.equals(((Gop) obj).BIo) : ((Gop) obj).BIo == null) {
            String str = this.zQM;
            if (str == null) {
                if (((Gop) obj).zQM == null) {
                    return true;
                }
            } else if (str.equals(((Gop) obj).zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        VIE.BIo bIo = this.BIo;
        int i = 0;
        int hashCode = ((bIo == null ? 0 : bIo.hashCode()) ^ 1000003) * 1000003;
        String str = this.zQM;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DeviceLockScreenStateCheckEvent{unlockState=");
        zZm.append(this.BIo);
        zZm.append(", lastTimeInUnlockedState=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

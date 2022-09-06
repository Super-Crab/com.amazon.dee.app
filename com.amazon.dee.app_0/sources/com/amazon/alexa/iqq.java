package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.KLX;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_PhoneCallControllerStatePayload.java */
/* loaded from: classes.dex */
public abstract class iqq extends KLX {
    public final HbJ BIo;
    public final Sas zQM;
    public final List<vUA> zZm;
    public final rAH zyO;

    /* compiled from: $AutoValue_PhoneCallControllerStatePayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends KLX.zZm {
        public HbJ BIo;
        public Sas zQM;
        public List<vUA> zZm;
        public rAH zyO;
    }

    public iqq(@Nullable List<vUA> list, @Nullable HbJ hbJ, Sas sas, @Nullable rAH rah) {
        this.zZm = list;
        this.BIo = hbJ;
        if (sas != null) {
            this.zQM = sas;
            this.zyO = rah;
            return;
        }
        throw new NullPointerException("Null device");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KLX)) {
            return false;
        }
        List<vUA> list = this.zZm;
        if (list != null ? list.equals(((iqq) obj).zZm) : ((iqq) obj).zZm == null) {
            HbJ hbJ = this.BIo;
            if (hbJ != null ? hbJ.equals(((iqq) obj).BIo) : ((iqq) obj).BIo == null) {
                iqq iqqVar = (iqq) obj;
                if (this.zQM.equals(iqqVar.zQM)) {
                    rAH rah = this.zyO;
                    if (rah == null) {
                        if (iqqVar.zyO == null) {
                            return true;
                        }
                    } else if (rah.equals(iqqVar.zyO)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        List<vUA> list = this.zZm;
        int i = 0;
        int hashCode = ((list == null ? 0 : list.hashCode()) ^ 1000003) * 1000003;
        HbJ hbJ = this.BIo;
        int hashCode2 = (((hashCode ^ (hbJ == null ? 0 : hbJ.hashCode())) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        rAH rah = this.zyO;
        if (rah != null) {
            i = rah.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PhoneCallControllerStatePayload{allCalls=");
        zZm2.append(this.zZm);
        zZm2.append(", currentCall=");
        zZm2.append(this.BIo);
        zZm2.append(", device=");
        zZm2.append(this.zQM);
        zZm2.append(", configuration=");
        return C0179Pya.BIo(zZm2, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

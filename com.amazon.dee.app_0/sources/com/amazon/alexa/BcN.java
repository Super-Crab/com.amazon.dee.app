package com.amazon.alexa;

import com.amazon.alexa.VIE;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
import javax.annotation.Nullable;
/* compiled from: $AutoValue_TrustedStatesPayload.java */
/* loaded from: classes.dex */
public abstract class BcN extends pUe {
    public final String BIo;
    public final VIE.zZm zQM;
    public final VIE.BIo zZm;
    public final List<PcE> zyO;

    public BcN(VIE.BIo bIo, @Nullable String str, @Nullable VIE.zZm zzm, List<PcE> list) {
        if (bIo != null) {
            this.zZm = bIo;
            this.BIo = str;
            this.zQM = zzm;
            if (list != null) {
                this.zyO = list;
                return;
            }
            throw new NullPointerException("Null sessionStates");
        }
        throw new NullPointerException("Null unlockState");
    }

    public boolean equals(Object obj) {
        String str;
        VIE.zZm zzm;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pUe)) {
            return false;
        }
        BcN bcN = (BcN) obj;
        return this.zZm.equals(bcN.zZm) && ((str = this.BIo) != null ? str.equals(bcN.BIo) : bcN.BIo == null) && ((zzm = this.zQM) != null ? zzm.equals(bcN.zQM) : bcN.zQM == null) && this.zyO.equals(bcN.zyO);
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        String str = this.BIo;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        VIE.zZm zzm = this.zQM;
        if (zzm != null) {
            i = zzm.hashCode();
        }
        return ((hashCode2 ^ i) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("TrustedStatesPayload{unlockState=");
        zZm.append(this.zZm);
        zZm.append(", lastTimeInUnlockedState=");
        zZm.append(this.BIo);
        zZm.append(", unlockMethod=");
        zZm.append(this.zQM);
        zZm.append(", sessionStates=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

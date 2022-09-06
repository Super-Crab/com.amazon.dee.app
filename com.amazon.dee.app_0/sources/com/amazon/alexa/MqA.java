package com.amazon.alexa;

import com.amazon.alexa.PMW;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import javax.annotation.Nullable;
/* compiled from: AutoValue_DownchannelEstablishmentFailureEvent.java */
/* loaded from: classes.dex */
public final class MqA extends PMW {
    public final PMW.zZm BIo;
    public final Exception zQM;
    public final Integer zyO;

    public MqA(PMW.zZm zzm, @Nullable Exception exc, @Nullable Integer num) {
        if (zzm != null) {
            this.BIo = zzm;
            this.zQM = exc;
            this.zyO = num;
            return;
        }
        throw new NullPointerException("Null downchannelEstablishmentFailureType");
    }

    public boolean equals(Object obj) {
        Exception exc;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PMW)) {
            return false;
        }
        MqA mqA = (MqA) obj;
        if (this.BIo.equals(mqA.BIo) && ((exc = this.zQM) != null ? exc.equals(mqA.zQM) : mqA.zQM == null)) {
            Integer num = this.zyO;
            if (num == null) {
                if (mqA.zyO == null) {
                    return true;
                }
            } else if (num.equals(mqA.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.BIo.hashCode() ^ 1000003) * 1000003;
        Exception exc = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (exc == null ? 0 : exc.hashCode())) * 1000003;
        Integer num = this.zyO;
        if (num != null) {
            i = num.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DownchannelEstablishmentFailureEvent{downchannelEstablishmentFailureType=");
        zZm.append(this.BIo);
        zZm.append(", reason=");
        zZm.append(this.zQM);
        zZm.append(", responseCode=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

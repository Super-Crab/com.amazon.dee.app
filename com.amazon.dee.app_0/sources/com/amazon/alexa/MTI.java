package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UserPerceivedLatencyCalculatedEvent.java */
/* loaded from: classes.dex */
public final class MTI extends WbI {
    public final String BIo;
    public final long JTe;
    public final boolean LPk;
    public final long Qle;
    public final long jiA;
    public final DialogRequestIdentifier zQM;
    public final XWx zyO;

    public MTI(String str, DialogRequestIdentifier dialogRequestIdentifier, XWx xWx, long j, long j2, long j3, boolean z) {
        if (str != null) {
            this.BIo = str;
            if (dialogRequestIdentifier != null) {
                this.zQM = dialogRequestIdentifier;
                if (xWx != null) {
                    this.zyO = xWx;
                    this.jiA = j;
                    this.Qle = j2;
                    this.JTe = j3;
                    this.LPk = z;
                    return;
                }
                throw new NullPointerException("Null dialogTurnIdentifier");
            }
            throw new NullPointerException("Null dialogRequestIdentifier");
        }
        throw new NullPointerException("Null invocationType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WbI)) {
            return false;
        }
        MTI mti = (MTI) obj;
        return this.BIo.equals(mti.BIo) && this.zQM.equals(mti.zQM) && this.zyO.equals(mti.zyO) && this.jiA == mti.jiA && this.Qle == mti.Qle && this.JTe == mti.JTe && this.LPk == mti.LPk;
    }

    public int hashCode() {
        long j = this.jiA;
        long j2 = this.Qle;
        long j3 = this.JTe;
        return ((((((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ (this.LPk ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("UserPerceivedLatencyCalculatedEvent{invocationType=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequestIdentifier=");
        zZm.append(this.zQM);
        zZm.append(", dialogTurnIdentifier=");
        zZm.append(this.zyO);
        zZm.append(", endOfSpeechOffsetRealtimeMs=");
        zZm.append(this.jiA);
        zZm.append(", alexaSpeechStartedRealtimeMs=");
        zZm.append(this.Qle);
        zZm.append(", userPerceivedLatencyMs=");
        zZm.append(this.JTe);
        zZm.append(", opusEncoded=");
        return C0179Pya.zZm(zZm, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

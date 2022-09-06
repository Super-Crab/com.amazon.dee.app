package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioItemStateChangedEvent.java */
/* loaded from: classes.dex */
public final class IyB extends CKO {
    public final AlexaPlayerInfoState BIo;
    public final xNT zQM;
    public final long zyO;

    public IyB(AlexaPlayerInfoState alexaPlayerInfoState, xNT xnt, long j) {
        if (alexaPlayerInfoState != null) {
            this.BIo = alexaPlayerInfoState;
            if (xnt != null) {
                this.zQM = xnt;
                this.zyO = j;
                return;
            }
            throw new NullPointerException("Null audioItemIdentifier");
        }
        throw new NullPointerException("Null playerInfoState");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CKO)) {
            return false;
        }
        IyB iyB = (IyB) obj;
        return this.BIo.equals(iyB.BIo) && this.zQM.equals(iyB.zQM) && this.zyO == iyB.zyO;
    }

    public int hashCode() {
        long j = this.zyO;
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioItemStateChangedEvent{playerInfoState=");
        zZm.append(this.BIo);
        zZm.append(", audioItemIdentifier=");
        zZm.append(this.zQM);
        zZm.append(", offset=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

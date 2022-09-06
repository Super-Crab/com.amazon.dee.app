package com.amazon.alexa;

import com.amazon.alexa.NEv;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallEvent_FailureEvent.java */
/* loaded from: classes.dex */
public final class cUA extends NEv.zZm {
    public final eOP BIo;
    public final ApiCallFailure zQM;

    public cUA(eOP eop, ApiCallFailure apiCallFailure) {
        if (eop != null) {
            this.BIo = eop;
            if (apiCallFailure != null) {
                this.zQM = apiCallFailure;
                return;
            }
            throw new NullPointerException("Null failure");
        }
        throw new NullPointerException("Null apiCallMetadata");
    }

    @Override // com.amazon.alexa.NEv
    public eOP BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NEv.zZm)) {
            return false;
        }
        cUA cua = (cUA) obj;
        return this.BIo.equals(cua.BIo) && this.zQM.equals(cua.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("FailureEvent{apiCallMetadata=");
        zZm.append(this.BIo);
        zZm.append(", failure=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

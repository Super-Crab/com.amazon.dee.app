package com.amazon.alexa;

import com.amazon.alexa.ZAZ;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import okhttp3.Response;
/* compiled from: AutoValue_ResponseReceivedEvent.java */
/* loaded from: classes.dex */
public final class UuN extends ZAZ {
    public final ZAZ.zZm BIo;
    public final TtM jiA;
    public final Response zQM;
    public final eOP zyO;

    public UuN(ZAZ.zZm zzm, Response response, eOP eop, TtM ttM) {
        if (zzm != null) {
            this.BIo = zzm;
            if (response != null) {
                this.zQM = response;
                if (eop != null) {
                    this.zyO = eop;
                    if (ttM != null) {
                        this.jiA = ttM;
                        return;
                    }
                    throw new NullPointerException("Null sendMessageCallback");
                }
                throw new NullPointerException("Null apiCallMetadata");
            }
            throw new NullPointerException("Null response");
        }
        throw new NullPointerException("Null responseInfo");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZAZ)) {
            return false;
        }
        UuN uuN = (UuN) obj;
        return this.BIo.equals(uuN.BIo) && this.zQM.equals(uuN.zQM) && this.zyO.equals(uuN.zyO) && this.jiA.equals(uuN.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ResponseReceivedEvent{responseInfo=");
        zZm.append(this.BIo);
        zZm.append(", response=");
        zZm.append(this.zQM);
        zZm.append(", apiCallMetadata=");
        zZm.append(this.zyO);
        zZm.append(", sendMessageCallback=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

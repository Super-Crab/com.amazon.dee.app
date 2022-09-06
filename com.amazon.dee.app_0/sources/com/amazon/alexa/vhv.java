package com.amazon.alexa;

import com.amazon.alexa.api.ApiName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ApiCallMetadata.java */
/* loaded from: classes.dex */
public final class vhv extends eOP {
    public final MNR BIo;
    public final ApiName zQM;

    public vhv(MNR mnr, ApiName apiName) {
        if (mnr != null) {
            this.BIo = mnr;
            if (apiName != null) {
                this.zQM = apiName;
                return;
            }
            throw new NullPointerException("Null apiName");
        }
        throw new NullPointerException("Null apiCallIdentifier");
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ApiCallMetadata{apiCallIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", apiName=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

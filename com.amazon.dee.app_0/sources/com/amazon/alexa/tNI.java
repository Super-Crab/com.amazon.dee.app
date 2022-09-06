package com.amazon.alexa;

import com.amazon.alexa.api.ApiName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_DeprecatedApiUseEvent.java */
/* loaded from: classes.dex */
public final class tNI extends Vdm {
    public final ApiName BIo;

    public tNI(ApiName apiName) {
        if (apiName != null) {
            this.BIo = apiName;
            return;
        }
        throw new NullPointerException("Null apiName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vdm)) {
            return false;
        }
        return this.BIo.equals(((tNI) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("DeprecatedApiUseEvent{apiName="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

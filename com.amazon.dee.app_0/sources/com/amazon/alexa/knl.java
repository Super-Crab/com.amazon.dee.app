package com.amazon.alexa;

import com.amazon.alexa.ZAZ;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ResponseReceivedEvent_ResponseInfo.java */
/* loaded from: classes.dex */
public final class knl extends ZAZ.zZm {
    public final boolean BIo;
    public final boolean zZm;

    public knl(boolean z, boolean z2) {
        this.zZm = z;
        this.BIo = z2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZAZ.zZm)) {
            return false;
        }
        knl knlVar = (knl) obj;
        return this.zZm == knlVar.zZm && this.BIo == knlVar.BIo;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((this.zZm ? 1231 : 1237) ^ 1000003) * 1000003;
        if (!this.BIo) {
            i = 1237;
        }
        return i2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ResponseInfo{asynchronousResponse=");
        zZm.append(this.zZm);
        zZm.append(", guaranteedDelivery=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

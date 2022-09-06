package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_ExternalComponentStatesCollectedEvent.java */
/* loaded from: classes.dex */
public final class fqV extends UyX {
    public final long BIo;
    public final int zQM;

    public fqV(long j, int i) {
        this.BIo = j;
        this.zQM = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UyX)) {
            return false;
        }
        fqV fqv = (fqV) obj;
        return this.BIo == fqv.BIo && this.zQM == fqv.zQM;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ this.zQM;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalComponentStatesCollectedEvent{collectionTimeInMs=");
        zZm.append(this.BIo);
        zZm.append(", numberOfExternalProviders=");
        return GeneratedOutlineSupport1.outline86(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

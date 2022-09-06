package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalComponentStateCollectedEvent.java */
/* loaded from: classes.dex */
public final class qib extends SuC {
    public final PackageName BIo;
    public final long zQM;
    public final boolean zyO;

    public qib(PackageName packageName, long j, boolean z) {
        if (packageName != null) {
            this.BIo = packageName;
            this.zQM = j;
            this.zyO = z;
            return;
        }
        throw new NullPointerException("Null packageName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SuC)) {
            return false;
        }
        qib qibVar = (qib) obj;
        return this.BIo.equals(qibVar.BIo) && this.zQM == qibVar.zQM && this.zyO == qibVar.zyO;
    }

    public int hashCode() {
        long j = this.zQM;
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ (this.zyO ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalComponentStateCollectedEvent{packageName=");
        zZm.append(this.BIo);
        zZm.append(", collectionTimeMilliseconds=");
        zZm.append(this.zQM);
        zZm.append(", success=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;
/* compiled from: $AutoValue_ChannelState.java */
/* loaded from: classes.dex */
public abstract class hgr extends aNh {
    public final long BIo;
    public final boolean zQM;
    public final dnp zZm;

    public hgr(dnp dnpVar, long j, boolean z) {
        if (dnpVar != null) {
            this.zZm = dnpVar;
            this.BIo = j;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null latestInterfaceName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aNh)) {
            return false;
        }
        hgr hgrVar = (hgr) obj;
        return this.zZm.equals(hgrVar.zZm) && this.BIo == hgrVar.BIo && this.zQM == hgrVar.zQM;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }
}

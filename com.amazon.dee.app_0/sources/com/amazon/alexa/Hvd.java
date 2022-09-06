package com.amazon.alexa;

import com.amazon.alexa.UMd;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
/* compiled from: AutoValue_CapabilityRefreshEvent_FinishedEvent.java */
/* loaded from: classes.dex */
public final class Hvd extends UMd.zZm {
    public final boolean BIo;
    public final int JTe;
    public final int Qle;
    public final long jiA;
    public final boolean zQM;
    public final Set<Capability> zyO;

    public Hvd(boolean z, boolean z2, Set<Capability> set, long j, int i, int i2) {
        this.BIo = z;
        this.zQM = z2;
        if (set != null) {
            this.zyO = set;
            this.jiA = j;
            this.Qle = i;
            this.JTe = i2;
            return;
        }
        throw new NullPointerException("Null getUpdatedCapabilities");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UMd.zZm)) {
            return false;
        }
        Hvd hvd = (Hvd) obj;
        return this.BIo == hvd.BIo && this.zQM == hvd.zQM && this.zyO.equals(hvd.zyO) && this.jiA == hvd.jiA && this.Qle == hvd.Qle && this.JTe == hvd.JTe;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((this.BIo ? 1231 : 1237) ^ 1000003) * 1000003;
        if (!this.zQM) {
            i = 1237;
        }
        long j = this.jiA;
        return ((((((((i2 ^ i) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ this.Qle) * 1000003) ^ this.JTe;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("FinishedEvent{getCapabilitiesWereUpdated=");
        zZm.append(this.BIo);
        zZm.append(", areCapabilityAgentsInternal=");
        zZm.append(this.zQM);
        zZm.append(", getUpdatedCapabilities=");
        zZm.append(this.zyO);
        zZm.append(", getTimeToRefreshCapabilitiesMs=");
        zZm.append(this.jiA);
        zZm.append(", getCountOfCapabilityAgentsToContact=");
        zZm.append(this.Qle);
        zZm.append(", getCountOfCapabilityAgentsResponded=");
        return GeneratedOutlineSupport1.outline86(zZm, this.JTe, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_NetworkBandwidthReportingMetricEvent.java */
/* loaded from: classes.dex */
public final class XWd extends DlG {
    public final int BIo;

    public XWd(int i) {
        this.BIo = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof DlG) && this.BIo == ((XWd) obj).BIo;
    }

    public int hashCode() {
        return this.BIo ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(C0179Pya.zZm("NetworkBandwidthReportingMetricEvent{networkUpstreamBandwidthKbps="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

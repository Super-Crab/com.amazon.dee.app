package com.amazon.alexa;

import com.amazon.alexa.Fwh;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ExternalCapabilityAgentMetricEvent_ECAMultipleAgentsErrorEvent.java */
/* loaded from: classes.dex */
public final class jLE extends Fwh.zZm {
    public final Namespace BIo;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fwh.zZm)) {
            return false;
        }
        return this.BIo.equals(((jLE) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("ECAMultipleAgentsErrorEvent{namespace="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

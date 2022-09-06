package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.interactions.VisualActivityTrackerChannelState;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_VisualActivityTrackerChannelState.java */
/* loaded from: classes.dex */
public abstract class NZn extends VisualActivityTrackerChannelState {
    public final dnp zZm;

    public NZn(dnp dnpVar) {
        if (dnpVar != null) {
            this.zZm = dnpVar;
            return;
        }
        throw new NullPointerException("Null interfaceName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VisualActivityTrackerChannelState)) {
            return false;
        }
        return this.zZm.equals(((NZn) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("VisualActivityTrackerChannelState{interfaceName="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

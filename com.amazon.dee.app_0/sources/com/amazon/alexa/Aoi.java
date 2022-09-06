package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.interactions.ActivityTrackerChannelState;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ActivityTrackerChannelState.java */
/* loaded from: classes.dex */
public abstract class Aoi extends ActivityTrackerChannelState {
    public final long BIo;
    public final dnp zZm;

    public Aoi(dnp dnpVar, long j) {
        if (dnpVar != null) {
            this.zZm = dnpVar;
            this.BIo = j;
            return;
        }
        throw new NullPointerException("Null interfaceName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ActivityTrackerChannelState)) {
            return false;
        }
        Aoi aoi = (Aoi) obj;
        return this.zZm.equals(aoi.zZm) && this.BIo == aoi.BIo;
    }

    public int hashCode() {
        long j = this.BIo;
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ActivityTrackerChannelState{interfaceName=");
        zZm.append(this.zZm);
        zZm.append(", idleTimeInMilliseconds=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

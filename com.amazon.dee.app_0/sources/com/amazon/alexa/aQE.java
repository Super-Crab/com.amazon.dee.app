package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Gzu;
import com.amazon.alexa.client.alexaservice.interactions.VisualActivityTrackerChannelState;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_VisualActivityTrackerPayload.java */
/* loaded from: classes.dex */
public abstract class aQE extends Gzu {
    public final VisualActivityTrackerChannelState zZm;

    /* compiled from: $AutoValue_VisualActivityTrackerPayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends Gzu.zZm {
        public VisualActivityTrackerChannelState zZm;
    }

    public aQE(@Nullable VisualActivityTrackerChannelState visualActivityTrackerChannelState) {
        this.zZm = visualActivityTrackerChannelState;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Gzu)) {
            return false;
        }
        VisualActivityTrackerChannelState visualActivityTrackerChannelState = this.zZm;
        if (visualActivityTrackerChannelState != null) {
            return visualActivityTrackerChannelState.equals(((aQE) obj).zZm);
        }
        return ((aQE) obj).zZm == null;
    }

    public int hashCode() {
        VisualActivityTrackerChannelState visualActivityTrackerChannelState = this.zZm;
        return (visualActivityTrackerChannelState == null ? 0 : visualActivityTrackerChannelState.hashCode()) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("VisualActivityTrackerPayload{focused="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

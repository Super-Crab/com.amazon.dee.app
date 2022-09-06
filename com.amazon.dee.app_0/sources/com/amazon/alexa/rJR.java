package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_MissedExternalCapabilityAddedEvent.java */
/* loaded from: classes.dex */
public final class rJR extends Ega {
    public final int BIo;

    public rJR(int i) {
        this.BIo = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Ega) && this.BIo == ((rJR) obj).BIo;
    }

    public int hashCode() {
        return this.BIo ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(C0179Pya.zZm("MissedExternalCapabilityAddedEvent{numberOfCapabilities="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

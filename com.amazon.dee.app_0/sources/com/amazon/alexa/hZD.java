package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: AutoValue_ClientConnectionDisconnectedEvent.java */
/* loaded from: classes.dex */
public final class hZD extends TWb {
    public final int BIo;

    public hZD(int i) {
        this.BIo = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof TWb) && this.BIo == ((hZD) obj).BIo;
    }

    public int hashCode() {
        return this.BIo ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(C0179Pya.zZm("ClientConnectionDisconnectedEvent{sizeOfConnectedClients="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

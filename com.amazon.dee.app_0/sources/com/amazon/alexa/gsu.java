package com.amazon.alexa;

import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ClientConnectionConnectedEvent.java */
/* loaded from: classes.dex */
public final class gsu extends ZRZ {
    public final int BIo;
    public final ExtendedClient zQM;

    public gsu(int i, ExtendedClient extendedClient) {
        this.BIo = i;
        if (extendedClient != null) {
            this.zQM = extendedClient;
            return;
        }
        throw new NullPointerException("Null getClient");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ZRZ)) {
            return false;
        }
        gsu gsuVar = (gsu) obj;
        return this.BIo == gsuVar.BIo && this.zQM.equals(gsuVar.zQM);
    }

    public int hashCode() {
        return ((this.BIo ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ClientConnectionConnectedEvent{sizeOfConnectedClients=");
        zZm.append(this.BIo);
        zZm.append(", getClient=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

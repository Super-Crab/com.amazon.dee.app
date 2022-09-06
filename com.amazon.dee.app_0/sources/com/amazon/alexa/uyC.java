package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ClientDisconnectedEvent.java */
/* loaded from: classes.dex */
public final class uyC extends xZV {
    public final ExtendedClient BIo;
    public final boolean zQM;

    public uyC(@Nullable ExtendedClient extendedClient, boolean z) {
        this.BIo = extendedClient;
        this.zQM = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof xZV)) {
            return false;
        }
        ExtendedClient extendedClient = this.BIo;
        if (extendedClient != null ? extendedClient.equals(((uyC) obj).BIo) : ((uyC) obj).BIo == null) {
            if (this.zQM == ((uyC) obj).zQM) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        ExtendedClient extendedClient = this.BIo;
        return (((extendedClient == null ? 0 : extendedClient.hashCode()) ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ClientDisconnectedEvent{getClient=");
        zZm.append(this.BIo);
        zZm.append(", wasExpectedDisconnection=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ConnectivityErrorEvent.java */
/* loaded from: classes.dex */
public final class dCD extends FOR {
    public final boolean BIo;
    public final boolean zQM;
    public final DialogRequestIdentifier zyO;

    public dCD(boolean z, boolean z2, @Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        this.BIo = z;
        this.zQM = z2;
        this.zyO = dialogRequestIdentifier;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FOR)) {
            return false;
        }
        dCD dcd = (dCD) obj;
        if (this.BIo == dcd.BIo && this.zQM == dcd.zQM) {
            DialogRequestIdentifier dialogRequestIdentifier = this.zyO;
            if (dialogRequestIdentifier == null) {
                if (dcd.zyO == null) {
                    return true;
                }
            } else if (dialogRequestIdentifier.equals(dcd.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int i2 = ((this.BIo ? 1231 : 1237) ^ 1000003) * 1000003;
        if (!this.zQM) {
            i = 1237;
        }
        int i3 = (i2 ^ i) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.zyO;
        return i3 ^ (dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ConnectivityErrorEvent{isNetworkTransition=");
        zZm.append(this.BIo);
        zZm.append(", isActiveNetworkTooSlow=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestIdentifier=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

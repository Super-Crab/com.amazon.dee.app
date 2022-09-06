package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_RequestIdentifier.java */
/* loaded from: classes.dex */
public final class Fkl extends RrI {
    public final DialogRequestIdentifier BIo;
    public final String zZm;

    public Fkl(String str, @Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        if (str != null) {
            this.zZm = str;
            this.BIo = dialogRequestIdentifier;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RrI)) {
            return false;
        }
        Fkl fkl = (Fkl) obj;
        if (this.zZm.equals(fkl.zZm)) {
            DialogRequestIdentifier dialogRequestIdentifier = this.BIo;
            if (dialogRequestIdentifier == null) {
                if (fkl.BIo == null) {
                    return true;
                }
            } else if (dialogRequestIdentifier.equals(fkl.BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.BIo;
        return hashCode ^ (dialogRequestIdentifier == null ? 0 : dialogRequestIdentifier.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("RequestIdentifier{value=");
        zZm.append(this.zZm);
        zZm.append(", dialogRequestIdentifier=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

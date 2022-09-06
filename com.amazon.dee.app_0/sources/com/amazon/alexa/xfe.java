package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_NewDialogRequestPayload.java */
/* loaded from: classes.dex */
public abstract class xfe extends JTD {
    public final DialogRequestIdentifier zZm;

    public xfe(DialogRequestIdentifier dialogRequestIdentifier) {
        if (dialogRequestIdentifier != null) {
            this.zZm = dialogRequestIdentifier;
            return;
        }
        throw new NullPointerException("Null dialogRequestId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof JTD)) {
            return false;
        }
        return this.zZm.equals(((xfe) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("NewDialogRequestPayload{dialogRequestId="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

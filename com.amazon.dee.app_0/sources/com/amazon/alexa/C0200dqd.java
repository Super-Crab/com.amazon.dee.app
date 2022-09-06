package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.kbp;
/* compiled from: AutoValue_FinishDialogInteractionEvent_SuccessEvent.java */
/* renamed from: com.amazon.alexa.dqd  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0200dqd extends kbp.jiA {
    public final DialogRequestIdentifier BIo;
    public final boolean zQM;

    public C0200dqd(DialogRequestIdentifier dialogRequestIdentifier, boolean z) {
        if (dialogRequestIdentifier != null) {
            this.BIo = dialogRequestIdentifier;
            this.zQM = z;
            return;
        }
        throw new NullPointerException("Null dialogRequestIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbp.jiA)) {
            return false;
        }
        C0200dqd c0200dqd = (C0200dqd) obj;
        return this.BIo.equals(c0200dqd.BIo) && this.zQM == c0200dqd.zQM;
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ (this.zQM ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SuccessEvent{dialogRequestIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

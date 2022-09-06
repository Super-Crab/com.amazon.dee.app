package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.kbp;
/* compiled from: AutoValue_FinishDialogInteractionEvent_AbandonEvent.java */
/* renamed from: com.amazon.alexa.cdA  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0192cdA extends kbp.zZm {
    public final XWx BIo;
    public final boolean jiA;
    public final DialogRequestIdentifier zQM;
    public final mMl zyO;

    public C0192cdA(@Nullable XWx xWx, @Nullable DialogRequestIdentifier dialogRequestIdentifier, mMl mml, boolean z) {
        this.BIo = xWx;
        this.zQM = dialogRequestIdentifier;
        if (mml != null) {
            this.zyO = mml;
            this.jiA = z;
            return;
        }
        throw new NullPointerException("Null abandonReason");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbp.zZm)) {
            return false;
        }
        XWx xWx = this.BIo;
        if (xWx != null ? xWx.equals(((C0192cdA) obj).BIo) : ((C0192cdA) obj).BIo == null) {
            DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
            if (dialogRequestIdentifier != null ? dialogRequestIdentifier.equals(((C0192cdA) obj).zQM) : ((C0192cdA) obj).zQM == null) {
                C0192cdA c0192cdA = (C0192cdA) obj;
                if (this.zyO.equals(c0192cdA.zyO) && this.jiA == c0192cdA.jiA) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        XWx xWx = this.BIo;
        int i = 0;
        int hashCode = ((xWx == null ? 0 : xWx.hashCode()) ^ 1000003) * 1000003;
        DialogRequestIdentifier dialogRequestIdentifier = this.zQM;
        if (dialogRequestIdentifier != null) {
            i = dialogRequestIdentifier.hashCode();
        }
        return ((((hashCode ^ i) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AbandonEvent{dialogTurnIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zQM);
        zZm.append(", abandonReason=");
        zZm.append(this.zyO);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

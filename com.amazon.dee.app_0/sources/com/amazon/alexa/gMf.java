package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.kbp;
import java.util.Map;
/* compiled from: AutoValue_FinishDialogInteractionEvent_FailureEvent.java */
/* loaded from: classes.dex */
public final class gMf extends kbp.zQM {
    public final DialogRequestIdentifier BIo;
    public final boolean jiA;
    public final bij zQM;
    public final Map<String, String> zyO;

    public gMf(DialogRequestIdentifier dialogRequestIdentifier, bij bijVar, @Nullable Map<String, String> map, boolean z) {
        if (dialogRequestIdentifier != null) {
            this.BIo = dialogRequestIdentifier;
            if (bijVar != null) {
                this.zQM = bijVar;
                this.zyO = map;
                this.jiA = z;
                return;
            }
            throw new NullPointerException("Null failureReason");
        }
        throw new NullPointerException("Null dialogRequestIdentifier");
    }

    public boolean equals(Object obj) {
        Map<String, String> map;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbp.zQM)) {
            return false;
        }
        gMf gmf = (gMf) obj;
        return this.BIo.equals(gmf.BIo) && this.zQM.equals(gmf.zQM) && ((map = this.zyO) != null ? map.equals(gmf.zyO) : gmf.zyO == null) && this.jiA == gmf.jiA;
    }

    public int hashCode() {
        int hashCode = (((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        Map<String, String> map = this.zyO;
        return ((hashCode ^ (map == null ? 0 : map.hashCode())) * 1000003) ^ (this.jiA ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("FailureEvent{dialogRequestIdentifier=");
        zZm.append(this.BIo);
        zZm.append(", failureReason=");
        zZm.append(this.zQM);
        zZm.append(", failureInformation=");
        zZm.append(this.zyO);
        zZm.append(", textDialog=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

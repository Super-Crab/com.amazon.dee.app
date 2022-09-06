package com.amazon.alexa;

import com.amazon.alexa.api.AlertType;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlertStoppedEvent.java */
/* loaded from: classes.dex */
public final class TGU extends thq {
    public final String BIo;
    public final AlertType zQM;

    public TGU(String str, AlertType alertType) {
        if (str != null) {
            this.BIo = str;
            if (alertType != null) {
                this.zQM = alertType;
                return;
            }
            throw new NullPointerException("Null alertType");
        }
        throw new NullPointerException("Null alertId");
    }

    @Override // com.amazon.alexa.FpO
    public String BIo() {
        return this.BIo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof thq)) {
            return false;
        }
        thq thqVar = (thq) obj;
        return this.BIo.equals(thqVar.BIo()) && this.zQM.equals(thqVar.zQM());
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlertStoppedEvent{alertId=");
        zZm.append(this.BIo);
        zZm.append(", alertType=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.FpO
    public AlertType zQM() {
        return this.zQM;
    }
}

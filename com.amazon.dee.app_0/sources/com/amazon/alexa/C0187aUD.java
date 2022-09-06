package com.amazon.alexa;

import com.amazon.alexa.api.AlertType;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlertStartedEvent.java */
/* renamed from: com.amazon.alexa.aUD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0187aUD extends aBV {
    public final String BIo;
    public final AlertType zQM;

    public C0187aUD(String str, AlertType alertType) {
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
        if (!(obj instanceof aBV)) {
            return false;
        }
        aBV abv = (aBV) obj;
        return this.BIo.equals(abv.BIo()) && this.zQM.equals(abv.zQM());
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlertStartedEvent{alertId=");
        zZm.append(this.BIo);
        zZm.append(", alertType=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.FpO
    public AlertType zQM() {
        return this.zQM;
    }
}

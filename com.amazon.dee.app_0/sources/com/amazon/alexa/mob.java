package com.amazon.alexa;

import com.amazon.alexa.ZAZ;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_MessageReceivedEvent.java */
/* loaded from: classes.dex */
public final class mob extends BJt {
    public final Message BIo;
    public final ZAZ.zZm zQM;

    public mob(Message message, ZAZ.zZm zzm) {
        if (message != null) {
            this.BIo = message;
            if (zzm != null) {
                this.zQM = zzm;
                return;
            }
            throw new NullPointerException("Null getResponseInfo");
        }
        throw new NullPointerException("Null message");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BJt)) {
            return false;
        }
        mob mobVar = (mob) obj;
        return this.BIo.equals(mobVar.BIo) && this.zQM.equals(mobVar.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("MessageReceivedEvent{message=");
        zZm.append(this.BIo);
        zZm.append(", getResponseInfo=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

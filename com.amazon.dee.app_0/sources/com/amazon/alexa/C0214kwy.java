package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.fuM;
/* compiled from: AutoValue_RecordingEvent_StoppedEvent.java */
/* renamed from: com.amazon.alexa.kwy  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public final class C0214kwy extends fuM.jiA {
    public final fuM.zyO BIo;
    public final DialogRequestIdentifier zQM;

    public C0214kwy(fuM.zyO zyo, DialogRequestIdentifier dialogRequestIdentifier) {
        if (zyo != null) {
            this.BIo = zyo;
            if (dialogRequestIdentifier != null) {
                this.zQM = dialogRequestIdentifier;
                return;
            }
            throw new NullPointerException("Null dialogRequestId");
        }
        throw new NullPointerException("Null stopRecordingSource");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fuM.jiA)) {
            return false;
        }
        C0214kwy c0214kwy = (C0214kwy) obj;
        return this.BIo.equals(c0214kwy.BIo) && this.zQM.equals(c0214kwy.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("StoppedEvent{stopRecordingSource=");
        zZm.append(this.BIo);
        zZm.append(", dialogRequestId=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

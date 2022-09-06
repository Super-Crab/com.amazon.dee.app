package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_CancelMessageEvent.java */
/* loaded from: classes.dex */
public final class NBU extends SQt {
    public final Message BIo;
    public final MessageProcessingCallbacks zQM;

    public NBU(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
        if (message != null) {
            this.BIo = message;
            if (messageProcessingCallbacks != null) {
                this.zQM = messageProcessingCallbacks;
                return;
            }
            throw new NullPointerException("Null messageProcessingCallbacks");
        }
        throw new NullPointerException("Null message");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SQt)) {
            return false;
        }
        NBU nbu = (NBU) obj;
        return this.BIo.equals(nbu.BIo) && this.zQM.equals(nbu.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("CancelMessageEvent{message=");
        zZm.append(this.BIo);
        zZm.append(", messageProcessingCallbacks=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

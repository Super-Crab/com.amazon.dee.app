package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messagesequencer.MessageProcessingCallbacks;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_PreprocessMessageEvent.java */
/* loaded from: classes.dex */
public final class liQ extends Vba {
    public final Message BIo;
    public final MessageProcessingCallbacks zQM;

    public liQ(Message message, MessageProcessingCallbacks messageProcessingCallbacks) {
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
        if (!(obj instanceof Vba)) {
            return false;
        }
        liQ liq = (liQ) obj;
        return this.BIo.equals(liq.BIo) && this.zQM.equals(liq.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("PreprocessMessageEvent{message=");
        zZm.append(this.BIo);
        zZm.append(", messageProcessingCallbacks=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

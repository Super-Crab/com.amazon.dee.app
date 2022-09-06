package com.amazon.alexa;

import com.amazon.alexa.api.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_TextResponseReceivedEvent.java */
/* loaded from: classes.dex */
public final class fru extends AbstractC0208iNL {
    public final TextResponse BIo;

    public fru(TextResponse textResponse) {
        if (textResponse != null) {
            this.BIo = textResponse;
            return;
        }
        throw new NullPointerException("Null textResponse");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0208iNL)) {
            return false;
        }
        return this.BIo.equals(((fru) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("TextResponseReceivedEvent{textResponse="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

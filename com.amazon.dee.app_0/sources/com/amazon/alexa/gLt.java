package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlexaDownEvent.java */
/* loaded from: classes.dex */
public final class gLt extends cXw {
    public final boolean BIo;

    public gLt(boolean z) {
        this.BIo = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof cXw) && this.BIo == ((gLt) obj).BIo;
    }

    public int hashCode() {
        return (this.BIo ? 1231 : 1237) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.zZm(C0179Pya.zZm("AlexaDownEvent{textDialog="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

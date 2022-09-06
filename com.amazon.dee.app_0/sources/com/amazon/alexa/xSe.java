package com.amazon.alexa;

import android.content.Intent;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_UnhandledIntentEvent.java */
/* loaded from: classes.dex */
public final class xSe extends Ehk {
    public final Intent BIo;

    public xSe(Intent intent) {
        if (intent != null) {
            this.BIo = intent;
            return;
        }
        throw new NullPointerException("Null intent");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Ehk)) {
            return false;
        }
        return this.BIo.equals(((xSe) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("UnhandledIntentEvent{intent="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
import java.util.Locale;
/* compiled from: $AutoValue_LocalesPayload.java */
/* loaded from: classes.dex */
public abstract class GUj extends sGd {
    public final List<Locale> zZm;

    public GUj(List<Locale> list) {
        if (list != null) {
            this.zZm = list;
            return;
        }
        throw new NullPointerException("Null locales");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof sGd)) {
            return false;
        }
        return this.zZm.equals(((GUj) obj).zZm);
    }

    public int hashCode() {
        return this.zZm.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("LocalesPayload{locales="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

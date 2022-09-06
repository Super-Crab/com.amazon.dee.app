package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
/* compiled from: $AutoValue_PlayerInfoPayload_Progress.java */
/* loaded from: classes.dex */
public abstract class zfK extends qgo.BIo {
    public final Long zZm;

    public zfK(@Nullable Long l) {
        this.zZm = l;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qgo.BIo)) {
            return false;
        }
        Long l = this.zZm;
        if (l != null) {
            return l.equals(((zfK) obj).zZm);
        }
        return ((zfK) obj).zZm == null;
    }

    public int hashCode() {
        Long l = this.zZm;
        return (l == null ? 0 : l.hashCode()) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("Progress{mediaLength="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

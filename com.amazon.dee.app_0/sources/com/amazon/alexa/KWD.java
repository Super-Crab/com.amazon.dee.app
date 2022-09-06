package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: $AutoValue_SoftwareInfoPayload.java */
/* loaded from: classes.dex */
public abstract class KWD extends wOp {
    public final int zZm;

    public KWD(int i) {
        this.zZm = i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof wOp) && this.zZm == ((KWD) obj).zZm;
    }

    public int hashCode() {
        return this.zZm ^ 1000003;
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline86(C0179Pya.zZm("SoftwareInfoPayload{firmwareVersion="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_WakeWordInitiatorPayload.java */
/* loaded from: classes.dex */
public abstract class fXI extends kbU {
    public final String BIo;
    public final DWt zZm;

    public fXI(@Nullable DWt dWt, @Nullable String str) {
        this.zZm = dWt;
        this.BIo = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kbU)) {
            return false;
        }
        DWt dWt = this.zZm;
        if (dWt != null ? dWt.equals(((fXI) obj).zZm) : ((fXI) obj).zZm == null) {
            String str = this.BIo;
            if (str == null) {
                if (((fXI) obj).BIo == null) {
                    return true;
                }
            } else if (str.equals(((fXI) obj).BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        DWt dWt = this.zZm;
        int i = 0;
        int hashCode = ((dWt == null ? 0 : dWt.hashCode()) ^ 1000003) * 1000003;
        String str = this.BIo;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("WakeWordInitiatorPayload{wakeWordIndices=");
        zZm.append(this.zZm);
        zZm.append(", wakeWord=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ExceptionPayload.java */
/* loaded from: classes.dex */
public abstract class ZGM extends pTS {
    public final String BIo;
    public final String zZm;

    public ZGM(String str, @Nullable String str2) {
        if (str != null) {
            this.zZm = str;
            this.BIo = str2;
            return;
        }
        throw new NullPointerException("Null code");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof pTS)) {
            return false;
        }
        ZGM zgm = (ZGM) obj;
        if (this.zZm.equals(zgm.zZm)) {
            String str = this.BIo;
            if (str == null) {
                if (zgm.BIo == null) {
                    return true;
                }
            } else if (str.equals(zgm.BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        String str = this.BIo;
        return hashCode ^ (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExceptionPayload{code=");
        zZm.append(this.zZm);
        zZm.append(", description=");
        return C0179Pya.zZm(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

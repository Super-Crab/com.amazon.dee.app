package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.LWv;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ExceptionEncounteredPayload.java */
/* loaded from: classes.dex */
public abstract class AgQ extends LWv {
    public final String BIo;
    public final LWv.BIo zZm;

    /* compiled from: $AutoValue_ExceptionEncounteredPayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends LWv.zZm {
        public String BIo;
        public LWv.BIo zZm;
    }

    public AgQ(LWv.BIo bIo, @Nullable String str) {
        if (bIo != null) {
            this.zZm = bIo;
            this.BIo = str;
            return;
        }
        throw new NullPointerException("Null error");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LWv)) {
            return false;
        }
        AgQ agQ = (AgQ) obj;
        if (this.zZm.equals(agQ.zZm)) {
            String str = this.BIo;
            if (str == null) {
                if (agQ.BIo == null) {
                    return true;
                }
            } else if (str.equals(agQ.BIo)) {
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
        StringBuilder zZm2 = C0179Pya.zZm("ExceptionEncounteredPayload{error=");
        zZm2.append(this.zZm);
        zZm2.append(", unparsedDirective=");
        return C0179Pya.zZm(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

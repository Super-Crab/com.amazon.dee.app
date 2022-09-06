package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Date;
import javax.annotation.Nullable;
/* compiled from: AutoValue_ExternalComponentStateCachingPreference.java */
/* loaded from: classes.dex */
public final class Yxp extends kvw {
    public final boolean BIo;
    public final Date zQM;
    public final Namespace zZm;

    public Yxp(Namespace namespace, boolean z, @Nullable Date date) {
        if (namespace != null) {
            this.zZm = namespace;
            this.BIo = z;
            this.zQM = date;
            return;
        }
        throw new NullPointerException("Null namespace");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof kvw)) {
            return false;
        }
        Yxp yxp = (Yxp) obj;
        if (this.zZm.equals(yxp.zZm) && this.BIo == yxp.BIo) {
            Date date = this.zQM;
            if (date == null) {
                if (yxp.zQM == null) {
                    return true;
                }
            } else if (date.equals(yxp.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237)) * 1000003;
        Date date = this.zQM;
        return hashCode ^ (date == null ? 0 : date.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalComponentStateCachingPreference{namespace=");
        zZm.append(this.zZm);
        zZm.append(", cachingEnabled=");
        zZm.append(this.BIo);
        zZm.append(", dateUpdated=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

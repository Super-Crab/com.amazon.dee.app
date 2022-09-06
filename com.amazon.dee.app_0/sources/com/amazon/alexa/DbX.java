package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.jrC;
/* compiled from: AutoValue_AlexaLauncherMetricEvent_DirectiveProcessEvent.java */
/* loaded from: classes.dex */
public final class DbX extends jrC.zZm {
    public final String BIo;
    public final long zQM;
    public final long zyO;

    public /* synthetic */ DbX(String str, long j, long j2, ZII zii) {
        this.BIo = str;
        this.zQM = j;
        this.zyO = j2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof jrC.zZm)) {
            return false;
        }
        DbX dbX = (DbX) obj;
        return this.BIo.equals(dbX.BIo) && this.zQM == dbX.zQM && this.zyO == dbX.zyO;
    }

    public int hashCode() {
        long j = this.zQM;
        long j2 = this.zyO;
        return ((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DirectiveProcessEvent{directiveName=");
        zZm.append(this.BIo);
        zZm.append(", processTimeMillis=");
        zZm.append(this.zQM);
        zZm.append(", userPerceivedLatency=");
        return C0179Pya.zZm(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

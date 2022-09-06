package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.ARM;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Map;
/* compiled from: AutoValue_TextInteractionEvent_ResultEvent.java */
/* loaded from: classes.dex */
public final class uBu extends ARM.BIo {
    public final String BIo;
    public final long Qle;
    public final Map<String, String> jiA;
    public final String zQM;
    public final DialogRequestIdentifier zyO;

    public uBu(String str, String str2, DialogRequestIdentifier dialogRequestIdentifier, @Nullable Map<String, String> map, long j) {
        if (str != null) {
            this.BIo = str;
            if (str2 != null) {
                this.zQM = str2;
                if (dialogRequestIdentifier != null) {
                    this.zyO = dialogRequestIdentifier;
                    this.jiA = map;
                    this.Qle = j;
                    return;
                }
                throw new NullPointerException("Null dialogRequestId");
            }
            throw new NullPointerException("Null invocationType");
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        Map<String, String> map;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ARM.BIo)) {
            return false;
        }
        uBu ubu = (uBu) obj;
        return this.BIo.equals(ubu.BIo) && this.zQM.equals(ubu.zQM) && this.zyO.equals(ubu.zyO) && ((map = this.jiA) != null ? map.equals(ubu.jiA) : ubu.jiA == null) && this.Qle == ubu.Qle;
    }

    public int hashCode() {
        int hashCode = (((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003;
        Map<String, String> map = this.jiA;
        int hashCode2 = map == null ? 0 : map.hashCode();
        long j = this.Qle;
        return ((hashCode ^ hashCode2) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ResultEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zyO);
        zZm.append(", failureInformation=");
        zZm.append(this.jiA);
        zZm.append(", time=");
        return C0179Pya.zZm(zZm, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

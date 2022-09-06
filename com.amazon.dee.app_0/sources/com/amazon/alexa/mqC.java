package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.BaP;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Map;
/* compiled from: AutoValue_VoiceInteractionEvent_ResultEvent.java */
/* loaded from: classes.dex */
public final class mqC extends BaP.zQM {
    public final String BIo;
    public final long Qle;
    public final Map<String, String> jiA;
    public final String zQM;
    public final DialogRequestIdentifier zyO;

    public mqC(String str, String str2, DialogRequestIdentifier dialogRequestIdentifier, @Nullable Map<String, String> map, long j) {
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

    @Override // com.amazon.alexa.BaP
    public DialogRequestIdentifier BIo() {
        return this.zyO;
    }

    public boolean equals(Object obj) {
        Map<String, String> map;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaP.zQM)) {
            return false;
        }
        mqC mqc = (mqC) obj;
        return this.BIo.equals(mqc.BIo) && this.zQM.equals(mqc.zQM) && this.zyO.equals(mqc.zyO) && ((map = this.jiA) != null ? map.equals(mqc.jiA) : mqc.jiA == null) && this.Qle == mqc.Qle;
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

    @Override // com.amazon.alexa.BaP
    public String zQM() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.BaP
    public String zyO() {
        return this.BIo;
    }
}

package com.amazon.alexa;

import com.amazon.alexa.ARM;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_TextInteractionEvent_AttemptEvent.java */
/* loaded from: classes.dex */
public final class zaQ extends ARM.zZm {
    public final String BIo;
    public final String jiA;
    public final String zQM;
    public final DialogRequestIdentifier zyO;

    public zaQ(String str, String str2, DialogRequestIdentifier dialogRequestIdentifier, String str3) {
        if (str != null) {
            this.BIo = str;
            if (str2 != null) {
                this.zQM = str2;
                if (dialogRequestIdentifier != null) {
                    this.zyO = dialogRequestIdentifier;
                    if (str3 != null) {
                        this.jiA = str3;
                        return;
                    }
                    throw new NullPointerException("Null softwareVersion");
                }
                throw new NullPointerException("Null dialogRequestId");
            }
            throw new NullPointerException("Null invocationType");
        }
        throw new NullPointerException("Null name");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ARM.zZm)) {
            return false;
        }
        zaQ zaq = (zaQ) obj;
        return this.BIo.equals(zaq.BIo) && this.zQM.equals(zaq.zQM) && this.zyO.equals(zaq.zyO) && this.jiA.equals(zaq.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AttemptEvent{name=");
        zZm.append(this.BIo);
        zZm.append(", invocationType=");
        zZm.append(this.zQM);
        zZm.append(", dialogRequestId=");
        zZm.append(this.zyO);
        zZm.append(", softwareVersion=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

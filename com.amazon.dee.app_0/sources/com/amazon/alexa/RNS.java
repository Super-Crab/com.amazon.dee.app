package com.amazon.alexa;

import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ScheduleInteractionEvent.java */
/* loaded from: classes.dex */
public final class RNS extends mZe {
    public final aVo BIo;
    public final DialogRequestIdentifier jiA;
    public final jDH zQM;
    public final PJz zyO;

    public RNS(aVo avo, jDH jdh, PJz pJz, DialogRequestIdentifier dialogRequestIdentifier) {
        if (avo != null) {
            this.BIo = avo;
            if (jdh != null) {
                this.zQM = jdh;
                if (pJz != null) {
                    this.zyO = pJz;
                    if (dialogRequestIdentifier != null) {
                        this.jiA = dialogRequestIdentifier;
                        return;
                    }
                    throw new NullPointerException("Null dialogRequestIdentifier");
                }
                throw new NullPointerException("Null audioMetadata");
            }
            throw new NullPointerException("Null interaction");
        }
        throw new NullPointerException("Null channelType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof mZe)) {
            return false;
        }
        RNS rns = (RNS) obj;
        return this.BIo.equals(rns.BIo) && this.zQM.equals(rns.zQM) && this.zyO.equals(rns.zyO) && this.jiA.equals(rns.jiA);
    }

    public int hashCode() {
        return ((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ScheduleInteractionEvent{channelType=");
        zZm.append(this.BIo);
        zZm.append(", interaction=");
        zZm.append(this.zQM);
        zZm.append(", audioMetadata=");
        zZm.append(this.zyO);
        zZm.append(", dialogRequestIdentifier=");
        return C0179Pya.BIo(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

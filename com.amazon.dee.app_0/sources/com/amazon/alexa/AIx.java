package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_ScheduleVisualInteractionEvent.java */
/* loaded from: classes.dex */
public final class AIx extends qUD {
    public final JiQ BIo;
    public final AbstractC0239xsr zQM;

    public AIx(JiQ jiQ, AbstractC0239xsr abstractC0239xsr) {
        if (jiQ != null) {
            this.BIo = jiQ;
            if (abstractC0239xsr != null) {
                this.zQM = abstractC0239xsr;
                return;
            }
            throw new NullPointerException("Null interaction");
        }
        throw new NullPointerException("Null channelType");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qUD)) {
            return false;
        }
        AIx aIx = (AIx) obj;
        return this.BIo.equals(aIx.BIo) && this.zQM.equals(aIx.zQM);
    }

    public int hashCode() {
        return ((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ScheduleVisualInteractionEvent{channelType=");
        zZm.append(this.BIo);
        zZm.append(", interaction=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

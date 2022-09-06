package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AudioItem.java */
/* loaded from: classes.dex */
public abstract class QCY extends BIn {
    public final fcj BIo;
    public final xNT zZm;

    public QCY(xNT xnt, fcj fcjVar) {
        if (xnt != null) {
            this.zZm = xnt;
            if (fcjVar != null) {
                this.BIo = fcjVar;
                return;
            }
            throw new NullPointerException("Null stream");
        }
        throw new NullPointerException("Null audioItemId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BIn)) {
            return false;
        }
        QCY qcy = (QCY) obj;
        return this.zZm.equals(qcy.zZm) && this.BIo.equals(qcy.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AudioItem{audioItemId=");
        zZm.append(this.zZm);
        zZm.append(", stream=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

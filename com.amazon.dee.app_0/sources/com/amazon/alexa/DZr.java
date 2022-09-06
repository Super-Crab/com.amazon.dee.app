package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_ExternalMediaPlayerStatePayload.java */
/* loaded from: classes.dex */
public abstract class DZr extends BGK {
    public final AbstractC0188bKf BIo;
    public final vQe zQM;
    public final String zZm;
    public final Set<HkJ> zyO;

    public DZr(String str, AbstractC0188bKf abstractC0188bKf, vQe vqe, Set<HkJ> set) {
        if (str != null) {
            this.zZm = str;
            if (abstractC0188bKf != null) {
                this.BIo = abstractC0188bKf;
                if (vqe != null) {
                    this.zQM = vqe;
                    if (set != null) {
                        this.zyO = set;
                        return;
                    }
                    throw new NullPointerException("Null players");
                }
                throw new NullPointerException("Null playerInFocus");
            }
            throw new NullPointerException("Null spiVersion");
        }
        throw new NullPointerException("Null agent");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BGK)) {
            return false;
        }
        DZr dZr = (DZr) obj;
        return this.zZm.equals(dZr.zZm) && this.BIo.equals(dZr.BIo) && this.zQM.equals(dZr.zQM) && this.zyO.equals(dZr.zyO);
    }

    public int hashCode() {
        return ((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalMediaPlayerStatePayload{agent=");
        zZm.append(this.zZm);
        zZm.append(", spiVersion=");
        zZm.append(this.BIo);
        zZm.append(", playerInFocus=");
        zZm.append(this.zQM);
        zZm.append(", players=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

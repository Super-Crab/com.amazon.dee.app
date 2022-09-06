package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.vST;
import java.util.Set;
/* compiled from: $AutoValue_ReportDiscoveredPlayersPayload_Player.java */
/* loaded from: classes.dex */
public abstract class GZF extends vST.BIo {
    public final AbstractC0188bKf BIo;
    public final Set<String> zQM;
    public final FHI zZm;
    public final vST.BIo.zZm zyO;

    public GZF(FHI fhi, AbstractC0188bKf abstractC0188bKf, Set<String> set, vST.BIo.zZm zzm) {
        if (fhi != null) {
            this.zZm = fhi;
            if (abstractC0188bKf != null) {
                this.BIo = abstractC0188bKf;
                if (set != null) {
                    this.zQM = set;
                    if (zzm != null) {
                        this.zyO = zzm;
                        return;
                    }
                    throw new NullPointerException("Null validationMethod");
                }
                throw new NullPointerException("Null validationData");
            }
            throw new NullPointerException("Null spiVersion");
        }
        throw new NullPointerException("Null localPlayerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vST.BIo)) {
            return false;
        }
        GZF gzf = (GZF) obj;
        return this.zZm.equals(gzf.zZm) && this.BIo.equals(gzf.BIo) && this.zQM.equals(gzf.zQM) && this.zyO.equals(gzf.zyO);
    }

    public int hashCode() {
        return ((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Player{localPlayerId=");
        zZm.append(this.zZm);
        zZm.append(", spiVersion=");
        zZm.append(this.BIo);
        zZm.append(", validationData=");
        zZm.append(this.zQM);
        zZm.append(", validationMethod=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Rgi;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlayerErrorPayload.java */
/* loaded from: classes.dex */
public abstract class qQM extends Rgi {
    public final Long BIo;
    public final GWl JTe;
    public final Hir Qle;
    public final vQe jiA;
    public final String zQM;
    public final Iof zZm;
    public final Boolean zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_PlayerErrorPayload.java */
    /* loaded from: classes.dex */
    public static final class zZm extends Rgi.zZm {
        public Long BIo;
        public GWl JTe;
        public Hir Qle;
        public vQe jiA;
        public String zQM;
        public Iof zZm;
        public Boolean zyO;
    }

    public qQM(Iof iof, Long l, String str, Boolean bool, @Nullable vQe vqe, @Nullable Hir hir, @Nullable GWl gWl) {
        if (iof != null) {
            this.zZm = iof;
            if (l != null) {
                this.BIo = l;
                if (str != null) {
                    this.zQM = str;
                    if (bool != null) {
                        this.zyO = bool;
                        this.jiA = vqe;
                        this.Qle = hir;
                        this.JTe = gWl;
                        return;
                    }
                    throw new NullPointerException("Null fatal");
                }
                throw new NullPointerException("Null description");
            }
            throw new NullPointerException("Null code");
        }
        throw new NullPointerException("Null errorName");
    }

    public boolean equals(Object obj) {
        vQe vqe;
        Hir hir;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Rgi)) {
            return false;
        }
        qQM qqm = (qQM) obj;
        if (this.zZm.equals(qqm.zZm) && this.BIo.equals(qqm.BIo) && this.zQM.equals(qqm.zQM) && this.zyO.equals(qqm.zyO) && ((vqe = this.jiA) != null ? vqe.equals(qqm.jiA) : qqm.jiA == null) && ((hir = this.Qle) != null ? hir.equals(qqm.Qle) : qqm.Qle == null)) {
            GWl gWl = this.JTe;
            if (gWl == null) {
                if (qqm.JTe == null) {
                    return true;
                }
            } else if (gWl.equals(qqm.JTe)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003;
        vQe vqe = this.jiA;
        int i = 0;
        int hashCode2 = (hashCode ^ (vqe == null ? 0 : vqe.hashCode())) * 1000003;
        Hir hir = this.Qle;
        int hashCode3 = (hashCode2 ^ (hir == null ? 0 : hir.hashCode())) * 1000003;
        GWl gWl = this.JTe;
        if (gWl != null) {
            i = gWl.hashCode();
        }
        return hashCode3 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlayerErrorPayload{errorName=");
        zZm2.append(this.zZm);
        zZm2.append(", code=");
        zZm2.append(this.BIo);
        zZm2.append(", description=");
        zZm2.append(this.zQM);
        zZm2.append(", fatal=");
        zZm2.append(this.zyO);
        zZm2.append(", playerId=");
        zZm2.append(this.jiA);
        zZm2.append(", skillToken=");
        zZm2.append(this.Qle);
        zZm2.append(", playbackSessionId=");
        return C0179Pya.BIo(zZm2, this.JTe, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

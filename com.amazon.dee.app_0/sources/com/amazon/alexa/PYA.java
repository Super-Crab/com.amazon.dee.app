package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Kyp;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_PlayerStructure;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: $AutoValue_PlayerStructure.java */
/* loaded from: classes.dex */
public abstract class PYA extends Kyp {
    public final NdN BIo;
    public final XSR JTe;
    public final MAh LPk;
    public final AKJ Qle;
    public final long jiA;
    public final Set<rjL> zQM;
    public final vQe zZm;
    public final AbstractC0197ddD zyO;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_PlayerStructure.java */
    /* loaded from: classes.dex */
    public static final class zZm extends Kyp.zZm {
        public NdN BIo;
        public XSR JTe;
        public MAh LPk;
        public AKJ Qle;
        public Long jiA;
        public Set<rjL> zQM;
        public vQe zZm;
        public AbstractC0197ddD zyO;

        @Override // com.amazon.alexa.Kyp.zZm
        public Kyp.zZm zZm(long j) {
            this.jiA = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.Kyp.zZm
        public Kyp zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " playerId");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " positionMilliseconds");
            }
            if (str.isEmpty()) {
                return new AutoValue_PlayerStructure(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA.longValue(), this.Qle, this.JTe, this.LPk);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    public PYA(vQe vqe, @Nullable NdN ndN, @Nullable Set<rjL> set, @Nullable AbstractC0197ddD abstractC0197ddD, long j, @Nullable AKJ akj, @Nullable XSR xsr, @Nullable MAh mAh) {
        if (vqe != null) {
            this.zZm = vqe;
            this.BIo = ndN;
            this.zQM = set;
            this.zyO = abstractC0197ddD;
            this.jiA = j;
            this.Qle = akj;
            this.JTe = xsr;
            this.LPk = mAh;
            return;
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        NdN ndN;
        Set<rjL> set;
        AbstractC0197ddD abstractC0197ddD;
        AKJ akj;
        XSR xsr;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Kyp)) {
            return false;
        }
        PYA pya = (PYA) obj;
        if (this.zZm.equals(pya.zZm) && ((ndN = this.BIo) != null ? ndN.equals(pya.BIo) : pya.BIo == null) && ((set = this.zQM) != null ? set.equals(pya.zQM) : pya.zQM == null) && ((abstractC0197ddD = this.zyO) != null ? abstractC0197ddD.equals(pya.zyO) : pya.zyO == null) && this.jiA == pya.jiA && ((akj = this.Qle) != null ? akj.equals(pya.Qle) : pya.Qle == null) && ((xsr = this.JTe) != null ? xsr.equals(pya.JTe) : pya.JTe == null)) {
            MAh mAh = this.LPk;
            if (mAh == null) {
                if (pya.LPk == null) {
                    return true;
                }
            } else if (mAh.equals(pya.LPk)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.zZm.hashCode() ^ 1000003) * 1000003;
        NdN ndN = this.BIo;
        int i = 0;
        int hashCode2 = (hashCode ^ (ndN == null ? 0 : ndN.hashCode())) * 1000003;
        Set<rjL> set = this.zQM;
        int hashCode3 = (hashCode2 ^ (set == null ? 0 : set.hashCode())) * 1000003;
        AbstractC0197ddD abstractC0197ddD = this.zyO;
        int hashCode4 = abstractC0197ddD == null ? 0 : abstractC0197ddD.hashCode();
        long j = this.jiA;
        int i2 = (((hashCode3 ^ hashCode4) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        AKJ akj = this.Qle;
        int hashCode5 = (i2 ^ (akj == null ? 0 : akj.hashCode())) * 1000003;
        XSR xsr = this.JTe;
        int hashCode6 = (hashCode5 ^ (xsr == null ? 0 : xsr.hashCode())) * 1000003;
        MAh mAh = this.LPk;
        if (mAh != null) {
            i = mAh.hashCode();
        }
        return hashCode6 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlayerStructure{playerId=");
        zZm2.append(this.zZm);
        zZm2.append(", state=");
        zZm2.append(this.BIo);
        zZm2.append(", supportedOperations=");
        zZm2.append(this.zQM);
        zZm2.append(", media=");
        zZm2.append(this.zyO);
        zZm2.append(", positionMilliseconds=");
        zZm2.append(this.jiA);
        zZm2.append(", shuffle=");
        zZm2.append(this.Qle);
        zZm2.append(", repeat=");
        zZm2.append(this.JTe);
        zZm2.append(", favorite=");
        return C0179Pya.BIo(zZm2, this.LPk, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

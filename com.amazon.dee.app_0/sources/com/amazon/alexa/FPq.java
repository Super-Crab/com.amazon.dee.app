package com.amazon.alexa;

import com.amazon.alexa.UTs;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_SingleTargetResponseEventPayload.java */
/* loaded from: classes.dex */
public abstract class FPq extends UTs {
    public final String BIo;
    public final List<IJL> jiA;
    public final StC zQM;
    public final IUU zZm;
    public final Zxk zyO;

    /* compiled from: $AutoValue_SingleTargetResponseEventPayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends UTs.zZm {
        public String BIo;
        public List<IJL> jiA;
        public StC zQM;
        public IUU zZm;
        public Zxk zyO;
    }

    public FPq(IUU iuu, String str, StC stC, Zxk zxk, List<IJL> list) {
        if (iuu != null) {
            this.zZm = iuu;
            if (str != null) {
                this.BIo = str;
                if (stC != null) {
                    this.zQM = stC;
                    if (zxk != null) {
                        this.zyO = zxk;
                        if (list != null) {
                            this.jiA = list;
                            return;
                        }
                        throw new NullPointerException("Null reasons");
                    }
                    throw new NullPointerException("Null outcome");
                }
                throw new NullPointerException("Null target");
            }
            throw new NullPointerException("Null type");
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UTs)) {
            return false;
        }
        FPq fPq = (FPq) obj;
        return this.zZm.equals(fPq.zZm) && this.BIo.equals(fPq.BIo) && this.zQM.equals(fPq.zQM) && this.zyO.equals(fPq.zyO) && this.jiA.equals(fPq.jiA);
    }

    public int hashCode() {
        return ((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("SingleTargetResponseEventPayload{token=");
        zZm2.append(this.zZm);
        zZm2.append(", type=");
        zZm2.append(this.BIo);
        zZm2.append(", target=");
        zZm2.append(this.zQM);
        zZm2.append(", outcome=");
        zZm2.append(this.zyO);
        zZm2.append(", reasons=");
        return C0179Pya.BIo(zZm2, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

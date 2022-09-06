package com.amazon.alexa;

import com.amazon.alexa.Jqr;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_MultipleTargetsResponseEventPayload.java */
/* loaded from: classes.dex */
public abstract class IHD extends Jqr {
    public final String BIo;
    public final String Qle;
    public final List<IJL> jiA;
    public final List<StC> zQM;
    public final IUU zZm;
    public final Zxk zyO;

    /* compiled from: $AutoValue_MultipleTargetsResponseEventPayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends Jqr.zZm {
        public String BIo;
        public String Qle;
        public List<IJL> jiA;
        public List<StC> zQM;
        public IUU zZm;
        public Zxk zyO;
    }

    public IHD(IUU iuu, String str, List<StC> list, Zxk zxk, List<IJL> list2, String str2) {
        if (iuu != null) {
            this.zZm = iuu;
            if (str != null) {
                this.BIo = str;
                if (list != null) {
                    this.zQM = list;
                    if (zxk != null) {
                        this.zyO = zxk;
                        if (list2 != null) {
                            this.jiA = list2;
                            if (str2 != null) {
                                this.Qle = str2;
                                return;
                            }
                            throw new NullPointerException("Null description");
                        }
                        throw new NullPointerException("Null reasons");
                    }
                    throw new NullPointerException("Null outcome");
                }
                throw new NullPointerException("Null targets");
            }
            throw new NullPointerException("Null type");
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Jqr)) {
            return false;
        }
        IHD ihd = (IHD) obj;
        return this.zZm.equals(ihd.zZm) && this.BIo.equals(ihd.BIo) && this.zQM.equals(ihd.zQM) && this.zyO.equals(ihd.zyO) && this.jiA.equals(ihd.jiA) && this.Qle.equals(ihd.Qle);
    }

    public int hashCode() {
        return ((((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("MultipleTargetsResponseEventPayload{token=");
        zZm2.append(this.zZm);
        zZm2.append(", type=");
        zZm2.append(this.BIo);
        zZm2.append(", targets=");
        zZm2.append(this.zQM);
        zZm2.append(", outcome=");
        zZm2.append(this.zyO);
        zZm2.append(", reasons=");
        zZm2.append(this.jiA);
        zZm2.append(", description=");
        return C0179Pya.zZm(zZm2, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

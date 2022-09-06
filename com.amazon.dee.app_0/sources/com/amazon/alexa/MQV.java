package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.Suv;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Date;
/* compiled from: $AutoValue_GeolocationStatePayload.java */
/* loaded from: classes.dex */
public abstract class MQV extends Suv {
    public final Date BIo;
    public final rfd Qle;
    public final wze jiA;
    public final hYy zQM;
    public final Xdr zZm;
    public final Gkq zyO;

    public MQV(Xdr xdr, Date date, @Nullable hYy hyy, @Nullable Gkq gkq, @Nullable wze wzeVar, @Nullable rfd rfdVar) {
        if (xdr != null) {
            this.zZm = xdr;
            if (date != null) {
                this.BIo = date;
                this.zQM = hyy;
                this.zyO = gkq;
                this.jiA = wzeVar;
                this.Qle = rfdVar;
                return;
            }
            throw new NullPointerException("Null timestamp");
        }
        throw new NullPointerException("Null locationServices");
    }

    public boolean equals(Object obj) {
        hYy hyy;
        Gkq gkq;
        wze wzeVar;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Suv)) {
            return false;
        }
        MQV mqv = (MQV) obj;
        if (this.zZm.equals(mqv.zZm) && this.BIo.equals(mqv.BIo) && ((hyy = this.zQM) != null ? hyy.equals(mqv.zQM) : mqv.zQM == null) && ((gkq = this.zyO) != null ? gkq.equals(mqv.zyO) : mqv.zyO == null) && ((wzeVar = this.jiA) != null ? wzeVar.equals(mqv.jiA) : mqv.jiA == null)) {
            rfd rfdVar = this.Qle;
            if (rfdVar == null) {
                if (mqv.Qle == null) {
                    return true;
                }
            } else if (rfdVar.equals(mqv.Qle)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        hYy hyy = this.zQM;
        int i = 0;
        int hashCode2 = (hashCode ^ (hyy == null ? 0 : hyy.hashCode())) * 1000003;
        Gkq gkq = this.zyO;
        int hashCode3 = (hashCode2 ^ (gkq == null ? 0 : gkq.hashCode())) * 1000003;
        wze wzeVar = this.jiA;
        int hashCode4 = (hashCode3 ^ (wzeVar == null ? 0 : wzeVar.hashCode())) * 1000003;
        rfd rfdVar = this.Qle;
        if (rfdVar != null) {
            i = rfdVar.hashCode();
        }
        return hashCode4 ^ i;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("GeolocationStatePayload{locationServices=");
        zZm2.append(this.zZm);
        zZm2.append(", timestamp=");
        zZm2.append(this.BIo);
        zZm2.append(", coordinate=");
        zZm2.append(this.zQM);
        zZm2.append(", altitude=");
        zZm2.append(this.zyO);
        zZm2.append(", heading=");
        zZm2.append(this.jiA);
        zZm2.append(", speed=");
        return C0179Pya.BIo(zZm2, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* compiled from: $AutoValue_GeolocationStatePayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends Suv.zZm {
        public Date BIo;
        public rfd Qle;
        public wze jiA;
        public hYy zQM;
        public Xdr zZm;
        public Gkq zyO;

        @Override // com.amazon.alexa.Suv.zZm
        public Suv.zZm zZm(Xdr xdr) {
            if (xdr != null) {
                this.zZm = xdr;
                return this;
            }
            throw new NullPointerException("Null locationServices");
        }

        @Override // com.amazon.alexa.Suv.zZm
        public Suv.zZm zZm(Date date) {
            if (date != null) {
                this.BIo = date;
                return this;
            }
            throw new NullPointerException("Null timestamp");
        }
    }
}

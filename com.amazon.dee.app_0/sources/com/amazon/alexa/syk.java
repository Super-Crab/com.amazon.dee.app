package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.vUW;
import java.util.List;
/* compiled from: AutoValue_AlexaLauncherEvent.java */
/* loaded from: classes.dex */
public final class syk extends vUW {
    public final pUc BIo;
    public final String Qle;
    public final List<IJL> jiA;
    public final IUU zQM;
    public final Zxk zyO;

    public /* synthetic */ syk(pUc puc, IUU iuu, Zxk zxk, List list, String str, FLw fLw) {
        this.BIo = puc;
        this.zQM = iuu;
        this.zyO = zxk;
        this.jiA = list;
        this.Qle = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof vUW)) {
            return false;
        }
        syk sykVar = (syk) obj;
        return this.BIo.equals(sykVar.BIo) && this.zQM.equals(sykVar.zQM) && this.zyO.equals(sykVar.zyO) && this.jiA.equals(sykVar.jiA) && this.Qle.equals(sykVar.Qle);
    }

    public int hashCode() {
        return ((((((((this.BIo.hashCode() ^ 1000003) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("AlexaLauncherEvent{target=");
        zZm2.append(this.BIo);
        zZm2.append(", token=");
        zZm2.append(this.zQM);
        zZm2.append(", outcome=");
        zZm2.append(this.zyO);
        zZm2.append(", reasons=");
        zZm2.append(this.jiA);
        zZm2.append(", eventType=");
        return C0179Pya.zZm(zZm2, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* compiled from: AutoValue_AlexaLauncherEvent.java */
    /* loaded from: classes.dex */
    static final class zZm extends vUW.zZm {
        public IUU BIo;
        public String jiA;
        public Zxk zQM;
        public pUc zZm;
        public List<IJL> zyO;

        @Override // com.amazon.alexa.vUW.zZm
        public vUW.zZm zZm(pUc puc) {
            if (puc != null) {
                this.zZm = puc;
                return this;
            }
            throw new NullPointerException("Null target");
        }

        @Override // com.amazon.alexa.vUW.zZm
        public vUW.zZm zZm(IUU iuu) {
            if (iuu != null) {
                this.BIo = iuu;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        @Override // com.amazon.alexa.vUW.zZm
        public vUW.zZm zZm(Zxk zxk) {
            if (zxk != null) {
                this.zQM = zxk;
                return this;
            }
            throw new NullPointerException("Null outcome");
        }

        @Override // com.amazon.alexa.vUW.zZm
        public vUW.zZm zZm(List<IJL> list) {
            if (list != null) {
                this.zyO = list;
                return this;
            }
            throw new NullPointerException("Null reasons");
        }

        @Override // com.amazon.alexa.vUW.zZm
        public vUW zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " target");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " token");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " outcome");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " reasons");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " eventType");
            }
            if (str.isEmpty()) {
                return new syk(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA, null);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}

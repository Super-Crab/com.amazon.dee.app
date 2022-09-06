package com.amazon.alexa;

import android.content.ComponentName;
import com.amazon.alexa.KHc;
import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.ArrayList;
/* compiled from: AutoValue_ExternalCapabilityPreregistrationEntity.java */
/* loaded from: classes.dex */
public final class Qrg extends KHc {
    public final PackageName BIo;
    public final zjD JTe;
    public final qzJ Qle;
    public final EPu jiA;
    public final jVi zQM;
    public final ComponentName zZm;
    public final ArrayList<Capability> zyO;

    public /* synthetic */ Qrg(ComponentName componentName, PackageName packageName, jVi jvi, ArrayList arrayList, EPu ePu, qzJ qzj, zjD zjd, Kmb kmb) {
        this.zZm = componentName;
        this.BIo = packageName;
        this.zQM = jvi;
        this.zyO = arrayList;
        this.jiA = ePu;
        this.Qle = qzj;
        this.JTe = zjd;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof KHc)) {
            return false;
        }
        Qrg qrg = (Qrg) obj;
        return this.zZm.equals(qrg.zZm) && this.BIo.equals(qrg.BIo) && this.zQM.equals(qrg.zQM) && this.zyO.equals(qrg.zyO) && this.jiA.equals(qrg.jiA) && this.Qle.equals(qrg.Qle) && this.JTe.equals(qrg.JTe);
    }

    public int hashCode() {
        return ((((((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA.hashCode()) * 1000003) ^ this.Qle.hashCode()) * 1000003) ^ this.JTe.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ExternalCapabilityPreregistrationEntity{componentName=");
        zZm2.append(this.zZm);
        zZm2.append(", packageName=");
        zZm2.append(this.BIo);
        zZm2.append(", capabilityAgentVersion=");
        zZm2.append(this.zQM);
        zZm2.append(", supportedCapabilities=");
        zZm2.append(this.zyO);
        zZm2.append(", autoUpdatePreference=");
        zZm2.append(this.jiA);
        zZm2.append(", registrationStatus=");
        zZm2.append(this.Qle);
        zZm2.append(", isFollowing=");
        return C0179Pya.BIo(zZm2, this.JTe, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    /* compiled from: AutoValue_ExternalCapabilityPreregistrationEntity.java */
    /* loaded from: classes.dex */
    static final class zZm extends KHc.zZm {
        public PackageName BIo;
        public zjD JTe;
        public qzJ Qle;
        public EPu jiA;
        public jVi zQM;
        public ComponentName zZm;
        public ArrayList<Capability> zyO;

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(PackageName packageName) {
            if (packageName != null) {
                this.BIo = packageName;
                return this;
            }
            throw new NullPointerException("Null packageName");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(jVi jvi) {
            if (jvi != null) {
                this.zQM = jvi;
                return this;
            }
            throw new NullPointerException("Null capabilityAgentVersion");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(ArrayList<Capability> arrayList) {
            if (arrayList != null) {
                this.zyO = arrayList;
                return this;
            }
            throw new NullPointerException("Null supportedCapabilities");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(EPu ePu) {
            if (ePu != null) {
                this.jiA = ePu;
                return this;
            }
            throw new NullPointerException("Null autoUpdatePreference");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(qzJ qzj) {
            if (qzj != null) {
                this.Qle = qzj;
                return this;
            }
            throw new NullPointerException("Null registrationStatus");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc.zZm zZm(zjD zjd) {
            if (zjd != null) {
                this.JTe = zjd;
                return this;
            }
            throw new NullPointerException("Null isFollowing");
        }

        @Override // com.amazon.alexa.KHc.zZm
        public KHc zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " componentName");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " packageName");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " capabilityAgentVersion");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " supportedCapabilities");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " autoUpdatePreference");
            }
            if (this.Qle == null) {
                str = C0179Pya.zZm(str, " registrationStatus");
            }
            if (this.JTe == null) {
                str = C0179Pya.zZm(str, " isFollowing");
            }
            if (str.isEmpty()) {
                return new Qrg(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA, this.Qle, this.JTe, null);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}

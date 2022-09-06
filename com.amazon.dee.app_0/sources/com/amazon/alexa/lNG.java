package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity;
import com.amazon.alexa.client.core.messages.Name;
import com.amazon.alexa.client.core.messages.Namespace;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Date;
import javax.annotation.Nullable;
/* compiled from: AutoValue_ExternalComponentStateEntity.java */
/* loaded from: classes.dex */
public final class lNG extends ExternalComponentStateEntity {
    public final Name BIo;
    public final Date Qle;
    public final int jiA;
    public final String zQM;
    public final Namespace zZm;
    public final PackageName zyO;

    public /* synthetic */ lNG(Namespace namespace, Name name, String str, PackageName packageName, int i, Date date, uDi udi) {
        this.zZm = namespace;
        this.BIo = name;
        this.zQM = str;
        this.zyO = packageName;
        this.jiA = i;
        this.Qle = date;
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    @Nullable
    public Date BIo() {
        return this.Qle;
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    public String JTe() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    public int Qle() {
        return this.jiA;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ExternalComponentStateEntity)) {
            return false;
        }
        ExternalComponentStateEntity externalComponentStateEntity = (ExternalComponentStateEntity) obj;
        if (this.zZm.equals(externalComponentStateEntity.zyO()) && this.BIo.equals(externalComponentStateEntity.zQM()) && this.zQM.equals(externalComponentStateEntity.JTe()) && this.zyO.equals(externalComponentStateEntity.jiA()) && this.jiA == externalComponentStateEntity.Qle()) {
            Date date = this.Qle;
            if (date == null) {
                if (externalComponentStateEntity.BIo() == null) {
                    return true;
                }
            } else if (date.equals(externalComponentStateEntity.BIo())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003) ^ this.zyO.hashCode()) * 1000003) ^ this.jiA) * 1000003;
        Date date = this.Qle;
        return hashCode ^ (date == null ? 0 : date.hashCode());
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    public PackageName jiA() {
        return this.zyO;
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ExternalComponentStateEntity{namespace=");
        zZm2.append(this.zZm);
        zZm2.append(", name=");
        zZm2.append(this.BIo);
        zZm2.append(", payload=");
        zZm2.append(this.zQM);
        zZm2.append(", packageName=");
        zZm2.append(this.zyO);
        zZm2.append(", packageVersionCode=");
        zZm2.append(this.jiA);
        zZm2.append(", dateUpdated=");
        return C0179Pya.BIo(zZm2, this.Qle, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    public Name zQM() {
        return this.BIo;
    }

    @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity
    public Namespace zyO() {
        return this.zZm;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AutoValue_ExternalComponentStateEntity.java */
    /* loaded from: classes.dex */
    public static final class zZm extends ExternalComponentStateEntity.zZm {
        public Name BIo;
        public Date Qle;
        public Integer jiA;
        public String zQM;
        public Namespace zZm;
        public PackageName zyO;

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity.zZm zZm(Namespace namespace) {
            if (namespace != null) {
                this.zZm = namespace;
                return this;
            }
            throw new NullPointerException("Null namespace");
        }

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity.zZm zZm(Name name) {
            if (name != null) {
                this.BIo = name;
                return this;
            }
            throw new NullPointerException("Null name");
        }

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity.zZm zZm(String str) {
            if (str != null) {
                this.zQM = str;
                return this;
            }
            throw new NullPointerException("Null payload");
        }

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity.zZm zZm(PackageName packageName) {
            if (packageName != null) {
                this.zyO = packageName;
                return this;
            }
            throw new NullPointerException("Null packageName");
        }

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity.zZm zZm(int i) {
            this.jiA = Integer.valueOf(i);
            return this;
        }

        @Override // com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateEntity.zZm
        public ExternalComponentStateEntity zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " namespace");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " name");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " payload");
            }
            if (this.zyO == null) {
                str = C0179Pya.zZm(str, " packageName");
            }
            if (this.jiA == null) {
                str = C0179Pya.zZm(str, " packageVersionCode");
            }
            if (str.isEmpty()) {
                return new lNG(this.zZm, this.BIo, this.zQM, this.zyO, this.jiA.intValue(), this.Qle, null);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }
}

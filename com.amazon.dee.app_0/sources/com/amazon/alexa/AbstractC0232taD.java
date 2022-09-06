package com.amazon.alexa;

import android.content.ComponentName;
import com.amazon.alexa.Iab;
import com.amazon.alexa.client.alexaservice.capabilities.v2.AutoValue_ExternalCapabilityAgentRegistrationData;
import com.amazon.alexa.client.core.messages.PackageName;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_ExternalCapabilityAgentRegistrationData.java */
/* renamed from: com.amazon.alexa.taD  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public abstract class AbstractC0232taD extends Iab {
    public final ComponentName BIo;
    public final iaZ zQM;
    public final PackageName zZm;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_ExternalCapabilityAgentRegistrationData.java */
    /* renamed from: com.amazon.alexa.taD$zZm */
    /* loaded from: classes.dex */
    public static final class zZm extends Iab.zZm {
        public ComponentName BIo;
        public iaZ zQM;
        public PackageName zZm;

        @Override // com.amazon.alexa.Iab.zZm
        public Iab zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " packageName");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " componentName");
            }
            if (this.zQM == null) {
                str = C0179Pya.zZm(str, " externalCapabilityAgentRegistrationRawData");
            }
            if (str.isEmpty()) {
                return new AutoValue_ExternalCapabilityAgentRegistrationData(this.zZm, this.BIo, this.zQM);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    public AbstractC0232taD(PackageName packageName, ComponentName componentName, iaZ iaz) {
        if (packageName != null) {
            this.zZm = packageName;
            if (componentName != null) {
                this.BIo = componentName;
                if (iaz != null) {
                    this.zQM = iaz;
                    return;
                }
                throw new NullPointerException("Null externalCapabilityAgentRegistrationRawData");
            }
            throw new NullPointerException("Null componentName");
        }
        throw new NullPointerException("Null packageName");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Iab)) {
            return false;
        }
        AbstractC0232taD abstractC0232taD = (AbstractC0232taD) obj;
        return this.zZm.equals(abstractC0232taD.zZm) && this.BIo.equals(abstractC0232taD.BIo) && this.zQM.equals(abstractC0232taD.zQM);
    }

    public int hashCode() {
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("ExternalCapabilityAgentRegistrationData{packageName=");
        zZm2.append(this.zZm);
        zZm2.append(", componentName=");
        zZm2.append(this.BIo);
        zZm2.append(", externalCapabilityAgentRegistrationRawData=");
        return C0179Pya.BIo(zZm2, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.ArrayList;
/* compiled from: $AutoValue_ExternalCapabilityAgentRegistrationRawData.java */
/* loaded from: classes.dex */
public abstract class Ubd extends iaZ {
    public final EPu BIo;
    public final ArrayList<Capability> zQM;
    public final jVi zZm;

    public Ubd(jVi jvi, EPu ePu, ArrayList<Capability> arrayList) {
        if (jvi != null) {
            this.zZm = jvi;
            if (ePu != null) {
                this.BIo = ePu;
                if (arrayList != null) {
                    this.zQM = arrayList;
                    return;
                }
                throw new NullPointerException("Null supportedCapabilities");
            }
            throw new NullPointerException("Null autoUpdate");
        }
        throw new NullPointerException("Null capabilityAgentVersion");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof iaZ)) {
            return false;
        }
        Ubd ubd = (Ubd) obj;
        return this.zZm.equals(ubd.zZm) && this.BIo.equals(ubd.BIo) && this.zQM.equals(ubd.zQM);
    }

    public int hashCode() {
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExternalCapabilityAgentRegistrationRawData{capabilityAgentVersion=");
        zZm.append(this.zZm);
        zZm.append(", autoUpdate=");
        zZm.append(this.BIo);
        zZm.append(", supportedCapabilities=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

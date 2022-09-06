package com.amazon.alexa;

import com.amazon.alexa.client.core.capabilities.Capability;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_CapabilityPublishRequest.java */
/* loaded from: classes.dex */
public abstract class TpD extends CAj {
    public final List<Capability> BIo;
    public final Cta zQM;
    public final String zZm;

    public TpD(String str, List<Capability> list, Cta cta) {
        if (str != null) {
            this.zZm = str;
            if (list != null) {
                this.BIo = list;
                if (cta != null) {
                    this.zQM = cta;
                    return;
                }
                throw new NullPointerException("Null legacyFlags");
            }
            throw new NullPointerException("Null capabilities");
        }
        throw new NullPointerException("Null envelopeVersion");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CAj)) {
            return false;
        }
        TpD tpD = (TpD) obj;
        return this.zZm.equals(tpD.zZm) && this.BIo.equals(tpD.BIo) && this.zQM.equals(tpD.zQM);
    }

    public int hashCode() {
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("CapabilityPublishRequest{envelopeVersion=");
        zZm.append(this.zZm);
        zZm.append(", capabilities=");
        zZm.append(this.BIo);
        zZm.append(", legacyFlags=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: $AutoValue_DisambiguateAndLaunchTargetPayload.java */
/* loaded from: classes.dex */
public abstract class rSg extends gUg {
    public final List<pUc> BIo;
    public final String zQM;
    public final IUU zZm;

    public rSg(IUU iuu, List<pUc> list, String str) {
        if (iuu != null) {
            this.zZm = iuu;
            if (list != null) {
                this.BIo = list;
                if (str != null) {
                    this.zQM = str;
                    return;
                }
                throw new NullPointerException("Null description");
            }
            throw new NullPointerException("Null targets");
        }
        throw new NullPointerException("Null token");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gUg)) {
            return false;
        }
        rSg rsg = (rSg) obj;
        return this.zZm.equals(rsg.zZm) && this.BIo.equals(rsg.BIo) && this.zQM.equals(rsg.zQM);
    }

    public int hashCode() {
        return ((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("DisambiguateAndLaunchTargetPayload{token=");
        zZm.append(this.zZm);
        zZm.append(", targets=");
        zZm.append(this.BIo);
        zZm.append(", description=");
        return C0179Pya.zZm(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

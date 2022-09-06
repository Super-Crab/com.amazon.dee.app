package com.amazon.alexa;

import com.amazon.alexa.YKQ;
import com.amazon.alexa.client.alexaservice.componentstate.ComponentState;
import com.amazon.alexa.client.alexaservice.componentstate.ExternalComponentStateProvider;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.Set;
/* compiled from: AutoValue_ExternalComponentStateAuthority_StateProviderCallableResult.java */
/* loaded from: classes.dex */
public final class sSP extends YKQ.zQM {
    public final Set<ComponentState> BIo;
    public final ExternalComponentStateProvider zZm;

    public sSP(ExternalComponentStateProvider externalComponentStateProvider, Set<ComponentState> set) {
        if (externalComponentStateProvider != null) {
            this.zZm = externalComponentStateProvider;
            if (set != null) {
                this.BIo = set;
                return;
            }
            throw new NullPointerException("Null componentStates");
        }
        throw new NullPointerException("Null externalStateProvider");
    }

    @Override // com.amazon.alexa.YKQ.zQM
    public ExternalComponentStateProvider BIo() {
        return this.zZm;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof YKQ.zQM)) {
            return false;
        }
        sSP ssp = (sSP) obj;
        return this.zZm.equals(ssp.zZm) && this.BIo.equals(ssp.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("StateProviderCallableResult{externalStateProvider=");
        zZm.append(this.zZm);
        zZm.append(", componentStates=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    @Override // com.amazon.alexa.YKQ.zQM
    public Set<ComponentState> zZm() {
        return this.BIo;
    }
}

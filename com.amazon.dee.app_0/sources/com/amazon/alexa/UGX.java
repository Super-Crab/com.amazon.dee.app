package com.amazon.alexa;

import com.amazon.alexa.dEA;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_PlayerRuntimeState.java */
/* loaded from: classes.dex */
public abstract class UGX extends dEA {
    public final GWl BIo;
    public final vQe zZm;

    /* compiled from: $AutoValue_PlayerRuntimeState.java */
    /* loaded from: classes.dex */
    static final class zZm extends dEA.zZm {
        public GWl BIo;
        public vQe zZm;

        @Override // com.amazon.alexa.dEA.zZm
        public dEA.zZm zZm(GWl gWl) {
            if (gWl != null) {
                this.BIo = gWl;
                return this;
            }
            throw new NullPointerException("Null inactiveAvsPlaybackSessionId");
        }
    }

    public UGX(vQe vqe, GWl gWl) {
        if (vqe != null) {
            this.zZm = vqe;
            if (gWl != null) {
                this.BIo = gWl;
                return;
            }
            throw new NullPointerException("Null inactiveAvsPlaybackSessionId");
        }
        throw new NullPointerException("Null playerId");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dEA)) {
            return false;
        }
        UGX ugx = (UGX) obj;
        return this.zZm.equals(ugx.zZm) && this.BIo.equals(ugx.BIo);
    }

    public int hashCode() {
        return ((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("PlayerRuntimeState{playerId=");
        zZm2.append(this.zZm);
        zZm2.append(", inactiveAvsPlaybackSessionId=");
        return C0179Pya.BIo(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

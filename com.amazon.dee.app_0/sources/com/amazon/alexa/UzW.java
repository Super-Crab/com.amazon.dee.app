package com.amazon.alexa;

import com.amazon.alexa.VCD;
import com.amazon.alexa.client.alexaservice.speaker.payload.AutoValue_VolumeEventPayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_VolumeEventPayload.java */
/* loaded from: classes.dex */
public abstract class UzW extends VCD {
    public final boolean BIo;
    public final long zZm;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: $AutoValue_VolumeEventPayload.java */
    /* loaded from: classes.dex */
    public static final class zZm extends VCD.zZm {
        public Boolean BIo;
        public Long zZm;

        @Override // com.amazon.alexa.VCD.zZm
        public VCD.zZm zZm(long j) {
            this.zZm = Long.valueOf(j);
            return this;
        }

        @Override // com.amazon.alexa.VCD.zZm
        public VCD.zZm zZm(boolean z) {
            this.BIo = Boolean.valueOf(z);
            return this;
        }

        @Override // com.amazon.alexa.VCD.zZm
        public VCD zZm() {
            String str = "";
            if (this.zZm == null) {
                str = C0179Pya.zZm(str, " volume");
            }
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " muted");
            }
            if (str.isEmpty()) {
                return new AutoValue_VolumeEventPayload(this.zZm.longValue(), this.BIo.booleanValue());
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    public UzW(long j, boolean z) {
        this.zZm = j;
        this.BIo = z;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof VCD)) {
            return false;
        }
        UzW uzW = (UzW) obj;
        return this.zZm == uzW.zZm && this.BIo == uzW.BIo;
    }

    public int hashCode() {
        long j = this.zZm;
        return ((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237);
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("VolumeEventPayload{volume=");
        zZm2.append(this.zZm);
        zZm2.append(", muted=");
        return C0179Pya.zZm(zZm2, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_SynchronizeStateFailedEvent.java */
/* loaded from: classes.dex */
public final class MKA extends iRJ {
    public final boolean BIo;
    public final Integer zQM;
    public final Exception zyO;

    public MKA(boolean z, @Nullable Integer num, @Nullable Exception exc) {
        this.BIo = z;
        this.zQM = num;
        this.zyO = exc;
    }

    public boolean equals(Object obj) {
        Integer num;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof iRJ)) {
            return false;
        }
        MKA mka = (MKA) obj;
        if (this.BIo == mka.BIo && ((num = this.zQM) != null ? num.equals(mka.zQM) : mka.zQM == null)) {
            Exception exc = this.zyO;
            if (exc == null) {
                if (mka.zyO == null) {
                    return true;
                }
            } else if (exc.equals(mka.zyO)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = ((this.BIo ? 1231 : 1237) ^ 1000003) * 1000003;
        Integer num = this.zQM;
        int i2 = 0;
        int hashCode = (i ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Exception exc = this.zyO;
        if (exc != null) {
            i2 = exc.hashCode();
        }
        return hashCode ^ i2;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SynchronizeStateFailedEvent{isEstablishingDownchannel=");
        zZm.append(this.BIo);
        zZm.append(", responseCode=");
        zZm.append(this.zQM);
        zZm.append(", exception=");
        return C0179Pya.BIo(zZm, this.zyO, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

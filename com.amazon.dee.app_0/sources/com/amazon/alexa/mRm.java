package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.AutoValue_MediaStructure;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_MediaStructure.java */
/* loaded from: classes.dex */
public abstract class mRm extends AbstractC0197ddD {
    public final String BIo;
    public final WlR zQM;

    /* compiled from: $AutoValue_MediaStructure.java */
    /* loaded from: classes.dex */
    static final class zZm extends AbstractC0197ddD.zZm {
        public WlR BIo;
        public String zZm;

        @Override // com.amazon.alexa.AbstractC0197ddD.zZm
        public AbstractC0197ddD zZm() {
            String str = "";
            if (this.BIo == null) {
                str = C0179Pya.zZm(str, " value");
            }
            if (str.isEmpty()) {
                return new AutoValue_MediaStructure(this.zZm, this.BIo);
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str));
        }
    }

    public mRm(@Nullable String str, WlR wlR) {
        this.BIo = str;
        if (wlR != null) {
            this.zQM = wlR;
            return;
        }
        throw new NullPointerException("Null value");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0197ddD)) {
            return false;
        }
        String str = this.BIo;
        if (str != null ? str.equals(((mRm) obj).BIo) : ((mRm) obj).BIo == null) {
            if (this.zQM.equals(((mRm) obj).zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.BIo;
        return (((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("MediaStructure{type=");
        zZm2.append(this.BIo);
        zZm2.append(", value=");
        return C0179Pya.BIo(zZm2, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

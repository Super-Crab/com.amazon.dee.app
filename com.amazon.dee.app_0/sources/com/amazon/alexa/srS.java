package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
/* compiled from: $AutoValue_PlayerInfoPayload_Template.java */
/* loaded from: classes.dex */
public abstract class srS extends qgo.zQM {
    public final lsL BIo;
    public final Uri zZm;

    public srS(@Nullable Uri uri, @Nullable lsL lsl) {
        this.zZm = uri;
        this.BIo = lsl;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qgo.zQM)) {
            return false;
        }
        Uri uri = this.zZm;
        if (uri != null ? uri.equals(((srS) obj).zZm) : ((srS) obj).zZm == null) {
            lsL lsl = this.BIo;
            if (lsl == null) {
                if (((srS) obj).BIo == null) {
                    return true;
                }
            } else if (lsl.equals(((srS) obj).BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Uri uri = this.zZm;
        int i = 0;
        int hashCode = ((uri == null ? 0 : uri.hashCode()) ^ 1000003) * 1000003;
        lsL lsl = this.BIo;
        if (lsl != null) {
            i = lsl.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("Template{backgroundImageUrl=");
        zZm.append(this.zZm);
        zZm.append(", art=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

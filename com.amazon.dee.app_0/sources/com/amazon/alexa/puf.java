package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AlexaImage.java */
/* loaded from: classes.dex */
public abstract class puf extends lsL {
    public final Uri zZm;

    public puf(@Nullable Uri uri) {
        this.zZm = uri;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof lsL)) {
            return false;
        }
        Uri uri = this.zZm;
        if (uri != null) {
            return uri.equals(((puf) obj).zZm);
        }
        return ((puf) obj).zZm == null;
    }

    public int hashCode() {
        Uri uri = this.zZm;
        return (uri == null ? 0 : uri.hashCode()) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AlexaImage{url="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

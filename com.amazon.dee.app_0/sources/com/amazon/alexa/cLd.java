package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0183Xff;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.gson.JsonObject;
/* compiled from: $AutoValue_RecognizePayload.java */
/* loaded from: classes.dex */
public abstract class cLd extends AbstractC0183Xff {
    public final String BIo;
    public final JsonObject zQM;
    public final String zZm;

    /* compiled from: $AutoValue_RecognizePayload.java */
    /* loaded from: classes.dex */
    static final class zZm extends AbstractC0183Xff.zZm {
        public String BIo;
        public JsonObject zQM;
        public String zZm;
    }

    public cLd(String str, String str2, @Nullable JsonObject jsonObject) {
        if (str != null) {
            this.zZm = str;
            if (str2 != null) {
                this.BIo = str2;
                this.zQM = jsonObject;
                return;
            }
            throw new NullPointerException("Null format");
        }
        throw new NullPointerException("Null profile");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC0183Xff)) {
            return false;
        }
        cLd cld = (cLd) obj;
        if (this.zZm.equals(cld.zZm) && this.BIo.equals(cld.BIo)) {
            JsonObject jsonObject = this.zQM;
            if (jsonObject == null) {
                if (cld.zQM == null) {
                    return true;
                }
            } else if (jsonObject.equals(cld.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        JsonObject jsonObject = this.zQM;
        return hashCode ^ (jsonObject == null ? 0 : jsonObject.hashCode());
    }

    public String toString() {
        StringBuilder zZm2 = C0179Pya.zZm("RecognizePayload{profile=");
        zZm2.append(this.zZm);
        zZm2.append(", format=");
        zZm2.append(this.BIo);
        zZm2.append(", initiator=");
        return C0179Pya.BIo(zZm2, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

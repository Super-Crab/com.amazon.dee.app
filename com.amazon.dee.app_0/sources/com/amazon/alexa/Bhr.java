package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.google.gson.JsonObject;
/* compiled from: $AutoValue_ExpectSpeechPayload.java */
/* loaded from: classes.dex */
public abstract class Bhr extends nNv {
    public final JsonObject BIo;
    public final long zZm;

    public Bhr(long j, @Nullable JsonObject jsonObject) {
        this.zZm = j;
        this.BIo = jsonObject;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof nNv)) {
            return false;
        }
        Bhr bhr = (Bhr) obj;
        if (this.zZm == bhr.zZm) {
            JsonObject jsonObject = this.BIo;
            if (jsonObject == null) {
                if (bhr.BIo == null) {
                    return true;
                }
            } else if (jsonObject.equals(bhr.BIo)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long j = this.zZm;
        int i = (((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003;
        JsonObject jsonObject = this.BIo;
        return i ^ (jsonObject == null ? 0 : jsonObject.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("ExpectSpeechPayload{timeoutInMilliseconds=");
        zZm.append(this.zZm);
        zZm.append(", initiator=");
        return C0179Pya.BIo(zZm, this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

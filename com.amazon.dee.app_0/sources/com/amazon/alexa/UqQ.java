package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.DEe;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_SpeakPayload.java */
/* loaded from: classes.dex */
public abstract class UqQ extends DEe {
    public final C0176Pce BIo;
    public final String jiA;
    public final DEe.zZm zQM;
    public final Uri zZm;
    public final String zyO;

    public UqQ(Uri uri, C0176Pce c0176Pce, DEe.zZm zzm, @Nullable String str, @Nullable String str2) {
        if (uri != null) {
            this.zZm = uri;
            if (c0176Pce != null) {
                this.BIo = c0176Pce;
                if (zzm != null) {
                    this.zQM = zzm;
                    this.zyO = str;
                    this.jiA = str2;
                    return;
                }
                throw new NullPointerException("Null format");
            }
            throw new NullPointerException("Null token");
        }
        throw new NullPointerException("Null url");
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DEe)) {
            return false;
        }
        UqQ uqQ = (UqQ) obj;
        if (this.zZm.equals(uqQ.zZm) && this.BIo.equals(uqQ.BIo) && this.zQM.equals(uqQ.zQM) && ((str = this.zyO) != null ? str.equals(uqQ.zyO) : uqQ.zyO == null)) {
            String str2 = this.jiA;
            if (str2 == null) {
                if (uqQ.jiA == null) {
                    return true;
                }
            } else if (str2.equals(uqQ.jiA)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003) ^ this.zQM.hashCode()) * 1000003;
        String str = this.zyO;
        int i = 0;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.jiA;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 ^ i;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("SpeakPayload{url=");
        zZm.append(this.zZm);
        zZm.append(", token=");
        zZm.append(this.BIo);
        zZm.append(", format=");
        zZm.append(this.zQM);
        zZm.append(", caption=");
        zZm.append(this.zyO);
        zZm.append(", ssml=");
        return C0179Pya.zZm(zZm, this.jiA, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

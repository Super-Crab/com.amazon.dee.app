package com.amazon.alexa;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.List;
/* compiled from: AutoValue_TargetState.java */
/* loaded from: classes.dex */
public final class Aud extends Afe {
    public final boolean BIo;
    public final List<IJL> zQM;
    public final Intent zZm;

    public Aud(@Nullable Intent intent, boolean z, List<IJL> list) {
        this.zZm = intent;
        this.BIo = z;
        if (list != null) {
            this.zQM = list;
            return;
        }
        throw new NullPointerException("Null decisionReasons");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Afe)) {
            return false;
        }
        Intent intent = this.zZm;
        if (intent != null ? intent.equals(((Aud) obj).zZm) : ((Aud) obj).zZm == null) {
            Aud aud = (Aud) obj;
            if (this.BIo == aud.BIo && this.zQM.equals(aud.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        Intent intent = this.zZm;
        return (((((intent == null ? 0 : intent.hashCode()) ^ 1000003) * 1000003) ^ (this.BIo ? 1231 : 1237)) * 1000003) ^ this.zQM.hashCode();
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("TargetState{launchIntent=");
        zZm.append(this.zZm);
        zZm.append(", isFallbackSelected=");
        zZm.append(this.BIo);
        zZm.append(", decisionReasons=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

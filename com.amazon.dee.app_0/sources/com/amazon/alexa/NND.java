package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaUserSpeechProviderScope;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_MultiTurnDialogMetadata.java */
/* loaded from: classes.dex */
public final class NND extends eeF {
    public final String BIo;
    public final AlexaUserSpeechProviderScope zQM;
    public final piE zZm;

    public NND(piE pie, String str, @Nullable AlexaUserSpeechProviderScope alexaUserSpeechProviderScope) {
        if (pie != null) {
            this.zZm = pie;
            if (str != null) {
                this.BIo = str;
                this.zQM = alexaUserSpeechProviderScope;
                return;
            }
            throw new NullPointerException("Null softwareVersion");
        }
        throw new NullPointerException("Null userSpeechProviderIdentifier");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eeF)) {
            return false;
        }
        NND nnd = (NND) obj;
        if (this.zZm.equals(nnd.zZm) && this.BIo.equals(nnd.BIo)) {
            AlexaUserSpeechProviderScope alexaUserSpeechProviderScope = this.zQM;
            if (alexaUserSpeechProviderScope == null) {
                if (nnd.zQM == null) {
                    return true;
                }
            } else if (alexaUserSpeechProviderScope.equals(nnd.zQM)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.zZm.hashCode() ^ 1000003) * 1000003) ^ this.BIo.hashCode()) * 1000003;
        AlexaUserSpeechProviderScope alexaUserSpeechProviderScope = this.zQM;
        return hashCode ^ (alexaUserSpeechProviderScope == null ? 0 : alexaUserSpeechProviderScope.hashCode());
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("MultiTurnDialogMetadata{userSpeechProviderIdentifier=");
        zZm.append(this.zZm);
        zZm.append(", softwareVersion=");
        zZm.append(this.BIo);
        zZm.append(", alexaUserSpeechProviderScope=");
        return C0179Pya.BIo(zZm, this.zQM, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AlexaMediaPayload.java */
/* loaded from: classes.dex */
public final class Eqg extends AlexaMediaPayload {
    public final vQe zZm;

    public Eqg(@Nullable vQe vqe) {
        this.zZm = vqe;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AlexaMediaPayload)) {
            return false;
        }
        vQe vqe = this.zZm;
        vQe playerId = ((AlexaMediaPayload) obj).getPlayerId();
        return vqe == null ? playerId == null : vqe.equals(playerId);
    }

    @Override // com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload
    @Nullable
    public vQe getPlayerId() {
        return this.zZm;
    }

    public int hashCode() {
        vQe vqe = this.zZm;
        return (vqe == null ? 0 : vqe.hashCode()) ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AlexaMediaPayload{playerId="), this.zZm, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

package com.amazon.alexa;

import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: AutoValue_AudioPlaybackChangedEvent.java */
/* loaded from: classes.dex */
public final class CYr extends qZM {
    public final AlexaPlaybackState BIo;

    public CYr(AlexaPlaybackState alexaPlaybackState) {
        if (alexaPlaybackState != null) {
            this.BIo = alexaPlaybackState;
            return;
        }
        throw new NullPointerException("Null alexaPlaybackState");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof qZM)) {
            return false;
        }
        return this.BIo.equals(((CYr) obj).BIo);
    }

    public int hashCode() {
        return this.BIo.hashCode() ^ 1000003;
    }

    public String toString() {
        return C0179Pya.BIo(C0179Pya.zZm("AudioPlaybackChangedEvent{alexaPlaybackState="), this.BIo, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}

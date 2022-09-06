package com.amazon.alexa;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
/* compiled from: $AutoValue_AlexaUserSpeechVolumeChangedEvent.java */
/* loaded from: classes.dex */
public abstract class YEO extends uqh {
    public final float BIo;

    public YEO(float f) {
        this.BIo = f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof uqh) && Float.floatToIntBits(this.BIo) == Float.floatToIntBits(((YEO) obj).BIo);
    }

    public int hashCode() {
        return Float.floatToIntBits(this.BIo) ^ 1000003;
    }

    public String toString() {
        StringBuilder zZm = C0179Pya.zZm("AlexaUserSpeechVolumeChangedEvent{scaledVolume=");
        zZm.append(this.BIo);
        zZm.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return zZm.toString();
    }
}

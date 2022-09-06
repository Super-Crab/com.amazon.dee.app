package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum AlexaAudioPlaybackStatusListenerMessageType implements AlexaMessageType {
    UNKNOWN,
    AUDIO_PLAYBACK_STATUS;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlexaAudioPlaybackStatusListenerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}

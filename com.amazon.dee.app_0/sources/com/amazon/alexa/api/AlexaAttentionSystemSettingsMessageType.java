package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum AlexaAttentionSystemSettingsMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_WAKE_SOUND_ENABLED_CHANGED,
    ON_ENDPOINT_SOUND_ENABLED_CHANGED;

    public static AlexaAttentionSystemSettingsMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}

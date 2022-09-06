package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public enum AlexaUserSpeechProviderMessageType implements AlexaMessageType {
    UNKNOWN,
    SET_WAKE_WORD_DETECTION_ENABLED,
    ON_DIALOG_REQUESTED,
    ON_DIALOG_REQUEST_DENIED,
    ON_DIALOG_STARTED,
    ON_DIALOG_TURN_REQUESTED,
    ON_DIALOG_TURN_STARTED,
    ON_DIALOG_TURN_FINISHED,
    ON_DIALOG_FINISHED,
    PAUSE_WAKEWORD_DETECTION,
    RESUME_WAKEWORD_DETECTION;

    public static AlexaUserSpeechProviderMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}

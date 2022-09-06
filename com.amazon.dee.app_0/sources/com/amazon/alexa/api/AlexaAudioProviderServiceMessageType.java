package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
enum AlexaAudioProviderServiceMessageType implements AlexaMessageType {
    UNKNOWN,
    SET_WAKE_WORD_RECOGNITION_ENABLED,
    IS_WAKE_WORD_RECOGNITION_ENABLED,
    SET_LOCALE,
    GET_LOCALE,
    GET_SUPPORTED_LOCALES,
    CONNECT_FOR_ATTENTION_SYSTEM_UPDATES,
    START_DIALOG,
    GET_CUSTOM_SETTINGS_TITLE,
    GET_CUSTOM_SETTINGS_INTENT,
    GET_WAKE_WORD_CONFIDENCE_THRESHOLD,
    SET_WAKE_WORD_CONFIDENCE_THRESHOLD;

    public static AlexaAudioProviderServiceMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}

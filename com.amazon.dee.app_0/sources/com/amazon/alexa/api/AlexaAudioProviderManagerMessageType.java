package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum AlexaAudioProviderManagerMessageType implements AlexaMessageType {
    UNKNOWN,
    PREPARE,
    START_DIALOG,
    CONTINUE_DIALOG,
    STOP_DIALOG_TURN,
    REGISTER_ALEXA_STATE_LISTENER,
    DEREGISTER_ALEXA_STATE_LISTENER,
    ON_CLIENT_DISCONNECT,
    REGISTER_FORCE_DISCONNECT_LISTENER,
    DEREGISTER_FORCE_DISCONNECT_LISTENER,
    IS_USER_LOGGED_IN,
    START_DIALOG_WITH_METADATA,
    UPDATE_PREFERENCES,
    GET_PREFERENCES,
    GET_LOCALE,
    REGISTER_SETTINGS_LISTENER,
    DEREGISTER_SETTINGS_LISTENER,
    GET_LOCALES;

    public static AlexaAudioProviderManagerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("index ", i, " is out of bound"));
        }
        return values()[i];
    }
}

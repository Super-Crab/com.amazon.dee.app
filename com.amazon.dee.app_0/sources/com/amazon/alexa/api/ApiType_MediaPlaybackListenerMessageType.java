package com.amazon.alexa.api;

import com.amazon.alexa.api.messages.AlexaMessageType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public enum ApiType_MediaPlaybackListenerMessageType implements AlexaMessageType {
    UNKNOWN,
    ON_MEDIA_METADATA_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_METADATA,
    ON_MEDIA_PLAYBACK_STATE_UPDATE_COM_AMAZON_ALEXA_API_ALEXA_MEDIA_PLAYBACK_STATE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiType_MediaPlaybackListenerMessageType fromOrdinal(int i) {
        if (i < 0 || i >= values().length) {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline52("ordinal ", i, " is out of bound"));
        }
        return values()[i];
    }
}

package com.amazon.alexa.sharing.repo.models.acms.payload.sharing;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public enum SharingMessageType {
    SHARED_MESSAGE("message/shared-message"),
    DEFAULT_REACTION("reaction/default"),
    SONG("SONG"),
    ALEXA_SONG("alexa-music/song"),
    PHOTO_MESSAGE("message/photo-message");
    
    @NonNull
    private final String sharingMessageType;

    SharingMessageType(@NonNull String str) {
        this.sharingMessageType = str;
    }
}

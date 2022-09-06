package com.amazon.alexa.sharing.repo.models.acms.photos.enums;

import androidx.annotation.NonNull;
/* loaded from: classes10.dex */
public enum AttachmentMediaType {
    IMAGE("IMAGE"),
    VIDEO("VIDEO"),
    UNKNOWN("");
    
    @NonNull
    private final String attachmentMediaType;

    AttachmentMediaType(@NonNull String str) {
        this.attachmentMediaType = str;
    }
}

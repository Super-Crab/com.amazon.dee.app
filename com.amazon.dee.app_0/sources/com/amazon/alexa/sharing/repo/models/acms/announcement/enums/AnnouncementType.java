package com.amazon.alexa.sharing.repo.models.acms.announcement.enums;

import androidx.annotation.NonNull;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes10.dex */
public enum AnnouncementType {
    TEXT(Constants.TEXT_ANNOUNCEMENT_MESSAGE_TYPE),
    AUDIO("announcement/audio");
    
    @NonNull
    private final String announcementType;

    AnnouncementType(@NonNull String str) {
        this.announcementType = str;
    }
}

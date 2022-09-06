package com.amazon.deecomms.calling.enums;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public enum AnnouncementAvailability {
    ON,
    OFF;

    public static boolean isAnnouncementAvailable(@Nullable AnnouncementAvailability announcementAvailability) {
        return announcementAvailability == ON;
    }
}

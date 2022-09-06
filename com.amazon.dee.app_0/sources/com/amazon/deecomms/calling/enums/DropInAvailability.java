package com.amazon.deecomms.calling.enums;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public enum DropInAvailability {
    OFF,
    HOME,
    ALL;

    public static boolean isEnabled(@Nullable DropInAvailability dropInAvailability) {
        return dropInAvailability == HOME || dropInAvailability == ALL;
    }
}

package com.amazon.deecomms.calling.enums;

import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public enum DeviceCommsAvailability {
    ON,
    OFF;

    public static boolean isCommsEnabled(@Nullable DeviceCommsAvailability deviceCommsAvailability) {
        return deviceCommsAvailability == ON;
    }
}

package com.amazon.deecomms.calling.api.enums;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum DeviceIdentifierType {
    DeviceGruu("devicegruu");
    
    private final String value;

    DeviceIdentifierType(String str) {
        this.value = str;
    }

    public static boolean isValidEnum(@NonNull String str) {
        for (DeviceIdentifierType deviceIdentifierType : (DeviceIdentifierType[]) DeviceIdentifierType.class.getEnumConstants()) {
            if (deviceIdentifierType.toString().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Enum
    @NonNull
    public String toString() {
        return this.value;
    }
}

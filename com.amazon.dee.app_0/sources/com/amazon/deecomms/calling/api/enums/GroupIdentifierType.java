package com.amazon.deecomms.calling.api.enums;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum GroupIdentifierType {
    Groupid("groupid"),
    Homegroupid("homegroup");
    
    private final String value;

    GroupIdentifierType(String str) {
        this.value = str;
    }

    public static boolean isValidEnum(@NonNull String str) {
        for (GroupIdentifierType groupIdentifierType : (GroupIdentifierType[]) GroupIdentifierType.class.getEnumConstants()) {
            if (groupIdentifierType.toString().equalsIgnoreCase(str)) {
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

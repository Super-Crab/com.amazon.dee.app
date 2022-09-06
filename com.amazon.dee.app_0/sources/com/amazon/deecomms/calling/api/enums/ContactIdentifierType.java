package com.amazon.deecomms.calling.api.enums;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum ContactIdentifierType {
    Commsid("commsid"),
    Pceid("pceid"),
    Homegroupid("homegroup");
    
    private final String value;

    ContactIdentifierType(String str) {
        this.value = str;
    }

    public static boolean isValidEnum(@NonNull String str) {
        for (ContactIdentifierType contactIdentifierType : (ContactIdentifierType[]) ContactIdentifierType.class.getEnumConstants()) {
            if (contactIdentifierType.toString().equalsIgnoreCase(str)) {
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

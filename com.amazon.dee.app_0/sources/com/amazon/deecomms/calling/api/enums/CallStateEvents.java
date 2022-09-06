package com.amazon.deecomms.calling.api.enums;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum CallStateEvents {
    CallInitiated("comms::calling::A2ACall::Initiated"),
    CallAnswered("comms::calling::A2ACall::Answered"),
    CallEnded("comms::calling::A2ACall::Ended"),
    CallError("comms::calling::A2ACall::Error");
    
    private final String eventBusName;

    CallStateEvents(String str) {
        this.eventBusName = str;
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
        return this.eventBusName;
    }
}

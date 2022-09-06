package com.amazon.alexa.elements.api;

import androidx.annotation.NonNull;
/* loaded from: classes7.dex */
public enum InitializationPhase {
    UNKNOWN("unknown"),
    LOAD_ON_START("loadOnStart"),
    COMPLETE("complete");
    
    private final String phase;

    InitializationPhase(String str) {
        this.phase = str;
    }

    @NonNull
    public static InitializationPhase fromString(String str) {
        InitializationPhase[] values;
        for (InitializationPhase initializationPhase : values()) {
            if (initializationPhase.phase.equals(str)) {
                return initializationPhase;
            }
        }
        return UNKNOWN;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.phase;
    }
}

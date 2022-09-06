package com.amazon.deecomms.calling.enums;

import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public enum DataChannelLabel {
    IN_CALL_APPLICATION("in_call_application");
    
    private final String name;

    DataChannelLabel(@NonNull String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}

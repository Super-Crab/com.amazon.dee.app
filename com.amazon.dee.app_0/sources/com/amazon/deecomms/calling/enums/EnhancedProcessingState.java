package com.amazon.deecomms.calling.enums;

import androidx.annotation.NonNull;
import com.amazon.comms.models.gui.MediaSettingsInfo;
/* loaded from: classes12.dex */
public enum EnhancedProcessingState {
    ON("ON"),
    OFF("OFF"),
    NOT_APPLICABLE(MediaSettingsInfo.ENHANCED_PROCESSING_NOT_APPLICABLE);
    
    private String name;

    EnhancedProcessingState(@NonNull String str) {
        this.name = str;
    }

    public String getValue() {
        return this.name;
    }
}

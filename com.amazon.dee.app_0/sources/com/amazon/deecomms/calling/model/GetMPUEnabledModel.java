package com.amazon.deecomms.calling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class GetMPUEnabledModel {
    @JsonProperty("enabled")
    boolean enabled;
    @JsonProperty("enhancedProcessing")
    String enhancedProcessing;

    public String getEnhancedProcessing() {
        return this.enhancedProcessing;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setEnhancedProcessing(String str) {
        this.enhancedProcessing = str;
    }
}

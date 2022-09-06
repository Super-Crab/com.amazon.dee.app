package com.amazon.comms.models.gui;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
/* loaded from: classes11.dex */
public class MediaSettingsInfo implements Serializable {
    public static final String ENHANCED_PROCESSING_NOT_APPLICABLE = "NOT_APPLICABLE";
    public static final String ENHANCED_PROCESSING_OFF = "OFF";
    public static final String ENHANCED_PROCESSING_ON = "ON";
    private String enhancedProcessing;
    private boolean isEnhancedProcessingOptInRequired;

    /* loaded from: classes11.dex */
    public static class MediaSettingsInfoBuilder {
        private String enhancedProcessing;
        private boolean isEnhancedProcessingOptInRequired;

        MediaSettingsInfoBuilder() {
        }

        public MediaSettingsInfo build() {
            return new MediaSettingsInfo(this.enhancedProcessing, this.isEnhancedProcessingOptInRequired);
        }

        public MediaSettingsInfoBuilder enhancedProcessing(String str) {
            this.enhancedProcessing = str;
            return this;
        }

        public MediaSettingsInfoBuilder isEnhancedProcessingOptInRequired(boolean z) {
            this.isEnhancedProcessingOptInRequired = z;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaSettingsInfo.MediaSettingsInfoBuilder(enhancedProcessing=");
            outline107.append(this.enhancedProcessing);
            outline107.append(", isEnhancedProcessingOptInRequired=");
            return GeneratedOutlineSupport1.outline97(outline107, this.isEnhancedProcessingOptInRequired, ")");
        }
    }

    MediaSettingsInfo(String str, boolean z) {
        this.enhancedProcessing = str;
        this.isEnhancedProcessingOptInRequired = z;
    }

    public static MediaSettingsInfoBuilder builder() {
        return new MediaSettingsInfoBuilder();
    }

    public String getEnhancedProcessing() {
        return this.enhancedProcessing;
    }

    public boolean getIsEnhancedProcessingOptInRequired() {
        return this.isEnhancedProcessingOptInRequired;
    }

    @JsonProperty("isEnhancedProcessingOptInRequired")
    public boolean isEnhancedProcessingOptInRequired() {
        return this.isEnhancedProcessingOptInRequired;
    }
}

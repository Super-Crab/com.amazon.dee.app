package com.amazon.deecomms.calling.incallexperiences.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class TriggerActionResponse {
    @JsonProperty("statusCode")
    private String triggerStatusCode;

    /* loaded from: classes12.dex */
    public enum TriggerStatusCode {
        SUCCESS("Success"),
        VPC_IS_REQUIRED("VpcIsRequired"),
        AMC_OPT_IN_REQUIRED("AmcOptInRequired"),
        UNKNOWN("unknown");
        
        private String value;

        TriggerStatusCode(String str) {
            this.value = str;
        }

        public static TriggerStatusCode lookUp(String str) {
            TriggerStatusCode[] values;
            for (TriggerStatusCode triggerStatusCode : values()) {
                if (triggerStatusCode.getValue().equalsIgnoreCase(str)) {
                    return triggerStatusCode;
                }
            }
            return null;
        }

        public String getValue() {
            return this.value;
        }
    }

    public TriggerStatusCode getTriggerStatusCode() {
        TriggerStatusCode lookUp = TriggerStatusCode.lookUp(this.triggerStatusCode);
        if (lookUp != null) {
            return lookUp;
        }
        if (this.triggerStatusCode.toLowerCase().startsWith("amc")) {
            return TriggerStatusCode.AMC_OPT_IN_REQUIRED;
        }
        if (this.triggerStatusCode.toLowerCase().startsWith("vpc")) {
            return TriggerStatusCode.VPC_IS_REQUIRED;
        }
        return TriggerStatusCode.UNKNOWN;
    }

    public void setTriggerStatusCode(String str) {
        this.triggerStatusCode = str;
    }
}

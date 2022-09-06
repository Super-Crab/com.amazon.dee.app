package com.amazon.deecomms.calling.model;

import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes12.dex */
public class CallConfigurationsModel {
    @JsonProperty("configurations")
    public CallConfigurations configurations;

    /* loaded from: classes12.dex */
    public class CallConfigurations {
        @JsonProperty("audioStartBitrateInKbps")
        public int audioStartBitrateInKbps;

        public CallConfigurations() {
        }
    }
}

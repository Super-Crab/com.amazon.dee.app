package com.amazon.deecomms.ndt.model;

import amazon.speech.simclient.settings.Settings;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class CommsFeatureStatus {
    @JsonProperty("feature")
    private String feature;
    @JsonProperty(Settings.ListeningSettings.EXTRA_REASON)
    private String reason;
    @JsonProperty("supported")
    private boolean supported;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CommsFeatureStatus.class != obj.getClass()) {
            return false;
        }
        CommsFeatureStatus commsFeatureStatus = (CommsFeatureStatus) obj;
        return this.supported == commsFeatureStatus.supported && Objects.equal(this.feature, commsFeatureStatus.feature) && Objects.equal(this.reason, commsFeatureStatus.reason);
    }

    public String getFeature() {
        return this.feature;
    }

    public String getReason() {
        return this.reason;
    }

    public int hashCode() {
        return Objects.hashCode(this.feature, Boolean.valueOf(this.supported), this.reason);
    }

    public boolean isSupported() {
        return this.supported;
    }

    public void setFeature(String str) {
        this.feature = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setSupported(boolean z) {
        this.supported = z;
    }
}

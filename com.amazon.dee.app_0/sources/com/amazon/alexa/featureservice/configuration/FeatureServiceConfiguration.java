package com.amazon.alexa.featureservice.configuration;
/* loaded from: classes7.dex */
public class FeatureServiceConfiguration {
    private boolean enabled;
    private long featureRefreshTime;
    private long sessionUpdateThreshold;

    public long getFeatureRefreshTime() {
        return this.featureRefreshTime;
    }

    public long getSessionUpdateThreshold() {
        return this.sessionUpdateThreshold;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public void setFeatureRefreshTime(long j) {
        this.featureRefreshTime = j;
    }

    public void setSessionUpdateThreshold(long j) {
        this.sessionUpdateThreshold = j;
    }
}

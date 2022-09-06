package com.amazon.alexa.location.provider.interactor.sync.contextualevents;

import androidx.annotation.VisibleForTesting;
/* loaded from: classes9.dex */
public class LocationReportingRule {
    private int intervalInSeconds;
    private String type;

    public LocationReportingRule() {
    }

    public int getIntervalInSeconds() {
        return this.intervalInSeconds;
    }

    public String getType() {
        return this.type;
    }

    @VisibleForTesting
    LocationReportingRule(String str, int i) {
        this.type = str;
        this.intervalInSeconds = i;
    }
}

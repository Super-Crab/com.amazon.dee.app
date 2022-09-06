package com.amazon.alexa.location.provider.interactor.sync.contextualevents;

import androidx.annotation.VisibleForTesting;
/* loaded from: classes9.dex */
public class Subscription {
    private static final String SUBSCRIPTION_TYPE_PRECISE_LOCATION = "PreciseLocation";
    private String correlationToken;
    private String interfaceName;
    private LocationReportingRule reporting;
    private String subscriptionId;

    public Subscription() {
    }

    public String getCorrelationToken() {
        return this.correlationToken;
    }

    public String getInterfaceName() {
        return this.interfaceName;
    }

    public LocationReportingRule getReportingRule() {
        return this.reporting;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public boolean isLocationSubscription() {
        return SUBSCRIPTION_TYPE_PRECISE_LOCATION.equals(this.interfaceName);
    }

    @VisibleForTesting
    Subscription(String str, LocationReportingRule locationReportingRule) {
        this.interfaceName = str;
        this.reporting = locationReportingRule;
    }
}

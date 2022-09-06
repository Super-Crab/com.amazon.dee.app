package com.amazonaws.mobileconnectors.amazonmobileanalytics;

import com.amazonaws.ClientConfiguration;
@Deprecated
/* loaded from: classes13.dex */
public class AnalyticsConfig {
    private boolean allowEventCollection;
    private boolean allowWANDelivery;
    private ClientConfiguration clientConfiguration;

    public AnalyticsConfig() {
        this(new ClientConfiguration());
    }

    public boolean getAllowEventCollection() {
        return this.allowEventCollection;
    }

    public boolean getAllowWANDelivery() {
        return this.allowWANDelivery;
    }

    public ClientConfiguration getClientConfiguration() {
        return this.clientConfiguration;
    }

    public AnalyticsConfig withAllowsEventCollection(boolean z) {
        this.allowEventCollection = z;
        return this;
    }

    public AnalyticsConfig withAllowsWANDelivery(boolean z) {
        this.allowWANDelivery = z;
        return this;
    }

    public AnalyticsConfig withClientConfiguration(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = new ClientConfiguration(clientConfiguration);
        return this;
    }

    public AnalyticsConfig(ClientConfiguration clientConfiguration) {
        this.allowEventCollection = true;
        this.allowWANDelivery = true;
        if (clientConfiguration != null) {
            this.clientConfiguration = new ClientConfiguration(clientConfiguration);
            return;
        }
        throw new IllegalArgumentException();
    }
}

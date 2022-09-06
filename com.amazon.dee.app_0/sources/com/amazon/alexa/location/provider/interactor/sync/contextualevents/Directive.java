package com.amazon.alexa.location.provider.interactor.sync.contextualevents;

import java.util.List;
/* loaded from: classes9.dex */
public class Directive {
    private String correlationToken;
    private String customerId;
    private String deviceId;
    private List<Subscription> subscriptions;
    private String type;
    private String version;

    public String getCorrelationToken() {
        return this.correlationToken;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public List<Subscription> getSubscriptions() {
        return this.subscriptions;
    }

    public String getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }
}

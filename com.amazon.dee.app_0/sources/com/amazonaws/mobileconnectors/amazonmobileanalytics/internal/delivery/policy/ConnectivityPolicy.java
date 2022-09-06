package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Connectivity;
@Deprecated
/* loaded from: classes13.dex */
public class ConnectivityPolicy implements DeliveryPolicy {
    private static final boolean ALLOW_WAN_DEFAULT = true;
    private static final String ALLOW_WAN_KEY = "allowWANEventDelivery";
    private final Connectivity connectivity;
    private final AnalyticsContext context;
    private final boolean isWanAllowed;

    public ConnectivityPolicy(AnalyticsContext analyticsContext, boolean z) {
        this.context = analyticsContext;
        this.connectivity = analyticsContext.getSystem().getConnectivity();
        this.isWanAllowed = z;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy
    public void handleDeliveryAttempt(boolean z) {
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy
    public boolean isAllowed() {
        boolean z = this.context.getConfiguration().optBoolean(ALLOW_WAN_KEY, true).booleanValue() && this.isWanAllowed;
        if (this.connectivity.isConnected()) {
            return this.connectivity.hasWifi() || this.connectivity.hasWired() || (this.connectivity.hasWAN() && z);
        }
        return false;
    }
}

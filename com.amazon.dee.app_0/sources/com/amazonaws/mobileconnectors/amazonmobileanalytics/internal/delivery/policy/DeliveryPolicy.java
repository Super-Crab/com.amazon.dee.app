package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy;
@Deprecated
/* loaded from: classes13.dex */
public interface DeliveryPolicy {
    void handleDeliveryAttempt(boolean z);

    boolean isAllowed();
}

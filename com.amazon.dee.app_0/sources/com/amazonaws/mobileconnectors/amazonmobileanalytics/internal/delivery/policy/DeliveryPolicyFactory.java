package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy;
@Deprecated
/* loaded from: classes13.dex */
public interface DeliveryPolicyFactory {
    DeliveryPolicy newBackgroundSubmissionTimePolicy();

    DeliveryPolicy newConnectivityPolicy();

    DeliveryPolicy newForceSubmissionTimePolicy();
}

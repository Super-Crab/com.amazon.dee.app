package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultDeliveryPolicyFactory implements DeliveryPolicyFactory {
    private static final long BG_SUBMISSION_WAIT_TIME_SEC = 0;
    private static final long FORCE_SUBMISSION_WAIT_TIME_SEC = 60;
    public static final String KEY_FORCE_SUBMISSION_WAIT = "forceSubmissionWaitTime";
    private final long backgroundSubmissionInterval;
    private final AnalyticsContext context;
    public final long forceSubmissionInterval;
    private final boolean isWanAllowed;

    public DefaultDeliveryPolicyFactory(AnalyticsContext analyticsContext, boolean z) {
        this.context = analyticsContext;
        this.isWanAllowed = z;
        this.forceSubmissionInterval = analyticsContext.getConfiguration().optLong(KEY_FORCE_SUBMISSION_WAIT, 60L).longValue() * 1000;
        this.backgroundSubmissionInterval = analyticsContext.getConfiguration().optLong("backgroundSubmissionWaitTime", 0L).longValue() * 1000;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicyFactory
    public DeliveryPolicy newBackgroundSubmissionTimePolicy() {
        return new SubmissionTimePolicy(this.context, Long.valueOf(this.backgroundSubmissionInterval));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicyFactory
    public DeliveryPolicy newConnectivityPolicy() {
        return new ConnectivityPolicy(this.context, this.isWanAllowed);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicyFactory
    public DeliveryPolicy newForceSubmissionTimePolicy() {
        return new SubmissionTimePolicy(this.context, Long.valueOf(this.forceSubmissionInterval));
    }
}

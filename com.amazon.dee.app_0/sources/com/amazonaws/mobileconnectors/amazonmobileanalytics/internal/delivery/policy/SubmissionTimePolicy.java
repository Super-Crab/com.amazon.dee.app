package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy;

import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
@Deprecated
/* loaded from: classes13.dex */
public class SubmissionTimePolicy implements DeliveryPolicy {
    static final String SUBMITTED_TIME_KEY = "SubmissionTimePolicy.submissionTime";
    private final AnalyticsContext context;
    private Long lastSubmittedTime;
    private final Long waitInterval;

    public SubmissionTimePolicy(AnalyticsContext analyticsContext, Long l) {
        this.context = analyticsContext;
        this.waitInterval = l;
        this.lastSubmittedTime = Long.valueOf(analyticsContext.getSystem().getPreferences().getLong(SUBMITTED_TIME_KEY, 0L));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy
    public void handleDeliveryAttempt(boolean z) {
        if (z) {
            this.lastSubmittedTime = Long.valueOf(System.currentTimeMillis());
            this.context.getSystem().getPreferences().putLong(SUBMITTED_TIME_KEY, this.lastSubmittedTime.longValue());
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy
    public boolean isAllowed() {
        return System.currentTimeMillis() - this.lastSubmittedTime.longValue() > this.waitInterval.longValue();
    }
}

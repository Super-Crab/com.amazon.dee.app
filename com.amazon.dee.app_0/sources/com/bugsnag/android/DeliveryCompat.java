package com.bugsnag.android;

import androidx.annotation.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeliveryCompat implements Delivery {
    volatile ErrorReportApiClient errorReportApiClient;
    volatile SessionTrackingApiClient sessionTrackingApiClient;

    @Override // com.bugsnag.android.Delivery
    public void deliver(@NonNull SessionTrackingPayload sessionTrackingPayload, @NonNull Configuration configuration) throws DeliveryFailureException {
        if (this.sessionTrackingApiClient != null) {
            try {
                this.sessionTrackingApiClient.postSessionTrackingPayload(configuration.getSessionEndpoint(), sessionTrackingPayload, configuration.getSessionApiHeaders());
            } catch (Throwable th) {
                handleException(th);
            }
        }
    }

    void handleException(Throwable th) throws DeliveryFailureException {
        if (!(th instanceof NetworkException)) {
            Logger.warn("Ignoring Exception, this API is deprecated", th);
            return;
        }
        throw new DeliveryFailureException(th.getMessage(), th);
    }

    @Override // com.bugsnag.android.Delivery
    public void deliver(@NonNull Report report, @NonNull Configuration configuration) throws DeliveryFailureException {
        if (this.errorReportApiClient != null) {
            try {
                this.errorReportApiClient.postReport(configuration.getEndpoint(), report, configuration.getErrorApiHeaders());
            } catch (Throwable th) {
                handleException(th);
            }
        }
    }
}

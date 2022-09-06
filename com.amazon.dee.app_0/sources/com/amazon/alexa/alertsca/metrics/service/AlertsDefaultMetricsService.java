package com.amazon.alexa.alertsca.metrics.service;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.metrics.MetricsElement;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Lists;
import java.util.List;
/* loaded from: classes6.dex */
public class AlertsDefaultMetricsService implements MetricsService {
    @VisibleForTesting
    static final String COMPONENT = "vox_speech";
    static final List<String> EXCLUDED_PATTERS = Lists.newArrayList("Alerts.Service.Actions.WAKE_UP", "Alerts.Notifications.Cancel.");
    @VisibleForTesting
    static final String SUB_COMPONENT = "alerts";
    private static final String TAG = "AlertsDefaultMetricsService";
    private final Mobilytics mobilytics;

    public AlertsDefaultMetricsService(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void cancelTimer(String str) {
        throw new UnsupportedOperationException("cancelTimer is not supported yet.");
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordError(String str, String str2) {
        this.mobilytics.recordOccurrence(GeneratedOutlineSupport1.outline72("Error.", str), true, "vox_speech", SUB_COMPONENT, OwnerIdentifier.HHO_MOBILE);
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordEvent(String str) {
        for (String str2 : EXCLUDED_PATTERS) {
            if (str.startsWith(str2)) {
                String str3 = "Excluding event: " + str + ", for matching pattern: " + str2;
                return;
            }
        }
        this.mobilytics.recordOccurrence(str, true, "vox_speech", SUB_COMPONENT, OwnerIdentifier.HHO_MOBILE);
        GeneratedOutlineSupport1.outline158("Recording Alerts event: ", str);
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordOccurrence(String str, boolean z) {
        this.mobilytics.recordOccurrence(str, z, "vox_speech", SUB_COMPONENT, OwnerIdentifier.HHO_MOBILE);
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordPercentOccurrence(String str, boolean z) {
        this.mobilytics.recordPercentOccurrence(str, z, "vox_speech", SUB_COMPONENT, OwnerIdentifier.HHO_MOBILE);
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordTimer(MetricsTimer metricsTimer) {
        throw new UnsupportedOperationException("recordTimer is not supported yet.");
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public MetricsTimer startTimer(String str) {
        throw new UnsupportedOperationException("startTimer is not supported yet.");
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordTimer(String str) {
        throw new UnsupportedOperationException("recordTimer is not supported yet.");
    }

    @Override // com.amazon.alexa.alertsca.metrics.service.MetricsService
    public void recordEvent(MetricsElement metricsElement) {
        recordEvent(metricsElement.getEventName());
    }
}

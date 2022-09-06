package com.amazon.alexa.externalnotifications.capability.events;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* loaded from: classes7.dex */
public class AmfApiCallbacks extends AlexaApiCallbacks {
    @VisibleForTesting
    protected static final String ATTEMPT_SUFFIX = ".event.attempt";
    @VisibleForTesting
    protected static final String FAILURE_SUFFIX = ".event.failure";
    @VisibleForTesting
    protected static final String FAILURE_TYPE_CUSTOM_ENTRY_KEY = "failureType";
    @VisibleForTesting
    protected static final String SUCCESS_SUFFIX = ".event.success";
    private static final String TAG = "AmfApiCallbacks";
    private final String eventName;
    private final ExternalNotificationsMetricsRecorder metricsRecorder;

    public AmfApiCallbacks(String str, ExternalNotificationsMetricsRecorder externalNotificationsMetricsRecorder) {
        this.eventName = str;
        this.metricsRecorder = externalNotificationsMetricsRecorder;
        externalNotificationsMetricsRecorder.recordCounter(str + ATTEMPT_SUFFIX);
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    public void onFailure(final ApiCallFailure apiCallFailure) {
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Send event failed for: "), this.eventName, TAG);
        this.metricsRecorder.recordCounter(GeneratedOutlineSupport1.outline91(new StringBuilder(), this.eventName, FAILURE_SUFFIX), new HashMap<String, Object>() { // from class: com.amazon.alexa.externalnotifications.capability.events.AmfApiCallbacks.1
            {
                put(AmfApiCallbacks.FAILURE_TYPE_CUSTOM_ENTRY_KEY, apiCallFailure.getFailureType());
            }
        });
    }

    @Override // com.amazon.alexa.api.AlexaApiCallbacks
    public void onSuccess() {
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Send event succeeded for: "), this.eventName, TAG);
        ExternalNotificationsMetricsRecorder externalNotificationsMetricsRecorder = this.metricsRecorder;
        externalNotificationsMetricsRecorder.recordCounter(this.eventName + SUCCESS_SUFFIX);
    }
}

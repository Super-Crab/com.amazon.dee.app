package com.amazon.alexa.presence.service.guardrails;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.presence.library.MetricsRecorder;
import com.amazon.alexa.presence.library.storage.PersistentLocalStorage;
import com.amazon.alexa.presence.metrics.MetricsId;
import com.amazon.alexa.presence.metrics.MetricsMethod;
/* loaded from: classes9.dex */
public class PresenceFeatureEnabledGuardrail implements PresenceFeatureGuardrailInterface {
    private static final String TAG = "com.amazon.alexa.presence.service.guardrails.PresenceFeatureEnabledGuardrail";
    private final Context ctx;
    private final String featureDomain;
    private MetricsRecorder metrics;
    private final PersistentLocalStorage.PresenceDataStore presenceDataStore;

    public PresenceFeatureEnabledGuardrail(String str, PersistentLocalStorage.PresenceDataStore presenceDataStore, Context context) {
        this.featureDomain = str;
        this.presenceDataStore = presenceDataStore;
        this.ctx = context;
        try {
            this.metrics = MetricsRecorder.getMetricsUtil();
        } catch (Throwable unused) {
            Log.w(TAG, "Unable to setup metrics service.");
        }
    }

    @Override // com.amazon.alexa.presence.service.guardrails.PresenceFeatureGuardrailInterface
    public boolean checkGuardrail() {
        boolean z = !this.presenceDataStore.getDomains(this.ctx).contains(this.featureDomain);
        if (z) {
            this.metrics.recordCount(MetricsId.PASSED_GUARDRAIL, MetricsMethod.PRESENCE_FEATURE_ENABLED_GUARDRAIL);
        } else {
            this.metrics.recordCount(MetricsId.FAILED_GUARDRAIL, MetricsMethod.PRESENCE_FEATURE_ENABLED_GUARDRAIL);
        }
        return z;
    }
}

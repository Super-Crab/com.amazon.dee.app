package com.amazon.alexa.presence;

import android.content.Context;
import com.amazon.alexa.presence.utils.MetricsUtil;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
import com.dee.app.metrics.MetricsServiceV2;
import javax.inject.Inject;
/* loaded from: classes9.dex */
public class PresenceApplicationLifecycleObserver implements ApplicationLifecycleObserver {
    private static final String TAG = Presence.tag();
    private final Context context;
    private final PresenceController controller;
    private final MetricsServiceV2 metricsServiceV2;

    @Inject
    public PresenceApplicationLifecycleObserver(MetricsServiceV2 metricsServiceV2, Context context, PresenceController presenceController) {
        this.metricsServiceV2 = metricsServiceV2;
        this.context = context;
        this.controller = presenceController;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        MetricsUtil.recordCount(this.metricsServiceV2, MetricsUtil.MetricsId.START_SCANNING_APP_FOREGROUND, MetricsUtil.Method.START_SCANNING_WORKFLOW);
        this.controller.enablePresence(this.context);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
    }
}

package com.amazon.alexa.sharing.broadcast;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.sharing.Constants;
import com.amazon.alexa.sharing.MetricKeys;
import com.amazon.alexa.sharing.comms.CommsMetricsEmitter;
import com.amazon.comms.log.CommsLogger;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Lazy;
import java.util.Collections;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes10.dex */
public class ShareSheetIntentBroadcastReceiver extends BroadcastReceiver {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ShareSheetIntentBroadcastReceiver.class);
    private static final String TAG = ShareSheetIntentBroadcastReceiver.class.getSimpleName();
    @Inject
    public Lazy<AlexaCommsCoreMetricsService> metricsServiceLazy;
    private CommsMetricsEmitter metricsEmitter = new CommsMetricsEmitter(this.metricsServiceLazy, TAG);

    @VisibleForTesting
    public CommsMetricsEmitter getMetricsEmitter() {
        return this.metricsEmitter;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String packageName = ((ComponentName) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT")).getPackageName();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Following app has been selected during sharing sheet: " + packageName);
        getMetricsEmitter().recordOccurrenceMetric(String.format(MetricKeys.ALEXA_SHARING_SHARE_SHEET_APP_SELECTED, packageName), Collections.EMPTY_MAP);
    }
}

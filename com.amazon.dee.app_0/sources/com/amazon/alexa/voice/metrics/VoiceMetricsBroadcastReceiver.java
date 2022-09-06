package com.amazon.alexa.voice.metrics;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.dagger.VoiceDependencies;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import javax.inject.Inject;
/* loaded from: classes11.dex */
public class VoiceMetricsBroadcastReceiver extends BroadcastReceiver {
    @VisibleForTesting
    static final String ACTION = "amazon.alexa.intent.action.REPORT_METRIC";
    @VisibleForTesting
    static final String EXTRA_NAME = "amazon.alexa.intent.extras.EVENT_NAME";
    @VisibleForTesting
    static final String EXTRA_TIMESTAMP = "amazon.alexa.intent.extras.EVENT_TIMESTAMP";
    @Inject
    VoxMetricEventProcessingService eventProcessingService;

    @VisibleForTesting
    void injectDependencies() {
        VoiceDependencies.inject(this);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (!ACTION.equals(intent.getAction())) {
            return;
        }
        injectDependencies();
        String stringExtra = intent.getStringExtra(EXTRA_NAME);
        long longExtra = intent.getLongExtra(EXTRA_TIMESTAMP, 0L);
        Logger.verbose("VoiceMetricsBroadcastReceiver, event:" + stringExtra + ", timestamp:" + longExtra);
        if (stringExtra == null || stringExtra.equals("")) {
            return;
        }
        this.eventProcessingService.process(VoxMetricEvent.create(stringExtra, longExtra));
    }
}

package com.amazon.alexa.handsfree.latencyreporter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.handsfree.latencyreporter.TimestampIntentConverter;
/* loaded from: classes8.dex */
public class AlexaUIEventReceiver extends BroadcastReceiver {
    static final String ACTION_PUBLISH_UI_EVENT = "com.amazon.alexa.intent.action.PUBLISH_UI_EVENT";
    static final String BUNDLE_KEY_EVENT_REALTIME_MS = "EVENT_REALTIME_MS";
    static final String EXTRA_EVENT_DATA = "com.amazon.alexa.intent.extra.EVENT_DATA";
    static final String EXTRA_EVENT_NAME = "com.amazon.alexa.intent.extra.EVENT_NAME";
    private final TimestampIntentConverter mTimestampIntentConverter;

    public AlexaUIEventReceiver() {
        this(new TimestampIntentConverter());
    }

    void enqueueWork(@NonNull Context context, @NonNull Intent intent) {
        LatencyJobIntentService.enqueueWork(context, intent);
    }

    long getCurrentTimeMillisFromElapsedRealTime(long j) {
        return (System.currentTimeMillis() - SystemClock.elapsedRealtime()) + j;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if ("com.amazon.alexa.intent.action.PUBLISH_UI_EVENT".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("com.amazon.alexa.intent.extra.EVENT_NAME");
            Bundle bundleExtra = intent.getBundleExtra("com.amazon.alexa.intent.extra.EVENT_DATA");
            if (!TimestampIntentConverter.TimestampName.ALEXA_UI_SHOWN.name().equals(stringExtra) || bundleExtra == null) {
                return;
            }
            enqueueWork(context, this.mTimestampIntentConverter.convertToIntent(TimestampIntentConverter.TimestampName.ALEXA_UI_SHOWN.name(), null, getCurrentTimeMillisFromElapsedRealTime(bundleExtra.getLong(BUNDLE_KEY_EVENT_REALTIME_MS))));
        }
    }

    AlexaUIEventReceiver(@NonNull TimestampIntentConverter timestampIntentConverter) {
        this.mTimestampIntentConverter = timestampIntentConverter;
    }
}

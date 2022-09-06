package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.UUID;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class MetricBroadcastSender {
    static final String EXTRA_API_CALL_ID = "com.amazon.alexa.intent.extras.API_CALL_ID";
    static final String EXTRA_CLIENT = "com.amazon.alexa.intent.extras.CLIENT";
    static final String EXTRA_DIALOG_TURN_ID = "com.amazon.alexa.intent.extras.DIALOG_TURN_ID";
    static final String EXTRA_EVENT_ID = "com.amazon.alexa.intent.extras.EVENT_ID";
    static final String EXTRA_INVOCATION_TYPE = "com.amazon.alexa.intent.extras.INVOCATION_TYPE";
    static final String EXTRA_LATENCY = "com.amazon.alexa.intent.extras.EVENT_LATENCY";
    static final String EXTRA_NAME = "com.amazon.alexa.intent.extras.EVENT_NAME";
    static final String EXTRA_SOFTWARE_VERSION = "com.amazon.alexa.intent.extras.SOFTWARE_VERSION";
    static final String EXTRA_SOURCE_PACKAGE = "com.amazon.alexa.intent.extras.SOURCE_PACKAGE";
    static final String EXTRA_TIMESTAMP = "com.amazon.alexa.intent.extras.EVENT_TIMESTAMP";
    static final String GENERIC_ACTION = "com.amazon.alexa.intent.action.REPORT_GENERIC_METRIC_EVENT";
    static final String VOICE_ACTION = "com.amazon.alexa.intent.action.REPORT_VOICE_INTERACTION_METRIC_EVENT";
    private final ExtendedClient client;
    private final Context context;
    private final String leaderPackageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetricBroadcastSender(ManagedServiceConnection managedServiceConnection) {
        this.context = managedServiceConnection.getContext();
        this.client = managedServiceConnection.getClient();
        ComponentName serviceComponentName = managedServiceConnection.getServiceComponentName();
        this.leaderPackageName = serviceComponentName != null ? serviceComponentName.getPackageName() : null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendEvent(AlexaMetricsName alexaMetricsName) {
        sendEvent(alexaMetricsName, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendEvent(AlexaMetricsName alexaMetricsName, long j) {
        sendEvent(alexaMetricsName, "", Long.valueOf(j));
    }

    void sendEvent(AlexaMetricsName alexaMetricsName, String str) {
        sendEvent(alexaMetricsName, str, null);
    }

    void sendEvent(AlexaMetricsName alexaMetricsName, String str, @Nullable Long l) {
        sendEvent(alexaMetricsName, str, l, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendEvent(AlexaMetricsName alexaMetricsName, String str, @Nullable Long l, @Nullable String str2) {
        Intent intent = new Intent(GENERIC_ACTION);
        String str3 = this.leaderPackageName;
        if (str3 == null) {
            str3 = this.context.getPackageName();
        }
        intent.setPackage(str3);
        if (l != null) {
            intent.putExtra(EXTRA_LATENCY, l.longValue());
        }
        if (str2 != null) {
            intent.putExtra(EXTRA_API_CALL_ID, str2);
        }
        intent.putExtra(EXTRA_NAME, alexaMetricsName.getValue());
        intent.putExtra(EXTRA_EVENT_ID, UUID.randomUUID().toString());
        intent.putExtra(EXTRA_TIMESTAMP, SystemClock.elapsedRealtime());
        intent.putExtra(EXTRA_SOURCE_PACKAGE, this.context.getPackageName());
        intent.putExtra(EXTRA_CLIENT, this.client.getBundle());
        intent.putExtra(EXTRA_SOFTWARE_VERSION, str);
        this.context.sendBroadcast(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendVoiceInteractionEvent(AlexaMetricsName alexaMetricsName, @Nullable String str) {
        sendVoiceInteractionEvent(alexaMetricsName, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendVoiceInteractionEvent(AlexaMetricsName alexaMetricsName, @Nullable String str, @Nullable String str2) {
        sendVoiceInteractionEvent(alexaMetricsName, str, str2, "");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sendVoiceInteractionEvent(AlexaMetricsName alexaMetricsName, @Nullable String str, @Nullable String str2, String str3) {
        Intent intent = new Intent(VOICE_ACTION);
        String str4 = this.leaderPackageName;
        if (str4 == null) {
            str4 = this.context.getPackageName();
        }
        intent.setPackage(str4);
        intent.putExtra(EXTRA_NAME, alexaMetricsName.getValue());
        intent.putExtra(EXTRA_EVENT_ID, UUID.randomUUID().toString());
        intent.putExtra(EXTRA_TIMESTAMP, SystemClock.elapsedRealtime());
        intent.putExtra(EXTRA_SOURCE_PACKAGE, this.context.getPackageName());
        intent.putExtra(EXTRA_CLIENT, this.client.getBundle());
        if (str != null) {
            intent.putExtra(EXTRA_INVOCATION_TYPE, str);
        }
        if (str2 != null) {
            intent.putExtra(EXTRA_DIALOG_TURN_ID, str2);
        }
        intent.putExtra(EXTRA_SOFTWARE_VERSION, str3);
        this.context.sendBroadcast(intent);
    }
}

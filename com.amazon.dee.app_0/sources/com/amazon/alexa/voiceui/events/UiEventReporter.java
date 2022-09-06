package com.amazon.alexa.voiceui.events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes11.dex */
public class UiEventReporter {
    public static final String ACTION_PUBLISH_UI_EVENT = "com.amazon.alexa.intent.action.PUBLISH_UI_EVENT";
    public static final String EXTRA_EVENT_DATA = "com.amazon.alexa.intent.extra.EVENT_DATA";
    public static final String EXTRA_EVENT_NAME = "com.amazon.alexa.intent.extra.EVENT_NAME";
    private final Context context;
    private final LocalBroadcastManager localBroadcastManager;
    private final String packageName;

    @Inject
    public UiEventReporter(Context context) {
        this(context, context.getPackageName(), LocalBroadcastManager.getInstance(context));
    }

    public void sendEvent(UiEventName uiEventName, @Nullable Bundle bundle) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Intent intent = new Intent("com.amazon.alexa.intent.action.PUBLISH_UI_EVENT");
        intent.setPackage(this.packageName);
        intent.putExtra("com.amazon.alexa.intent.extra.EVENT_NAME", uiEventName.name());
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putLong(AlexaMetadataBundleKey.EVENT_REALTIME_MS.name(), elapsedRealtime);
        intent.putExtra("com.amazon.alexa.intent.extra.EVENT_DATA", bundle);
        this.localBroadcastManager.sendBroadcast(intent);
        if (UiEventName.ALEXA_UI_SHOWN.equals(uiEventName) || UiEventName.ALEXA_UI_DISMISSED.equals(uiEventName)) {
            this.context.sendBroadcast(intent);
        }
    }

    @VisibleForTesting
    UiEventReporter(Context context, String str, LocalBroadcastManager localBroadcastManager) {
        this.context = context;
        this.packageName = str;
        this.localBroadcastManager = localBroadcastManager;
    }
}

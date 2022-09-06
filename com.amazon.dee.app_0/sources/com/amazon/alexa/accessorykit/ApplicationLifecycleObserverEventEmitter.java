package com.amazon.alexa.accessorykit;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver;
/* loaded from: classes6.dex */
public class ApplicationLifecycleObserverEventEmitter implements ApplicationLifecycleObserver {
    public static final String INTENT_ACTION_APPLICATION_LIFECYCLE = "com.amazon.alexa.accessorykit.action.application.lifecycle";
    public static final String KEY_LIFECYCLE_EVENT = "lifecycle_event";
    public static final String LIFECYCLE_EVENT_START = "onStart";
    public static final String LIFECYCLE_EVENT_STOP = "onStop";
    private static final String TAG = "ApplicationLifecycleObserverEventEmitter:";
    private final Context context;

    public ApplicationLifecycleObserverEventEmitter(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
    }

    private void sendApplicationLifecycleEvent(String str) {
        this.context.sendBroadcast(new Intent(INTENT_ACTION_APPLICATION_LIFECYCLE).setPackage(AccessoriesFactory.getAppName()).setFlags(268435456).putExtra(KEY_LIFECYCLE_EVENT, str), "com.amazon.alexa.accessory.ACCESSORY_BROADCAST_PERMISSION");
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStart() {
        Logger.d("%s onStart", TAG);
        sendApplicationLifecycleEvent(LIFECYCLE_EVENT_START);
    }

    @Override // com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleObserver
    public void onStop() {
        Logger.d("%s onStop", TAG);
        sendApplicationLifecycleEvent(LIFECYCLE_EVENT_STOP);
    }
}

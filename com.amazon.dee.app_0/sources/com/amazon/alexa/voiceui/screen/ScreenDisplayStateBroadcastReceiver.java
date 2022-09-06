package com.amazon.alexa.voiceui.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes11.dex */
public class ScreenDisplayStateBroadcastReceiver extends BroadcastReceiver {
    private final Context context;
    private AtomicBoolean isRegistered = new AtomicBoolean(false);
    private final DisplayStateChangeListener listener;
    private static final String TAG = ScreenDisplayStateBroadcastReceiver.class.getSimpleName();
    private static final IntentFilter INTENT_FILTER = new IntentFilter();

    /* loaded from: classes11.dex */
    public interface DisplayStateChangeListener {
        void onScreenEvent(boolean z);
    }

    static {
        INTENT_FILTER.addAction("android.intent.action.SCREEN_ON");
        INTENT_FILTER.addAction("android.intent.action.SCREEN_OFF");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ScreenDisplayStateBroadcastReceiver(Context context, DisplayStateChangeListener displayStateChangeListener) {
        this.context = context;
        this.listener = displayStateChangeListener;
    }

    public void deregister() {
        if (this.isRegistered.compareAndSet(true, false)) {
            this.context.unregisterReceiver(this);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)) {
            Log.i(TAG, "Screen is off");
            this.listener.onScreenEvent(false);
        } else if (!"android.intent.action.SCREEN_ON".equals(action)) {
        } else {
            Log.i(TAG, "Screen is on");
            this.listener.onScreenEvent(true);
        }
    }

    public void register() {
        if (this.isRegistered.compareAndSet(false, true)) {
            this.context.registerReceiver(this, INTENT_FILTER);
        }
    }
}

package com.amazon.communication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.amazon.communication.ScreenEventListener;
import com.amazon.dp.logger.DPLogger;
import com.dp.utils.FailFast;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes12.dex */
public class ScreenEventMonitorImpl extends BroadcastReceiver implements ScreenEventMonitor {
    private static final DPLogger log = new DPLogger("TComm.ScreenEventMonitorImpl");
    protected final Context mContext;
    protected final IntentFilter[] mFilters;
    protected boolean mIsStarted = false;
    protected final List<ScreenEventListener> mListeners = new CopyOnWriteArrayList();

    public ScreenEventMonitorImpl(Context context) {
        if (context != null) {
            this.mContext = context;
            this.mFilters = new IntentFilter[]{new IntentFilter("android.intent.action.SCREEN_ON"), new IntentFilter("android.intent.action.SCREEN_OFF")};
            return;
        }
        throw new IllegalArgumentException("context must not be null");
    }

    private void notifyScreenEventListeners(ScreenEventListener.Event event) {
        FailFast.expectNotNull(this.mListeners);
        log.verbose("notifyScreenEventListeners", "notifying screen event listeners", "number of listeners", Integer.valueOf(this.mListeners.size()));
        for (ScreenEventListener screenEventListener : this.mListeners) {
            FailFast.expectNotNull(screenEventListener);
            screenEventListener.onScreenEvent(event);
        }
    }

    @Override // com.amazon.communication.ScreenEventMonitor
    public void deregisterScreenEventListener(ScreenEventListener screenEventListener) {
        if (screenEventListener != null) {
            FailFast.expectNotNull(this.mListeners);
            this.mListeners.remove(screenEventListener);
            return;
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        PowerManager.WakeLock newWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, "TComm.ScreenEventMonitor");
        newWakeLock.acquire(PowerManagerWrapperImpl.WAKE_LOCK_TIMEOUT_MS);
        try {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(action) || "android.intent.action.SCREEN_OFF".equals(action)) {
                log.verbose("onReceive", "received intent about screen event", "action", action);
                notifyScreenEventListeners("android.intent.action.SCREEN_ON".equals(action) ? ScreenEventListener.Event.ON : ScreenEventListener.Event.OFF);
            }
        } finally {
            newWakeLock.release();
        }
    }

    @Override // com.amazon.communication.ScreenEventMonitor
    public void registerScreenEventListener(ScreenEventListener screenEventListener) {
        if (screenEventListener != null) {
            FailFast.expectNotNull(this.mListeners);
            this.mListeners.add(screenEventListener);
            return;
        }
        throw new IllegalArgumentException("listener must not be null");
    }

    @Override // com.amazon.communication.ScreenEventMonitor
    public synchronized void start() {
        if (!this.mIsStarted) {
            log.verbose("start", "starting ScreenEventMonitor", new Object[0]);
            for (IntentFilter intentFilter : this.mFilters) {
                this.mContext.registerReceiver(this, intentFilter);
            }
            this.mIsStarted = true;
        } else {
            log.warn("start", "attempted to start ScreenEventMonitor, but it was already running", new Object[0]);
        }
    }

    @Override // com.amazon.communication.ScreenEventMonitor
    public synchronized void stop() {
        if (this.mIsStarted) {
            this.mContext.unregisterReceiver(this);
            log.verbose("stop", "ScreenEventMonitor stopped", new Object[0]);
            this.mListeners.clear();
            this.mIsStarted = false;
        } else {
            log.warn("stop", "attempted to stop ScreenEventMonitor, but it was not running", new Object[0]);
        }
    }
}

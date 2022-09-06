package com.amazon.alexa.voiceui.screen;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import android.view.Window;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voiceui.screen.ScreenDisplayStateBroadcastReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.TimeUnit;
/* loaded from: classes11.dex */
public abstract class ScreenLockManager {
    @VisibleForTesting
    static final long SCREEN_OFF_AFTER_INTERACTION_MS = 45000;
    private final BehaviorSubject<Boolean> displayObservable = BehaviorSubject.createDefault(Boolean.valueOf(isScreenOn()));
    private final Handler handler;
    private final PowerManager powerManager;
    private final ScreenDisplayStateBroadcastReceiver screenDisplayStateBroadcastReceiver;
    private Runnable screenOffRunnable;
    private final WakeLockWrapper wakeLock;
    private final Window window;

    /* loaded from: classes11.dex */
    private static class DisplayListener implements ScreenDisplayStateBroadcastReceiver.DisplayStateChangeListener {
        private final BehaviorSubject<Boolean> screenOnObservable;

        DisplayListener(BehaviorSubject<Boolean> behaviorSubject) {
            this.screenOnObservable = behaviorSubject;
        }

        @Override // com.amazon.alexa.voiceui.screen.ScreenDisplayStateBroadcastReceiver.DisplayStateChangeListener
        public void onScreenEvent(boolean z) {
            this.screenOnObservable.onNext(Boolean.valueOf(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public static class WakeLockWrapper {
        private final PowerManager.WakeLock wakeLock;

        WakeLockWrapper(PowerManager.WakeLock wakeLock) {
            this.wakeLock = wakeLock;
        }

        public void acquire() {
            this.wakeLock.acquire(TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES));
        }

        public boolean isHeld() {
            return this.wakeLock.isHeld();
        }

        public void release() {
            this.wakeLock.release();
        }
    }

    public ScreenLockManager(Window window, Handler handler, Context context, PowerManager powerManager) {
        this.window = window;
        this.handler = handler;
        this.powerManager = powerManager;
        this.wakeLock = getWakeLock(context);
        this.screenDisplayStateBroadcastReceiver = getScreenDisplayStateBroadcastReceiver(context, new DisplayListener(this.displayObservable));
    }

    private void acquireWakeLock() {
        if (!this.wakeLock.isHeld()) {
            this.wakeLock.acquire();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disableKeepScreenOn() {
        this.window.clearFlags(128);
        releaseWakeLock();
    }

    private void keepScreenOn() {
        this.window.addFlags(128);
        acquireWakeLock();
    }

    private void releaseWakeLock() {
        if (this.wakeLock.isHeld()) {
            this.wakeLock.release();
        }
    }

    public void deregisterForDisplayStateUpdates() {
        this.screenDisplayStateBroadcastReceiver.deregister();
    }

    @VisibleForTesting
    ScreenDisplayStateBroadcastReceiver getScreenDisplayStateBroadcastReceiver(Context context, ScreenDisplayStateBroadcastReceiver.DisplayStateChangeListener displayStateChangeListener) {
        return new ScreenDisplayStateBroadcastReceiver(context, displayStateChangeListener);
    }

    @VisibleForTesting
    WakeLockWrapper getWakeLock(Context context) {
        PowerManager powerManager = this.powerManager;
        return new WakeLockWrapper(powerManager.newWakeLock(268435466, context.getPackageName() + ":screenlock"));
    }

    public boolean isScreenOn() {
        return this.powerManager.isInteractive();
    }

    public void registerForDisplayStateUpdates() {
        this.screenDisplayStateBroadcastReceiver.register();
    }

    public void releaseLock() {
        this.handler.removeCallbacks(this.screenOffRunnable);
        disableKeepScreenOn();
    }

    public void requestLock() {
        this.handler.removeCallbacks(this.screenOffRunnable);
        this.screenOffRunnable = new Runnable() { // from class: com.amazon.alexa.voiceui.screen.ScreenLockManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (ScreenLockManager.this.shouldDisableKeepScreenOn()) {
                    ScreenLockManager.this.disableKeepScreenOn();
                } else {
                    ScreenLockManager.this.handler.postDelayed(this, ScreenLockManager.SCREEN_OFF_AFTER_INTERACTION_MS);
                }
            }
        };
        this.handler.postDelayed(this.screenOffRunnable, SCREEN_OFF_AFTER_INTERACTION_MS);
        keepScreenOn();
    }

    public abstract boolean shouldDisableKeepScreenOn();

    public Observable<Boolean> subscribeToScreenOn() {
        return this.displayObservable.distinctUntilChanged();
    }
}

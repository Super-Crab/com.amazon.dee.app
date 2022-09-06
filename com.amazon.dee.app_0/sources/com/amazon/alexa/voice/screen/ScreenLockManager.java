package com.amazon.alexa.voice.screen;

import android.content.Context;
import android.os.Handler;
import android.os.PowerManager;
import android.view.Window;
/* loaded from: classes11.dex */
public abstract class ScreenLockManager {
    private static final long SCREEN_OFF_AFTER_INTERACTION_MS = 45000;
    private final Handler handler;
    private Runnable screenOffRunnable;
    private final PowerManager.WakeLock wakeLock;
    private final Window window;

    public ScreenLockManager(Window window, Handler handler, Context context) {
        this.window = window;
        this.handler = handler;
        this.wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(268435466, "screenlock");
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

    public void releaseLock() {
        this.handler.removeCallbacks(this.screenOffRunnable);
        disableKeepScreenOn();
    }

    public void requestLock() {
        this.handler.removeCallbacks(this.screenOffRunnable);
        this.screenOffRunnable = new Runnable() { // from class: com.amazon.alexa.voice.screen.ScreenLockManager.1
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
}

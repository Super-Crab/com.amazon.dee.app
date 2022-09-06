package com.amazon.alexa.accessory.notificationpublisher.timers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.AccessoryTtsStateManager;
import com.amazon.alexa.accessory.notificationpublisher.notificationsource.NotificationSource;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Timer;
import java.util.TimerTask;
/* loaded from: classes.dex */
public class BaseTimer {
    long durationInMillis;
    private String logTag;
    @Nullable
    private NotificationSource notificationSource;
    private volatile boolean running;
    private long startTimeInMillis;
    private Timer timer;
    private TimerExpiredCallback timerExpiredCallback;
    private TimerTask timerTask;
    int timerType;

    private BaseTimer() {
    }

    private void startTimerTask() {
        String str = this.logTag;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("startTimerTask starting a timer for duration (Millis) - ");
        outline107.append(this.durationInMillis);
        Log.i(str, outline107.toString());
        this.timer = new Timer();
        this.timerTask = new BaseTimerTask(this);
        this.timer.schedule(this.timerTask, this.durationInMillis);
        this.running = true;
        this.startTimeInMillis = System.currentTimeMillis();
        String str2 = this.logTag;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("startTimerTask - Start timestamp - ");
        outline1072.append(this.startTimeInMillis);
        Log.d(str2, outline1072.toString());
        int i = this.timerType;
        if (i == 1 || i == 4) {
            AccessoryTtsStateManager.getInstance().updateAccessoryTtsState(this.durationInMillis);
        }
    }

    public synchronized void cancel() {
        Log.i(this.logTag, "cancel called");
        if (this.timer != null && this.running) {
            Log.i(this.logTag, "cancel - Time is running, cancel it");
            this.timer.cancel();
            this.timerTask.cancel();
            this.running = false;
        }
    }

    @Nullable
    public synchronized NotificationSource getNotificationSource() {
        return this.notificationSource;
    }

    public synchronized boolean isRunning() {
        String str = this.logTag;
        Log.d(str, "isRunning called - " + this.running);
        return this.running;
    }

    public synchronized void restart() {
        Log.i(this.logTag, "reset called");
        cancel();
        startTimerTask();
    }

    public synchronized void start(@Nullable NotificationSource notificationSource, @NonNull TimerExpiredCallback timerExpiredCallback) throws IllegalStateException, IllegalArgumentException {
        Log.i(this.logTag, "start called");
        if (this.running) {
            Log.e(this.logTag, "Timer is already running, cannot start a running timer");
            throw new IllegalStateException("Cannot start a timer if it is already running");
        } else if (timerExpiredCallback != null) {
            if (notificationSource != null) {
                String str = this.logTag;
                Log.d(str, "start with source - " + notificationSource.displayString());
            }
            this.notificationSource = notificationSource;
            this.timerExpiredCallback = timerExpiredCallback;
            startTimerTask();
        } else {
            Log.e(this.logTag, "Timer expired callback cannot be null");
            throw new IllegalArgumentException("TimerExpiredCallback cannot be null");
        }
    }

    public long timeElapsedInMillis() {
        if (this.running) {
            return System.currentTimeMillis() - this.startTimeInMillis;
        }
        return Long.MIN_VALUE;
    }

    public long timeRemainingInMillis() {
        if (this.running) {
            return this.durationInMillis - timeElapsedInMillis();
        }
        return Long.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void timerExpired() {
        Log.i(this.logTag, "timerExpired - Timer expired");
        if (this.timerExpiredCallback != null) {
            this.timerExpiredCallback.onTimerExpired(this.timerType, this.notificationSource);
        }
        this.timer.cancel();
        this.running = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateDefaultTimeDuration() {
        throw new UnsupportedOperationException("Implementation to be provided by inherited class.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseTimer(@NonNull String str) {
        this();
        this.logTag = str;
    }

    public synchronized boolean isRunning(@Nullable NotificationSource notificationSource) {
        String str = this.logTag;
        Log.i(str, "isRunning called for source - " + this.running);
        if (notificationSource == null && this.notificationSource == null) {
            Log.i(this.logTag, "isRunning - No source");
            return this.running;
        }
        boolean z = false;
        if (notificationSource != null && this.notificationSource != null) {
            String str2 = this.logTag;
            Log.d(str2, "isRunning source - " + notificationSource.displayString());
            String str3 = this.logTag;
            Log.d(str3, "isRunning this.source - " + this.notificationSource.displayString());
            if (this.running && notificationSource.equals(this.notificationSource)) {
                z = true;
            }
            return z;
        }
        Log.i(this.logTag, "isRunning returning false");
        return false;
    }

    public synchronized void restart(@Nullable NotificationSource notificationSource, @NonNull TimerExpiredCallback timerExpiredCallback) throws IllegalStateException, IllegalArgumentException {
        Log.i(this.logTag, "reset called with Source");
        if (notificationSource != null) {
            String str = this.logTag;
            Log.d(str, "reset with source - " + notificationSource.displayString());
        }
        cancel();
        start(notificationSource, timerExpiredCallback);
    }
}

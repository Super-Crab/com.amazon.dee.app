package com.amazon.alexa.alertsca.notification;

import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.notification.AlexaAlertsNotificationBuilderFactory;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public class CountdownClock implements Runnable {
    private static final String LONG_NEGATIVE_TIMER_DISPLAY_FORMAT = "-%d:%02d:%02d";
    private static final String LONG_TIMER_DISPLAY_FORMAT = "%d:%02d:%02d";
    private static final String SHORT_NEGATIVE_TIMER_DISPLAY_FORMAT = "-%02d:%02d";
    private static final String SHORT_TIMER_DISPLAY_FORMAT = "%02d:%02d";
    private static final long UPDATE_FREQUENCY_MS = 20;
    private final AlertRecord alertRecord;
    private RemainingTime currentDisplayedTime;
    private final long initialCurrentTimeMillis = System.currentTimeMillis();
    private final long initialElapsedTimeMillis = SystemClock.elapsedRealtime();
    private final ScheduledFuture<?> timeUpdater;
    private final AlexaAlertsNotificationBuilderFactory.NotificationUpdateHandler updateHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class RemainingTime {
        private final long hoursRemaining;
        private final long minutesRemaining;
        private final long secondsRemaining;

        RemainingTime(long j, long j2, long j3) {
            this.hoursRemaining = j;
            this.minutesRemaining = j2;
            this.secondsRemaining = j3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || RemainingTime.class != obj.getClass()) {
                return false;
            }
            RemainingTime remainingTime = (RemainingTime) obj;
            return this.hoursRemaining == remainingTime.hoursRemaining && this.minutesRemaining == remainingTime.minutesRemaining && this.secondsRemaining == remainingTime.secondsRemaining;
        }

        public int hashCode() {
            return Objects.hash(Long.valueOf(this.hoursRemaining), Long.valueOf(this.minutesRemaining), Long.valueOf(this.secondsRemaining));
        }

        public String toString() {
            if (this.secondsRemaining > 0) {
                long j = this.hoursRemaining;
                if (j > 0) {
                    return String.format(Locale.US, CountdownClock.LONG_TIMER_DISPLAY_FORMAT, Long.valueOf(j), Long.valueOf(this.minutesRemaining), Long.valueOf(this.secondsRemaining));
                }
                return String.format(Locale.US, CountdownClock.SHORT_TIMER_DISPLAY_FORMAT, Long.valueOf(this.minutesRemaining), Long.valueOf(this.secondsRemaining));
            }
            long j2 = this.hoursRemaining;
            if (j2 < 0) {
                return String.format(Locale.US, CountdownClock.LONG_NEGATIVE_TIMER_DISPLAY_FORMAT, Long.valueOf(Math.abs(j2)), Long.valueOf(Math.abs(this.minutesRemaining)), Long.valueOf(Math.abs(this.secondsRemaining)));
            }
            return String.format(Locale.US, CountdownClock.SHORT_NEGATIVE_TIMER_DISPLAY_FORMAT, Long.valueOf(Math.abs(this.minutesRemaining)), Long.valueOf(Math.abs(this.secondsRemaining)));
        }
    }

    public CountdownClock(AlertRecord alertRecord, ScheduledExecutorService scheduledExecutorService, AlexaAlertsNotificationBuilderFactory.NotificationUpdateHandler notificationUpdateHandler) {
        this.alertRecord = alertRecord;
        this.updateHandler = notificationUpdateHandler;
        this.currentDisplayedTime = getRemainingTime(alertRecord, this.initialCurrentTimeMillis, 0L);
        this.timeUpdater = scheduledExecutorService.scheduleAtFixedRate(this, 0L, 20L, TimeUnit.MILLISECONDS);
    }

    @VisibleForTesting
    static RemainingTime getRemainingTime(AlertRecord alertRecord, long j, long j2) {
        long time = alertRecord.getScheduledTime().getTime() - (j + j2);
        long convert = TimeUnit.HOURS.convert(time, TimeUnit.MILLISECONDS);
        long convert2 = TimeUnit.MINUTES.convert(time - TimeUnit.HOURS.toMillis(convert), TimeUnit.MILLISECONDS);
        return new RemainingTime(convert, convert2, TimeUnit.SECONDS.convert((time - TimeUnit.HOURS.toMillis(convert)) - TimeUnit.MINUTES.toMillis(convert2), TimeUnit.MILLISECONDS));
    }

    @Override // java.lang.Runnable
    public void run() {
        RemainingTime remainingTime = getRemainingTime(this.alertRecord, this.initialCurrentTimeMillis, SystemClock.elapsedRealtime() - this.initialElapsedTimeMillis);
        if (!this.currentDisplayedTime.equals(remainingTime)) {
            this.currentDisplayedTime = remainingTime;
            this.updateHandler.onUpdate(this.alertRecord, this.currentDisplayedTime.toString());
        }
    }

    public void stop() {
        this.timeUpdater.cancel(true);
    }
}

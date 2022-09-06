package com.amazon.deecomms.calling.controller;

import android.content.Context;
import com.amazon.deecomms.common.util.JodaTimeInitializer;
import com.amazon.deecomms.util.ThreadUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
/* loaded from: classes12.dex */
public class CallTimerManager extends JodaTimeInitializer {
    private static final String DURATION_FORMAT = "%s";
    private static final int DURATION_INCREMENT_IN_SECONDS = 1;
    private static final long TIMER_DELAY_IN_MILLIS = 1000;
    private static final long TIMER_REFRESH_IN_MILLIS = 1000;
    private String mCallDuration;
    private Period mDuration;
    private final PeriodFormatter mDurationFormatter;
    private final List<NotificationUpdateListener> mNotificationUpdateListenerList;
    private Timer mTimer;
    private TimerTask mTimerTask;

    /* loaded from: classes12.dex */
    class CallDurationTimerTask extends TimerTask {
        CallDurationTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            CallTimerManager.this.incrementDuration();
            CallTimerManager.this.updateCallDuration();
        }
    }

    /* loaded from: classes12.dex */
    public interface NotificationUpdateListener {
        void onDurationUpdated(String str);
    }

    public CallTimerManager(Context context) {
        super(context);
        this.mDurationFormatter = buildDurationFormatter(false);
        this.mNotificationUpdateListenerList = Collections.synchronizedList(new ArrayList());
    }

    private static PeriodFormatter buildDurationFormatter(boolean z) {
        PeriodFormatterBuilder minimumPrintedDigits = new PeriodFormatterBuilder().minimumPrintedDigits(2);
        return (z ? minimumPrintedDigits.printZeroAlways().appendHours() : minimumPrintedDigits.appendHours().printZeroAlways()).appendSuffix(":").appendMinutes().appendSuffix(":").appendSeconds().toFormatter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void incrementDuration() {
        this.mDuration = this.mDuration.plusSeconds(1);
    }

    private void notifyTimerUpdate() {
        synchronized (this.mNotificationUpdateListenerList) {
            for (final NotificationUpdateListener notificationUpdateListener : this.mNotificationUpdateListenerList) {
                ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.calling.controller.-$$Lambda$CallTimerManager$8ujpK1_JBYbTLbvsCFZc3Nzmnqc
                    @Override // java.lang.Runnable
                    public final void run() {
                        CallTimerManager.this.lambda$notifyTimerUpdate$0$CallTimerManager(notificationUpdateListener);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCallDuration() {
        this.mCallDuration = String.format(DURATION_FORMAT, this.mDurationFormatter.print(this.mDuration.normalizedStandard(PeriodType.time())));
        notifyTimerUpdate();
    }

    public void addListener(NotificationUpdateListener notificationUpdateListener) {
        if (notificationUpdateListener != null) {
            this.mNotificationUpdateListenerList.add(notificationUpdateListener);
        }
    }

    public String getCallDuration() {
        return this.mCallDuration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getDurationInSeconds() {
        Period period = this.mDuration;
        if (period != null) {
            return period.getSeconds();
        }
        return -1;
    }

    public /* synthetic */ void lambda$notifyTimerUpdate$0$CallTimerManager(NotificationUpdateListener notificationUpdateListener) {
        notificationUpdateListener.onDurationUpdated(this.mCallDuration);
    }

    public void removeListener(NotificationUpdateListener notificationUpdateListener) {
        if (notificationUpdateListener != null) {
            this.mNotificationUpdateListenerList.remove(notificationUpdateListener);
        }
    }

    public void startTimer() {
        this.mTimerTask = new CallDurationTimerTask();
        this.mTimer = new Timer();
        this.mDuration = new Period();
        this.mTimer.schedule(this.mTimerTask, 1000L, 1000L);
    }

    public void stopTimer() {
        TimerTask timerTask = this.mTimerTask;
        if (timerTask == null || this.mTimer == null) {
            return;
        }
        timerTask.cancel();
        this.mTimer.purge();
        this.mTimer.cancel();
        this.mCallDuration = "";
    }
}

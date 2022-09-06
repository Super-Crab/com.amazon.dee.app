package com.amazon.alexa.alertsca;

import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsAnalyzer {
    private static final String TAG = "AlertsAnalyzer";
    @VisibleForTesting
    static final long VALID_ALERT_DELIVERY_INTERVAL_MINUTES = 30;

    /* loaded from: classes6.dex */
    interface AlertExpireHandler {
        void onExpire(AlertRecord alertRecord);
    }

    /* loaded from: classes6.dex */
    interface AlertLaunchHandler {
        void onLaunch(AlertRecord alertRecord);
    }

    /* loaded from: classes6.dex */
    interface AlertScheduledHandler {
        void onSchedule(AlertRecord alertRecord);
    }

    private static long getTimeRemainingForAlert(long j) {
        return j - System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void analyze(AlertRecord alertRecord, AlertScheduledHandler alertScheduledHandler, AlertLaunchHandler alertLaunchHandler, AlertExpireHandler alertExpireHandler) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Analyzing: ");
        outline107.append(alertRecord.getToken());
        outline107.toString();
        long timeRemainingForAlert = getTimeRemainingForAlert(alertRecord.getScheduledTime().getTime());
        if (TimeUnit.MILLISECONDS.convert(VALID_ALERT_DELIVERY_INTERVAL_MINUTES, TimeUnit.MINUTES) + timeRemainingForAlert < 0) {
            GeneratedOutlineSupport1.outline153("Analyzed as expire: ", timeRemainingForAlert);
            alertExpireHandler.onExpire(alertRecord);
        } else if (timeRemainingForAlert <= 0) {
            GeneratedOutlineSupport1.outline153("Analyzed as trigger: ", timeRemainingForAlert);
            alertLaunchHandler.onLaunch(alertRecord);
        } else {
            GeneratedOutlineSupport1.outline153("Analyzed as schedule: ", timeRemainingForAlert);
            alertScheduledHandler.onSchedule(alertRecord);
        }
    }
}

package com.amazon.deecomms.api;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public abstract class CommsDelegateBase {
    public abstract void authExpired();

    public abstract void callEnded();

    public abstract void commsLogout();

    public abstract MAPAccountManager getAccountManager();

    public abstract String getDeviceTypeId();

    public abstract void incomingCall();

    public abstract boolean isFeatureEnabled(CommsFeature commsFeature);

    public abstract void loadingComplete(CommsView commsView);

    public abstract void loadingCompleteOobe();

    public abstract void recordCounterMetric(CounterMetric counterMetric);

    public abstract void recordTimerMetric(TimerMetric timerMetric);

    public boolean startMainActivity(@NonNull Context context, @NonNull CommsView commsView) {
        throw new UnsupportedOperationException();
    }

    public abstract void startTimerMetric(CommsMetric commsMetric);

    public abstract void stopTimerMetric(CommsMetric commsMetric);
}

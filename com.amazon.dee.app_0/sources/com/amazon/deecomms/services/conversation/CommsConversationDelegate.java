package com.amazon.deecomms.services.conversation;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsFeature;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.services.metrics.DefaultMetricsTimer;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.dee.app.metrics.MetricsCounter;
import com.dee.app.metrics.MetricsService;
import com.dee.app.metrics.MetricsTimer;
import dagger.Lazy;
import java.util.HashMap;
@Deprecated
/* loaded from: classes12.dex */
public class CommsConversationDelegate extends CommsDelegateBase {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsConversationDelegate.class);
    private Lazy<EventBus> eventBus;
    private MAPAccountManager mapAccountManager;
    private MetricsService metrics;

    public CommsConversationDelegate(MAPAccountManager mAPAccountManager, MetricsService metricsService, Lazy<EventBus> lazy) {
        this.mapAccountManager = mAPAccountManager;
        this.metrics = metricsService;
        this.eventBus = lazy;
    }

    private MetricsTimer convertTimerMetricToMetricsTimer(TimerMetric timerMetric) {
        return new DefaultMetricsTimer(timerMetric.getMetricName(), timerMetric.getServiceName(), new HashMap(timerMetric.getMetadata()), timerMetric.getTimeDelta(), false);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void authExpired() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void callEnded() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void commsLogout() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public MAPAccountManager getAccountManager() {
        return this.mapAccountManager;
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public String getDeviceTypeId() {
        return "A2TF17PFR55MTB";
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void incomingCall() {
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public boolean isFeatureEnabled(CommsFeature commsFeature) {
        return false;
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void loadingComplete(CommsView commsView) {
        this.eventBus.mo358get().publish(new Message.Builder().setEventType(CommsEventBusConstants.COMMS_CONVERSATION_COMPLETE).build());
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void loadingCompleteOobe() {
        this.eventBus.mo358get().publish(new Message.Builder().setEventType(CommsEventBusConstants.COMMS_OOBE_COMPLETE).build());
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void recordCounterMetric(CounterMetric counterMetric) {
        MetricsCounter startCounter = this.metrics.startCounter(counterMetric.getMetricName(), counterMetric.getServiceName(), new HashMap(counterMetric.getMetadata()));
        this.metrics.incrementCounterByValue(counterMetric.getMetricName(), counterMetric.getCount().intValue());
        this.metrics.recordCounter(startCounter);
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void recordTimerMetric(TimerMetric timerMetric) {
        this.metrics.recordTimer(convertTimerMetricToMetricsTimer(timerMetric));
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public boolean startMainActivity(@NonNull Context context, CommsView commsView) {
        char c;
        String string = context.getSharedPreferences(Constants.MAIN_IN_FG, 0).getString(Constants.MAIN_IN_FG, Constants.GONE);
        int hashCode = string.hashCode();
        if (hashCode == -1825417917) {
            if (string.equals(Constants.FOREGROUND)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -847101650) {
            if (hashCode == 2193567 && string.equals(Constants.GONE)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (string.equals(Constants.BACKGROUND)) {
                c = 1;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c != 1 && c != 2) {
                LOG.w("The status of MainActivity was an unhandled value of " + string);
                return false;
            }
            try {
                Intent intent = new Intent(context, Class.forName("com.amazon.dee.app.ui.main.MainActivity"));
                intent.setAction(Constants.COMMS_VOICE_NAV_ACTION);
                intent.putExtra(Constants.COMMS_VOICE_NAV_ACTION, commsView.name());
                intent.setFlags(131072);
                intent.setFlags(268435456);
                context.startActivity(intent);
                return true;
            } catch (ClassNotFoundException e) {
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Main activity not found: ");
                outline1.append(e.getCause());
                throw new IllegalArgumentException(outline1.toString());
            }
        }
        return false;
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void startTimerMetric(CommsMetric commsMetric) {
        this.metrics.startTimer(commsMetric.getMetricName(), commsMetric.getServiceName(), new HashMap(commsMetric.getMetadata()));
    }

    @Override // com.amazon.deecomms.api.CommsDelegateBase
    public void stopTimerMetric(CommsMetric commsMetric) {
        if (this.metrics.isOngoingEvent(commsMetric.getMetricName())) {
            this.metrics.recordTimer(commsMetric.getMetricName(), new HashMap(commsMetric.getMetadata()));
        }
    }
}

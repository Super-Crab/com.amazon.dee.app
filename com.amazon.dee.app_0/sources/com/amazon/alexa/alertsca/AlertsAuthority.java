package com.amazon.alexa.alertsca;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessories.protocols.ConnectedAccessoryInquirer;
import com.amazon.alexa.alertsca.AlertsAnalyzer;
import com.amazon.alexa.alertsca.AlertsConstants;
import com.amazon.alexa.alertsca.events.AlertScheduledEvent;
import com.amazon.alexa.alertsca.events.AlertStartedEvent;
import com.amazon.alexa.alertsca.events.AlertStoppedEvent;
import com.amazon.alexa.alertsca.events.AlertUnscheduledEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.payload.AlertsPayload;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledExecutorService;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
@Singleton
/* loaded from: classes6.dex */
public class AlertsAuthority implements AlertStoppedListener {
    @VisibleForTesting
    static final int ALARM_TIMER_SILENCE_INTERVAL = 900000;
    @VisibleForTesting
    static final String ALERTS_PERMISSION_NAME = "com.amazon.alexa.alertsca.events.AlertEvent";
    public static final Set<AlertType> ALERT_TYPES_SHOWING_FULL_SCREEN = Sets.immutableEnumSet(AlertType.TIMER, AlertType.ALARM);
    private static final String TAG = "AlertsAuthority";
    private final AlertView alertView;
    private final AlertsAnalyzer alertsAnalyzer;
    private final AlertsEventBus alertsEventBus;
    private final AlertsExoPlayer alertsExoPlayer;
    private final AlertsScheduler alertsScheduler;
    private final AlertsStore alertsStore;
    private final AlexaEventSender alexaEventSender;
    private final AlexaInteractionScheduler alexaInteractionScheduler;
    private final AudioFocusManager audioFocusManager;
    private final ConnectedAccessoryInquirer connectedAccessoryInquirer;
    private final Context context;
    private final Gson gson;
    private final Handler handler;
    private final MetricsService metricsService;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ScoAuthority scoAuthority;
    private final Map<AlertsToken, AlertInteraction> activeAlerts = new HashMap();
    private final Map<AlertsToken, AlertRecord> allAlerts = new HashMap();

    /* renamed from: com.amazon.alexa.alertsca.AlertsAuthority$3  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType = new int[AlertType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.ALARM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.REMINDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$compat$alerts$AlertType[AlertType.TIMER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private class AlertStoreExpirer implements AlertsAnalyzer.AlertExpireHandler {
        private AlertStoreExpirer() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertExpireHandler
        public void onExpire(AlertRecord alertRecord) {
            synchronized (AlertsAuthority.this) {
                String unused = AlertsAuthority.TAG;
                String str = "onExpire from store: " + alertRecord.getToken().getValue();
                AlertsAuthority.this.sendAlertStopped(alertRecord.getToken());
                AlertsAuthority.this.deleteAlert(alertRecord.getToken());
            }
        }
    }

    /* loaded from: classes6.dex */
    private class AlertStoreScheduler implements AlertsAnalyzer.AlertScheduledHandler {
        private AlertStoreScheduler() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertScheduledHandler
        public void onSchedule(AlertRecord alertRecord) {
            synchronized (AlertsAuthority.this) {
                String unused = AlertsAuthority.TAG;
                String str = "onSchedule from store: " + alertRecord.getToken().getValue();
                AlertsAuthority.this.allAlerts.put(alertRecord.getToken(), alertRecord);
                AlertsAuthority.this.alertsScheduler.schedule(AlertsAuthority.this.context, alertRecord, true);
                AlertsAuthority.this.alertsEventBus.post(AlertScheduledEvent.create(alertRecord));
            }
        }
    }

    /* loaded from: classes6.dex */
    private class AlertStoreTriggerer implements AlertsAnalyzer.AlertLaunchHandler {
        private AlertStoreTriggerer() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertLaunchHandler
        public void onLaunch(AlertRecord alertRecord) {
            synchronized (AlertsAuthority.this) {
                String unused = AlertsAuthority.TAG;
                String str = "onLaunch from store: " + alertRecord.getToken().getValue();
                AlertsAuthority.this.launch(alertRecord);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class SetAlertExpirer implements AlertsAnalyzer.AlertExpireHandler {
        private SetAlertExpirer() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertExpireHandler
        public void onExpire(AlertRecord alertRecord) {
            String unused = AlertsAuthority.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onExpire from directive: ");
            outline107.append(alertRecord.getToken().getValue());
            outline107.toString();
            AlertsAuthority.this.sendAlertStopped(alertRecord.getToken());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class SetAlertScheduler implements AlertsAnalyzer.AlertScheduledHandler {
        private SetAlertScheduler() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertScheduledHandler
        public void onSchedule(AlertRecord alertRecord) {
            String unused = AlertsAuthority.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onSchedule from directive: ");
            outline107.append(alertRecord.getToken().getValue());
            outline107.toString();
            AlertsAuthority.this.allAlerts.put(alertRecord.getToken(), alertRecord);
            AlertsAuthority.this.alertsScheduler.schedule(AlertsAuthority.this.context, alertRecord);
            AlertsAuthority.this.alertsStore.add(alertRecord);
            AlertsAuthority.this.alertsEventBus.post(AlertScheduledEvent.create(alertRecord));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class SetAlertTriggerer implements AlertsAnalyzer.AlertLaunchHandler {
        private SetAlertTriggerer() {
        }

        @Override // com.amazon.alexa.alertsca.AlertsAnalyzer.AlertLaunchHandler
        public void onLaunch(AlertRecord alertRecord) {
            String unused = AlertsAuthority.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onLaunch from directive: ");
            outline107.append(alertRecord.getToken().getValue());
            outline107.toString();
            AlertsAuthority.this.launch(alertRecord);
        }
    }

    @Inject
    public AlertsAuthority(Context context, AlertsEventBus alertsEventBus, AlertsScheduler alertsScheduler, final AlertsStore alertsStore, final AlertsAnalyzer alertsAnalyzer, Gson gson, AlertsExoPlayer alertsExoPlayer, AudioFocusManager audioFocusManager, AlexaEventSender alexaEventSender, AlexaInteractionScheduler alexaInteractionScheduler, @Named("shared_scheduler") ScheduledExecutorService scheduledExecutorService, AlertView alertView, Handler handler, ConnectedAccessoryInquirer connectedAccessoryInquirer, ScoAuthority scoAuthority, MetricsService metricsService) {
        this.context = context;
        this.alertsScheduler = alertsScheduler;
        this.alertsEventBus = alertsEventBus;
        this.alertsStore = alertsStore;
        this.alertsAnalyzer = alertsAnalyzer;
        this.gson = gson;
        this.alertsExoPlayer = alertsExoPlayer;
        this.audioFocusManager = audioFocusManager;
        this.alexaEventSender = alexaEventSender;
        this.alexaInteractionScheduler = alexaInteractionScheduler;
        this.scheduledExecutorService = scheduledExecutorService;
        this.alertView = alertView;
        this.handler = handler;
        this.connectedAccessoryInquirer = connectedAccessoryInquirer;
        this.scoAuthority = scoAuthority;
        this.metricsService = metricsService;
        alertsEventBus.register(this);
        scheduledExecutorService.submit(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsAuthority.1
            @Override // java.lang.Runnable
            public void run() {
                for (AlertRecord alertRecord : alertsStore.getAll()) {
                    alertsAnalyzer.analyze(alertRecord, new AlertStoreScheduler(), new AlertStoreTriggerer(), new AlertStoreExpirer());
                }
            }
        });
    }

    private Uri createPlayItem(AlertsToken alertsToken, AlertType alertType, Boolean bool) {
        AlertSoundName alertSoundName;
        Uri uri = Uri.EMPTY;
        if (this.alertsStore.hasAssets(alertsToken)) {
            uri = bool.booleanValue() ? this.alertsStore.getForegroundAsset(alertsToken) : this.alertsStore.getBackgroundAsset(alertsToken);
        }
        if (uri == Uri.EMPTY) {
            int ordinal = alertType.ordinal();
            if (ordinal == 0) {
                alertSoundName = bool.booleanValue() ? AlertSoundName.ALARM_FOREGROUND_SOUND : AlertSoundName.ALARM_BACKGROUND_SOUND;
            } else if (ordinal != 2) {
                alertSoundName = bool.booleanValue() ? AlertSoundName.TIMER_FOREGROUND_SOUND : AlertSoundName.TIMER_BACKGROUND_SOUND;
            } else {
                alertSoundName = bool.booleanValue() ? AlertSoundName.REMINDER_FOREGROUND_SOUND : AlertSoundName.REMINDER_BACKGROUND_SOUND;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("rawresource:///");
            outline107.append(alertSoundName.getResourceId());
            return Uri.parse(outline107.toString());
        }
        return uri;
    }

    private void scheduleAlertInteraction(AlertRecord alertRecord, Uri uri, Uri uri2) {
        AlertInteraction alertInteraction = new AlertInteraction(this.alertsEventBus, alertRecord, uri, uri2, this, this, this.gson, this.alertsExoPlayer, this.audioFocusManager, this.alexaEventSender, this.alexaInteractionScheduler, this.connectedAccessoryInquirer, this.scoAuthority, this.metricsService);
        this.activeAlerts.put(alertRecord.getToken(), alertInteraction);
        this.alexaInteractionScheduler.schedule(alertInteraction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAlertStopped(AlertsToken alertsToken) {
        String str = "sendAlertStopped: " + alertsToken;
        this.alexaEventSender.send(new AlexaEvent(AlexaHeader.create("Alerts", AlertsConstants.Alerts.Events.AlertStopped.NAME), new AlexaPayload(this.gson.toJson(AlertsPayload.create(alertsToken)))), true);
    }

    private void sendIntent(Intent intent) {
        String str = TAG;
        Log.i(str, "sending an intent: " + intent);
        this.context.sendBroadcast(intent, "com.amazon.alexa.alertsca.events.AlertEvent");
    }

    private void stopAllActiveAlerts() {
        for (AlertsToken alertsToken : new HashSet(this.allAlerts.keySet())) {
            stopActiveAlert(alertsToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean deleteAlert(AlertsToken alertsToken) {
        return deleteAlert(alertsToken, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void deleteAllAlerts() {
        for (AlertsToken alertsToken : new HashSet(this.allAlerts.keySet())) {
            deleteAlert(alertsToken);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized SortedSet<AlertRecord> getActiveAlerts() {
        TreeSet treeSet;
        treeSet = new TreeSet(new AlertRecordComparator());
        for (AlertsToken alertsToken : this.activeAlerts.keySet()) {
            treeSet.add(this.allAlerts.get(alertsToken));
        }
        return treeSet;
    }

    @VisibleForTesting
    int getAlertSilenceInterval() {
        return 900000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Set<AlertRecord> getAllAlerts() {
        return new HashSet(this.allAlerts.values());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean hasAlert(AlertsToken alertsToken) {
        return this.allAlerts.containsKey(alertsToken);
    }

    synchronized boolean isEmpty() {
        return this.allAlerts.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void launch(AlertsToken alertsToken) {
        AlertRecord alertRecord = this.allAlerts.get(alertsToken);
        if (alertRecord != null) {
            launch(alertRecord);
        }
    }

    @VisibleForTesting
    void launchActiveAlertSilentCountdown(final AlertsToken alertsToken) {
        this.handler.postDelayed(new Runnable() { // from class: com.amazon.alexa.alertsca.AlertsAuthority.2
            @Override // java.lang.Runnable
            public void run() {
                String unused = AlertsAuthority.TAG;
                AlertsAuthority.this.silenceActiveAlert(alertsToken);
            }
        }, getAlertSilenceInterval());
    }

    @Subscribe
    public void on(AlertStartedEvent alertStartedEvent) {
        sendIntent(AlertsIntentFactory.createAlertStartedIntent(this.context, alertStartedEvent.getAlertRecord()));
    }

    @Override // com.amazon.alexa.alertsca.AlertStoppedListener
    public synchronized void onAlertStopped(AlertsToken alertsToken) {
        deleteAlert(alertsToken);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean setAlert(AlertRecord alertRecord) {
        String str = "Setting alert: " + alertRecord.getToken().getValue();
        if (this.activeAlerts.containsKey(alertRecord.getToken())) {
            stopActiveAlert(alertRecord.getToken());
        }
        this.alertsAnalyzer.analyze(alertRecord, new SetAlertScheduler(), new SetAlertTriggerer(), new SetAlertExpirer());
        return true;
    }

    @VisibleForTesting
    void silenceActiveAlert(AlertsToken alertsToken) {
        AlertInteraction alertInteraction = this.activeAlerts.get(alertsToken);
        if (alertInteraction != null) {
            alertInteraction.onSilent();
        }
    }

    @VisibleForTesting
    void stopActiveAlert(AlertsToken alertsToken) {
        stopActiveAlert(alertsToken, false);
    }

    public synchronized void teardown() {
        stopAllActiveAlerts();
        this.alertsEventBus.unregister(this);
        this.alertsStore.teardown();
        this.alertsExoPlayer.release();
        this.scoAuthority.teardown();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean deleteAlert(AlertsToken alertsToken, boolean z) {
        String str = "Deleting alert: " + alertsToken.getValue();
        if (this.activeAlerts.containsKey(alertsToken)) {
            stopActiveAlert(alertsToken, z);
            this.alertView.dismissAlert(alertsToken);
        } else if (z && this.allAlerts.containsKey(alertsToken) && this.allAlerts.get(alertsToken).getType().equals(AlertType.TIMER)) {
            String str2 = "Deleting inactive timer: " + alertsToken.getValue();
            sendAlertStopped(alertsToken);
        }
        AlertRecord remove = this.allAlerts.remove(alertsToken);
        this.alertsStore.remove(alertsToken);
        this.alertsScheduler.unSchedule(alertsToken);
        if (remove != null) {
            this.alertsEventBus.post(AlertUnscheduledEvent.create(remove, z));
            return true;
        }
        return false;
    }

    void stopActiveAlert(AlertsToken alertsToken, boolean z) {
        AlertInteraction remove = this.activeAlerts.remove(alertsToken);
        if (remove != null) {
            remove.stopAsync(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launch(AlertRecord alertRecord) {
        AlertsToken token = alertRecord.getToken();
        AlertType type = alertRecord.getType();
        String str = "Launching alert: " + token + ", of type: " + type;
        if (!this.allAlerts.containsKey(token)) {
            this.allAlerts.put(token, alertRecord);
        }
        if (this.activeAlerts.containsKey(token)) {
            Log.w(TAG, "Alert is already active!");
            this.metricsService.recordEvent(MetricsConstants.ALERTS.ALREADY_ACTIVE.element(type.name()));
            return;
        }
        String str2 = type + " triggered: " + token.getValue();
        scheduleAlertInteraction(alertRecord, createPlayItem(token, type, true), createPlayItem(token, type, false));
        if (ALERT_TYPES_SHOWING_FULL_SCREEN.contains(type)) {
            this.alertView.showAlert(alertRecord);
            launchActiveAlertSilentCountdown(token);
        }
        this.metricsService.recordEvent(MetricsConstants.ALERTS.LAUNCH.element(type.name()));
    }

    @Subscribe
    public void on(AlertStoppedEvent alertStoppedEvent) {
        sendIntent(AlertsIntentFactory.createAlertStoppedIntent(this.context, alertStoppedEvent.getAlertRecord()));
    }
}

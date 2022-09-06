package com.amazon.alexa.alertsca;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.AlertsConstants;
import com.amazon.alexa.alertsca.AlertsIntentFactory;
import com.amazon.alexa.alertsca.events.AlertCleanUpEvent;
import com.amazon.alexa.alertsca.metrics.MetricsConstants;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.alertsca.payload.AlertsPayload;
import com.amazon.alexa.alertsca.payload.AlertsStatePayload;
import com.amazon.alexa.alertsca.payload.DeleteAlertsPayload;
import com.amazon.alexa.alertsca.payload.SetAlertsPayload;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaDirective;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import dagger.Lazy;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class AlertsCapabilityAgent {
    @VisibleForTesting
    static final String ALERTS_PERMISSION_NAME = "com.amazon.alexa.alertsca.events.AlertEvent";
    private static final String TAG = "AlertsCapabilityAgent";
    @VisibleForTesting
    static final String TIMERS_AND_ALARMS_AVAILABILITY_KEY = "areTimersAndAlarmsAvailable";
    private final AlertsAuthority alertsAuthority;
    private final AlertsEventBus alertsEventBus;
    private final AlexaEventSender alexaEventSender;
    private final Context context;
    private final Gson gson;
    private final MetricsService metricsService;
    private final Lazy<SharedPreferences> sharedPreferencesLazy;

    @Inject
    public AlertsCapabilityAgent(AlertsAuthority alertsAuthority, Gson gson, AlertsEventBus alertsEventBus, Context context, AlexaEventSender alexaEventSender, MetricsService metricsService, @Named("ALERTS_DATA_STORE") Lazy<SharedPreferences> lazy) {
        this.alertsAuthority = alertsAuthority;
        this.gson = gson;
        this.alertsEventBus = alertsEventBus;
        this.context = context;
        this.alexaEventSender = alexaEventSender;
        this.metricsService = metricsService;
        this.sharedPreferencesLazy = lazy;
    }

    private void enableRescheduleOnBoot() {
        this.context.getPackageManager().setComponentEnabledSetting(getComponentName(), 1, 1);
    }

    private boolean handleDeleteAlertDirective(AlexaPayload alexaPayload) {
        try {
            AlertsPayload alertsPayload = (AlertsPayload) this.gson.fromJson(alexaPayload.getPayload(), (Class<Object>) AlertsPayload.class);
            boolean deleteAlert = this.alertsAuthority.hasAlert(alertsPayload.getToken()) ? this.alertsAuthority.deleteAlert(alertsPayload.getToken()) : false;
            sendDeleteAlertResultEvent(alexaPayload, deleteAlert ? AlertsConstants.Alerts.Events.DeleteAlertSucceeded.NAME : AlertsConstants.Alerts.Events.DeleteAlertFailed.NAME);
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.DELETE_ALERT.result(deleteAlert));
            return true;
        } catch (JsonSyntaxException e) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.PARSING_DELETE_ALERT.failure());
            Log.e(TAG, "Couldn't parse the DeleteAlert directive.", e);
            return false;
        }
    }

    private boolean handleDeleteMultipleAlertsDirective(AlexaPayload alexaPayload) {
        String payload = alexaPayload.getPayload();
        try {
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (AlertsToken alertsToken : ((DeleteAlertsPayload) this.gson.fromJson(payload, (Class<Object>) DeleteAlertsPayload.class)).getTokens()) {
                if (this.alertsAuthority.hasAlert(alertsToken) && this.alertsAuthority.deleteAlert(alertsToken)) {
                    hashSet.add(alertsToken);
                } else {
                    hashSet2.add(alertsToken);
                }
            }
            sendDeletedAlerts(hashSet, true);
            sendDeletedAlerts(hashSet2, false);
            return true;
        } catch (JsonSyntaxException e) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.PARSING_DELETE_MULTIPLE_ALERTS.failure());
            Log.e(TAG, "Couldn't parse the DeleteAlerts directive.", e);
            return false;
        }
    }

    private boolean handleSetAlertDirective(AlexaPayload alexaPayload) {
        try {
            SetAlertsPayload setAlertsPayload = (SetAlertsPayload) this.gson.fromJson(alexaPayload.getPayload(), (Class<Object>) SetAlertsPayload.class);
            AlertsToken token = setAlertsPayload.getToken();
            if (this.alertsAuthority.hasAlert(token)) {
                this.alertsAuthority.deleteAlert(token);
            }
            AlertRecord alertRecord = getAlertRecord(setAlertsPayload);
            sendSetAlertStatusEvent(alertRecord, this.alertsAuthority.setAlert(alertRecord));
            enableRescheduleOnBoot();
            return true;
        } catch (JsonSyntaxException e) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.PARSING_SET_ALERT.failure());
            Log.e(TAG, "Couldn't parse the SetAlert directive.", e);
            return false;
        }
    }

    private void sendDeleteAlertResultEvent(AlexaPayload alexaPayload, String str) {
        String str2 = "sendDeleteAlertResultEvent: " + str;
        this.alexaEventSender.send(new AlexaEvent(AlexaHeader.create("Alerts", str), alexaPayload), false);
    }

    private void sendDeletedAlerts(Set<AlertsToken> set, boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sendDeletedAlerts: count = ");
        outline107.append(set.size());
        outline107.toString();
        if (set.isEmpty()) {
            return;
        }
        sendDeleteAlertResultEvent(new AlexaPayload(this.gson.toJson(DeleteAlertsPayload.builder().setTokens(set))), z ? AlertsConstants.Alerts.Events.DeleteAlertsSucceeded.NAME : AlertsConstants.Alerts.Events.DeleteAlertsFailed.NAME);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.DELETE_ALERTS.result(z));
    }

    private void sendSetAlertStatusEvent(AlertRecord alertRecord, boolean z) {
        String str = z ? AlertsConstants.Alerts.Events.SetAlertSucceeded.NAME : AlertsConstants.Alerts.Events.SetAlertFailed.NAME;
        String str2 = "sendSetAlertStatusEvent: " + str;
        this.alexaEventSender.send(new AlexaEvent(AlexaHeader.create("Alerts", str), new AlexaPayload(this.gson.toJson(AlertsPayload.create(alertRecord.getToken())))), false);
        this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.SET_ALERT.element(alertRecord.getType().name()).result(z));
    }

    public boolean areTimersAndAlarmsAvailable() {
        return this.sharedPreferencesLazy.mo358get().getBoolean(TIMERS_AND_ALARMS_AVAILABILITY_KEY, false);
    }

    @VisibleForTesting
    AlertRecord getAlertRecord(SetAlertsPayload setAlertsPayload) {
        AlertRecord.Builder label = AlertRecord.builder().setToken(setAlertsPayload.getToken()).setType(setAlertsPayload.getType()).setScheduledTime(setAlertsPayload.getScheduledTime()).setLabel(setAlertsPayload.getLabel());
        if (setAlertsPayload.getAssets() != null) {
            label.setAssets(setAlertsPayload.getAssets()).setAssetPlayOrder(setAlertsPayload.getAssetPlayOrder()).setBackgroundAlertAsset(setAlertsPayload.getBackgroundAlertAsset()).setLoopCount(setAlertsPayload.getLoopCount()).setLoopPauseInMilliseconds(setAlertsPayload.getLoopPauseInMilliSeconds());
        }
        return label.build();
    }

    @VisibleForTesting
    ComponentName getComponentName() {
        return new ComponentName(this.context, AlertReschedulingBootCompletedReceiver.class);
    }

    public synchronized AlexaContext getContext() {
        Set<AlertRecord> allAlerts;
        allAlerts = this.alertsAuthority.getAllAlerts();
        return new AlexaContext(AlexaHeader.create("Alerts", AlertsConstants.Alerts.ComponentStates.AlertsState.NAME), new AlexaPayload(this.gson.toJson(AlertsStatePayload.builder().setAllAlerts(allAlerts).setActiveAlerts(this.alertsAuthority.getActiveAlerts()).build())));
    }

    public synchronized void handleIntent(Intent intent) {
        String stringExtra = intent.getStringExtra(AlertsIntentFactory.ExtraKeys.ALERT_TOKEN);
        if (stringExtra == null) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.ACTIONS.NULL_TOKEN);
            return;
        }
        String str = "handleIntent: action = " + intent.getAction();
        AlertsToken create = AlertsToken.create(stringExtra);
        boolean z = false;
        int intExtra = intent.getIntExtra(AlertsIntentFactory.ExtraKeys.ALERT_RECORD_ID, 0);
        boolean booleanExtra = intent.getBooleanExtra(AlertsIntentFactory.EXTRA_DISMISSED_BY_USER, false);
        if (booleanExtra) {
            this.metricsService.recordEvent(MetricsConstants.ALERTS.NOTIFICATIONS.DISMISS);
        }
        String action = intent.getAction();
        switch (action.hashCode()) {
            case -1097923295:
                if (action.equals("com.amazon.alexa.alertsca.intent.action.REMINDER_TRIGGERED")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case -768781690:
                if (action.equals("com.amazon.alexa.alertsca.intent.action.TIMER_TRIGGERED")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 434595474:
                if (action.equals("com.amazon.alexa.alertsca.intent.action.ALARM_TRIGGERED")) {
                    break;
                }
                z = true;
                break;
            case 1877771260:
                if (action.equals("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (!z || z || z) {
            String str2 = "Triggered: " + create;
            if (this.alertsAuthority.hasAlert(create)) {
                this.alertsAuthority.launch(create);
            }
        } else if (!z) {
            Log.w(TAG, "Unknown action: " + intent.getAction());
        } else {
            String str3 = "Stopping: " + create;
            if (this.alertsAuthority.hasAlert(create)) {
                this.alertsAuthority.deleteAlert(create, booleanExtra);
            } else {
                this.alertsEventBus.post(AlertCleanUpEvent.create(intExtra));
            }
        }
    }

    public synchronized void onLogOut() {
        this.alertsAuthority.deleteAllAlerts();
        this.sharedPreferencesLazy.mo358get().edit().clear().commit();
    }

    public synchronized boolean process(AlexaDirective alexaDirective) {
        String namespace = alexaDirective.getNamespace();
        String name = alexaDirective.getName();
        if (!"Alerts".equals(namespace)) {
            Log.w(TAG, "Not Alerts namespace: " + namespace);
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.UNKNOWN_NAMESPACE);
            return false;
        }
        r0 = "process directive: " + name;
        if (AlertsConstants.Alerts.Directives.SetAlert.NAME.equals(name)) {
            return handleSetAlertDirective(alexaDirective.getAlexaPayload());
        } else if (AlertsConstants.Alerts.Directives.DeleteAlert.NAME.equals(name)) {
            return handleDeleteAlertDirective(alexaDirective.getAlexaPayload());
        } else if (AlertsConstants.Alerts.Directives.DeleteAlerts.NAME.equals(name)) {
            return handleDeleteMultipleAlertsDirective(alexaDirective.getAlexaPayload());
        } else {
            Log.w(TAG, "Unknown directive: " + name);
            this.metricsService.recordEvent(MetricsConstants.ALERTS.DIRECTIVES.UNKNOWN);
            return false;
        }
    }

    public synchronized void teardown() {
        this.alertsAuthority.teardown();
    }

    @VisibleForTesting
    void updateCapabilities() {
        this.context.sendBroadcast(AlertsIntentFactory.createUpdateCapabilitiesIntent(), "com.amazon.alexa.alertsca.events.AlertEvent");
    }

    public void updateTimersAndAlarmsAvailability(boolean z) {
        SharedPreferences mo358get = this.sharedPreferencesLazy.mo358get();
        if (!mo358get.contains(TIMERS_AND_ALARMS_AVAILABILITY_KEY) || mo358get.getBoolean(TIMERS_AND_ALARMS_AVAILABILITY_KEY, false) != z) {
            mo358get.edit().putBoolean(TIMERS_AND_ALARMS_AVAILABILITY_KEY, z).commit();
            updateCapabilities();
        }
    }
}

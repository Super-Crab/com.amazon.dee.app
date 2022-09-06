package com.amazon.alexa.alertsca;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.alertsca.alertdisplay.AlertsDisplayActivity;
import com.amazon.alexa.alertsca.utils.StringUtils;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.device.api.DeviceInformation;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlertsIntentFactory {
    @VisibleForTesting
    static final String DEFAULT_TIMERS_AND_ALARMS_URL = "http://alexa.amazon.com/spa/index.html#timersAndAlarms";
    public static final String EXTRA_DISMISSED_BY_USER = "EXTRA_DISMISSED_BY_USER";
    private static final String EXTRA_REQUIRES_FOREGROUND = "EXTRA_REQUIRES_FOREGROUND";
    private static final String TAG = "AlertsIntentFactory";
    public static final Map<AlertType, String> ROUTE_BY_ALERT_TYPE = new ImmutableMap.Builder().mo7828put(AlertType.TIMER, "/timers").mo7828put(AlertType.ALARM, "/alarms").mo7828put(AlertType.REMINDER, "/reminders").mo7826build();
    public static final Map<AlertType, String> ACTION_BY_ALERT_TYPE = new ImmutableMap.Builder().mo7828put(AlertType.TIMER, "com.amazon.alexa.alertsca.intent.action.TIMER_TRIGGERED").mo7828put(AlertType.ALARM, "com.amazon.alexa.alertsca.intent.action.ALARM_TRIGGERED").mo7828put(AlertType.REMINDER, "com.amazon.alexa.alertsca.intent.action.REMINDER_TRIGGERED").mo7826build();

    /* loaded from: classes6.dex */
    public static class Actions {
        static final String ALARM_TRIGGERED = "com.amazon.alexa.alertsca.intent.action.ALARM_TRIGGERED";
        static final String ALERTS_STARTED = "com.amazon.alexa.alertsca.intent.action.ALERTS_STARTED";
        static final String ALERTS_STOPPED = "com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED";
        public static final String DISMISS_UI = "com.amazon.alexa.alertsca.intent.action.DISMISS_UI";
        static final String LOG_OUT = "com.amazon.alexa.alertsca.intent.action.LOG_OUT";
        static final String REMINDER_TRIGGERED = "com.amazon.alexa.alertsca.intent.action.REMINDER_TRIGGERED";
        static final String TIMER_TRIGGERED = "com.amazon.alexa.alertsca.intent.action.TIMER_TRIGGERED";
        static final String UPDATE_CAPABILITIES = "com.amazon.alexa.alertsca.intent.action.UPDATE_CAPABILITIES";
        public static final String UPDATE_TIMERS_AND_ALARMS_AVAILABILITY = "com.amazon.alexa.alertsca.intent.action.UPDATE_TIMERS_AND_ALARMS_AVAILABILITY";
        static final String WAKE_UP = "com.amazon.alexa.alertsca.intent.action.WAKE_UP";
    }

    /* loaded from: classes6.dex */
    public static class ExtraKeys {
        public static final String ALERT_LABEL = "com.amazon.alexa.alertsca.intent.extra.ALERT_LABEL";
        public static final String ALERT_RECORD_ID = "com.amazon.alexa.alertsca.intent.extra.ALERT_RECORD_ID";
        public static final String ALERT_RECORD_TYPE = "com.amazon.alexa.alertsca.intent.extra.ALERT_RECORD_TYPE";
        public static final String ALERT_TIME = "com.amazon.alexa.alertsca.intent.extra.ALERT_TIME";
        public static final String ALERT_TOKEN = "com.amazon.alexa.alertsca.intent.extra.ALERT_TOKEN";
        public static final String ARE_TIMERS_AND_ALARMS_AVAILABLE = "com.amazon.alexa.alertsca.intent.extra.TIMERS_AND_ALARMS_AVAILABLE";
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class ReminderRoute {
        private static final String BASE_PAGE_ROUTE = "https://alexa.amazon.com/?fragment=v2/reminders-alarms-timers/reminders-detail";
        private static final String DASH = "-";
        private static final String SLASH = "/";
        private static final String SUFFIX = "_REMINDER";
        private final DeviceInformation deviceInformation;
        private final String remindersHomePageRoute;
        private final String token;

        /* loaded from: classes6.dex */
        public static final class Builder {
            private DeviceInformation deviceInformation;
            private String remindersHomePageRoute;
            private String token;

            public ReminderRoute build() {
                return new ReminderRoute(this);
            }

            public Builder withDeviceInformation(DeviceInformation deviceInformation) {
                this.deviceInformation = deviceInformation;
                return this;
            }

            public Builder withRemindersHomePageRoute(String str) {
                this.remindersHomePageRoute = str;
                return this;
            }

            public Builder withToken(String str) {
                this.token = str;
                return this;
            }
        }

        public ReminderRoute(Builder builder) {
            this.token = (String) Objects.requireNonNull(builder.token, "token cannot be null.");
            this.deviceInformation = (DeviceInformation) Objects.requireNonNull(builder.deviceInformation, "deviceInformation cannot be null.");
            this.remindersHomePageRoute = (String) Objects.requireNonNull(builder.remindersHomePageRoute, "remindersHomePageRoute cannot be null.");
        }

        public Uri getUri() {
            try {
                String deviceSerialNumber = this.deviceInformation.getDeviceSerialNumber();
                String deviceType = this.deviceInformation.getDeviceType();
                String concat = BASE_PAGE_ROUTE.concat("/").concat(deviceType).concat("-").concat(deviceSerialNumber).concat("-").concat(StringUtils.getLastPart(this.token, 35)).concat(SUFFIX);
                String str = AlertsIntentFactory.TAG;
                Log.i(str, "fullReminderRoute = " + concat);
                return Uri.parse(concat);
            } catch (Exception e) {
                Log.e(AlertsIntentFactory.TAG, "Error getting device information.", e);
                return Uri.parse(this.remindersHomePageRoute);
            }
        }
    }

    public static Intent createAlertStartedIntent(Context context, AlertRecord alertRecord) {
        return createAlexaServiceIntent(context, alertRecord).setAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STARTED");
    }

    public static Intent createAlertStoppedIntent(Context context, AlertRecord alertRecord) {
        return createAlexaServiceIntent(context, alertRecord).setAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED");
    }

    private static Intent createAlexaServiceIntent(Context context, AlertRecord alertRecord) {
        return new Intent().putExtra(ExtraKeys.ALERT_TOKEN, alertRecord.getToken().getValue()).putExtra(ExtraKeys.ALERT_RECORD_ID, alertRecord.getToken().getValue()).putExtra(ExtraKeys.ALERT_RECORD_TYPE, alertRecord.getType().ordinal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent createIntent(Context context, AlertRecord alertRecord) {
        AlertType type = alertRecord.getType();
        AlertsToken token = alertRecord.getToken();
        Intent intent = new Intent(context, AlertsCapabilityAgentService.class);
        intent.setAction(ACTION_BY_ALERT_TYPE.get(type));
        intent.putExtra(EXTRA_REQUIRES_FOREGROUND, true);
        intent.putExtra(ExtraKeys.ALERT_TOKEN, token.getValue());
        intent.putExtra(ExtraKeys.ALERT_RECORD_ID, alertRecord.hashCode());
        return intent;
    }

    @VisibleForTesting
    static Intent createNavigationIntent(Context context, AlertRecord alertRecord, @Nullable DeviceInformation deviceInformation) {
        Intent intent = new Intent("android.intent.action.VIEW", createNavigationUrl(alertRecord, deviceInformation));
        intent.putExtra(ExtraKeys.ALERT_TOKEN, alertRecord.getToken().getValue());
        intent.putExtra(ExtraKeys.ALERT_RECORD_ID, alertRecord.hashCode());
        intent.setPackage(context.getPackageName());
        return intent;
    }

    public static PendingIntent createNavigationPendingIntent(Context context, AlertRecord alertRecord, @Nullable DeviceInformation deviceInformation) {
        return PendingIntent.getActivity(context, alertRecord.getToken().hashCode(), createNavigationIntent(context, alertRecord, deviceInformation), 134217728);
    }

    @VisibleForTesting
    static Uri createNavigationUrl(AlertRecord alertRecord, @Nullable DeviceInformation deviceInformation) {
        String outline72 = GeneratedOutlineSupport1.outline72(DEFAULT_TIMERS_AND_ALARMS_URL, ROUTE_BY_ALERT_TYPE.get(alertRecord.getType()));
        if (alertRecord.isReminder() && deviceInformation != null) {
            return new ReminderRoute.Builder().withToken(alertRecord.getToken().getValue()).withDeviceInformation(deviceInformation).withRemindersHomePageRoute(outline72).build().getUri();
        }
        return Uri.parse(outline72);
    }

    public static PendingIntent createNotificationStopIntent(Context context, AlertRecord alertRecord) {
        Intent createIntent = createIntent(context, alertRecord);
        createIntent.setAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED");
        createIntent.putExtra(EXTRA_DISMISSED_BY_USER, true);
        return PendingIntent.getService(context, alertRecord.getToken().hashCode(), createIntent, 1073741824);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PendingIntent createPendingIntent(Context context, AlertRecord alertRecord) {
        Intent createIntent = createIntent(context, alertRecord);
        AlertsToken token = alertRecord.getToken();
        int i = Build.VERSION.SDK_INT;
        return PendingIntent.getForegroundService(context, token.hashCode(), createIntent, 1073741824);
    }

    public static Intent createStopIntent(Context context, String str, int i) {
        AlertsToken create = AlertsToken.create(str);
        Intent intent = new Intent(context, AlertsCapabilityAgentService.class);
        intent.setAction("com.amazon.alexa.alertsca.intent.action.ALERTS_STOPPED");
        intent.putExtra(EXTRA_REQUIRES_FOREGROUND, true);
        intent.putExtra(ExtraKeys.ALERT_TOKEN, create.getValue());
        intent.putExtra(ExtraKeys.ALERT_RECORD_ID, i);
        return intent;
    }

    public static Intent createUpdateAvailabilityIntent(Context context, boolean z) {
        Intent intent = new Intent(context, AlertsCapabilityAgentService.class);
        intent.setAction(Actions.UPDATE_TIMERS_AND_ALARMS_AVAILABILITY);
        intent.putExtra(ExtraKeys.ARE_TIMERS_AND_ALARMS_AVAILABLE, z);
        return intent;
    }

    public static Intent createUpdateCapabilitiesIntent() {
        Intent intent = new Intent();
        intent.setAction("com.amazon.alexa.alertsca.intent.action.UPDATE_CAPABILITIES");
        return intent;
    }

    public static Intent getAlertsDisplayActivityIntent(Context context, AlertRecord alertRecord) {
        Intent intent = new Intent(context, AlertsDisplayActivity.class);
        intent.putExtra(ExtraKeys.ALERT_TOKEN, alertRecord.getToken().getValue());
        intent.putExtra(ExtraKeys.ALERT_TIME, alertRecord.getScheduledTime().getTime());
        intent.putExtra(ExtraKeys.ALERT_RECORD_TYPE, alertRecord.getType().name());
        intent.putExtra(ExtraKeys.ALERT_RECORD_ID, alertRecord.hashCode());
        intent.putExtra(ExtraKeys.ALERT_LABEL, alertRecord.getLabel().getValue());
        intent.setAction(String.valueOf(System.currentTimeMillis()));
        intent.setFlags(268435456);
        return intent;
    }
}

package com.amazon.alexa.alertsca.metrics;

import com.amazon.alexa.alertsca.AlertsConstants;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
/* loaded from: classes6.dex */
public final class MetricsConstants {
    public static final Alerts ALERTS = new Alerts();

    /* loaded from: classes6.dex */
    public static class Actions extends MetricsElement {
        public final MetricsElement NULL_TOKEN;

        public Actions(MetricsElement metricsElement) {
            super("Actions", metricsElement);
            this.NULL_TOKEN = MetricsConstants.create("NullToken", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class Alerts extends MetricsElement {
        public final Actions ACTIONS;
        public final MetricsElement ALREADY_ACTIVE;
        public final Directives DIRECTIVES;
        public final DisplayActivity DISPLAY_ACTIVITY;
        public final ExoPlayer EXO_PLAYER;
        public final MetricsElement LAUNCH;
        public final Notifications NOTIFICATIONS;
        public final MetricsElement SCHEDULE;
        public final AlertsService SERVICE;
        public final MetricsElement STARTED;
        public final Unexpected UNEXPECTED;
        public final MetricsElement UNSCHEDULE;

        private Alerts() {
            super("Alerts");
            this.NOTIFICATIONS = new Notifications(this);
            this.EXO_PLAYER = new ExoPlayer(this);
            this.DIRECTIVES = new Directives(this);
            this.ACTIONS = new Actions(this);
            this.DISPLAY_ACTIVITY = new DisplayActivity(this);
            this.SERVICE = new AlertsService(this);
            this.UNEXPECTED = new Unexpected(this);
            this.SCHEDULE = MetricsConstants.create("Schedule", this);
            this.UNSCHEDULE = MetricsConstants.create("Unschedule", this);
            this.LAUNCH = MetricsConstants.create("Launch", this);
            this.STARTED = MetricsConstants.create("Started", this);
            this.ALREADY_ACTIVE = MetricsConstants.create("AlreadyActive", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class AlertsService extends MetricsElement {
        public final Actions ACTIONS;
        public final MetricsElement CONNECTION;
        public final MetricsElement START_FOREGROUND;

        public AlertsService(MetricsElement metricsElement) {
            super("Service", metricsElement);
            this.ACTIONS = new Actions(this);
            this.START_FOREGROUND = MetricsConstants.create("StartForeground", this);
            this.CONNECTION = MetricsConstants.create("Connection", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class Directives extends MetricsElement {
        public final MetricsElement DELETE_ALERT;
        public final MetricsElement DELETE_ALERTS;
        public final MetricsElement PARSING_DELETE_ALERT;
        public final MetricsElement PARSING_DELETE_MULTIPLE_ALERTS;
        public final MetricsElement PARSING_SET_ALERT;
        public final MetricsElement SET_ALERT;
        public final MetricsElement UNKNOWN;
        public final MetricsElement UNKNOWN_NAMESPACE;

        public Directives(MetricsElement metricsElement) {
            super("Directives", metricsElement);
            this.DELETE_ALERTS = MetricsConstants.create(AlertsConstants.Alerts.Directives.DeleteAlerts.NAME, this);
            this.DELETE_ALERT = MetricsConstants.create(AlertsConstants.Alerts.Directives.DeleteAlert.NAME, this);
            this.SET_ALERT = MetricsConstants.create(AlertsConstants.Alerts.Directives.SetAlert.NAME, this);
            this.UNKNOWN = MetricsConstants.create("Unknown", this);
            this.UNKNOWN_NAMESPACE = MetricsConstants.create("UnknownNamespace", this);
            this.PARSING_DELETE_ALERT = MetricsConstants.create("ParsingDeleteAlert", this);
            this.PARSING_SET_ALERT = MetricsConstants.create("ParsingSetAlert", this);
            this.PARSING_DELETE_MULTIPLE_ALERTS = MetricsConstants.create("ParsingDeleteMultipleAlerts", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class DisplayActivity extends MetricsElement {
        public final MetricsElement ALERT_VIEW_DISMISS;
        public final MetricsElement ALERT_VIEW_SHOW;
        public final MetricsElement BACK_PRESSED;
        public final MetricsElement CLICKED_DISMISS;
        public final MetricsElement NEW_INTENT;
        public final MetricsElement ON_DISMISS;
        public final MetricsElement SHOW;

        public DisplayActivity(MetricsElement metricsElement) {
            super("DisplayActivity", metricsElement);
            this.SHOW = MetricsConstants.create("Show", this);
            this.NEW_INTENT = MetricsConstants.create("NewIntent", this);
            this.BACK_PRESSED = MetricsConstants.create("BackPressed", this);
            this.CLICKED_DISMISS = MetricsConstants.create("ClickedDismiss", this);
            this.ON_DISMISS = MetricsConstants.create("OnDismiss", this);
            this.ALERT_VIEW_SHOW = MetricsConstants.create("AlertViewShow", this);
            this.ALERT_VIEW_DISMISS = MetricsConstants.create("AlertViewDismiss", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class ExoPlayer extends MetricsElement {
        public final MetricsElement CANNOT_PREPARE;
        public final MetricsElement CREATE_PLAYER;
        public final MetricsElement PAUSE;
        public final MetricsElement PLAY;
        public final MetricsElement PLAYER_ERROR;
        public final MetricsElement STOP;
        public final MetricsElement USAGE;

        public ExoPlayer(MetricsElement metricsElement) {
            super("ExoPlayer", metricsElement);
            this.PLAY = MetricsConstants.create(EntertainmentMetrics.Button.PLAY, this);
            this.PAUSE = MetricsConstants.create(EntertainmentMetrics.Button.PAUSE, this);
            this.STOP = MetricsConstants.create(PCCConstants.STOP_DIRECTIVE, this);
            this.CREATE_PLAYER = MetricsConstants.create("CreatePlayer", this);
            this.CANNOT_PREPARE = MetricsConstants.create("CannotPrepare", this);
            this.PLAYER_ERROR = MetricsConstants.create("PlayerError", this);
            this.USAGE = MetricsConstants.create("Usage", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class Notifications extends MetricsElement {
        public final MetricsElement CANCEL;
        public final MetricsElement DISMISS;
        public final MetricsElement FULL_SCREEN_INTENT;
        public final MetricsElement NOTIFY;

        public Notifications(MetricsElement metricsElement) {
            super(AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS, metricsElement);
            this.NOTIFY = MetricsConstants.create("Notify", this);
            this.CANCEL = MetricsConstants.create("Cancel", this);
            this.DISMISS = MetricsConstants.create("Dismiss", this);
            this.FULL_SCREEN_INTENT = MetricsConstants.create("FullScreenIntent", this);
        }
    }

    /* loaded from: classes6.dex */
    public static class Unexpected extends MetricsElement {
        public final MetricsElement NULL_DEVICE_INFO;

        public Unexpected(MetricsElement metricsElement) {
            super("Unexpected", metricsElement);
            this.NULL_DEVICE_INFO = MetricsConstants.create("NullDeviceInfo", this);
        }
    }

    private MetricsConstants() {
        throw new AssertionError("Cannot create an instance of this class!");
    }

    public static MetricsElement create(String str, MetricsElement metricsElement) {
        return new MetricsElement(str, metricsElement);
    }
}

package com.amazon.alexa.client.metrics.core;
/* loaded from: classes6.dex */
public final class AlexaMetricsConstants {
    public static final String NAME = "AlexaMobileAndroid";
    public static final String SERVICE_NAME = "alexaservice";

    /* loaded from: classes6.dex */
    public final class AMPD {
        public static final String DEVICE_UNLOCK_STATE = "deviceLockState";
        public static final String LAST_UNLOCK_TIMESTAMP = "lastUnlockTimestamp";

        private AMPD() {
        }
    }

    /* loaded from: classes6.dex */
    public final class CardMode {
        public static final String CARD_MODE = "cardMode";
        public static final String DRIVE_MODE = "DRIVE_MODE";
        public static final String STANDARD_MODE = "STANDARD";

        private CardMode() {
        }
    }

    /* loaded from: classes6.dex */
    public final class DriveModeMetrics {
        public static final String APP_COMPONENT_NAME = "DriveMode";
        public static final String CARD_SHOWN_POSTFIX = "Displayed";
        public static final String NAVIGATE_TO_LINK_POSTFIX = "Selected";

        private DriveModeMetrics() {
        }
    }

    /* loaded from: classes6.dex */
    public final class EventConstants {
        public static final String ANDROID_ID = "androidId";
        public static final String APP_COMPONENT = "AppComponent";
        public static final String APP_VERSION_COUNTRY_CODE = "AppVersion:CountryCode";
        public static final String APP_VERSION_COUNTRY_CODE_MARKETPLACE_ID = "AppVersion:CountryCode:MarketplaceID";
        public static final String APP_VERSION_COUNTRY_CODE_MARKETPLACE_ID_CODE = "AppVersion:CountryCode:MarketplaceIDCode";
        public static final String APP_VERSION_MARKETPLACE_ID = "AppVersion:MarketplaceID";
        public static final String APP_VERSION_MARKETPLACE_ID_CODE = "AppVersion:MarketplaceIDCode";
        public static final String APP_VERSION_OS_TYPE = "AppVersion:OSType";
        public static final String ATTRIBUTION_TAG = "attributionTag";
        public static final String CATEGORY = "CATEGORY";
        public static final String CONTENT_PROVIDER = "contentProvider";
        public static final String COUNTER_VALUE = "CounterValue";
        public static final String COUNTRY_CODE = "CountryCode";
        public static final String COUNTRY_CODE_MARKETPLACE_ID = "CountryCode:MarketplaceID";
        public static final String COUNTRY_CODE_MARKETPLACE_ID_CODE = "CountryCode:MarketplaceIDCode";
        public static final String DIALOG_REQUEST_ID = "dialogId";
        public static final String DIRECTED_ID = "DirectedID";
        public static final String EVENT_NAME = "EventName";
        public static final String EVENT_NUMERIC_VALUE = "EventNumericValue";
        public static final String EVENT_TIMESTAMP = "EventTimestamp";
        public static final String EVENT_TYPE = "EventType";
        public static final String EVENT_VALUE = "EventValue";
        public static final String FIRST_LAUNCH = "FirstLaunch";
        public static final String INVOCATION_TYPE_EXPECT_SPEECH = "AlexaService.ExpectSpeech";
        public static final String INVOCATION_TYPE_NEW_DIALOG_REQUEST = "AlexaService.NewDialogRequest";
        public static final String INVOCATION_TYPE_NOTIFICATION = "AlexaService.Notification";
        public static final String INVOCATION_TYPE_WAKE_WORD = "AlexaService.WakeWord";
        public static final String LOCALE = "locale";
        public static final String LOCAL_TIMEZONE = "localTimezone";
        public static final String LOCATION_ENABLED = "locationEnabled";
        public static final String MARKETPLACE_ID = "MarketplaceID";
        public static final String MARKETPLACE_ID_CODE = "MarketplaceIDCode";
        public static final String MOBILYTICS_SESSION_ID = "mobilyticsSessionId";
        public static final String OS_TYPE = "OSType";
        public static final String OWNER_IDENTIFIER = "ownerIdentifier";
        public static final String PFM = "pfm";
        public static final String RECORD_TIMER_END = "RecordTimerEnd";
        public static final String SEND_PRIORITY = "sendPriority";
        public static final String SEPARATOR = ":";
        public static final String SERVICE_NAME = "ServiceName";
        public static final String SERVICE_VERSION = "AppVersion";
        public static final String SOURCE = "source";
        public static final String STATUS_CODE = "statusCode";
        public static final String TAP_TO_TALK = "tapToTalk";
        public static final String TIMER_VALUE = "TimerValue";
        public static final String TITLE = "title";
        public static final String USER_SPEECH_INGRESS_TYPE = "ingressType";
        public static final String USER_SPEECH_INVOCATION_TYPE = "invocationType";
        public static final String WAKE_WORD = "wakeWord";

        private EventConstants() {
        }
    }

    /* loaded from: classes6.dex */
    public final class EventTypes {
        public static final String EVENT_TYPE_COUNTER = "Counter";
        public static final String EVENT_TYPE_DATA = "Data";
        public static final String EVENT_TYPE_ENGAGEMENT = "Engagement";
        public static final String EVENT_TYPE_ERROR = "Error";
        public static final String EVENT_TYPE_GENERAL = "General";
        public static final String EVENT_TYPE_IMPRESSION = "Impression";
        public static final String EVENT_TYPE_MONITIZATION = "Monitization";
        public static final String EVENT_TYPE_NOTIFICATION = "Notification";
        public static final String EVENT_TYPE_NOTIFICATION_CLICK = "NotificationClick";
        public static final String EVENT_TYPE_OCCURRENCE = "Occurence";
        public static final String EVENT_TYPE_RECORD_TIMER = "RecordTimer";
        public static final String EVENT_TYPE_START_TIMER = "StartTimer";
        public static final String EVENT_TYPE_TIMER = "Timer";

        private EventTypes() {
        }
    }

    /* loaded from: classes6.dex */
    public final class Launcher {
        public static final String EVENT_NAME_PREFIX = "Alexa.Launcher.";
        public static final String OUTCOME = "launcherOutcome";
        public static final String REASONS = "launcherReasons";
        public static final String TARGET = "launcherTarget";
        public static final String TOKEN = "launcherToken";

        private Launcher() {
        }
    }

    /* loaded from: classes6.dex */
    public final class MetricEvents {
        public static final String APP_CRASH = "APP_CRASH";
        public static final String APP_CRASH_COUNT = "APP_CRASH_COUNT";
        public static final String BEGIN_SESSION = "BEGIN_SESSION";
        public static final String CARD_SHOWN = "CardShown";
        public static final String DOZE_MODE_CHANGED = "DOZE_MODE_CHANGED";
        public static final String DOZE_MODE_CHANGE_SUCCESSFUL = "DOZE_MODE_CHANGE_SUCCESSFUL";
        public static final String DOZE_MODE_CHANGE_TIMEOUT = "DOZE_MODE_CHANGE_TIMEOUT";
        public static final String END_SESSION = "END_SESSION";
        public static final String ESTIMATED_USER_PERCEIVED_LATENCY = "EstimatedUserPerceivedLatency";
        public static final String LOCATION_ENABLED = "LocationEnabled";
        public static final String LOCATION_PERMISSION_INITIAL_CONVERSION = "LocationInitialConversion";
        public static final String SERVICE_INSTALL = "SERVICE_INSTALL";
        public static final String SERVICE_UPDATE = "SERVICE_UPDATE";
        public static final String USER_PERCEIVED_LATENCY = "UserPerceivedLatency";
        public static final String VOX_CANCELLED = "VOX_ASK_ALEXA_CANCELLED";
        public static final String VOX_FAILURE = "VOX_ASK_ALEXA_FAILED";
        public static final String VOX_FAILURE_AVS_ERROR_PREFIX = "VOX_FAILURE_AVS_ERROR_";
        public static final String VOX_OFFLINE_VUI_ALEXA_DOWN = "VOX_OFFLINE_VUI_ALEXA_DOWN";
        public static final String VOX_OFFLINE_VUI_LOST_CONNECTION = "VOX_OFFLINE_VUI_LOST_CONNECTION";
        public static final String VOX_OFFLINE_VUI_NOT_CONNECTED = "VOX_OFFLINE_VUI_NOT_CONNECTED";
        public static final String VOX_SERVICE_CREATION_TIME = "ServiceCreationTime";
        public static final String VOX_START = "VOX_ASK_ALEXA";
        public static final String VOX_UNSUPPORTED_DIRECTIVE = "VOX_UNSUPPORTED_DIRECTIVE";

        private MetricEvents() {
        }
    }

    /* loaded from: classes6.dex */
    public final class MetricsComponents {
        public static final String ALEXA_VOICE_SERVICE = "vox_speech_v2";
        public static final String APP_COMPONENT_NAME = "vox_speech";
        public static final String CARD_INGRESS_COMPONENT_NAME = "AlexaApp.CardIngress";
        public static final String DCM = "DCM";
        public static final String KINESIS = "kinesis";
        public static final String METRICS_DATA = "service.metrics.alexaservice";
        public static final String MOBILYTICS_V2 = "mobilytics_v2";

        private MetricsComponents() {
        }
    }

    private AlexaMetricsConstants() {
    }
}

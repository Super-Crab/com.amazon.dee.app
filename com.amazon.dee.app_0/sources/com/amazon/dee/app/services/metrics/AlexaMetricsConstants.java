package com.amazon.dee.app.services.metrics;
/* loaded from: classes12.dex */
public final class AlexaMetricsConstants {
    public static final String NAME = "AlexaMobileAndroid";

    /* loaded from: classes12.dex */
    public static final class EventConstants {
        public static final String ALEXA_MOBILE_NOTIFICATIONS = "1235005e-4e8f-4ef2-82bc-34157415015b";
        public static final String ANDROID_ID = "androidId";
        public static final String APP_COMPONENT = "AppComponent";
        public static final String APP_FOREGROUNDED = "AppForegrounded";
        public static final String APP_UPDATE = "app_update";
        public static final String APP_VERSION = "AppVersion";
        public static final String APP_VERSION_COUNTRY_CODE = "AppVersion:CountryCode";
        public static final String APP_VERSION_COUNTRY_CODE_MARKETPLACE_ID_CODE = "AppVersion:CountryCode:MarketplaceIDCode";
        public static final String APP_VERSION_MARKETPLACE_ID_CODE = "AppVersion:MarketplaceIDCode";
        public static final String APP_VERSION_OS_TYPE_CODE = "AppVersion:OSType";
        public static final String APP_VERSION_OS_TYPE_COUNTRY_CODE = "AppVersion:OSType:CountryCode";
        public static final String APP_VERSION_OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE = "AppVersion:OSType:CountryCode:MarketplaceIDCode";
        public static final String APP_VERSION_OS_TYPE_MARKETPLACE_ID_CODE = "AppVersion:OSType:MarketplaceIDCode";
        public static final String ATTRIBUTION_TAG = "attributionTag";
        public static final String COUNTER_VALUE = "CounterValue";
        public static final String COUNTRY_CODE = "CountryCode";
        public static final String COUNTRY_CODE_MARKETPLACE_ID_CODE = "CountryCode:MarketplaceIDCode";
        public static final String CUSTOM_ENTRIES = "CustomEntries";
        public static final String DEVICE_MODEL = "DeviceModel";
        public static final String DIRECTED_ID = "DirectedID";
        public static final String EVENT_NAME = "EventName";
        public static final String EVENT_NUMERIC_VALUE = "EventNumericValue";
        public static final String EVENT_TIMESTAMP = "EventTimestamp";
        public static final String EVENT_TYPE = "EventType";
        public static final String EVENT_VALUE = "EventValue";
        public static final String FIRST_INSTALL = "first_install";
        public static final String FIRST_LAUNCH = "FirstLaunch";
        public static final String INTERACTION_EVENT_TYPE_KEY = "interactionType";
        public static final String ITEM_PRICE = "ItemPrice";
        public static final String LOCALE = "locale";
        public static final String LOCAL_TIMEZONE = "localTimezone";
        public static final String MARKETPLACE_ID_CODE = "MarketplaceIDCode";
        public static final String MESSAGE = "Message";
        public static final String METRICS_SESSION_STORAGE = "service.metrics";
        public static final String MOBILYTICS_SESSION_ID = "mobilyticsSessionId";
        public static final String NETWORK = "Network";
        public static final String NEW_VALUE = "NewValue";
        public static final String OLD_VALUE = "OldValue";
        public static final String OPERATIONAL_EVENT_TYPE_KEY = "operationalEventType";
        public static final String OS_TYPE = "OSType";
        public static final String OS_TYPE_COUNTRY_CODE = "OSType:CountryCode";
        public static final String OS_TYPE_COUNTRY_CODE_MARKETPLACE_ID_CODE = "OSType:CountryCode:MarketplaceIDCode";
        public static final String OS_TYPE_MARKETPLACE_ID_CODE = "OSType:MarketplaceIDCode";
        public static final String OWNER_IDENTIFIER_KEY = "ownerIdentifier";
        public static final String PFM = "pfm";
        public static final String PLATFORM_ANDROID_OWNER_IDENTIFIER = "cbea4080-337a-4b7e-8e0b-ea16ec85c09a";
        public static final String PRODUCT_ID = "ProductID";
        public static final String RECORD_TIMER_END = "RecordTimerEnd";
        public static final String SEND_PRIORITY = "sendPriority";
        public static final String SEPARATOR = ":";
        public static final String SERVICE_NAME = "ServiceName";
        public static final String SESSION_ID = "SessionID";
        public static final String SOURCE = "Source";
        public static final String STATUS_CODE = "statusCode";
        public static final String SUBCOMPONENT = "subComponent";
        public static final String TIMER_VALUE = "TimerValue";
        public static final String VERSION_NAME = "AppVersionName";

        private EventConstants() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class EventTypes {
        public static final String EVENT_TYPE_COUNTER = "Counter";
        public static final String EVENT_TYPE_DATA = "Data";
        public static final String EVENT_TYPE_ENGAGEMENT = "Engagement";
        public static final String EVENT_TYPE_ENGAGEMENT_PMET = "engagement";
        public static final String EVENT_TYPE_ERROR = "Error";
        public static final String EVENT_TYPE_EVENT = "Event";
        public static final String EVENT_TYPE_FATAL = "Fatal";
        public static final String EVENT_TYPE_GENERAL = "General";
        public static final String EVENT_TYPE_IMPRESSION = "Impression";
        public static final String EVENT_TYPE_MOBILYTICS_INTERACTION = "MobilyticsInteraction";
        public static final String EVENT_TYPE_MOBILYTICS_OPERATIONAL = "MobilyticsOperational";
        public static final String EVENT_TYPE_MONETIZATION = "Monetization";
        public static final String EVENT_TYPE_OCCURRENCE = "Occurrence";
        public static final String EVENT_TYPE_RECORD_TIMER = "RecordTimer";
        public static final String EVENT_TYPE_START_TIMER = "StartTimer";
        public static final String EVENT_TYPE_TIMER = "Timer";

        private EventTypes() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class Format {
        public static final String NAVIGATION = "%S_%S_TO_%S_DURATION";

        private Format() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class MetricEvents {
        public static final String ACCESSIBILITY_ENABLED = "ACCESSIBILITY_ENABLED";
        public static final String ACCESSIBILITY_OPTIONS = "ACCESSIBILITY_OPTIONS";
        public static final String ACCESSIBILITY_SCREEN_READER = "ACCESSIBILITY_SCREEN_READER";
        public static final String ACTIVITY_COLD_START = "ACTIVITY_COLD_START";
        public static final String ACTIVITY_CREATED = "ActivityCreated";
        public static final String ACTIVITY_DESTROYED = "ActivityDestroyed";
        public static final String ACTIVITY_PAUSED = "ActivityPaused";
        public static final String ACTIVITY_RESUMED = "ActivityResumed";
        public static final String ACTIVITY_STARTED = "ActivityStarted";
        public static final String ACTIVITY_STOPPED = "ActivityStopped";
        public static final String ADM_REGISTRATION_ERROR = "ADM_REGISTRATION_ERROR";
        public static final String ADM_REGISTRATION_SUCCESS = "ADM_REGISTRATION_SUCESS";
        public static final String ALEXAWEBVIEW_START_TO_COMPLETE_TIMER = "ALEXAWEBVIEW_START_TO_COMPLETE_TIMER";
        public static final String APP_CLOSE = "APP_CLOSE";
        public static final String APP_COLD_SIGN_IN_SUCCESS = "APP_COLD_SIGN_IN_SUCCESS";
        public static final String APP_INSTALL = "APP_INSTALL";
        public static final String APP_LAUNCHED_IN_FOREGROUND = "APP_LAUNCHED_IN_FOREGROUND";
        public static final String APP_MEMORY_WARNING = "APP_MEMORY_WARNING";
        public static final String APP_OPEN = "APP_OPEN";
        public static final String APP_RESTART = "APP_RESTART";
        public static final String APP_RESUME = "APP_RESUME";
        public static final String APP_REVIEW_REQUEST_ANDROID = "APP_REVIEW_REQUEST_ANDROID";
        public static final String APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_ANDROID = "APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_ANDROID";
        public static final String APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_FIREOS = "APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_FIREOS";
        public static final String APP_REVIEW_REQUEST_FIREOS = "APP_REVIEW_REQUEST_FIREOS";
        public static final String APP_REVIEW_REQUEST_NOT_NOW_BUTTON_ANDROID = "APP_REVIEW_REQUEST_NOT_NOW_BUTTON_ANDROID";
        public static final String APP_REVIEW_REQUEST_NOT_NOW_BUTTON_FIREOS = "APP_REVIEW_REQUEST_NOT_NOW_BUTTON_FIREOS";
        public static final String APP_REVIEW_REQUEST_REPORT_PROBLEM_ANDROID = "APP_REVIEW_REQUEST_REPORT_PROBLEM_ANDROID";
        public static final String APP_REVIEW_REQUEST_REPORT_PROBLEM_FIREOS = "APP_REVIEW_REQUEST_REPORT_PROBLEM_FIREOS";
        public static final String APP_REVIEW_REQUEST_YES_ANDROID = "APP_REVIEW_REQUEST_YES_ANDROID";
        public static final String APP_REVIEW_REQUEST_YES_FIREOS = "APP_REVIEW_REQUEST_YES_FIREOS";
        public static final String APP_SESSION = "APP_SESSION";
        public static final String APP_SESSION_LANDSCAPE_ENABLED = "APP_SESSION_LANDSCAPE_ENABLED";
        public static final String APP_SESSION_LANDSCAPE_USE_10 = "APP_SESSION_LANDSCAPE_USE_10";
        public static final String APP_SESSION_LANDSCAPE_USE_60 = "APP_SESSION_LANDSCAPE_USE_60";
        public static final String APP_SESSION_MEMORY_WARNING = "APP_SESSION_MEMORY_WARNING";
        public static final String APP_TERMINATE = "APP_TERMINATE";
        public static final String APP_THEME_CHANNEL = "APP_THEME_CHANNEL";
        public static final String APP_THEME_JASPER_DARK = "APP_THEME_JASPER_DARK";
        public static final String APP_THEME_JASPER_LIGHT = "APP_THEME_JASPER_LIGHT";
        public static final String APP_THEME_JASPER_UNKNOWN = "APP_THEME_JASPER_UNKNOWN";
        public static final String APP_TIME = "APP_TIME";
        public static final String APP_UPDATE = "APP_UPDATE";
        public static final String APP_WARM_SIGN_IN_SUCCESS = "APP_WARM_SIGN_IN_SUCCESS";
        public static final String AUTH_ERROR_CORAL_SERVICE_EXCEPTION = "AuthenticationErrorCoralServiceException";
        public static final String AUTH_ERROR_LOGIN_CANCELLED = "AuthenticationErrorLoginCanceled";
        public static final String AUTH_ERROR_NETWORK_FAIL = "AuthenticationErrorNetwork";
        public static final String AUTH_ERROR_SSL_CERTIFICATE = "AuthenticationErrorSSL";
        public static final String AUTH_ERROR_UNKNOWN_CLASS = "AuthenticationErrorUnknownClass";
        public static final String AUTH_START = "AuthenticationStart";
        public static final String AUTH_SUCCESS = "AuthenticationSucceeded";
        public static final String AUTO_SAVE_WAS_ENABLED_FOR_AMPD = "AutoSaveWasEnabledForAMPD";
        public static final String COMMS_LAUNCH_EXISTING_USER_OOBE = "COMMS_LAUNCH_EXISTING_USER_OOBE";
        public static final String COMMS_LAUNCH_NEW_USER_OOBE = "COMMS_LAUNCH_NEW_USER_OOBE";
        public static final String COMMS_SIGN_OUT = "COMMS_SIGN_OUT";
        public static final String CONFIGURATION_CHANGE_RESTART = "CONFIGURATION_CHANGE_RESTART";
        public static final String CONNECT_TO_CLOUD_DRIVE_ERROR = "CONNECT_TO_CLOUD_DRIVE_ERROR";
        public static final String CRYPTO_DECRYPT_MESSAGE_ERROR_PERCENT = "CRYPTO_DECRYPT_MESSAGE_ERROR_PERCENT";
        public static final String CRYPTO_DECRYPT_MESSAGE_EVENT = "CRYPTO_DECRYPT_MESSAGE_EVENT";
        public static final String CRYPTO_EXPIRE_KEY_PAIR_ERROR_PERCENT = "CRYPTO_EXPIRE_KEY_PAIR_ERROR_PERCENT";
        public static final String CRYPTO_EXPIRE_KEY_PAIR_EVENT = "CRYPTO_EXPIRE_KEY_PAIR_EVENT";
        public static final String CRYPTO_GENERATE_KEY_PAIR_ERROR_PERCENT = "CRYPTO_GENERATE_KEY_PAIR_ERROR_PERCENT";
        public static final String CRYPTO_GET_PUBLIC_KEY_EVENT = "CRYPTO_GET_PUBLIC_KEY_EVENT";
        public static final String CRYPTO_NOT_AVAILABLE_PERCENT = "CRYPTO_NOT_AVAILABLE_PERCENT";
        public static final String CRYPTO_RETRIEVE_KEY_PAIR_ERROR_PERCENT = "CRYPTO_RETRIEVE_KEY_PAIR_ERROR_PERCENT";
        public static final String CS_NATIVE_INIT = "iNativeInit";
        public static final String DEFAULT_METRICS_INITIALIZED = "DEFAULT_METRICS_INITIALIZED";
        public static final String DMPS_DEREGISTRATION_ERROR = "DMPS_DEREGISTRATION_ERROR";
        public static final String DMPS_REGISTRATION_ERROR = "DMPS_REGISTRATION_ERROR";
        public static final String DMPS_REGISTRATION_SUCCESS = "DMPS_REGISTRATION_SUCESS";
        public static final String ENDPOINTS_CONSTRUCTION_FAILURE = "ENDPOINTS_CONSTRUCTION_FAILURE";
        public static final String ENDPOINTS_REQUEST_ATTEMPT = "ENDPOINTS_REQUEST_ATTEMPT";
        public static final String ENDPOINTS_REQUEST_FAILURE = "ENDPOINTS_REQUEST_FAILURE";
        public static final String ENDPOINTS_REQUEST_SUCCESS_RATE = "ENDPOINTS_REQUEST_SUCCESS_RATE";
        public static final String FAIL_TO_DELETE_UPLOAD_STAGING_FILE = "FAIL_TO_DELETE_UPLOAD_STAGING_FILE";
        public static final String FCM_DEREGISTRATION_ERROR = "FCM_DEREGISTRATION_ERROR";
        public static final String FCM_REGISTRATION_ERROR = "FCM_REGISTRATION_ERROR";
        public static final String FCM_REGISTRATION_SUCCESS = "FCM_REGISTRATION_SUCESS";
        public static final String FCM_TOKEN_INVALIDATION_ERROR = "FCM_TOKEN_INVALIDATION_ERROR";
        public static final String INIT_AMAZON_DRIVE_ERROR = "INIT_AMAZON_DRIVE_ERROR";
        public static final String INTERCEPT_REFRESH_TOKENS = "INTERCEPT_REFRESH_TOKENS";
        public static final String IN_APP_RATING_ERROR = "InAppRatingError";
        public static final String IN_APP_RATING_EXPERIMENT = "InAppRatingExperiment";
        public static final String IN_APP_RATING_FINISHED = "InAppRatingFinished";
        public static final String IN_APP_RATING_STARTED = "InAppRatingStarted";
        public static final String KNIGHT_REMOVE_CDS_ERROR = "KNIGHT_REMOVE_CDS_ERROR";
        public static final String KNIGHT_REMOVE_CDS_SUCCESS = "KNIGHT_REMOVE_CDS_SUCCESS";
        public static final String KNIGHT_REMOVE_DWCS_ERROR = "KNIGHT_REMOVE_DWCS_ERROR";
        public static final String KNIGHT_REMOVE_DWCS_SUCCESS = "KNIGHT_REMOVE_DWCS_SUCCESS";
        public static final String KNIGHT_UPLOAD_CDS_ERROR = "KNIGHT_UPLOAD_CDS_ERROR";
        public static final String KNIGHT_UPLOAD_CDS_SUCCESS = "KNIGHT_UPLOAD_CDS_SUCCESS";
        public static final String KNIGHT_UPLOAD_CDS_TIME = "KNIGHT_UPLOAD_CDS_TIME";
        public static final String KNIGHT_UPLOAD_DWCS_ERROR = "KNIGHT_UPLOAD_DWCS_ERROR";
        public static final String KNIGHT_UPLOAD_DWCS_SUCCESS = "KNIGHT_UPLOAD_DWCS_SUCCESS";
        public static final String KNIGHT_UPLOAD_DWCS_TIME = "KNIGHT_UPLOAD_DWCS_TIME";
        public static final String KNIGHT_UPLOAD_DWCS_TIMEOUT = "KNIGHT_UPLOAD_DWCS_TIMEOUT";
        public static final String KNIGHT_UPLOAD_ERROR = "KNIGHT_UPLOAD_ERROR";
        public static final String KNIGHT_UPLOAD_TIME = "KNIGHT_UPLOAD_TIME";
        public static final String LOCATION_PERMISSION_DENIED = "permissions.location.denied";
        public static final String LOCATION_PERMISSION_ERROR = "permissions.location.error";
        public static final String LOCATION_PERMISSION_GRANTED_ALWAYS = "permissions.location.granted.always";
        public static final String LOCATION_PERMISSION_GRANTED_FOREGROUND_ONLY = "permissions.location.granted.foregroundonly";
        public static final String LOCATION_PERMISSION_TRANSITION = "permissions.location.transition";
        public static final String MAP_COOKIES_FETCH_FAILURE = "MAP_COOKIES_FETCH_FAILURE";
        public static final String MEMORY_GRAPHICS_SIZE = "MEMORY_GRAPHICS_SIZE";
        public static final String MEMORY_JAVA_HEAP_SIZE = "MEMORY_JAVA_HEAP_SIZE";
        public static final String MEMORY_NATIVE_HEAP_SIZE = "MEMORY_NATIVE_HEAP_SIZE";
        public static final String MEMORY_PROCESS_SIZE = "MEMORY_PROCESS_SIZE";
        public static final String MENU_CHANNELS_DEVICES = "MENU_CHANNELS_DEVICES";
        public static final String MENU_CHANNELS_ENTERTAINMENT = "MENU_CHANNELS_ENTERTAINMENT";
        public static final String MENU_CHANNELS_HOME = "MENU_CHANNELS_HOME";
        public static final String MENU_CONVERSATIONS = "MENU_CONVERSATIONS";
        public static final String MENU_MORE_NAVIGATION_MENU = "MENU_MORE_NAVIGATION_MENU";
        public static final String MENU_NOWPLAYING = "MENU_NOWPLAYING";
        public static final String MENU_NOWPLAYING_ELEMENTS = "MENU_NOWPLAYING_ELEMENTS";
        public static final String MESSAGE_ERROR_PERCENT = "MESSAGE_ERROR_PERCENT";
        public static final String METRICS_BRIDGE_ERROR = "METRICS_BRIDGE_ERROR";
        public static final String MOBILYTICS_INITIALIZED = "MOBILYTICS_INITIALIZED";
        public static final String NATIVE_NOW_PLAYING_UI = "NativeNowPlayingVisible";
        public static final String NEARBY_PERMISSIONS_DENIED = "NEARBY_PERMISSIONS_DENIED";
        public static final String NOTIFICATIONS_OS_SETTING_DISABLED = "NOTIFICATION_DISABLED";
        public static final String NOTIFICATIONS_OS_SETTING_ENABLED = "NOTIFICATION_ENABLED";
        public static final String NOTIFICATION_BANNER_DISPLAY = "NOTIFICATION_BANNER_DISPLAY";
        public static final String NOTIFICATION_CLICK = "NOTIFICATION_CLICK";
        public static final String NOTIFICATION_CONTAINS_MEDIA_PERCENT = "NOTIFICATION_CONTAINS_MEDIA_PERCENT";
        public static final String NOTIFICATION_MEDIA_FETCH_SUCCESS_PERCENT = "NOTIFICATION_MEDIA_FETCH_SUCCESS_PERCENT";
        public static final String NOTIFICATION_RECEIVED = "NOTIFICATION_RECEIVED";
        public static final String NOTIFICATION_RECEIVED_PUSH_MESSAGE_API = "NOTIFICATION_RECEIVED_PUSH_MESSAGE_API";
        public static final String NOTIFICATION_RECEIVED_SEND_TO_PHONE_API = "NOTIFICATION_RECEIVED_SEND_TO_PHONE_API";
        public static final String NOTIFICATION_RECEIVED_USER_MISSMATCH_ERROR = "NOTIFICATION_RECEIVED_USER_MISSMATCH_ERROR";
        public static final String NOTIFICATION_SUBMIT_SUCCESS_PERCENT = "NOTIFICATION_CONTAINS_MEDIA_PERCENT_SUBMIT_PERCENT";
        public static final String NOTIFICATION_USED_ALT_TEXT_PERCENT = "NOTIFICATION_CONTAINS_ALT_TEXT_PERCENT";
        public static final String NO_NETWORK_ERROR = "NO_NETWORK_ERROR";
        public static final String ON_CREATE_DURATION = "_ON_CREATE_DURATION";
        public static final String OOBE_CORS_ERROR = "OOBE_CORS_ERROR";
        public static final String PROFILE_LAUNCH_EXISTING_USER_OOBE = "PROFILE_LAUNCH_EXISTING_USER_OOBE";
        public static final String QUICK_ACTIONS_WIDGET_ALARM_LATENCY = "QUICK_ACTIONS_WIDGET_ALARM_LATENCY";
        public static final String QUICK_ACTIONS_WIDGET_ANNOUNCE_LATENCY = "QUICK_ACTIONS_WIDGET_ANNOUNCE_LATENCY";
        public static final String QUICK_ACTIONS_WIDGET_LISTS_LATENCY = "QUICK_ACTIONS_WIDGET_LISTS_LATENCY";
        public static final String QUICK_ACTIONS_WIDGET_REMINDER_LATENCY = "QUICK_ACTIONS_WIDGET_REMINDER_LATENCY";
        public static final String QUICK_ACTIONS_WIDGET_UNKNOWN_LATENCY = "QUICK_ACTIONS_WIDGET_UNKNOWN_LATENCY";
        public static final String REFRESH_AUTH_INTERCEPT_SUCCESS_RATE = "REFRESH_AUTH_INTERCEPT_SUCCESS_RATE";
        public static final String RETRIEVE_AUTH_TOKEN_COMPLETE = "RETRIEVE_AUTH_TOKEN_COMPLETE";
        public static final String RETRIEVE_AUTH_TOKEN_EXECUTE_GET_FAIL = "RETRIEVE_AUTH_TOKEN_EXECUTE_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_GET_FAIL = "RETRIEVE_AUTH_TOKEN_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_INTERRUPT_GET_FAIL = "RETRIEVE_AUTH_TOKEN_INTERRUPT_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_MAP_GET_FAIL = "RETRIEVE_AUTH_TOKEN_MAP_GET_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_REGISTER_FAIL = "RETRIEVE_AUTH_TOKEN_REGISTER_FAIL";
        public static final String RETRIEVE_AUTH_TOKEN_START = "RETRIEVE_AUTH_TOKEN_START";
        public static final String RETRIEVE_AUTH_TOKEN_UPDATE = "RETRIEVE_AUTH_TOKEN_UPDATE";
        public static final String RIGHT_MENU_ADD_DEVICE = "RIGHT_MENU_ADD_DEVICE";
        public static final String RIGHT_MENU_BLUEPRINTS = "RIGHT_MENU_BLUEPRINTS";
        public static final String RIGHT_MENU_CONTACTS = "RIGHT_MENU_CONTACTS";
        public static final String RIGHT_MENU_ELEMENTS_DEVICE_SETTINGS = "RIGHT_MENU_ELEMENTS_DEVICE_SETTINGS";
        public static final String RIGHT_MENU_HELP = "RIGHT_MENU_HELP";
        public static final String RIGHT_MENU_HOMEFEED = "RIGHT_MENU_HOMEFEED";
        public static final String RIGHT_MENU_LISTS = "RIGHT_MENU_LISTS";
        public static final String RIGHT_MENU_MUSIC_BOOKS = "RIGHT_MENU_MUSIC_BOOKS";
        public static final String RIGHT_MENU_ROUTINES = "RIGHT_MENU_ROUTINES";
        public static final String RIGHT_MENU_SKILLS = "RIGHT_MENU_SKILLS";
        public static final String RIGHT_MENU_SKILLS_CHANNELS = "RIGHT_MENU_SKILLS_CHANNELS";
        public static final String RIGHT_MENU_THINGS_TO_TRY_ELEMENTS = "RIGHT_MENU_THINGSTOTRY_ELEMENTS";
        public static final String RIGHT_MENU_TIMERS_ALARMS = "RIGHT_MENU_TIMERS_ALARMS";
        public static final String RN_BRIDGE_ALREADY_READY = "RN_BRIDGE_ALREADY_READY";
        public static final String RN_BRIDGE_READY_AFTER_TIMEOUT = "RN_BRIDGE_READY_AFTER_TIMEOUT";
        public static final String RN_BRIDGE_READY_ON_CHANGE = "RN_BRIDGE_READY_ON_CHANGE";
        public static final String SETUP_ACCOUNT_REQUEST_CANCEL_ERROR = "SETUP_ACCOUNT_REQUEST_CANCEL_ERROR";
        public static final String SETUP_ACCOUNT_REQUEST_ERROR = "SETUP_ACCOUNT_REQUEST_ERROR";
        public static final String SIGN_OUT_ATTEMPT = "SIGN_OUT_ATTEMPT";
        public static final String SOFT_AP_PIE_WIFI_PASSTHROUGH = "SOFT_AP_PIE_WIFI_PASSTHROUGH";
        public static final String STARTUP_STATE_MACHINE = "STARTUP_STATE_MACHINE";
        public static final String TCOMM_INITIALIZATION_FAILURE = "TCOMM_INITIALIZATION_FAILURE";
        public static final String TCOMM_INITIALIZATION_LATENCY = "TCOMM_INITIALIZATION_LATENCY";
        public static final String TCOMM_START_REQUEST = "TCOMM_START_REQUEST";
        public static final String TCOMM_STOP_REQUEST = "TCOMM_STOP_REQUEST";
        public static final String TOOLBAR_YOUR_SKILLS = "TOOLBAR_YOUR_SKILLS";
        public static final String TRIM_MEMORY_COMPLETE = "TRIM_MEMORY_COMPLETE";
        public static final String TRIM_MEMORY_MODERATE = "TRIM_MEMORY_MODERATE";
        public static final String TRIM_MEMORY_RUNNING_CRITICAL = "TRIM_MEMORY_RUNNING_CRITICAL";
        public static final String UNAUTHORIZED_REQUEST = "UNAUTHORIZED_REQUEST";
        public static final String VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_NULL = "VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_NULL";
        public static final String VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_PROFILE_NULL = "VOICE_ENROLLMENT_COMMS_OOBE_EVENT_USER_PROFILE_NULL";
        public static final String VOICE_ENROLLMENT_SKIP_EXISTING_USER_OOBE = "VOICE_ENROLLMENT_SKIP_EXISTING_USER_OOBE";
        public static final String VOICE_ENROLLMENT_SKIP_NEW_USER_OOBE = "VOICE_ENROLLMENT_SKIP_NEW_USER_OOBE";
        public static final String VOICE_ENROLLMENT_START_EXISTING_USER_OOBE = "VOICE_ENROLLMENT_START_EXISTING_USER_OOBE";
        public static final String VOICE_ENROLLMENT_START_NEW_USER_OOBE = "VOICE_ENROLLMENT_START_NEW_USER_OOBE";
        public static final String WEBVIEW_HTTP_ERROR = "WEBVIEW_HTTP_ERROR";
        public static final String WEBVIEW_RESOURCE_ERROR = "WEBVIEW_RESOURCE_ERROR";
        public static final String WHEN_READY = "WHEN_READY";

        /* loaded from: classes12.dex */
        public static final class AuthError {
            public static final String COOKIE_AUTH_ERROR = "CookieAuthError";
            public static final String EMPTY_AUTH_TOKEN = "EmptyAuthToken";
            public static final String ENDPOINTS_UPDATE = "EndpointsUpdate";
            public static final String HOST_NOT_ALLOWED = "HostNotAllowed";
            public static final String KEY_DIRECTED_ID = "directedId";
            public static final String KEY_EXCEPTION_MESSAGE = "exceptionMessage";
            public static final String KEY_TRANSFORMED_URL = "transformedUrl";
            public static final String KEY_URL = "url";
            public static final String REQ_AUTHENTICATED = "RequestAlreadyAuthenticated";
            public static final String REQ_INVALID_PROTOCOL = "InvalidProtocol";
            public static final String USER_NOT_REGISTERED = "UserNotRegistered";

            private AuthError() {
            }
        }

        /* loaded from: classes12.dex */
        public static final class Deeplink {
            public static final String INVALID_DESTINATION_DEEPLINK_URL = "INVALID_DESTINATION_DEEPLINK_URL";
            public static final String SONAR_FALLBACK_DEEPLINK_URL = "SONAR_FALLBACK_DEEPLINK_URL";
            public static final String URL = "DEEPLINK_URL";
        }

        /* loaded from: classes12.dex */
        public static final class TTCF {
            public static final String LOADING = "react.lmnt.core.page-loading.time";
            public static final String ROUTING = "react.lmnt.core.route.time";
            public static final String TTCF = "react.lmnt.core.route-and-page-loading.time";

            private TTCF() {
            }
        }

        private MetricEvents() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class MetricsComponents {
        public static final String ALEXA_APPLICATION = "Application";
        public static final String ALEXA_APPLICATION_SKILLS_SUBCOMPONENT = "ApplicationYourSkills";
        public static final String BLUETOOTH_SERVICE = "DefaultBluetoothService";
        public static final String BOTTOM_MENU = "BottomMenu";
        public static final String BOTTOM_MENU_CHANNELS_DEVICES_SUBCOMPONENT = "BottomMenuChannelsDevices";
        public static final String BOTTOM_MENU_CHANNELS_ENTERTAINMENT_SUBCOMPONENT = "BottomMenuChannelsEntertainment";
        public static final String BOTTOM_MENU_CHANNELS_HOME_SUBCOMPONENT = "BottomMenuChannelsHome";
        public static final String BOTTOM_MENU_HOME_SUBCOMPONENT = "BottomMenuHome";
        public static final String BOTTOM_MENU_MORE_MENU_SUBCOMPONENT = "BottomMenuMoreMenu";
        public static final String BOTTOM_MENU_NOW_PLAYING_SUBCOMPONENT = "BottomMenuNowPlaying";
        public static final String CLOUD_DRIVE_SERVICE = "CloudDriveService";
        public static final String CORAL_SERVICE = "CoralService";
        public static final String DEEPLINKING = "Deeplinking";
        public static final String ELEMENTS = "elements";
        public static final String ENTERTAINMENT = "Entertainment";
        public static final String IN_APP_RATING_EXPERIMENT_TREATMENT_C = "TreatmentC";
        public static final String IN_APP_RATING_EXPERIMENT_TREATMENT_T1 = "TreatmentT1";
        public static final String IN_APP_RATING_REVIEW_LAUNCH_FLOW_ERROR = "InAppRatingReviewLaunchFlowError";
        public static final String IN_APP_RATING_REVIEW_REQUEST_FLOW_ERROR = "InAppRatingReviewRequestFlowError";
        public static final String IN_APP_RATING_REVIEW_UNSUCCESSFUL_FLOW_ERROR = "InAppRatingReviewUnsuccessfulFlowError";
        public static final String LAUNCHER_WIDGET_COLD_START = "LAUNCHER_WIDGET_COLD_START";
        public static final String LAUNCHER_WIDGET_WARM_START = "LAUNCHER_WIDGET_WARM_START";
        public static final String METRICS_BRIDGE = "MetricsBridge";
        public static final String METRICS_BRIDGE_METRIC_SERVICE_SUBCOMPONENT = "MetricsBridgeMetricService";
        public static final String NOTIFICATIONS = "Notifications";
        public static final String NOTIFICATIONS_CRYPTO_HANDLER_SUBCOMPONENT = "NotificationsCryptoHandler";
        public static final String NOTIFICATIONS_LANDING_PAGE_SUBCOMPONENT = "NotificationsLandingPage";
        public static final String NOTIFICATIONS_RECEIVER_SUBCOMPONENT = "NotificationsReceiver";
        public static final String NOTIFICATIONS_UI_BUILDER_SUBCOMPONENT = "NotificationsUIBuilder";
        public static final String OKHTTP_CLIENT = "OkHttpClient";
        public static final String OOBE = "OOBE";
        public static final String PREFETCH = "Prefetch";
        public static final String PUSH_MESSAGE_DEFAULT_TYPE = "Default";
        public static final String PUSH_NOTIFICATIONS = "PushNotifications";
        public static final String QUICK_ACTIONS_WIDGET = "QUICK_ACTIONS_WIDGET";
        public static final String REAUTHENTICATION = "Reauthentication";
        public static final String RIGHT_MENU = "RightMenu";
        public static final String RIGHT_MENU_BLUEPRINTS_SUBCOMPONENT = "RightMenuBlueprints";
        public static final String RIGHT_MENU_HELP_SUBCOMPONENT = "RightMenuHelp";
        public static final String RIGHT_MENU_SKILLS_CHANNELS_SUBCOMPONENT = "RightMenuSkillsChannels";
        public static final String RIGHT_MENU_SKILLS_SUBCOMPONENT = "RightMenuSkills";
        public static final String TCOMM = "TCOMM";
        public static final String UNKNOWN = "UNKNOWN";
        public static final String VIEW_MODEL_SUBCOMPONENT = "MainViewModelSelectedItem";
        public static final String WEBVIEW = "Webview";
        public static final String WIFI_SERVICE = "DefaultWifiService";

        private MetricsComponents() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class MobilyticsColumn {
        public static final String ERROR_SHORT_MESSAGE = "error_short_message";
        public static final String ERROR_TITLE = "error_title";
        public static final String SOURCE_CONTEXT = "source_context";
        public static final String SOURCE_NAME = "source_name";
        public static final String STATUS_CODE = "status_code";

        private MobilyticsColumn() {
        }
    }

    private AlexaMetricsConstants() {
    }
}

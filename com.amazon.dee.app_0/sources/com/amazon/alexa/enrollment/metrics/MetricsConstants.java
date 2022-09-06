package com.amazon.alexa.enrollment.metrics;
/* loaded from: classes7.dex */
public final class MetricsConstants {
    public static final String VOICE_ENROLL_COMPONENT = "VoiceEnrollment";
    public static final String VOICE_ENROLL_LOGGING_PREFIX = "VoxEn_";
    public static final String VOICE_ENROLL_SUB_COMPONENT = "VoiceEnrollment";

    /* loaded from: classes7.dex */
    public static final class OperationalMetrics {
        public static final String ALEXA_SERVICES_CONNECTED = "ALEXA_SERVICES_CONNECTED";
        public static final String ALEXA_SERVICES_CONNECTION_RETRY = "ALEXA_SERVICES_CONNECTION_RETRY";
        public static final String ALEXA_SERVICES_DISCONNECTED = "ALEXA_SERVICES_DISCONNECTED";
        public static final String AMAZON_ALEXA_API_CALL_SUCCESS = "AMAZON_ALEXA_API_CALL_SUCCESS_";
        public static final String AMAZON_ALEXA_API_CALL_TIME = "AMAZON_ALEXA_API_CALL_TIME_";
        public static final String ENROLLMENT_CONTEXT_PREFIX = "ENROLLMENT_CONTEXT_";
        public static final String ENROLLMENT_STATUS_PREFIX = "ENROLLMENT_STATUS_";
        public static final String EVENT_BUS_EXCEPTION = "EVENT_BUS_EXCEPTION";
        public static final String EXCEPTION_FETCHING_AMAZON_DEVICE_TYPE = "EXCEPTION_FETCHING_AMAZON_DEVICE_TYPE";
        public static final String EXCEPTION_FETCHING_DSN = "EXCEPTION_FETCHING_DSN";
        public static final String GET_VOICE_ENROLL_ELIGIBILITY_SUCCESS = "GET_VOICE_ENROLL_ELIGIBILITY_SUCCESS";
        public static final String GET_VOICE_ENROLL_ELIGIBILITY_TIME = "GET_VOICE_ENROLL_ELIGIBILITY_TIME";
        public static final String GET_VOICE_ENROLL_STATUS_SUCCESS = "GET_VOICE_ENROLL_STATUS_SUCCESS";
        public static final String GET_VOICE_ENROLL_STATUS_TIME = "GET_VOICE_ENROLL_STATUS_TIME";
        public static final String SERVER_TIME_NOT_UPDATED = "SERVER_TIME_NOT_UPDATED";
        public static final String STARTING_VOICE_ACTIVITY = "STARTING_VOICE_ACTIVITY";
        public static final String START_VOICE_ENROLLMENT_DEVICE_NOT_AVAILABLE = "START_VOICE_ENROLLMENT_DEVICE_NOT_AVAILABLE";
        public static final String START_VOICE_ENROLLMENT_SUCCESS = "START_VOICE_ENROLLMENT_SUCCESS";
        public static final String START_VOICE_ENROLLMENT_TIME = "START_VOICE_ENROLLMENT_TIME";
        public static final String START_VOICE_ENROLLMENT_UNSUPPORTED_LOCALE = "START_VOICE_ENROLLMENT_INELIGIBLE_UNSUPPORTED_LOCALE";
        public static final String TRAINING_ACTIVITY_IS_NULL = "TRAINING_ACTIVITY_IS_NULL";
        public static final String TRAINING_DIALOG_ACTIVITY_IS_NULL = "TRAINING_DIALOG_ACTIVITY_IS_NULL";
        public static final String TRAINING_INDEX = "TRAINING_INDEX_";
        public static final String TRAINING_RETRY_COUNT_EXCEEDED = "TRAINING_RETRY_COUNT_EXCEEDED";
        public static final String VOICE_ACTIVITY_TIMER_EVENT_NAME_PREFIX = "VOICE_ACTIVITY_TIMER_EVENT_";
        public static final String VOICE_ENROLLMENT_RESTRICTED_IN_FIRE_TABLET = "VOICE_ENROLLMENT_RESRTICTED_IN_FIRE_TABLET";
        public static final String VOICE_ENROLL_ELIGIBILITY_FAILURE = "VOICE_ENROLL_ELIGIBILITY_FAILURE";
        public static final String VOICE_ENROLL_ELIGIBILITY_SUCCESS = "VOICE_ENROLL_ELIGIBILITY_SUCCESS";

        private OperationalMetrics() {
        }
    }

    /* loaded from: classes7.dex */
    public static final class UserInteractionMetrics {
        public static final String ALEXA_SERVICE_CONNECTION_FAILED = "ALEXA_SERVICE_CONNECTION_FAILED";
        public static final String ALEXA_SERVICE_CONNECTION_RETRY = "ALEXA_SERVICE_CONNECTION_RETRY";
        public static final String ALEXA_SERVICE_CONNECTION_RETRY_COUNT = "ALEXA_SERVICE_CONNECTION_RETRY_COUNT";
        public static final String ALEXA_SERVICE_CONNECTION_RETRY_EXHAUSTED = "ALEXA_SERVICE_CONNECTION_RETRY_EXHAUSTED";
        public static final String AUDIO_PERMISSION_GRANTED = "AUDIO_PERMISSION_GRANTED";
        public static final String AUDIO_PERMISSION_NOT_GRANTED = "AUDIO_PERMISSION_NOT_GRANTED";
        public static final String AUDIO_RECORDER_STATE_INVALID = "AUDIO_RECORDER_STATE_INVALID";
        public static final String AUDIO_SINK_CREATION_FAILED = "AUDIO_SINK_CREATION_FAILED";
        public static final String AUDIO_SINK_RESET_FAILED = "AUDIO_SINK_RESET_FAILED";
        public static final String AUDIO_SINK_STREAM_WRITE_FAILED = "AUDIO_SINK_STREAM_WRITE_FAILED";
        public static final String COMPLETION_BTN_CLICKED = "COMPLETION_BTN_CLICKED";
        public static final String COMPLETION_PAGE_BACK_BTN_CLICKED = "COMPLETION_PAGE_BACK_BTN_CLICKED";
        public static final String COMPLETION_PAGE_VIEW = "COMPLETION_PAGE_VIEW";
        public static final String DIALOG_IN_PROGRESS_REQUEST_RETRY = "DIALOG_IN_PROGRESS_REQUEST_RETRY";
        public static final String DIALOG_REQUEST_DENIED = "DIALOG_REQUEST_DENIED";
        public static final String DIALOG_REQUEST_NOT_ALLOWED = "DIALOG_REQUEST_NOT_ALLOWED";
        public static final String ENROLLMENT_COMPLETED = "ENROLLMENT_COMPLETED";
        public static final String ENROLLMENT_COMPLETED_FROM_USER_SPEECH_PROVIDER = "ENROLLMENT_COMPLETED_FROM_USER_SPEECH_PROVIDER";
        public static final String ENROLLMENT_ERROR_FROM_USER_SPEECH_PROVIDER = "ENROLLMENT_ERROR_FROM_USER_SPEECH_PROVIDER";
        public static final String ENROLLMENT_ERROR_FROM_VOICE_ACTIVITY = "ENROLLMENT_ERROR_FROM_VOICE_ACTIVITY";
        public static final String ENROLLMENT_STARTED = "ENROLLMENT_STARTED";
        public static final String ENROLLMENT_STARTED_FROM_USER_SPEECH_PROVIDER = "ENROLLMENT_STARTED_FROM_USER_SPEECH_PROVIDER";
        public static final String INTRO_PAGE_BACK_BTN_CLICKED = "INTRO_PAGE_BACK_BTN_CLICKED";
        public static final String INTRO_PAGE_BACK_CLICK = "INTRO_PAGE_BACK_CLICK";
        public static final String INTRO_PAGE_CONTINUE_CLICK = "INTRO_PAGE_CONTINUE_CLICK";
        public static final String INTRO_PAGE_PROMINENT_SKIP_CLICK = "INTRO_PAGE_PROMINENT_SKIP_CLICK";
        public static final String INTRO_PAGE_SKIP_CLICK = "INTRO_PAGE_SKIP_CLICK";
        public static final String INTRO_PAGE_SKIP_DIALOG_BACK_CLICKED = "INTRO_PAGE_SKIP_DIALOG_BACK_CLICKED";
        public static final String INTRO_PAGE_SKIP_DIALOG_SKIP_CLICKED = "INTRO_PAGE_SKIP_DIALOG_SKIP_CLICKED";
        public static final String INTRO_PAGE_VIEW = "INTRO_PAGE_VIEW";
        public static final String KIDS_INTRO_PAGE_BACK_BTN_CLICK = "KIDS_INTO_PAGE_BACK_BTN_CLICK";
        public static final String KIDS_INTRO_PAGE_VIEW = "KIDS_INTRO_PAGE_VIEW";
        public static final String KIDS_POPUP_GET_STARTED_CLICK = "KIDS_POPUP_GET_STARTED_CLICK";
        public static final String KIDS_POPUP_LATER_CLICK = "KIDS_POPUP_LATER_CLICK";
        public static final String KIDS_PRIMER_CLOSE_CLICK = "KIDS_PRIMER_CLOSE_CLICK";
        public static final String KIDS_PRIMER_CONTINUE_CLICK = "KIDS_PRIMER_CONTINUE_CLICK";
        public static final String KIDS_PRIMER_LEARN_MORE_CLICK = "KIDS_PRIMER_LEARN_MORE_CLICK";
        public static final String KIDS_PRIMER_PRIVACY_CLICK = "KIDS_PRIMER_PRIVACY_CLICK";
        public static final String KIDS_PRIMER_SKIP_CLICK = "KIDS_PRIMER_SKIP_CLICK";
        public static final String KIDS_SKIP_ALERT_POPUP_NO_CLICK = "KIDS_SKIP_ALERT_POPUP_NO_CLICK";
        public static final String KIDS_SKIP_ALERT_POPUP_YES_CLICK = "KIDS_SKIP_ALERT_POPUP_YES_CLICK";
        public static final String ON_DIALOG_REQUESTED_ERROR = "ON_DIALOG_REQUESTED_ERROR";
        public static final String ON_DIALOG_REQUESTED_SUCCESS = "ON_DIALOG_REQUESTED_SUCCESS";
        public static final String ON_DIALOG_TURN_REQUESTED = "ON_DIALOG_TURN_REQUESTED";
        public static final String ON_DIALOG_TURN_REQUESTED_ERROR = "ON_DIALOG_TURN_REQUESTED_ERROR";
        public static final String PRIVACY_TERMS_PAGE_OK_CLICK = "PRIVACY_TERMS_PAGE_OK_CLICK";
        public static final String PRIVACY_TERMS_PAGE_SKIP_CLICK = "PRIVACY_TERMS_PAGE_SKIP_CLICK";
        public static final String PRIVACY_TERMS_PAGE_VIEW = "PRIVACY_TERMS_PAGE_VIEW";
        public static final String RECORDER_ALREADY_ACQUIRED = "RECORDER_ALREADY_ACQUIRED";
        public static final String RECORDER_NOT_STARTED = "RECORDER_NOT_STARTED";
        public static final String RECORDER_NOT_STOPPED = "RECORDER_NOT_STOPPED";
        public static final String RETRY_ON_ENROLLMENT_FAILURE = "RETRY_ON_ENROLLMENT_FAILURE";
        public static final String TERMS_PAGE_VIEW = "TERMS_PAGE_VIEW";
        public static final String TRAINING_PAGE_BACK_CLICK = "TRAINING_PAGE_BACK_CLICK";
        public static final String TRAINING_PAGE_CANCELLED_DIALOG = "TRAINING_PAGE_CANCELLED_DIALOG";
        public static final String TRAINING_PAGE_SCRIM_BACK_CLICK = "TRAINING_PAGE_SCRIM_BACK_CLICK";
        public static final String TRAINING_PAGE_VIEW = "TRAINING_PAGE_VIEW";
        public static final String VOICE_PRIVACY_SETTINGS_FETCH_ERROR = "VOICE_PRIVACY_SETTINGS_FETCH_ERROR";
        public static final String VOICE_PRIVACY_SETTINGS_OPT_IN = "VOICE_PRIVACY_SETTINGS_OPT_IN";
        public static final String VOICE_PRIVACY_SETTINGS_OPT_OUT = "VOICE_PRIVACY_SETTINGS_OPT_OUT";

        private UserInteractionMetrics() {
        }
    }

    private MetricsConstants() {
    }
}

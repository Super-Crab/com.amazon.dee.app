package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.devicesetup.common.v1.Event;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class AlexaMetricsName {
    private static final String SEPARATOR = ".";
    private final String value;

    /* loaded from: classes6.dex */
    public static final class AccountManager {
        public static final AlexaMetricsName IS_LOGGED_IN_TIME_OUT = AlexaMetricsName.create("ACCOUNT_MANAGER_IS_LOGGED_IN_TIME_OUT");
        public static final AlexaMetricsName IS_LOGGED_IN_ERROR = AlexaMetricsName.create("ACCOUNT_MANAGER_IS_LOGGED_IN_ERROR");
        public static final AlexaMetricsName LOGGED_OUT_WHEN_SENDING_EVENT = AlexaMetricsName.create("ACCOUNT_MANAGER_LOGGED_OUT_WHEN_SENDING_EVENT");
    }

    /* loaded from: classes6.dex */
    public static final class Android {
        public static final AlexaMetricsName LOUDNESS_ENHANCER_UNSUPPORTED = AlexaMetricsName.create("LOUDNESS_ENHANCER_UNSUPPORTED");
    }

    /* loaded from: classes6.dex */
    public static final class AudioPlayer {
        public static final AlexaMetricsName BUFFERING_TIME = AlexaMetricsName.create("AUDIO_PLAYER_BUFFERING_TIME");
        public static final AlexaMetricsName BUFFERING_COUNT = AlexaMetricsName.create("AUDIO_PLAYER_BUFFERING_COUNT");
        public static final AlexaMetricsName BUFFERING_TIMEOUT = AlexaMetricsName.create("AUDIO_PLAYER_BUFFERING_TIMEOUT");
        public static final AlexaMetricsName CONNECTIVITY_FAILURE_RESUME_SUCCESS = AlexaMetricsName.create("AUDIO_PLAYER_CONNECTIVITY_FAILURE_RESUME_SUCCESS");
        public static final AlexaMetricsName CONNECTIVITY_FAILURE_RESUME_TIMEOUT = AlexaMetricsName.create("AUDIO_PLAYER_CONNECTIVITY_FAILURE_RESUME_TIMEOUT");
        public static final AlexaMetricsName PLAY_ATTEMPT = AlexaMetricsName.create("AUDIO_PLAYER_PLAY_ATTEMPT");
        public static final AlexaMetricsName PLAY_FAILURE = AlexaMetricsName.create("AUDIO_PLAYER_PLAY_FAILURE");
        public static final AlexaMetricsName PLAY_FAILURE_CONNECTIVITY = AlexaMetricsName.create("AUDIO_PLAYER_PLAY_FAILURE.CONNECTIVITY");
        public static final AlexaMetricsName AUDIO_PLAYER_PLAYBACK_IS_SLOW = AlexaMetricsName.create("AUDIO_PLAYER_PLAYBACK_IS_SLOW");
    }

    /* loaded from: classes6.dex */
    public static final class AudioPlayerTime {
        public static final AlexaMetricsName BETWEEN_PLAYBACK_STARTING_AND_PLAYBACK_STARTED = AlexaMetricsName.create("AUDIO_PLAYER_TIME_BETWEEN_PLAYBACK_STARTING_AND_PLAYBACK_STARTED");
        public static final AlexaMetricsName BETWEEN_PLAYBACK_RESUMING_AND_PLAYBACK_RESUMED = AlexaMetricsName.create("AUDIO_PLAYER_TIME_BETWEEN_PLAYBACK_RESUMING_AND_PLAYBACK_RESUMED");
        public static final AlexaMetricsName BETWEEN_LAST_PLAY_ITEM_FINISHED_AND_PLAYBACK_STARTED = AlexaMetricsName.create("AUDIO_PLAYER_TIME_BETWEEN_LAST_PLAY_ITEM_FINISHED_AND_PLAYBACK_STARTED");
    }

    /* loaded from: classes6.dex */
    public static final class Business {
        public static final AlexaMetricsName ALEXA_SESSION_LENGTH = AlexaMetricsName.create("ALEXA_SESSION_LENGTH");
    }

    /* loaded from: classes6.dex */
    public static final class CapabilityAgentInteraction {
        public static final AlexaMetricsName DIRECTIVE_DELIVERY = AlexaMetricsName.create("CAPABILITY_AGENT_INTERACTION.DIRECTIVE_DELIVERY");
    }

    /* loaded from: classes6.dex */
    public static final class ContextProviderGet {
        public static final AlexaMetricsName SUCCESS_PREFIX = AlexaMetricsName.create("CONTEXT_PROVIDER_GET_SUCCESS_");
        public static final AlexaMetricsName FAILURE_PREFIX = AlexaMetricsName.create("CONTEXT_PROVIDER_GET_FAILURE_");
        public static final AlexaMetricsName LATENCY_PREFIX = AlexaMetricsName.create("CONTEXT_PROVIDER_GET_LATENCY_");
    }

    /* loaded from: classes6.dex */
    public static final class DeviceLockScreenState {
        public static final AlexaMetricsName DEVICE_LOCK_SCREEN_STATE_CHECK = AlexaMetricsName.create("DEVICE_LOCK_SCREEN_STATE_CHECK");
    }

    /* loaded from: classes6.dex */
    public static final class Downchannel {
        public static final AlexaMetricsName AVAILABLE_LATENCY = AlexaMetricsName.create("DOWNCHANNEL_AVAILABLE_LATENCY");
        public static final AlexaMetricsName CONNECTION_LATENCY = AlexaMetricsName.create("DOWNCHANNEL_CONNECTION_LATENCY");
        public static final AlexaMetricsName FALSE_AUTHORIZATION_FAILURE = AlexaMetricsName.create("DOWNCHANNEL_FALSE_AUTHORIZATION_FAILURE");
        public static final AlexaMetricsName REAL_AUTHORIZATION_FAILURE = AlexaMetricsName.create("DOWNCHANNEL_REAL_AUTHORIZATION_FAILURE");
    }

    /* loaded from: classes6.dex */
    public static final class DownchannelEstablishmentFailure {
        public static final AlexaMetricsName IO_EXCEPTION = AlexaMetricsName.create("DOWNCHANNEL_ESTABLISHMENT_FAILURE_IO_EXCEPTION");
        public static final AlexaMetricsName NO_NETWORK = AlexaMetricsName.create("DOWNCHANNEL_ESTABLISHMENT_FAILURE_NO_NETWORK");
        public static final AlexaMetricsName AVS_FAILURE = AlexaMetricsName.create("DOWNCHANNEL_ESTABLISHMENT_FAILURE_AVS_FAILURE");
    }

    /* loaded from: classes6.dex */
    public static final class ExceptionEncountered {
        public static final AlexaMetricsName AVS = AlexaMetricsName.create("AVS_EXCEPTION_ENCOUNTERED");
        public static final AlexaMetricsName SDK = AlexaMetricsName.create("SDK_EXCEPTION_ENCOUNTERED");
    }

    /* loaded from: classes6.dex */
    public static final class ExternalCapabilityAgents {
        public static final AlexaMetricsName MISSED_EXTERNAL_CAPABILITY = AlexaMetricsName.create("MISSED_EXTERNAL_CAPABILITY");
        public static final AlexaMetricsName ECA_SCAN_SUCCESS = AlexaMetricsName.create("ECA_SCAN_SUCCESS");
        public static final AlexaMetricsName ECA_SCAN_FAILURE = AlexaMetricsName.create("ECA_SCAN_FAILURE");
        public static final AlexaMetricsName ECA_MULTIPLE_REGISTERED_ERROR = AlexaMetricsName.create("ECA_MULTIPLE_REGISTERED_ERROR");
    }

    /* loaded from: classes6.dex */
    public static final class ExternalComponentState {
        public static final AlexaMetricsName INSERT_LATENCY = AlexaMetricsName.create("EXTERNAL_COMPONENT_STATE_INSERT_LATENCY");
        public static final AlexaMetricsName QUERY_LATENCY = AlexaMetricsName.create("EXTERNAL_COMPONENT_STATE_QUERY_LATENCY");
        public static final AlexaMetricsName DELETE_LATENCY = AlexaMetricsName.create("EXTERNAL_COMPONENT_STATE_DELETE_LATENCY");
        public static final AlexaMetricsName GATHERING_LATENCY = AlexaMetricsName.create("EXTERNAL_COMPONENT_STATES_GATHERING_LATENCY");
        public static final AlexaMetricsName COUNT_EXCEEDED = AlexaMetricsName.create("EXTERNAL_COMPONENT_STATE_PROVIDERS_COUNT_EXCEEDED");
    }

    /* loaded from: classes6.dex */
    public static final class ExternalMediaPlayer {
        public static final AlexaMetricsName PLAYER_ERROR = AlexaMetricsName.create("EXTERNAL_MEDIA_PLAYER_ERROR");
        public static final AlexaMetricsName ACTION_START_SERVICE_FOR_MSP = AlexaMetricsName.create("ACTION_START_SERVICE_FOR_MSP");
        public static final AlexaMetricsName MEDIA_BROWSER_CONNECTION_STATUS = AlexaMetricsName.create("MEDIA_BROWSER_CONNECTION_STATUS");
    }

    /* loaded from: classes6.dex */
    public static final class InteractionSchedulerFailure {
        public static final AlexaMetricsName SCHEDULE_FAILED_NOT_CONNECTED = AlexaMetricsName.create("INTERACTION_SCHEDULER.SCHEDULE_FAILED.NOT_CONNECTED");
    }

    /* loaded from: classes6.dex */
    public static final class Latency {
        public static final AlexaMetricsName AMF_CREATION_TIME = AlexaMetricsName.create("AMF_CREATION_TIME_LATENCY");
        public static final AlexaMetricsName VUI_USER_PERCEIVED = AlexaMetricsName.create("VUI_USER_PERCEIVED_LATENCY");
        public static final AlexaMetricsName GUI_DATA_RECEIVED = AlexaMetricsName.create("GUI_DATA_RECEIVED_LATENCY");
        public static final AlexaMetricsName GUI_USER_PERCEIVED = AlexaMetricsName.create("GUI_USER_PERCEIVED_LATENCY");
        public static final AlexaMetricsName ATTENTION_SYSTEM = AlexaMetricsName.create("ATTENTION_SYSTEM_LATENCY");
        public static final AlexaMetricsName REFRESH_EXTERNAL_CAPABILITY_SUCCEEDED = AlexaMetricsName.create("REFRESH_EXTERNAL_CAPABILITY_SUCCEEDED");
        public static final AlexaMetricsName REFRESH_EXTERNAL_CAPABILITY_TIMEDOUT = AlexaMetricsName.create("REFRESH_EXTERNAL_CAPABILITY_TIMEDOUT");
        public static final AlexaMetricsName REFRESH_EXTERNAL_CAPABILITY_PERCENTAGE_FOUND = AlexaMetricsName.create("REFRESH_EXTERNAL_CAPABILITY_PERCENTAGE_FOUND");
        public static final AlexaMetricsName FEATURE_FLAG_LOAD_LATENCY = AlexaMetricsName.create("FEATURE_FLAG_LOAD_LATENCY");
    }

    /* loaded from: classes6.dex */
    public static final class LeaderSelection {
        public static final AlexaMetricsName ATTEMPTED = AlexaMetricsName.create("LEADER_SELECTION_ATTEMPTED");
        public static final AlexaMetricsName MISSING_PACKAGENAME = AlexaMetricsName.create("VERIFICATION_FAILED_MISSING_PACKAGENAME");
        public static final AlexaMetricsName INCORRECT_SIGNATURE = AlexaMetricsName.create("VERIFICATION_FAILED_INCORRECT_SIGNATURE");
        public static final AlexaMetricsName TIMEOUT = AlexaMetricsName.create("VERIFICATION_FAILED_TIMEOUT");
        public static final AlexaMetricsName DISABLED = AlexaMetricsName.create("VERIFICATION_FAILED_DISABLED");
        public static final AlexaMetricsName UNKNOWN_LEADER = AlexaMetricsName.create("VERIFICATION_FAILED_UNKNOWN_LEADER");
        public static final AlexaMetricsName UNKNOWN_ERROR = AlexaMetricsName.create("VERIFICATION_FAILED_UNKNOWN_ERROR");
    }

    /* loaded from: classes6.dex */
    public static final class MetricEvents {
        public static final AlexaMetricsName VOX_OFFLINE_VUI_ALEXA_DOWN = AlexaMetricsName.create(AlexaMetricsConstants.MetricEvents.VOX_OFFLINE_VUI_ALEXA_DOWN);
        public static final AlexaMetricsName VOX_OFFLINE_VUI_NOT_CONNECTED = AlexaMetricsName.create(AlexaMetricsConstants.MetricEvents.VOX_OFFLINE_VUI_NOT_CONNECTED);
        public static final AlexaMetricsName VOX_OFFLINE_VUI_LOST_CONNECTION = AlexaMetricsName.create(AlexaMetricsConstants.MetricEvents.VOX_OFFLINE_VUI_LOST_CONNECTION);
        public static final AlexaMetricsName VOX_OFFLINE_VUI_NETWORK_LOW_BANDWIDTH = AlexaMetricsName.create("VOX_OFFLINE_VUI_NETWORK_LOW_BANDWIDTH");
        public static final AlexaMetricsName VOX_OFFLINE_VUI_NETWORK_TRANSITION_AUTO = AlexaMetricsName.create("VOX_OFFLINE_VUI_NETWORK_TRANSITION_AUTO");
        public static final AlexaMetricsName VOX_OFFLINE_VUI_NETWORK_TRANSITION_NON_AUTO = AlexaMetricsName.create("VOX_OFFLINE_VUI_NETWORK_TRANSITION_NON_AUTO");
        public static final AlexaMetricsName VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_AUTO = AlexaMetricsName.create("VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_AUTO");
        public static final AlexaMetricsName VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_NON_AUTO = AlexaMetricsName.create("VOX_OFFLINE_VUI_CONNECTIVITY_ISSUE_NON_AUTO");
    }

    /* loaded from: classes6.dex */
    public static final class MicInitialization {
        public static final AlexaMetricsName SUCCESS = AlexaMetricsName.create("MIC_INITIALIZATION_SUCCESS");
        public static final AlexaMetricsName FAILURE = AlexaMetricsName.create("MIC_INITIALIZATION_FAILURE");
    }

    /* loaded from: classes6.dex */
    public static final class OfflinePromptPlayed {
        public static final AlexaMetricsName ALEXA_IS_DOWN = AlexaMetricsName.create("ALEXA_IS_DOWN_PROMPT_PLAYED");
        public static final AlexaMetricsName NO_INTERNET_CONNECTION = AlexaMetricsName.create("NO_INTERNET_CONNECTION_PROMPT_PLAYED");
        public static final AlexaMetricsName INTERNET_CONNECTION_LOST = AlexaMetricsName.create("INTERNET_CONNECTION_LOST_PROMPT_PLAYED");
        public static final AlexaMetricsName NETWORK_LOW_BANDWIDTH = AlexaMetricsName.create("NETWORK_LOW_BANDWIDTH_PROMPT_PLAYED");
        public static final AlexaMetricsName NETWORK_TRANSITION_AUTO = AlexaMetricsName.create("NETWORK_TRANSITION_AUTO_PROMPT_PLAYED");
        public static final AlexaMetricsName NETWORK_TRANSITION_NON_AUTO = AlexaMetricsName.create("NETWORK_TRANSITION_NON_AUTO_PROMPT_PLAYED");
        public static final AlexaMetricsName CONNECTIVITY_ISSUE_AUTO = AlexaMetricsName.create("CONNECTIVITY_ISSUE_AUTO_PROMPT_PLAYED");
        public static final AlexaMetricsName CONNECTIVITY_ISSUE_NON_AUTO = AlexaMetricsName.create("CONNECTIVITY_ISSUE_NON_AUTO_NON_AUTO_PROMPT_PLAYED");
        public static final AlexaMetricsName LOW_BANDWIDTH_VALUE = AlexaMetricsName.create("LOW_BANDWIDTH_VALUE");
    }

    /* loaded from: classes6.dex */
    public static final class OfflinePrompts {
        public static final AlexaMetricsName OFFLINE_PROMPT_DOWNLOAD_SUCCESS = AlexaMetricsName.create("OFFLINE_PROMPT_DOWNLOAD_SUCCESS");
        public static final AlexaMetricsName OFFLINE_PROMPT_DOWNLOAD_INTERRUPTED = AlexaMetricsName.create("OFFLINE_PROMPT_DOWNLOAD_INTERRUPTED");
        public static final AlexaMetricsName OFFLINE_PROMPT_DOWNLOAD_FAILURE = AlexaMetricsName.create("OFFLINE_PROMPT_DOWNLOAD_FAILURE");
    }

    /* loaded from: classes6.dex */
    public static final class Operational {
        public static final AlexaMetricsName UNSUPPORTED_DIRECTIVE_RECEIVED = AlexaMetricsName.create("UNSUPPORTED_DIRECTIVE_RECEIVED");
        public static final AlexaMetricsName SEND_ATTACHMENT_FAILURE = AlexaMetricsName.create("SEND_ATTACHMENT_FAILURE");
        public static final AlexaMetricsName AVS_REQUEST_ERROR = AlexaMetricsName.create("AVS_REQUEST_ERROR");
        public static final AlexaMetricsName DEPRECATED_API_USE = AlexaMetricsName.create("DEPRECATED_API_");
        public static final AlexaMetricsName ATTACHMENT_SIZE = AlexaMetricsName.create("ATTACHMENT_SIZE");
    }

    /* loaded from: classes6.dex */
    public static final class Pryon {
        public static final AlexaMetricsName PRYON_INITIALIZATION_SUCCESS = AlexaMetricsName.create("PRYON_INITIALIZATION_SUCCESS");
        public static final AlexaMetricsName PRYON_RESET = AlexaMetricsName.create("PRYON_RESET");
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE = AlexaMetricsName.create("PRYON_INITIALIZATION_FAILURE");
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_INVALID_CONFIG_PARAMETER = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "INVALID_CONFIG_PARAMETER"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_ALREADY_INITIALIZED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "ALREADY_INITIALIZED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_JNI_MEMORY_ALLOCATION_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "JNI_MEMORY_ALLOCATION_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_GLOBAL_REFERENCE_CREATION_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "GLOBAL_REFERENCE_CREATION_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_CLASS_PROPERTIES_QUERY_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "CLASS_PROPERTIES_QUERY_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_JAVA_CONFIG_PARAMETER_IMPORT_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "JAVA_CONFIG_PARAMETER_IMPORT_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_NATIVE_MEMORY_ALLOCATION_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "NATIVE_MEMORY_ALLOCATION_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_AUDIO_BUFFER_ALLOCATION_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "AUDIO_BUFFER_ALLOCATION_FAILED"));
        public static final AlexaMetricsName PRYON_INITIALIZATION_FAILURE_JNI_MEMORY_POINTER_SAVING_FAILED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(PRYON_INITIALIZATION_FAILURE, new StringBuilder(), ".", "JNI_MEMORY_POINTER_SAVING_FAILED"));
        public static final AlexaMetricsName PRYON_ERROR_EVENT = AlexaMetricsName.create("PRYON_ERROR_EVENT");
    }

    /* loaded from: classes6.dex */
    public static final class ReadinessTime {
        public static final AlexaMetricsName NOT_READY_TO_QUICK_READY = AlexaMetricsName.create("NOT_READY_TO_QUICK_READY");
        public static final AlexaMetricsName NOT_READY_TO_READY = AlexaMetricsName.create("NOT_READY_TO_READY");
        public static final AlexaMetricsName QUICK_READY_TO_READY = AlexaMetricsName.create("QUICK_READY_TO_READY");
    }

    /* loaded from: classes6.dex */
    public static final class SdkUsage {
        public static final AlexaMetricsName BIND_SERVICE_FAILED = AlexaMetricsName.create("BIND_SERVICE_FAILED");
        public static final AlexaMetricsName CLIENT_CONNECTED = AlexaMetricsName.create("CLIENT_CONNECTED");
        public static final AlexaMetricsName CLIENT_DISCONNECTED = AlexaMetricsName.create("CLIENT_DISCONNECTED");
        public static final AlexaMetricsName CLIENT_CONNECTION_LATENCY = AlexaMetricsName.create("CLIENT_CONNECTION_LATENCY");
        public static final AlexaMetricsName UNBIND_SERVICE_FAILED = AlexaMetricsName.create("UNBIND_SERVICE_FAILED");
        public static final AlexaMetricsName FOREGROUND_START_NOT_ALLOWED = AlexaMetricsName.create("FOREGROUND_START_NOT_ALLOWED");
        public static final AlexaMetricsName SERVICE_START_FAILED_REASON_PREFIX = AlexaMetricsName.create("SERVICE_START_FAILED_REASON_");

        /* loaded from: classes6.dex */
        public static final class ApiCalls {
            private static final AlexaMetricsName API_CALL = AlexaMetricsName.create("API_CALL").appendInjectable();

            /* loaded from: classes6.dex */
            public static final class Client {
                private static final AlexaMetricsName CLIENT = ApiCalls.API_CALL.appendToAlexaMetricsName("CLIENT");
                public static final AlexaMetricsName ATTEMPT = CLIENT.appendToAlexaMetricsName("ATTEMPT");
                public static final AlexaMetricsName SUCCESS = CLIENT.appendToAlexaMetricsName("SUCCESS");
                public static final AlexaMetricsName FAILURE = CLIENT.appendToAlexaMetricsName(Event.FAILURE);
            }

            /* loaded from: classes6.dex */
            public static final class Service {
                private static final AlexaMetricsName SERVICE = ApiCalls.API_CALL.appendToAlexaMetricsName("SERVICE");
                public static final AlexaMetricsName ATTEMPT = SERVICE.appendToAlexaMetricsName("ATTEMPT");
                public static final AlexaMetricsName SUCCESS = SERVICE.appendToAlexaMetricsName("SUCCESS");
                public static final AlexaMetricsName FAILURE = SERVICE.appendToAlexaMetricsName(Event.FAILURE);
                public static final AlexaMetricsName TEARDOWN = SERVICE.appendToAlexaMetricsName("TEARDOWN");
            }
        }
    }

    /* loaded from: classes6.dex */
    public static final class SearchUiEvents {
        public static final AlexaMetricsName VOX_SIMBA_ITEM_SELECTED = AlexaMetricsName.create("VOX_SIMBA_ITEM_SELECTED");
        public static final AlexaMetricsName VOX_SIMBA_PILL_SHOWN = AlexaMetricsName.create("VOX_SIMBA_PILL_SHOWN");
        public static final AlexaMetricsName VOX_TTA_LOCAL_PILL_SHOWN = AlexaMetricsName.create("VOX_TTA_LOCAL_PILL_SHOWN");
        public static final AlexaMetricsName VOX_TTA_LOCAL_PILL_SELECTED = AlexaMetricsName.create("VOX_TTA_LOCAL_PILL_SELECTED");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API");
        public static final AlexaMetricsName VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED = AlexaMetricsName.create("VOX_SIMBA_AVS_SPEAK_DIRECTIVE_RECEIVED");
        public static final AlexaMetricsName VOX_SIMBA_FRICTIVE_PROMPTS_API = AlexaMetricsName.create("VOX_SIMBA_FRICTIVE_PROMPTS_API");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API_CALL_COUNT = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API_CALL_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API_FAIL_COUNT = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API_FAIL_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API_SUCCESS_COUNT = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API_SUCCESS_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTIONS_CALL_COUNT = AlexaMetricsName.create("VOX_SIMBA_SUGGESTIONS_CALL_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTIONS_FAIL_COUNT = AlexaMetricsName.create("VOX_SIMBA_SUGGESTIONS_FAIL_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT = AlexaMetricsName.create("VOX_SIMBA_SUGGESTIONS_SUCCESS_COUNT");
        public static final AlexaMetricsName VOX_SIMBA_SWITCH_TO_VOICE = AlexaMetricsName.create("VOX_SIMBA_SWITCH_TO_VOICE");
        public static final AlexaMetricsName VOX_SIMBA_SHOW_MORE_TAPPED = AlexaMetricsName.create("VOX_SIMBA_SHOW_MORE_TAPPED");
        public static final AlexaMetricsName VOX_SIMBA_SHOW_MORE_DISPLAYED = AlexaMetricsName.create("VOX_SIMBA_SHOW_MORE_DISPLAYED");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTION_NOT_SELECTED = AlexaMetricsName.create("VOX_SIMBA_SUGGESTION_NOT_SELECTED");
        public static final AlexaMetricsName VOX_SIMBA_FRICTIVE_UTTERANCE = AlexaMetricsName.create("VOX_SIMBA_FRICTIVE_UTTERANCE");
        public static final AlexaMetricsName VOX_SIMBA_INCHAT_SHOWN = AlexaMetricsName.create("VOX_SIMBA_INCHAT_SHOWN");
        public static final AlexaMetricsName VOX_SIMBA_RESULT_SHOWN = AlexaMetricsName.create("VOX_SIMBA_RESULT_SHOWN");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTION_SHOWN = AlexaMetricsName.create("VOX_SIMBA_SUGGESTION_SHOWN");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTIONS_DISABLED_AND_CLEARED = AlexaMetricsName.create("VOX_SIMBA_SUGGESTIONS_DISABLED_AND_CLEARED");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API_CANCELLED = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API_CANCELLED");
        public static final AlexaMetricsName VOX_CHARS_BEFORE_SUGGESTIONS = AlexaMetricsName.create("VOX_CHARS_BEFORE_SUGGESTIONS");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTION_SELECTED_INDEX = AlexaMetricsName.create("VOX_SIMBA_SUGGESTION_SELECTED_INDEX");
        public static final AlexaMetricsName VOX_SIMBA_RESULT_SELECTED_INDEX = AlexaMetricsName.create("VOX_SIMBA_RESULT_SELECTED_INDEX");
        public static final AlexaMetricsName VOX_SIMBA_SEARCH_API_LATENCY = AlexaMetricsName.create("VOX_SIMBA_SEARCH_API_LATENCY");
        public static final AlexaMetricsName VOX_SIMBA_SUGGESTIONS_API_LATENCY = AlexaMetricsName.create("VOX_SIMBA_SUGGESTIONS_API_LATENCY");
        public static final AlexaMetricsName VOX_SIMBA_FRICTIVE_PROMPTS_API_LATENCY = AlexaMetricsName.create("VOX_SIMBA_FRICTIVE_PROMPTS_API_LATENCY");
    }

    /* loaded from: classes6.dex */
    public static final class SettingsUpdate {
        public static final AlexaMetricsName SEND_SUPPORTS_MOBILE_DOWNCHANNEL_SUCCEEDED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_SUPPORTS_MOBILE_DOWNCHANNEL_SUCCEEDED");
        public static final AlexaMetricsName SEND_SUPPORTS_MOBILE_DOWNCHANNEL_FAILED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_SUPPORTS_MOBILE_DOWNCHANNEL_FAILED");
        public static final AlexaMetricsName SEND_TIMEZONE_SUCCEEDED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_TIMEZONE_SUCCEEDED");
        public static final AlexaMetricsName SEND_TIMEZONE_FAILED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_TIMEZONE_FAILED");
        public static final AlexaMetricsName SEND_LOCALE_SUCCEEDED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_LOCALE_SUCCEEDED");
        public static final AlexaMetricsName SEND_LOCALE_FAILED = AlexaMetricsName.create("SETTINGS_UPDATE_SEND_LOCALE_FAILED");
    }

    /* loaded from: classes6.dex */
    public static final class SpeechMarks {
        public static final AlexaMetricsName TTS_UNSUPPORTED_MP3_FRAME = AlexaMetricsName.create("TTS_UNSUPPORTED_MP3_FRAME");
        public static final AlexaMetricsName TTS_PARSING_EXCEPTION = AlexaMetricsName.create("TTS_PARSING_EXCEPTION");
    }

    /* loaded from: classes6.dex */
    public static final class SynchronizeState {
        public static final AlexaMetricsName SYNCHRONIZE_STATE_FAILED = AlexaMetricsName.create("SYNCHRONIZE_STATE_FAILED");
        public static final AlexaMetricsName SYNCHRONIZE_STATE_FAILED_ON_DOWNCHANNEL_ESTABLISHMENT = AlexaMetricsName.create("SYNCHRONIZE_STATE_FAILED_ON_DOWNCHANNEL_ESTABLISHMENT");
    }

    /* loaded from: classes6.dex */
    public static final class TextInteraction {
        public static final AlexaMetricsName ATTEMPT = AlexaMetricsName.create("TEXT_INTERACTION_ATTEMPT");
        public static final AlexaMetricsName SUCCESS = AlexaMetricsName.create("TEXT_INTERACTION_SUCCESS");

        /* loaded from: classes6.dex */
        public static final class Abandoned {
            public static final AlexaMetricsName SCREEN_LOCKED = AlexaMetricsName.create("TEXT_INTERACTION_ABANDONED.SCREEN_LOCKED");
            public static final AlexaMetricsName SOURCE_ARBITRATION = AlexaMetricsName.create("TEXT_INTERACTION_ABANDONED.SOURCE_ARBITRATION");
            public static final AlexaMetricsName TEXT_TRANSFORMATION_FAILURE = AlexaMetricsName.create("TEXT_INTERACTION_ABANDONED.TEXT_TRANSFORMATION_FAILURE");
            public static final AlexaMetricsName OUT_OF_TURN = AlexaMetricsName.create("TEXT_INTERACTION_ABANDONED.OUT_OF_TURN");
            public static final AlexaMetricsName OUT_OF_TURN_CANNOT_REQUEST_DIALOG = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CANNOT_REQUEST_DIALOG"));
            public static final AlexaMetricsName OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CANNOT_REQUEST_FIRST_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "REQUEST_NOT_ALLOWED_TO_BARGE_IN"));
            public static final AlexaMetricsName OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_DIALOG_NOT_STARTED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "DIALOG_NOT_STARTED"));
            public static final AlexaMetricsName OUT_OF_TURN_DIALOG_NOT_CURRENT = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "DIALOG_NOT_CURRENT"));
            public static final AlexaMetricsName OUT_OF_TURN_UNEXPECTED_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "UNEXPECTED_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_UNEXPECTED_NEXT_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "UNEXPECTED_NEXT_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_START_DIALOG_NOT_ALLOWED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "START_DIALOG_NOT_ALLOWED"));
            public static final AlexaMetricsName OUT_OF_TURN_CONTINUE_INVALID_DIALOG = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CONTINUE_INVALID_DIALOG"));
        }

        /* loaded from: classes6.dex */
        public static final class Failure {
            public static final AlexaMetricsName BINDING_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.BINDING_ERROR");
            public static final AlexaMetricsName LOCAL_SERVICE_DISCONNECTED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.LOCAL_SERVICE_DISCONNECTED");
            public static final AlexaMetricsName LEADER_SELECTION_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.LEADER_SELECTION_ERROR");
            public static final AlexaMetricsName LEADER_DISABLED_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.LEADER_DISABLED");
            public static final AlexaMetricsName NETWORK_UNAVAILABLE = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.NETWORK_UNAVAILABLE");
            public static final AlexaMetricsName AVS_UNAVAILABLE_MISSING = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_UNAVAILABLE.MISSING");
            public static final AlexaMetricsName AVS_UNAVAILABLE_DOWNCHANNEL = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_UNAVAILABLE.DOWNCHANNEL");
            public static final AlexaMetricsName AVS_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_ERROR");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_UNINITIALIZED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.UNINITIALIZED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_UNAUTHORIZED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.UNAUTHORIZED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_NETWORK = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.NETWORK");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CONNECTION_NOT_ESTABLISHED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CONNECTION_NOT_CONNECTED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.DOWNCHANNEL_AVAILABLE");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CAPABILITY_PUBLISH");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.SYNCHRONIZE_STATE");
            public static final AlexaMetricsName TURN_TIMEOUT = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.TURN_TIMEOUT");
            public static final AlexaMetricsName NETWORK_REQUEST_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.NETWORK_REQUEST_ERROR");
            public static final AlexaMetricsName RESPONSE_PARSING_ERROR_MULTIPART = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.RESPONSE_PARSING_ERROR_MULTIPART");
            public static final AlexaMetricsName RESPONSE_PARSING_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.RESPONSE_PARSING_ERROR");
            public static final AlexaMetricsName REQUEST_PARSING_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.REQUEST_PARSING_ERROR");
            public static final AlexaMetricsName AUTHORIZATION_ERROR = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.AUTHORIZATION_ERROR");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_MESSAGING = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_MESSAGING");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED = AlexaMetricsName.create("TEXT_INTERACTION_FAILURE.CLIENT_TIMEOUT_EXCEEDED");
        }
    }

    /* loaded from: classes6.dex */
    public static final class TextUiEvents {
        public static final AlexaMetricsName VOX_TTA_LAUNCHED = AlexaMetricsName.create("VOX_TTA_LAUNCHED");
        public static final AlexaMetricsName VOX_TTA_CLOSED = AlexaMetricsName.create("VOX_TTA_CLOSED");
        public static final AlexaMetricsName VOX_TTA_MESSAGE_WELCOME_SHOWN = AlexaMetricsName.create("VOX_TTA_MESSAGE_WELCOME_SHOWN");
        public static final AlexaMetricsName VOX_TTA_MESSAGE_RETURNING_SHOWN = AlexaMetricsName.create("VOX_TTA_MESSAGE_RETURNING_SHOWN");
        public static final AlexaMetricsName VOX_TTA_MESSAGE_HISTORY_SHOWN = AlexaMetricsName.create("VOX_TTA_MESSAGE_HISTORY_SHOWN");
        public static final AlexaMetricsName VOX_TTA_MESSAGE_REFRESH_SHOWN = AlexaMetricsName.create("VOX_TTA_MESSAGE_REFRESH_SHOWN");
        public static final AlexaMetricsName VOX_TTA_DIALOG_SESSION_ENDED = AlexaMetricsName.create("VOX_TTA_DIALOG_SESSION_ENDED");
        public static final AlexaMetricsName VOX_TTA_CLEAR_SCREEN_ON_VOICE = AlexaMetricsName.create("VOX_TTA_CLEAR_SCREEN_ON_VOICE");
        public static final AlexaMetricsName VOX_TTA_IN_CHAT_LINK_GENERATED = AlexaMetricsName.create("VOX_TTA_IN_CHAT_LINK_GENERATED");
        public static final AlexaMetricsName VOX_TTA_IN_CHAT_LINK_TAPPED = AlexaMetricsName.create("VOX_TTA_IN_CHAT_LINK_TAPPED");
        public static final AlexaMetricsName VOX_TTA_SWITCHED_TO_SCRIM = AlexaMetricsName.create("VOX_TTA_SWITCHED_TO_SCRIM");
    }

    /* loaded from: classes6.dex */
    public static final class TextUiEventsLatency {
        public static final AlexaMetricsName VOX_TTA_VISIBLE_LATENCY = AlexaMetricsName.create("VOX_TTA_VISIBLE_LATENCY");
        public static final AlexaMetricsName VOX_TTA_RESPONSE_MESSAGE_RENDERED_LATENCY = AlexaMetricsName.create("VOX_TTA_RESPONSE_MESSAGE_RENDERED_LATENCY");
    }

    /* loaded from: classes6.dex */
    public static final class UiEvents {
        public static final AlexaMetricsName ATTENTION_SYSTEM_DISMISSED = AlexaMetricsName.create("ATTENTION_SYSTEM_DISMISSED");
        public static final AlexaMetricsName CARD_INGRESS_TAPPED = AlexaMetricsName.create("CARD_INGRESS_TAPPED");
        public static final AlexaMetricsName CARD_SHOWN = AlexaMetricsName.create("CARD_SHOWN");
        public static final AlexaMetricsName CARD_INTERACTED = AlexaMetricsName.create("CARD_INTERACTED");
        public static final AlexaMetricsName NAVIGATION_TO_EXTERNAL_LINK = AlexaMetricsName.create("NAVIGATION_TO_EXTERNAL_LINK");
        public static final AlexaMetricsName NAVIGATION_TO_INTERNAL_CARD = AlexaMetricsName.create("NAVIGATION_TO_INTERNAL_CARD");
        public static final AlexaMetricsName CARD_MATCHING_SUCCESS = AlexaMetricsName.create("CARD_MATCHING_SUCCESS");
        public static final AlexaMetricsName CARD_CONTROLLER_CREATION_ERROR = AlexaMetricsName.create("CARD_CONTROLLER_CREATION_ERROR");
        public static final AlexaMetricsName CARD_CREATION_ERROR = AlexaMetricsName.create("CARD_CREATION_ERROR");
        public static final AlexaMetricsName JSON_PARSING_VALID_JSON = AlexaMetricsName.create("JSON_PARSING_VALID_JSON");
        public static final AlexaMetricsName JSON_PARSING_ERROR = AlexaMetricsName.create("JSON_PARSING_ERROR");
        public static final AlexaMetricsName CARD_RENDER_SUCCESSFUL = AlexaMetricsName.create("CARD_RENDER_SUCCESSFUL");
    }

    /* loaded from: classes6.dex */
    public static final class UiEventsLatency {
        public static final AlexaMetricsName CARD_CREATION = AlexaMetricsName.create("CARD_CREATION_LATENCY");
        public static final AlexaMetricsName JSON_PARSING = AlexaMetricsName.create("JSON_PARSING_LATENCY");
        public static final AlexaMetricsName CARD_VIEWS_CREATED = AlexaMetricsName.create("CARD_VIEWS_CREATED_LATENCY");
        public static final AlexaMetricsName CARD_RENDER = AlexaMetricsName.create("CARD_RENDER_LATENCY");
    }

    /* loaded from: classes6.dex */
    public static final class UserInitiatedSpeechRequest {
        public static final AlexaMetricsName STARTED = AlexaMetricsName.create("USER_INITIATED_SPEECH_REQUEST_STARTED");
        public static final AlexaMetricsName FAILED = AlexaMetricsName.create("USER_INITIATED_SPEECH_REQUEST_FAILED");
    }

    /* loaded from: classes6.dex */
    public static final class VoiceInteraction {
        public static final AlexaMetricsName ATTEMPT = AlexaMetricsName.create("VOICE_INTERACTION_ATTEMPT");
        public static final AlexaMetricsName SUCCESS = AlexaMetricsName.create("VOICE_INTERACTION_SUCCESS");
        public static final AlexaMetricsName PROGRESS = AlexaMetricsName.create("VOICE_INTERACTION_PROGRESS");

        /* loaded from: classes6.dex */
        public static final class Abandoned {
            public static final AlexaMetricsName INVALID_AUDIO_METADATA = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.INVALID_AUDIO_METADATA");
            public static final AlexaMetricsName INVALID_WAKE_WORD = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.INVALID_WAKE_WORD");
            public static final AlexaMetricsName SCREEN_LOCKED = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.SCREEN_LOCKED");
            public static final AlexaMetricsName SOURCE_ARBITRATION = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.SOURCE_ARBITRATION");
            public static final AlexaMetricsName SPEECH_PROVIDER_NOT_REGISTERED = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.SPEECH_PROVIDER_NOT_REGISTERED");
            public static final AlexaMetricsName OUT_OF_TURN = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.OUT_OF_TURN");
            public static final AlexaMetricsName OUT_OF_TURN_CANNOT_REQUEST_DIALOG = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CANNOT_REQUEST_DIALOG"));
            public static final AlexaMetricsName OUT_OF_TURN_CANNOT_REQUEST_FIRST_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CANNOT_REQUEST_FIRST_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_BARGE_IN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "REQUEST_NOT_ALLOWED_TO_BARGE_IN"));
            public static final AlexaMetricsName OUT_OF_TURN_REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "REQUEST_NOT_ALLOWED_TO_START_FIRST_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_DIALOG_NOT_STARTED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "DIALOG_NOT_STARTED"));
            public static final AlexaMetricsName OUT_OF_TURN_DIALOG_NOT_CURRENT = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "DIALOG_NOT_CURRENT"));
            public static final AlexaMetricsName OUT_OF_TURN_UNEXPECTED_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "UNEXPECTED_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_UNEXPECTED_NEXT_TURN = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "UNEXPECTED_NEXT_TURN"));
            public static final AlexaMetricsName OUT_OF_TURN_START_DIALOG_NOT_ALLOWED = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "START_DIALOG_NOT_ALLOWED"));
            public static final AlexaMetricsName OUT_OF_TURN_CONTINUE_INVALID_DIALOG = AlexaMetricsName.create(GeneratedOutlineSupport1.outline35(OUT_OF_TURN, new StringBuilder(), ".", "CONTINUE_INVALID_DIALOG"));
            public static final AlexaMetricsName WAKE_WORD_ENGINE_NOT_READY = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.WAKE_WORD_ENGINE_NOT_READY");
            public static final AlexaMetricsName WAKE_WORD_AUDIO_INCOMPLETE = AlexaMetricsName.create("VOICE_INTERACTION_ABANDONED.WAKE_WORD_AUDIO_INCOMPLETE");
        }

        /* loaded from: classes6.dex */
        public static final class Cancelled {
            public static final AlexaMetricsName PAUSE_CONTROL = AlexaMetricsName.create("VOICE_INTERACTION_CANCELLED.PAUSE_CONTROL");
            public static final AlexaMetricsName CANCEL_USER_INTERACTION = AlexaMetricsName.create("VOICE_INTERACTION_CANCELLED.CANCEL_USER_INTERACTION");
        }

        /* loaded from: classes6.dex */
        public static final class Failure {
            public static final AlexaMetricsName BINDING_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.BINDING_ERROR");
            public static final AlexaMetricsName LOCAL_SERVICE_DISCONNECTED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.LOCAL_SERVICE_DISCONNECTED");
            public static final AlexaMetricsName LEADER_SELECTION_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.LEADER_SELECTION_ERROR");
            public static final AlexaMetricsName LEADER_DISABLED_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.LEADER_DISABLED");
            public static final AlexaMetricsName NETWORK_UNAVAILABLE = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.NETWORK_UNAVAILABLE");
            public static final AlexaMetricsName NETWORK_LOST = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.NETWORK_LOST");
            public static final AlexaMetricsName NETWORK_SWITCHED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.NETWORK_SWITCHED");
            public static final AlexaMetricsName AVS_UNAVAILABLE_MISSING = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_UNAVAILABLE.MISSING");
            public static final AlexaMetricsName AVS_UNAVAILABLE_DOWNCHANNEL = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_UNAVAILABLE.DOWNCHANNEL");
            public static final AlexaMetricsName AVS_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_ERROR");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_UNINITIALIZED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.UNINITIALIZED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_UNAUTHORIZED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.UNAUTHORIZED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_NETWORK = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.NETWORK");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_ESTABLISHED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CONNECTION_NOT_ESTABLISHED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CONNECTION_NOT_CONNECTED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CONNECTION_NOT_CONNECTED");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_DOWNCHANNEL_AVAILABLE = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.DOWNCHANNEL_AVAILABLE");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_CAPABILITY_PUBLISH = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.CAPABILITY_PUBLISH");
            public static final AlexaMetricsName AVS_CONNECTION_TIMEOUT_SYNCHRONIZE_STATE = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AVS_CONNECTION_TIMEOUT.SYNCHRONIZE_STATE");
            public static final AlexaMetricsName TURN_TIMEOUT = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.TURN_TIMEOUT");
            public static final AlexaMetricsName NETWORK_REQUEST_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.NETWORK_REQUEST_ERROR");
            public static final AlexaMetricsName START_RECORDING_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.START_RECORDING_ERROR");
            public static final AlexaMetricsName RESPONSE_PARSING_ERROR_MULTIPART = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RESPONSE_PARSING_ERROR_MULTIPART");
            public static final AlexaMetricsName RESPONSE_PARSING_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RESPONSE_PARSING_ERROR");
            public static final AlexaMetricsName REQUEST_PARSING_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.REQUEST_PARSING_ERROR");
            public static final AlexaMetricsName AUTHORIZATION_ERROR = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.AUTHORIZATION_ERROR");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_MESSAGE_TIMEOUT_EXCEEDED");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_CONCURRENT_REGISTER_ATTEMPT");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_INCOMPLETE_INTERACTION");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_UNKNOWN_FAILURE");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_MESSAGING = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_MESSAGING");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_CONCURRENT_WAKEWORD_VERIFICATION");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.INTERNAL_CLIENT_ERROR_WAKEWORD_VERIFICATION_BLOCKED");
            public static final AlexaMetricsName RECORDING_ERROR_AUDIO_RECORD_NOT_INITIALIZED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_AUDIO_RECORD_NOT_INITIALIZED");
            public static final AlexaMetricsName RECORDING_ERROR_FAILED_TO_START_RECORDING = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_FAILED_TO_START_RECORDING");
            public static final AlexaMetricsName RECORDING_ERROR_FAILED_TO_ACQUIRE_MIC = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_FAILED_TO_ACQUIRE_MIC");
            public static final AlexaMetricsName RECORDING_ERROR_START_TIMEOUT = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_START_TIMEOUT");
            public static final AlexaMetricsName RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_FAILED_TO_READ_FROM_AUDIO_RECORD");
            public static final AlexaMetricsName RECORDING_ERROR_IO_EXCEPTION = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_IO_EXCEPTION");
            public static final AlexaMetricsName RECORDING_ERROR_STOP_TIMEOUT = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_STOP_TIMEOUT");
            public static final AlexaMetricsName RECORDING_ERROR_STOPPED_THREAD = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_STOPPED_THREAD");
            public static final AlexaMetricsName RECORDING_ERROR_INITIALIZATION_EXCEPTION = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.RECORDING_ERROR_INITIALIZATION_EXCEPTION");
            public static final AlexaMetricsName INTERNAL_CLIENT_ERROR_CLIENT_TIMEOUT_EXCEEDED = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.CLIENT_TIMEOUT_EXCEEDED");
            public static final AlexaMetricsName CANNOT_EXPECT_SPEECH = AlexaMetricsName.create("VOICE_INTERACTION_FAILURE.CANNOT_EXPECT_SPEECH");
        }

        /* loaded from: classes6.dex */
        public static final class Recoverable {
            public static final AlexaMetricsName DOWNCHANNEL_CONNECTED = AlexaMetricsName.create("VOICE_INTERACTION_RECOVERABLE.DOWNCHANNEL_CONNECTED");
            public static final AlexaMetricsName DOWNCHANNEL_AVAILABLE = AlexaMetricsName.create("VOICE_INTERACTION_RECOVERABLE.DOWNCHANNEL_AVAILABLE");
        }
    }

    /* loaded from: classes6.dex */
    public static final class WakeWordModel {
        public static final AlexaMetricsName WAKE_WORD_MODEL_DOWNLOAD_SUCCESS = AlexaMetricsName.create("WAKE_WORD_MODEL_DOWNLOAD_SUCCESS");
        public static final AlexaMetricsName WAKE_WORD_MODEL_DOWNLOAD_INTERRUPTED = AlexaMetricsName.create("WAKE_WORD_MODEL_DOWNLOAD_INTERRUPTED");
        public static final AlexaMetricsName WAKE_WORD_MODEL_DOWNLOAD_FAILURE = AlexaMetricsName.create("WAKE_WORD_MODEL_DOWNLOAD_FAILURE");
        public static final AlexaMetricsName WAKE_WORD_MODEL_LOCALE_MISMATCH = AlexaMetricsName.create("WAKE_WORD_MODEL_LOCALE_MISMATCH");
    }

    /* loaded from: classes6.dex */
    public static final class WakeWordValidation {
        public static final AlexaMetricsName WAKE_WORD_VALIDATION_TIMEOUT = AlexaMetricsName.create("WAKE_WORD_VALIDATION_TIMEOUT");
        public static final AlexaMetricsName WAKE_WORD_VALIDATION_LATENCY = AlexaMetricsName.create("WAKE_WORD_VALIDATION_LATENCY");
        public static final AlexaMetricsName WAKE_WORD_VALIDATION_SPEECH_OFFSET = AlexaMetricsName.create("WAKE_WORD_VALIDATION_SPEECH_OFFSET");
    }

    private AlexaMetricsName(String str) {
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AlexaMetricsName create(String str) {
        return new AlexaMetricsName(str);
    }

    public AlexaMetricsName appendInjectable() {
        return appendToAlexaMetricsName("%s");
    }

    public AlexaMetricsName appendToAlexaMetricsName(@Nullable String str) {
        return create(GeneratedOutlineSupport1.outline92(new StringBuilder(), this.value, ".", str));
    }

    public String appendWith(@Nullable String str) {
        return GeneratedOutlineSupport1.outline92(new StringBuilder(), this.value, ".", str);
    }

    public String getValue() {
        return this.value;
    }

    public AlexaMetricsName injectWith(@NonNull String str) {
        return create(String.format(java.util.Locale.US, this.value, str));
    }

    public String postfix(@Nullable String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.value, str);
    }

    public String toString() {
        return getValue();
    }
}

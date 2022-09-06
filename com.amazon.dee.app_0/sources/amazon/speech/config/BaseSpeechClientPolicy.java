package amazon.speech.config;

import amazon.speech.config.AlexaDiscoveryPolicy;
import amazon.speech.config.util.NamespaceNameIdentifier;
import amazon.speech.util.DebugUtil;
import amazon.speech.util.StateDumper;
import amazon.speech.util.SystemPropertiesHelper;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.util.Log;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public abstract class BaseSpeechClientPolicy implements StateDumper {
    private static final String ACL_AVAILABLE = "acl_available";
    private static final String ACL_AVAILABLE_DEFAULT = "use_acl_default";
    private static final String ACL_HYBRIDROUTER_AVAILABLE = "acl_hybridrouter_available";
    private static final String ACL_HYBRIDROUTER_AVAILABLE_DEFAULT = "use_acl_hybridrouter_default";
    private static final String ACL_HYBRID_ROUTER_CONFIGURATION = "acl_hybrid_router_configuration";
    private static final String ACL_HYBRID_ROUTER_CONFIGURATION_DEFAULT = "";
    private static final String ALTERNATE_UPL_METRIC_PROGRAM_NAME = "alternative_upl_metrics_program_name";
    private static final long ARCUS_INTERVAL_MS = 86400000;
    private static final String AUDIORECORD_FRAME_COUNT = "audiorecord_frame_count";
    private static final String AUDIO_BROKER_CPU_BOOST_DURATION_MS = "audio_broker_cpu_boost_duration_ms";
    private static final String BACKGROUND_SOUND_HANDLE = "background_sound_handle";
    private static final String BACKLOG_HIGH_WATERMARK = "backlog_high_watermark";
    private static final String BACKLOG_LOW_WATERMARK = "backlog_low_watermark";
    private static final String CELEBRITY_WW_CONFIG = "celebrity_ww_config";
    private static final String CONNECT_ON_FIRST_INVOCATION = "connect_on_first_invocation";
    private static final String CONNECT_ON_UNLOCK_WITH_LOW_WIFI = "connect_on_unlocked_with_low_wifi";
    private static final String CONTINUE_SPEECH_SESSION_ON_CANCEL = "continue_speech_session_on_cancel";
    private static final String DEBUG_POLICY_PATH = "/sdcard/csm/policy.json";
    private static final String DEFAULT_AUDIO_INITIATOR = "default_audio_initiator";
    private static final String DEFAULT_CAPTURE_AUDIO_STREAM = "default_capture_audio_streams";
    private static final String DEFAULT_SETTINGS_FACTORY = "amazon.speech.sim.settings.SettingsFactoryBase";
    private static final String DEVICE_FAMILY = "device_family";
    private static final int DEVICE_FAMILY_DEFAULT = 999;
    private static final String DEVICE_MODE_PROVIDER = "device_mode_provider";
    private static final String DEVICE_REQUIRES_DATA_MIGRATION = "device_requires_data_migration";
    private static final String DISABLE_ALEXA_ON_DEREGISTRATION = "disable_alexa_on_deregistration";
    private static final String DISABLE_WAKEWORD_AT_OOBE = "disable_wakeword_at_oobe";
    private static final String ECHO_SPATIAL_AWARENESS = "echo_spatial_awareness";
    private static final String ENABLE_ASPS = "enable_asps";
    private static final String ENABLE_CPU_HEALTH_METRICS = "enable_cpu_health_metrics";
    private static final String ENABLE_DATASTREAMPROVIDER = "enable_data_stream_provider";
    private static final String ENABLE_LOCALE_SPECIFIC_WW = "enable_locale_specific_ww";
    private static final String ENABLE_MEMORY_HEALTH_METRICS = "enable_memory_health_metrics";
    private static final String ENABLE_MEMORY_PINNING = "enable_memory_pinning";
    private static final String ENABLE_MULTICHANNEL_AUDIO = "enable_multichannel_audio";
    private static final String ENABLE_OLD_WAKEWORD_DIRECTORY_CLEAN_UP = "enable_old_wakeword_directory_cleanup";
    private static final String ENABLE_SIM_HEARTBEAT = "scl_hearbeat_enable";
    private static final String ENABLE_SPC = "enable_spc";
    private static final String ENABLE_WAKEWORD_DOWNLOAD = "enable_wakeword_download";
    private static final boolean ENFORCES_CAPABILITY_PERMISSION_DEFAULT = false;
    private static final String FEATURE_CSM_DEBUG_MODE = "amazon.speech.csm_debug_mode";
    private static final String HOLD_WAKE_LOCK = "hold_wake_lock";
    private static final String HOLD_WAKE_LOCK_TILL_ALEXA_INTERACTION = "hold_wake_lock_till_alexa_interaction";
    private static final String INACTIVITY_TIMEOUT = "inactive_timeout_ms";
    private static final int LISTEN_DELAY_DEFAULT_MS = 200;
    private static final int LISTEN_DELAY_UNSET = -1;
    private static final String METRIC_PROGRAM_NAME = "metric_program_name";
    private static final String MULTICHANNEL_CONFIG = "multichannel_config";
    private static final String MULTICHANNEL_CONFIG_DEFAULT = "mic_channels=[0,3];ASR_out=true;ref_out=0";
    private static final String MULTICHANNEL_COUNT = "multichannel_channel_count";
    private static final int MULTICHANNEL_COUNT_DEFAULT = 3;
    private static final String NOTIFY_ASP_ON_COMMS_MESSAGE = "notify_asp_on_comms_message";
    private static final String OBSERVE_THERMAL_MITIGATION_INTENTS_FOR_SECONDARY_WW = "observe_thermal_mitigation_intents_for_secondary_ww";
    private static final String POLICY_FILE_NAME = "policy.json";
    private static final String PRYON_DECODER_PRIORITY = "pryon_decoder_priority";
    private static final String PRYON_HIGH_PRIORITY = "pryon_high_priority";
    private static final String REPORT_UPL_IN_SIM = "report_upl_sim";
    private static final String REUSE_SHARED_AUDIO_STREAM = "reuse_shared_audio_stream";
    private static final String SECURE_SETTING_PRIVACY_MODE = "secure_setting_privacy_mode";
    private static final String SEND_AND_OBSERVE_APPSTORE_CONTEXT = "send_and_observe_appstore_context";
    private static final String SEND_DEVICE_PREFERENCES_CONTEXT = "send_device_preferences_context";
    private static final String SEND_ERROR_ON_DEREGISTRATION = "send_error_on_deregistration";
    private static final String SEND_LOCKSCREEN_CONTEXT = "send_lockscreen_context";
    private static final String SERVER_ENDPOINT_CUTOFF = "server_endpoint_cutoff";
    private static final int SERVER_ENDPOINT_CUTOFF_DEFAULT = 60000;
    private static final String SETTINGS_FACTORY = "settings_factory";
    private static final String SHOULD_HANDLE_LOCALE_CHANGE = "should_handle_locale_change";
    private static final String SIM_ENABLE_SPEECH_MARK_SUPPRESSION = "sim_enable_speech_mark_suppression";
    private static final String SPEECHUI_ACTIVITY_ON_LOCKSCREEN = "speechui_activity_on_lockscreen";
    private static final String SUPPORTED_WAKE_WORD_MODELS = "supported_wake_word_models";
    private static final String SUPPORTS_ADAPTIVE_VOLUME = "supports_adaptive_volume";
    private static final String SUPPORTS_AGENT_CONFIG = "supports_agent_config";
    private static final String SUPPORTS_ANONYMOUS_MODE = "supports_anonymous_mode";
    private static final boolean SUPPORTS_CAPABILITY_AVAILABILITY_DEFAULT = false;
    private static final boolean SUPPORTS_CAPABILITY_DEFAULT = false;
    private static final boolean SUPPORTS_CAPABILITY_OVERRIDE_DEFAULT = false;
    private static final String SUPPORTS_HYBRID_ALEXA_FOCUS_MODEL = "supports_hybrid_alexa_focus_model";
    private static final String SUPPORTS_LEGAL_TERMS = "supports_legal_terms";
    private static final String SUPPORTS_MULTI_TURN_PROMPT = "supports_multi_turn_prompt";
    private static final String SUPPORTS_PROJECT_VZ_ASR_PROFILE = "supports_project_vz_asr_profile";
    private static final String SUPPORTS_TCP_METRICS = "supports_tcp_metrics";
    private static final String SUPPORTS_WW_WHEN_ALEXA_DISABLED = "ww_supports_alexa_disabled";
    private static final String SUPPORT_BETA_TESTING = "support_beta_testing";
    private static final String SUPPORT_DCF_PUBLISH_API = "support_dcf_publish_api";
    private static final String SUPPORT_DEFAULT_GO_BACK = "support_default_go_back";
    private static final String SUPPORT_DOWN_CHANNEL = "support_down_channel";
    private static final String SUPPORT_ETHERNET = "support_ethernet";
    private static final String SUPPORT_GEOLOCATION = "support_geolocation";
    private static final String SUPPORT_LOCAL_SEARCH_CONTEXT = "support_local_search_context";
    private static final String SUPPORT_NATIVE_WAKE_WORD_SERVICE_CORE = "support_native_wake_word_service_core";
    private static final String SUPPORT_OOBE = "support_oobe";
    private static final String SUPPORT_PARENTAL_CONTROLS = "support_parental_controls";
    private static final String SUPPORT_RESEND_AUDIO_AFTER_UNLOCK = "support_resend_audio_after_unlock";
    private static final String SUPPORT_TRUSTED_WIFI = "support_trusted_wifi";
    private static final int SYSTEM_PROPERTY_INT_UNSET = -1;
    private static final String TOGGLE_WAKEWORD_WITH_SCREEN = "toggle_wakeword_with_screen";
    private static final String TUTORIAL_ACTION_BEGIN = "tutorial_action_begin";
    private static final String TUTORIAL_ACTION_END = "tutorial_action_end";
    private static final String UPDATE_SETTINGS_ALEXA_SERVCIE = "update_locale_alexa_service";
    private static final String USE_APP_PRIVATE_STORAGE_FOR_DAVS = "use_app_private_storage_for_davs";
    private static final String USE_ENGLISH_LOCALE_IN_ASR = "use_english_locale_in_asr";
    private static final String USE_MODESWITCHUTIL = "use_modeswitchutil";
    private static final String USE_POWER_MANAGER_INACTIVITY = "use_power_manager_inactivity";
    private static final String USE_VSUPERBOWL = "use_vsuperbowl";
    private static final String WAKEUP_SCREEN_WHILE_LISTENING = "wakeup_screen_while_listening";
    private static final String WW_ENABLE_PLAYBACK_DETECTION = "ww_enable_playback_detection";
    private static final String WW_ENABLE_SPEECH_MARKS_SUPPRESSION = "ww_enable_speech_mark_suppression";
    private static final String WW_FEDERATED_LEARNING_ENABLED = "ww_federated_learning_enabled";
    private static final boolean WW_FEDERATED_LEARNING_ENABLED_DEFAULT = false;
    private static final String WW_ON_LOCKSCREEN = "ww_on_lockscreen";
    private static final String WW_SUPPRESS_PLAYBACK_AT_OOBE = "ww_suppress_playback_detector_at_oobe";
    private final boolean isShipMode;
    private final Context mContext;
    private JSONObject mPolicyJson;
    private String mPolicyStr;
    private int mSoundPolicy = -1;
    protected static final boolean DEBUG = DebugUtil.getShouldDebug(DebugUtil.Module.CONF);
    private static final String TAG = DebugUtil.getTag(DebugUtil.Module.CONF, BaseSpeechClientPolicy.class);
    private static final String LISTEN_DELAY_SYS_PROP = "persist.amazon.sim.listen.delay";
    private static final int sListenDelay = new SystemPropertiesHelper().getInt(LISTEN_DELAY_SYS_PROP, -1);
    private static final String SUPPORTS_CAPABILITY_SYS_PROP = "persist.csm.capability.support";
    private static final int sSupportsCapabilities = new SystemPropertiesHelper().getInt(SUPPORTS_CAPABILITY_SYS_PROP, -1);
    private static final String SUPPORTS_CAPABILITY_OVERRIDE_SYS_PROP = "persist.csm.capability.override";
    private static final int sSupportsCapabilityOverride = new SystemPropertiesHelper().getInt(SUPPORTS_CAPABILITY_OVERRIDE_SYS_PROP, -1);
    private static final String SUPPORTS_CAPABILITY_AVAILABILITY_SYS_PROP = "persist.csm.capability.supavail";
    private static final int sSupportsCapabilityAvailabilityConditional = new SystemPropertiesHelper().getInt(SUPPORTS_CAPABILITY_AVAILABILITY_SYS_PROP, -1);
    private static final String ENFORCES_CAPABILITY_PERMISSION_SYS_PROP = "persist.csm.capability.perm";
    private static final int sEnforcesCapabilityPermission = new SystemPropertiesHelper().getInt(ENFORCES_CAPABILITY_PERMISSION_SYS_PROP, -1);

    /* loaded from: classes.dex */
    public class WakeWordRuntime {

        /* loaded from: classes.dex */
        public class DefaultAudioSource {
            public static final int HOTWORD = 1999;

            public DefaultAudioSource() {
            }
        }

        /* loaded from: classes.dex */
        public class Platform {
            public static final int AOSP = 1;
            public static final int FIREOS = 0;

            public Platform() {
            }
        }

        public WakeWordRuntime() {
        }
    }

    protected BaseSpeechClientPolicy(Context context) {
        InputStream open;
        if (DEBUG) {
            Log.i(TAG, "Creating");
        }
        this.mContext = context;
        this.isShipMode = !this.mContext.getPackageManager().hasSystemFeature(FEATURE_CSM_DEBUG_MODE);
        if (DEBUG) {
            GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("Build type:"), Build.TYPE, TAG);
        }
        if (!Build.TYPE.equals("user") && debugPolicyExists()) {
            try {
                if (DEBUG) {
                    Log.i(TAG, "Opening policy file from /sdcard/csm/policy.json");
                }
                open = new FileInputStream(DEBUG_POLICY_PATH);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "Failed to load debug policy: shouldn't be here", e);
                return;
            } catch (SecurityException e2) {
                Log.e(TAG, "Failed to load debug policy", e2);
                return;
            }
        } else {
            AssetManager assets = this.mContext.getAssets();
            try {
                if (DEBUG) {
                    Log.i(TAG, "Opening policy file from the asset");
                }
                open = assets.open(POLICY_FILE_NAME);
            } catch (IOException e3) {
                Log.e(TAG, "Failed to load configured policy: ", e3);
                return;
            }
        }
        loadPolicy(open);
    }

    private boolean debugPolicyExists() {
        boolean exists = new File(DEBUG_POLICY_PATH).exists();
        if (DEBUG) {
            GeneratedOutlineSupport1.outline173("debugPolicyExists: ", exists, TAG);
        }
        return exists;
    }

    private boolean getDefaultSupportsArcusService() {
        return false;
    }

    private Set<String> getDefaultWakewordModels() {
        return new HashSet(Arrays.asList("alexa"));
    }

    private String getNameForPolicy(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "error" : "AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE" : "AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK" : "AUDIOFOCUS_GAIN_TRANSIENT" : "AUDIOFOCUS_GAIN";
    }

    public boolean allowExclusiveStreams() {
        return true;
    }

    public boolean doesSupportGeolocation() {
        return getPolicyBoolean(SUPPORT_GEOLOCATION, false);
    }

    @Override // amazon.speech.util.StateDumper
    public void dump(PrintWriter printWriter) {
        printWriter.println("Common Speech Middleware Policy:");
        try {
            if (this.mPolicyJson != null) {
                printWriter.println(this.mPolicyJson.toString(4));
            } else {
                printWriter.println(this.mPolicyStr);
            }
        } catch (JSONException unused) {
            printWriter.println(this.mPolicyStr);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("background sound policy = ");
        outline107.append(getNameForPolicy(getBackgroundSoundPolicy()));
        printWriter.println(outline107.toString());
    }

    @Override // amazon.speech.util.StateDumper
    public void dumpSecurely(PrintWriter printWriter) {
    }

    public boolean enableMultipleProfiles() {
        return true;
    }

    public boolean getACLAvailableDefault() {
        return getPolicyBoolean(ACL_AVAILABLE_DEFAULT, false);
    }

    public String getACLHybridRouterConfiguration() {
        return getPolicyString(ACL_HYBRID_ROUTER_CONFIGURATION, "");
    }

    public boolean getACLHybridRouterDefault() {
        return getPolicyBoolean(ACL_HYBRIDROUTER_AVAILABLE_DEFAULT, false);
    }

    public String getAlternateMetricsUplProgramName() {
        return getPolicyString(ALTERNATE_UPL_METRIC_PROGRAM_NAME, null);
    }

    public String getAppConfigId() {
        return null;
    }

    public long getArcusSyncIntervalMillis() {
        return 86400000L;
    }

    public int getAudioBrokerCpuBoostDurationMs() {
        return getPolicyInt(AUDIO_BROKER_CPU_BOOST_DURATION_MS, 15000);
    }

    public int getAudioRecordFrameCount() {
        return getPolicyInt(AUDIORECORD_FRAME_COUNT, -1);
    }

    public int getBackgroundSoundPolicy() {
        int i;
        int i2 = this.mSoundPolicy;
        if (i2 != -1) {
            return i2;
        }
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            Log.w(TAG, "shouldHoldWakelock: No configured policy is loaded!!!");
            return 3;
        }
        try {
            i = jSONObject.getInt(BACKGROUND_SOUND_HANDLE);
        } catch (JSONException unused) {
            boolean z = DEBUG;
            i = 3;
        }
        if (i != 3 && i != 2 && i != 4 && i != 1) {
            throw new AssertionError("Not a valid background sound policy. Should match the values supported in durationHint for AudioManager#requestAudioFocus");
        }
        if (DEBUG) {
            Log.i(TAG, "background sound handle[" + i + "]");
        }
        this.mSoundPolicy = i;
        return i;
    }

    public int getBacklogHighWaterMark() {
        return getPolicyInt(BACKLOG_HIGH_WATERMARK, 500);
    }

    public int getBacklogLowWaterMark() {
        return getPolicyInt(BACKLOG_LOW_WATERMARK, 250);
    }

    public Set<String> getBackwardsCompatibleInterfaces() {
        return new HashSet();
    }

    public DataStreamFactoryPolicy getDataStreamFactoryPolicy() {
        return DataStreamFactoryPolicy.AUDIO_STREAM;
    }

    public String getDefaultAudioInitiatorPolicy() {
        return getPolicyString(DEFAULT_AUDIO_INITIATOR, "TTS_PROMPT");
    }

    public long getDefaultCaptureAudioStream() {
        return getPolicyLong(DEFAULT_CAPTURE_AUDIO_STREAM, 1999L);
    }

    public String getDefaultConfigurationName() {
        return null;
    }

    protected boolean getDefaultEnforcesCapabilityPermission() {
        return false;
    }

    protected int getDefaultListenDelay() {
        return 200;
    }

    protected boolean getDefaultSupportsCapabilityAvailabilityConditional() {
        return false;
    }

    @Deprecated
    protected boolean getDefaultSupportsCapabilityOverride() {
        return false;
    }

    protected boolean getDefaultSupportsCapabilityPublish() {
        return false;
    }

    public int getDeviceFamily() {
        return getPolicyInt(DEVICE_FAMILY, 999);
    }

    public String getDeviceModeProviderName() {
        return getPolicyString(DEVICE_MODE_PROVIDER, "multi_modal");
    }

    public Map<String, String> getDeviceSpecificArcusAttributes() {
        return new HashMap();
    }

    public final Set<String> getDeviceSpecificWakeWordModels() {
        JSONArray policyArray = getPolicyArray(SUPPORTED_WAKE_WORD_MODELS, null);
        if (policyArray == null) {
            Log.wtf(TAG, "no supported wakemodels found, returning default");
            return getDefaultWakewordModels();
        }
        HashSet hashSet = new HashSet();
        int length = policyArray.length();
        if (DEBUG) {
            String str = "getDeviceSpecificWakeWordModels(COUNT) | length: " + length;
            String str2 = "getDeviceSpecificWakeWordModels(CONTENT) | supportedModels(JSON): " + policyArray;
        }
        for (int i = 0; i < length; i++) {
            try {
                String string = policyArray.getString(i);
                if (string != null && !string.isEmpty()) {
                    hashSet.add(string);
                    if (DEBUG) {
                        String str3 = "getDeviceSpecificWakeWordModels(ADD) | added: " + string;
                    }
                }
            } catch (JSONException unused) {
                Log.e(TAG, "no key found at ix: " + i);
            }
        }
        if (hashSet.size() != 0) {
            return hashSet;
        }
        Log.wtf(TAG, "no supported wakemodels found in array, returning default");
        return getDefaultWakewordModels();
    }

    public Set<NamespaceNameIdentifier> getDirectiveExemptionListForIgnoreWhileConnected() {
        HashSet hashSet = new HashSet();
        hashSet.add(new NamespaceNameIdentifier("Alexa.ProactiveStateReporter", "SetReportPolicy"));
        hashSet.add(new NamespaceNameIdentifier(CallProvider.Alexa, "EventProcessed"));
        return hashSet;
    }

    public AlexaDiscoveryPolicy getDiscoveryPolicy(Context context) {
        return new AlexaDiscoveryPolicy.Builder().setManufacturerName("amazon").setLocalizedDeviceDescription(getLocalizedDeviceDescription()).build();
    }

    public long getInactivityTimeout() {
        JSONObject jSONObject = this.mPolicyJson;
        long j = -1;
        if (jSONObject == null) {
            Log.w(TAG, "shouldHoldWakelock: No configured policy is loaded!!!");
            return -1L;
        }
        try {
            j = jSONObject.getLong(INACTIVITY_TIMEOUT);
        } catch (JSONException unused) {
            boolean z = DEBUG;
        }
        if (DEBUG) {
            String str = TAG;
            Log.i(str, "inactive timeout[" + j + "]");
        }
        return j;
    }

    public Map<String, Object> getLegacyCapabilities() {
        return new HashMap();
    }

    public int getListenDelay() {
        int i = sListenDelay;
        return i == -1 ? getDefaultListenDelay() : i;
    }

    public String getLocalizedDeviceDescription() {
        return "Amazon Alexa";
    }

    public Map<String, String> getMetricMetadata() {
        return Collections.emptyMap();
    }

    public String getMetricProgramName() {
        return getPolicyString(METRIC_PROGRAM_NAME, "Knight_Speech");
    }

    public int getMultichannelChannelCount() {
        return getPolicyInt(MULTICHANNEL_COUNT, 3);
    }

    public String getMultichannelConfig() {
        return getPolicyString(MULTICHANNEL_CONFIG, MULTICHANNEL_CONFIG_DEFAULT);
    }

    public Set<String> getPersistentDeviceContextNamespaceWhitelist() {
        HashSet hashSet = new HashSet();
        hashSet.add("System");
        return hashSet;
    }

    JSONArray getPolicyArray(String str, JSONArray jSONArray) {
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            Log.w(TAG, "No configured policy is loaded for lookup of key [" + str + "], return default value");
            return jSONArray;
        }
        try {
            if (jSONObject.has(str)) {
                jSONArray = this.mPolicyJson.getJSONArray(str);
            } else if (DEBUG) {
                Log.w(TAG, "Policy entry for [" + str + "] not found, return default value");
            }
        } catch (JSONException e) {
            Log.e(TAG, "Couldn't get policy key [" + str + "], return default value", e);
        }
        if (DEBUG) {
            String str2 = "[" + str + "]=" + jSONArray;
        }
        return jSONArray;
    }

    boolean getPolicyBoolean(String str, boolean z) {
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            String str2 = TAG;
            Log.w(str2, "No configured policy is loaded for lookup of key [" + str + "], return default value");
            return z;
        }
        try {
            if (jSONObject.has(str)) {
                z = this.mPolicyJson.getBoolean(str);
            } else if (DEBUG) {
                String str3 = TAG;
                Log.w(str3, "Policy entry for [" + str + "] not found, return default value");
            }
        } catch (JSONException e) {
            String str4 = TAG;
            Log.e(str4, "Couldn't get policy key [" + str + "], return default value", e);
        }
        if (DEBUG) {
            String str5 = TAG;
            Log.i(str5, "[" + str + "]=" + z);
        }
        return z;
    }

    int getPolicyInt(String str, int i) {
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            String str2 = TAG;
            Log.w(str2, "No configured policy is loaded for lookup of key [" + str + "], return default value");
            return i;
        }
        try {
            if (jSONObject.has(str)) {
                i = this.mPolicyJson.getInt(str);
            } else if (DEBUG) {
                String str3 = TAG;
                Log.w(str3, "Policy entry for [" + str + "] not found, return default value");
            }
        } catch (JSONException e) {
            String str4 = TAG;
            Log.e(str4, "Couldn't get policy key [" + str + "], return default value", e);
        }
        if (DEBUG) {
            String str5 = TAG;
            Log.i(str5, "[" + str + "]=" + i);
        }
        return i;
    }

    long getPolicyLong(String str, long j) {
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            String str2 = TAG;
            Log.w(str2, "No configured policy is loaded for lookup of key [" + str + "], return default value");
            return j;
        }
        try {
            if (jSONObject.has(str)) {
                j = this.mPolicyJson.getLong(str);
            } else if (DEBUG) {
                String str3 = TAG;
                Log.w(str3, "Policy entry for [" + str + "] not found, return default value");
            }
        } catch (JSONException e) {
            String str4 = TAG;
            Log.e(str4, "Couldn't get policy key [" + str + "], return default value", e);
        }
        if (DEBUG) {
            String str5 = TAG;
            Log.i(str5, "[" + str + "]=" + j);
        }
        return j;
    }

    String getPolicyString(String str, String str2) {
        JSONObject jSONObject = this.mPolicyJson;
        if (jSONObject == null) {
            String str3 = TAG;
            Log.w(str3, "No configured policy is loaded for lookup of key [" + str + "], return default value");
            return str2;
        }
        try {
            if (jSONObject.has(str)) {
                str2 = this.mPolicyJson.getString(str);
            } else if (DEBUG) {
                String str4 = TAG;
                Log.w(str4, "Policy entry for [" + str + "] not found, return default value");
            }
        } catch (JSONException e) {
            String str5 = TAG;
            Log.e(str5, "Couldn't get policy key [" + str + "], return default value", e);
        }
        if (DEBUG) {
            String str6 = TAG;
            Log.i(str6, "[" + str + "]=" + str2);
        }
        return str2;
    }

    public ProactiveReportingPolicy getProactiveReportingPolicy() {
        return ProactiveReportingPolicy.DEFAULT;
    }

    public int getPryonDecoderPriority() {
        return getPolicyInt(PRYON_DECODER_PRIORITY, 10);
    }

    public int getPryonHighPriority() {
        return getPolicyInt(PRYON_HIGH_PRIORITY, -10);
    }

    public int getServerEndpointCutoffMs() {
        return getPolicyInt(SERVER_ENDPOINT_CUTOFF, 60000);
    }

    public String getSettingsFactory() {
        return getPolicyString(SETTINGS_FACTORY, DEFAULT_SETTINGS_FACTORY);
    }

    public Map<String, Double> getStaticallyConfiguredInterfaces() {
        return new HashMap();
    }

    public StreamProviderFactoryPolicy getStreamProviderFactoryPolicy() {
        return StreamProviderFactoryPolicy.AUDIO_STREAM;
    }

    public final boolean getSupportsCapabilityAvailabilityConditional() {
        int i = sSupportsCapabilityAvailabilityConditional;
        if (i == -1) {
            return getDefaultSupportsCapabilityAvailabilityConditional();
        }
        return i == 1;
    }

    public final boolean getSupportsCapabilityPublish() {
        int i = sSupportsCapabilities;
        if (i == -1) {
            return getDefaultSupportsCapabilityPublish();
        }
        return i == 1;
    }

    public String getTutorialActionBegin() {
        return getPolicyString(TUTORIAL_ACTION_BEGIN, null);
    }

    public String getTutorialActionEnd() {
        return getPolicyString(TUTORIAL_ACTION_END, null);
    }

    public int getWakeWordDefaultAudioSource() {
        return 1999;
    }

    public WakeWordPolicy getWakeWordPolicy() {
        return null;
    }

    public int getWakeWordTargetPlatform() {
        return 0;
    }

    public int heartbeatAlarmType() {
        return 2;
    }

    public boolean isACLAvailable() {
        return getPolicyBoolean(ACL_AVAILABLE, false);
    }

    public boolean isACLHybridRouterAvailable() {
        return getPolicyBoolean(ACL_HYBRIDROUTER_AVAILABLE, false);
    }

    public boolean isCPUHealthMetricsEnabled() {
        return getPolicyBoolean(ENABLE_CPU_HEALTH_METRICS, false);
    }

    public boolean isCelebrityWakeWordEnabled() {
        return getPolicyBoolean(CELEBRITY_WW_CONFIG, false);
    }

    public boolean isEchoSpatialAwarenessSupported() {
        return getPolicyBoolean(ECHO_SPATIAL_AWARENESS, false);
    }

    public boolean isEthernetSupported() {
        return getPolicyBoolean(SUPPORT_ETHERNET, false);
    }

    public boolean isMemoryHealthMetricsEnabled() {
        return getPolicyBoolean(ENABLE_MEMORY_HEALTH_METRICS, false);
    }

    public boolean isMemoryPinningEnabled() {
        return getPolicyBoolean(ENABLE_MEMORY_PINNING, false);
    }

    public boolean isMultichannelAudioEnabled() {
        return getPolicyBoolean(ENABLE_MULTICHANNEL_AUDIO, false);
    }

    public boolean isNativeWakeWordServiceCoreSupported() {
        return getPolicyBoolean(SUPPORT_NATIVE_WAKE_WORD_SERVICE_CORE, true);
    }

    public boolean isShipBuild() {
        return this.isShipMode;
    }

    public boolean isTrustedWifiSupported() {
        return getPolicyBoolean(SUPPORT_TRUSTED_WIFI, false);
    }

    public boolean isWWFederatedLearningEnabled() {
        return getPolicyBoolean(WW_FEDERATED_LEARNING_ENABLED, false);
    }

    public boolean isWwSupportedOnLockscreen() {
        return getPolicyBoolean(WW_ON_LOCKSCREEN, false);
    }

    void loadPolicy(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Failed to read policy from[policy.json]", e);
                }
                try {
                    break;
                } catch (IOException e2) {
                    Log.w(TAG, "Failed to close BufferedReader for policy", e2);
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (IOException e3) {
                    Log.w(TAG, "Failed to close BufferedReader for policy", e3);
                }
                throw th;
            }
        }
        bufferedReader.close();
        this.mPolicyStr = sb.toString();
        if (DEBUG) {
            GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("loadPolicy: configured policy:"), this.mPolicyStr, TAG);
        }
        try {
            this.mPolicyJson = new JSONObject(this.mPolicyStr);
        } catch (JSONException e4) {
            Log.e(TAG, "Failed to parse configured policy from raw/res/policy_json", e4);
        }
    }

    public boolean requiresDataMigration() {
        return getPolicyBoolean(DEVICE_REQUIRES_DATA_MIGRATION, false);
    }

    public boolean shouldAcceptIntentListenTrigger() {
        return true;
    }

    public boolean shouldConnectOnFirstInvocation() {
        return getPolicyBoolean(CONNECT_ON_FIRST_INVOCATION, false);
    }

    public boolean shouldConnectOnUnlockedWithLowWifi() {
        return getPolicyBoolean(CONNECT_ON_UNLOCK_WITH_LOW_WIFI, false);
    }

    public boolean shouldDetectUserPresence() {
        return true;
    }

    public boolean shouldDeviceSupportDcfPublishApi() {
        return getPolicyBoolean(SUPPORT_DCF_PUBLISH_API, true);
    }

    public boolean shouldDisableAlexaOnDeregistration() {
        return getPolicyBoolean(DISABLE_ALEXA_ON_DEREGISTRATION, false);
    }

    public boolean shouldDisableWakewordAtOobe() {
        return getPolicyBoolean(DISABLE_WAKEWORD_AT_OOBE, false);
    }

    public boolean shouldEnableAsps() {
        return getPolicyBoolean(ENABLE_ASPS, true);
    }

    public boolean shouldEnableAutoHeartBeat() {
        return getPolicyBoolean(ENABLE_SIM_HEARTBEAT, false);
    }

    public boolean shouldEnableDataStreamProvider() {
        return getPolicyBoolean(ENABLE_DATASTREAMPROVIDER, false);
    }

    public boolean shouldEnableLocaleSpecificWW() {
        return getPolicyBoolean(ENABLE_LOCALE_SPECIFIC_WW, false);
    }

    public boolean shouldEnableOldWakeWordDirectoryCleanup() {
        return getPolicyBoolean(ENABLE_OLD_WAKEWORD_DIRECTORY_CLEAN_UP, false);
    }

    public boolean shouldEnablePlaybackDetection() {
        return getPolicyBoolean(WW_ENABLE_PLAYBACK_DETECTION, false);
    }

    public boolean shouldEnableSPC() {
        return getPolicyBoolean(ENABLE_SPC, false);
    }

    public boolean shouldEnableSimSpeechMarksSuppression() {
        return getPolicyBoolean(SIM_ENABLE_SPEECH_MARK_SUPPRESSION, false);
    }

    public boolean shouldEnableWakeWordDownload() {
        return getPolicyBoolean(ENABLE_WAKEWORD_DOWNLOAD, false);
    }

    public boolean shouldEnableWwSpeechMarksSuppression() {
        return getPolicyBoolean(WW_ENABLE_SPEECH_MARKS_SUPPRESSION, false);
    }

    public boolean shouldEnforceCapabilityPermission() {
        int i = sEnforcesCapabilityPermission;
        if (i == -1) {
            return getDefaultEnforcesCapabilityPermission();
        }
        return i == 1;
    }

    public boolean shouldHandleLocaleChange() {
        return getPolicyBoolean(SHOULD_HANDLE_LOCALE_CHANGE, true);
    }

    public boolean shouldHoldWakelock() {
        return getPolicyBoolean(HOLD_WAKE_LOCK, true);
    }

    public boolean shouldHoldWakelockTillAlexaInteraction() {
        return getPolicyBoolean(HOLD_WAKE_LOCK_TILL_ALEXA_INTERACTION, false);
    }

    public boolean shouldKeepAlive() {
        return true;
    }

    public boolean shouldNotifyAspOnCommsMessage() {
        return getPolicyBoolean(NOTIFY_ASP_ON_COMMS_MESSAGE, false);
    }

    public boolean shouldObserveThermalMitigationIntentsForSecondaryWakeword() {
        return getPolicyBoolean(OBSERVE_THERMAL_MITIGATION_INTENTS_FOR_SECONDARY_WW, false);
    }

    public boolean shouldRegisterWhisperPlay() {
        return false;
    }

    public boolean shouldReportUpl() {
        return getPolicyBoolean(REPORT_UPL_IN_SIM, true);
    }

    public boolean shouldRequireBinderPermission() {
        return false;
    }

    public boolean shouldResendAudioAfterUnlock() {
        return getPolicyBoolean(SUPPORT_RESEND_AUDIO_AFTER_UNLOCK, false);
    }

    public boolean shouldResetSCLConnectionUponCapabilityPublish() {
        return true;
    }

    public boolean shouldReuseAudioStream() {
        return getPolicyBoolean(REUSE_SHARED_AUDIO_STREAM, true);
    }

    public boolean shouldRun() {
        return true;
    }

    public boolean shouldSendAndObserveAppstoreContext() {
        return getPolicyBoolean(SEND_AND_OBSERVE_APPSTORE_CONTEXT, false);
    }

    public boolean shouldSendDevicePreferencesContext() {
        return getPolicyBoolean(SEND_DEVICE_PREFERENCES_CONTEXT, false);
    }

    public boolean shouldSendErrorOnDeregistration() {
        return getPolicyBoolean(SEND_ERROR_ON_DEREGISTRATION, false);
    }

    public boolean shouldSendLockscreenContext() {
        return getPolicyBoolean(SEND_LOCKSCREEN_CONTEXT, false);
    }

    public boolean shouldShowSpeechUiActivityOnLockscreen() {
        return getPolicyBoolean(SPEECHUI_ACTIVITY_ON_LOCKSCREEN, false);
    }

    public boolean shouldStreamToAHEWhenOnline() {
        return true;
    }

    public boolean shouldSupportBetaTesting() {
        return getPolicyBoolean(SUPPORT_BETA_TESTING, false);
    }

    public boolean shouldSupportDefaultGoBack() {
        return getPolicyBoolean(SUPPORT_DEFAULT_GO_BACK, false);
    }

    public boolean shouldSupportDownChannel() {
        return getPolicyBoolean(SUPPORT_DOWN_CHANNEL, false);
    }

    public boolean shouldSupportLocalSearchContext() {
        return getPolicyBoolean(SUPPORT_LOCAL_SEARCH_CONTEXT, false);
    }

    public boolean shouldSuppressPlaybackDetectorOnOobe() {
        return getPolicyBoolean(WW_SUPPRESS_PLAYBACK_AT_OOBE, false);
    }

    public boolean shouldToggleWakewordOnScreenChange() {
        return getPolicyBoolean(TOGGLE_WAKEWORD_WITH_SCREEN, false);
    }

    public boolean shouldUnMuteFromSim() {
        return true;
    }

    public boolean shouldUpdateSettingsInAlexaService() {
        return getPolicyBoolean(UPDATE_SETTINGS_ALEXA_SERVCIE, false);
    }

    public boolean shouldUseEnglishLocaleInASR() {
        return getPolicyBoolean(USE_ENGLISH_LOCALE_IN_ASR, false);
    }

    public boolean shouldUseModeSwitchUtil() {
        return getPolicyBoolean(USE_MODESWITCHUTIL, false);
    }

    public boolean shouldUsePowerManagerInactivity() {
        return getPolicyBoolean(USE_POWER_MANAGER_INACTIVITY, false);
    }

    public boolean shouldUseVSuperBowl() {
        return getPolicyBoolean(USE_VSUPERBOWL, false);
    }

    public boolean supportsAdaptiveVolume() {
        return getPolicyBoolean(SUPPORTS_ADAPTIVE_VOLUME, false);
    }

    public boolean supportsAgentConfig() {
        return getPolicyBoolean(SUPPORTS_AGENT_CONFIG, false);
    }

    public boolean supportsAlexaHybrid() {
        return false;
    }

    public boolean supportsAnonymousMode() {
        return getPolicyBoolean(SUPPORTS_ANONYMOUS_MODE, false);
    }

    public boolean supportsArcusService() {
        return getDefaultSupportsArcusService();
    }

    public boolean supportsAudioPresenceManager() {
        return false;
    }

    public boolean supportsContinuingSpeechSessionOnCancel() {
        return getPolicyBoolean(CONTINUE_SPEECH_SESSION_ON_CANCEL, true);
    }

    public boolean supportsHybridAlexaFocusModel() {
        return getPolicyBoolean(SUPPORTS_HYBRID_ALEXA_FOCUS_MODEL, false);
    }

    public boolean supportsLegalTerms() {
        return getPolicyBoolean(SUPPORTS_LEGAL_TERMS, false);
    }

    public boolean supportsMultiTurnPrompt() {
        return getPolicyBoolean(SUPPORTS_MULTI_TURN_PROMPT, false);
    }

    public boolean supportsOOBE() {
        return getPolicyBoolean(SUPPORT_OOBE, false);
    }

    public boolean supportsOpusCompression() {
        return false;
    }

    public boolean supportsParentalControls() {
        return getPolicyBoolean(SUPPORT_PARENTAL_CONTROLS, false);
    }

    public boolean supportsProjectVzAsrProfile() {
        return getPolicyBoolean(SUPPORTS_PROJECT_VZ_ASR_PROFILE, false);
    }

    public boolean supportsRemoteConfiguration() {
        return false;
    }

    public boolean supportsSecureSettingPrivacyMode() {
        return getPolicyBoolean(SECURE_SETTING_PRIVACY_MODE, false);
    }

    public boolean supportsTcpInfoMetrics() {
        return getPolicyBoolean(SUPPORTS_TCP_METRICS, true);
    }

    public boolean supportsWakeWordAcousticEventDetection() {
        return false;
    }

    public boolean supportsWakeWordAudioPresence() {
        return false;
    }

    public boolean supportsWakeWordWhenAlexaDisabled() {
        return getPolicyBoolean(SUPPORTS_WW_WHEN_ALEXA_DISABLED, true);
    }

    public boolean useAppPrivateStorageForDAVS() {
        return getPolicyBoolean(USE_APP_PRIVATE_STORAGE_FOR_DAVS, false);
    }

    public boolean useRemoteServiceForCsmOptions() {
        return false;
    }

    public boolean wakeUpScreenWhileListening() {
        return getPolicyBoolean(WAKEUP_SCREEN_WHILE_LISTENING, true);
    }
}

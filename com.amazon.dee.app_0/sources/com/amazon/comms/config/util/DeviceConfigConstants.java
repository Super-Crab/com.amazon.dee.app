package com.amazon.comms.config.util;

import com.amazon.comms.calling.service.AudioConfig;
import com.amazon.comms.calling.service.DynamicAcousticParams;
/* loaded from: classes11.dex */
public class DeviceConfigConstants {
    public static final String ALWAYS_ON_RUNTIME_POLICY = "com.amazon.comms.devicecalling.policy.AlwaysOnRuntimePolicy";
    public static final int AUDIO_START_BITRATE_32_KBPS = 32;
    public static final int BUFFER_SIZE_IN_SAMPLES_768 = 768;
    public static final int BUFFER_SIZE_IN_SAMPLES_768_2 = 1536;
    public static final int BUFFER_SIZE_IN_SAMPLES_768_3 = 2304;
    public static final int BUFFER_SIZE_IN_SAMPLES_896 = 896;
    public static final int BYTES_PER_SAMPLE_2 = 2;
    public static final String CALLING_UI_LAUNCHER = "com.amazon.comms.calling.app.CallingUiLauncher";
    public static final String CALLING_UI_NOTIFICATION_MANAGER = "com.amazon.comms.calling.app.CallingUiNotificationManager";
    public static final boolean DEFAULT_ACCEPT_PRESENCE_BROADCAST = true;
    public static final int DEFAULT_AUDIO_START_BITRATE_KBPS = 48;
    public static final long DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS = 3000;
    public static final long DEFAULT_CALL_STANDBY_TO_SHUTDOWN_TIMEOUT_MS = 12000;
    public static final boolean DEFAULT_CAMERA_SELECTOR_CAPABILITY = false;
    public static final boolean DEFAULT_CAMERA_SHUTTER_PRESENT = false;
    public static final boolean DEFAULT_CAN_RECEIVE_REMOTE_FACE_COORDINATES = false;
    public static final boolean DEFAULT_CAPTURE_TO_TEXTURE = true;
    public static final boolean DEFAULT_CONSTRAIN_VIDEO_UNTIL_FIRST_ICE_CONNECTION = false;
    public static final boolean DEFAULT_DATA_CHANNEL_SUPPORTED_FOR_ENHANCED_PROCESSING = true;
    public static final long DEFAULT_DEFERRED_BEGINCALL_TIMEOUT_MS = 5000;
    public static final boolean DEFAULT_ENABLE_AMZNB_AUDIO_FILTER = true;
    public static final boolean DEFAULT_ENABLE_CAMERA_METRICS_REPORTING = false;
    public static final int DEFAULT_FACE_COORDINATES_Y_OFFSET = 0;
    public static final AudioConfig.FastRenderPath DEFAULT_FAST_RENDER_PATH_OPTION;
    public static final boolean DEFAULT_FORCE_CAMERA2_API = false;
    public static final boolean DEFAULT_FORCE_RECEIVE_ONLY_VIDEO = false;
    public static final boolean DEFAULT_HAS_BUILT_IN_SPEAKER = true;
    public static final boolean DEFAULT_HAS_VDNA = false;
    public static final boolean DEFAULT_IGNORE_CAMERA_EVICTION_ERROR = false;
    public static final LedControllerType DEFAULT_LED_CONTROLLER_TYPE;
    public static final int DEFAULT_NONH264_VIDEO_FPS = 0;
    public static final int DEFAULT_NONH264_VIDEO_HEIGHT = 0;
    public static final int DEFAULT_NONH264_VIDEO_WIDTH = 0;
    public static final int DEFAULT_OR_OVERRIDEN_AUDIO_CAPTURE_SAMPLING_RATE_HZ = 16000;
    public static final int DEFAULT_OR_OVERRIDEN_AUDIO_RENDER_SAMPLING_RATE_HZ = 48000;
    public static final boolean DEFAULT_OVERRIDE_AUDIO_CAPTURE_SAMPLING_RATE = true;
    public static final boolean DEFAULT_OVERRIDE_AUDIO_RENDER_SAMPLING_RATE = false;
    public static final boolean DEFAULT_PERSIST_SIM_DEVICE_CONTEXT = true;
    public static final boolean DEFAULT_PIP_SWITCH_SUPPORTED = false;
    public static final boolean DEFAULT_PREFER_CAMERA1_API = false;
    public static final boolean DEFAULT_PREFER_WEBRTC_ACOUSTIC_ECHO_CANCELER = false;
    public static final boolean DEFAULT_PRESENCE_PUBLISH_CAPABLE = true;
    public static final boolean DEFAULT_PROCESS_PARKED_CALL_TOKEN = true;
    public static final boolean DEFAULT_PROVIDE_CALLING_SERVICE_HAL_PARAMETER = false;
    public static final boolean DEFAULT_REAL_TIME_TEXT_CAPABLE = false;
    public static final int DEFAULT_REDUCED_VIDEO_FPS_UNTIL_FIRST_ICE_CONN = 0;
    public static final int DEFAULT_REDUCED_VIDEO_HEIGHT_UNTIL_FIRST_ICE_CONN = 0;
    public static final int DEFAULT_REDUCED_VIDEO_WIDTH_UNTIL_FIRST_ICE_CONN = 0;
    public static final int DEFAULT_RENDER_TRACK_BUFFER_SIZE_BYTES = -1;
    public static final boolean DEFAULT_REQUIRES_CALL_ACKNOWLEDGEMENT = false;
    public static final boolean DEFAULT_SIMULATE_FIRST_FRAME_RECEIVED = false;
    public static final boolean DEFAULT_SMART_MOTION_MOTOR_ROTATION_SUPPORTED = false;
    public static final boolean DEFAULT_SMART_MOTION_TRACKING_SUPPORTED = false;
    public static final boolean DEFAULT_SUPPORTS_GLORIA_UI = false;
    public static final boolean DEFAULT_SUPPORTS_HANDS_FREE_PROFILE_ENABLED_DOCK = false;
    public static final boolean DEFAULT_SUPPORTS_HINTS = false;
    public static final boolean DEFAULT_SUPPORTS_INSIGHTS = true;
    public static final boolean DEFAULT_SUPPORTS_ONE_WAY_VIDEO_CALLING = false;
    public static final boolean DEFAULT_SUPPORT_DEFERRED_BEGINCALL = true;
    public static final boolean DEFAULT_UPDATE_CAMERA_HAL_FRAMERATE_ALLOWED = false;
    public static final int DEFAULT_VIDEO_FPS = 0;
    public static final int DEFAULT_VIDEO_HEIGHT = 0;
    public static final int DEFAULT_VIDEO_MAX_BITRATE = 0;
    public static final int DEFAULT_VIDEO_START_BITRATE = 0;
    public static final int DEFAULT_VIDEO_WIDTH = 0;
    public static final String DEFAULT_WEBRTC_FIELD_TRIALS = "";
    public static final int DEFAULT_WEBRTC_STATS_LOGGING_FREQUENCY = -1;
    public static final int DEFAULT_WEBRTC_STATS_POLLING_FREQUENCY_MS = 2000;
    public static final String DEVICE_JOBSCHEDULER_IMPL_FIRETV = "com.amazon.comms.starktachyon.device.ImmediateJobScheduler";
    public static final String DEVICE_JOBSCHEDULER_IMPL_HEADLESSTACHYON = "com.amazon.comms.calling.app.SimJobSchedulerImpl";
    public static final String DEVICE_JOBSCHEDULER_IMPL_MULTIMODALTACHYONARM = "com.amazon.comms.multimodaltachyonlib.device.ImmediateJobScheduler";
    public static final String DEVICE_JOBSCHEDULER_IMPL_MULTIMODALTACHYONX86 = "com.amazon.comms.multimodaltachyonx86.device.ImmediateJobScheduler";
    public static final DynamicAcousticParams.ConfigPath DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP;
    public static final DynamicAcousticParams.ConfigPath DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_NONE;
    public static final DynamicAcousticParams.ConfigPath DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_WEBRTC;
    public static final int FACE_COORDINATES_Y_OFFSET_40 = 40;
    public static final AudioConfig.FastRenderPath FAST_RENDER_PATH_AUDIOTRACK;
    public static final AudioConfig.FastRenderPath FAST_RENDER_PATH_NONE;
    public static final AudioConfig.FastRenderPath FAST_RENDER_PATH_OPENSLES;
    public static final String HEADLESS_UI_LAUNCHER = "com.amazon.comms.calling.app.HeadlessCallingUiLauncher";
    public static final String HEADLESS_UI_NOTIFICATION_MANAGER = "com.amazon.comms.calling.app.HeadlessCallingUiNotificationManager";
    public static final LedControllerType LED_CONTROLLER_TYPE_LED_CONTROLLER_SERVICE;
    public static final LedControllerType LED_CONTROLLER_TYPE_LIGHT_SERVICE;
    public static final LedControllerType LED_CONTROLLER_TYPE_NONE;
    public static final String SLIMFAST_RUNTIME_POLICY = "com.amazon.comms.devicecalling.policy.SlimfastRuntimePolicy";
    public static final String SURFACEVIEW_SHAPE_CIRCLE = "circle";
    public static final String SURFACEVIEW_SHAPE_NONE = "none";
    public static final String SURFACEVIEW_SHAPE_RECTANGLE = "rectangle";
    public static final String UI_MAIN_ACTIVITY_CALLING_ACTIVITY = "com.amazon.comms.calling.app.CallingActivity";
    public static final String UI_MAIN_ACTIVITY_EMPTY = "";
    public static final int VIDEO_BITRATE_1000_KBPS = 1000;
    public static final int VIDEO_BITRATE_2000_KBPS = 2000;
    public static final int VIDEO_BITRATE_2500_KBPS = 2500;
    public static final int VIDEO_BITRATE_3200_KBPS = 3200;
    public static final int VIDEO_BITRATE_600_KBPS = 600;
    public static final int VIDEO_BITRATE_800_KBPS = 800;
    public static final int VIDEO_BITRATE_900_KBPS = 900;
    public static final int VIDEO_FPS_0 = 0;
    public static final int VIDEO_FPS_15 = 15;
    public static final int VIDEO_FPS_24 = 24;
    public static final int VIDEO_FPS_30 = 30;
    public static final int VIDEO_FPS_35 = 35;
    public static final int VIDEO_HEIGHT_0 = 0;
    public static final int VIDEO_HEIGHT_180 = 180;
    public static final int VIDEO_HEIGHT_240 = 240;
    public static final int VIDEO_HEIGHT_360 = 360;
    public static final int VIDEO_HEIGHT_480 = 480;
    public static final int VIDEO_HEIGHT_720 = 720;
    public static final int VIDEO_HEIGHT_800 = 800;
    public static final int VIDEO_WIDTH_0 = 0;
    public static final int VIDEO_WIDTH_1280 = 1280;
    public static final int VIDEO_WIDTH_320 = 320;
    public static final int VIDEO_WIDTH_640 = 640;
    public static final int VIDEO_WIDTH_800 = 800;
    public static final String WEBRTC_FIELD_TRIAL_AMLOGICH264_ENABLED = "WebRTC-AMLOGICH264/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_AMLOGICH264_ENABLED_FPSADJUST = "WebRTC-AMLOGICH264/Enabled-FpsAdjust/";
    public static final String WEBRTC_FIELD_TRIAL_AUDIO_SENDSIDEBWE_ENABLED = "WebRTC-Audio-SendSideBwe/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_AVSYNC_AUDIODELAYESTIMATES_ENABLED_128 = "WebRTC-AVSync-AudioDelayEstimateMs/Enabled-128/";
    public static final String WEBRTC_FIELD_TRIAL_AVSYNC_AUDIODELAYESTIMATES_ENABLED_160 = "WebRTC-AVSync-AudioDelayEstimateMs/Enabled-160/";
    public static final String WEBRTC_FIELD_TRIAL_AVSYNC_AUDIODELAYESTIMATES_ENABLED_240 = "WebRTC-AVSync-AudioDelayEstimateMs/Enabled-240/";
    public static final String WEBRTC_FIELD_TRIAL_AVSYNC_AUDIODELAYESTIMATES_ENABLED_300 = "WebRTC-AVSync-AudioDelayEstimateMs/Enabled-300/";
    public static final String WEBRTC_FIELD_TRIAL_MSH264_ENABLED = "WebRTC-MSH264/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_MSH264_LOWLATENCY_ENABLED = "WebRTC-MSH264-LowLatency/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_MTKH264_ENABLED = "WebRTC-MTKH264/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_MTKH264_ENABLED_NOADJUST = "WebRTC-MTKH264/Enabled-NoAdjust/";
    public static final String WEBRTC_FIELD_TRIAL_MTKH264_LOWLATENCY_ENABLED = "WebRTC-MTKH264-LowLatency/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_RFC5389STUNRETRANSMISSIONS_ENABLED = "WebRTC-Rfc5389StunRetransmissions/Enabled/";
    public static final String WEBRTC_FIELD_TRIAL_VIDEOFRAMEEMIT_ENABLED = "VideoFrameEmit/Enabled/";
    public static final int WEBRTC_STATS_LOGGING_FREQUENCY_14000_MS = 14000;
    public static final int WEBRTC_STATS_POLLING_FREQUENCY_4000_MS = 4000;
    public static final int WEBRTC_STATS_POLLING_FREQUENCY_5000_MS = 5000;

    static {
        AudioConfig.FastRenderPath fastRenderPath = AudioConfig.FastRenderPath.NONE;
        DEFAULT_FAST_RENDER_PATH_OPTION = fastRenderPath;
        FAST_RENDER_PATH_NONE = fastRenderPath;
        FAST_RENDER_PATH_OPENSLES = AudioConfig.FastRenderPath.OPENSLES;
        FAST_RENDER_PATH_AUDIOTRACK = AudioConfig.FastRenderPath.AUDIOTRACK;
        DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_NONE = DynamicAcousticParams.ConfigPath.NONE;
        DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_LIBASP = DynamicAcousticParams.ConfigPath.LIBASP;
        DYNAMIC_ACOUSTIC_PARAMS_CONFIG_PATH_WEBRTC = DynamicAcousticParams.ConfigPath.WEBRTC;
        LedControllerType ledControllerType = LedControllerType.NONE;
        DEFAULT_LED_CONTROLLER_TYPE = ledControllerType;
        LED_CONTROLLER_TYPE_NONE = ledControllerType;
        LED_CONTROLLER_TYPE_LIGHT_SERVICE = LedControllerType.LIGHT_SERVICE;
        LED_CONTROLLER_TYPE_LED_CONTROLLER_SERVICE = LedControllerType.LED_CONTROLLER_SERVICE;
    }
}

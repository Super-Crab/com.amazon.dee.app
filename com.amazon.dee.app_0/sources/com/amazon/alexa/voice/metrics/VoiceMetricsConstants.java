package com.amazon.alexa.voice.metrics;

import com.amazon.alexa.voice.metrics.service.MetricsService;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class VoiceMetricsConstants {
    public static final String COMPONENT_VOICE_INGRESS = "VoiceIngress";
    public static final String COMPONENT_VOICE_SETTINGS = "HandsFreeSettings";
    public static final String COMPONENT_VOX_SPEECH = "vox_speech";
    public static final String LAUNCHER_WIDGET = "LAUNCHER_WIDGET";
    public static final String OCCURRENCE_START_VOICE_SUCCESS = "occurrence_start_voice_success";
    public static final String OWNER_IDENTIFIER = "8eb5778d-5d99-45c4-b507-55fa502bbb43";
    public static final String QUICK_ACTIONS_WIDGET_ASK_ALEXA_LATENCY = "QUICK_ACTIONS_WIDGET_ASK_ALEXA_LATENCY";
    public static final String UPGRADE_ACCOUNT_ATTEMPT = "UPGRADE_ACCOUNT_ATTEMPT";
    public static final String UPGRADE_ACCOUNT_FAILURE = "UPGRADE_ACCOUNT_FAILURE";
    public static final String VOX_LAUNCH_DELAYED_NOT_CONNECTED_YET = "VOX_DELAYED_NOT_CONNECTED";
    public static final String VOX_LAUNCH_FAILURE_AUDIO_NOT_AVAILABLE = "VOX_FAILURE_NO_AUDIO";
    public static final String VOX_LAUNCH_FAILURE_DOWNCHANNEL_CONNECTION_FAILED = "VOX_FAILED_DOWNCHANNEL_CONNECTION_FAILED";
    public static final String VOX_LAUNCH_FAILURE_NO_PERMISSIONS = "VOX_FAILED_NO_PERMISSIONS";
    public static final String VOX_MIC_CAN_START_RECORDING = "VOX_MIC_CAN_START_RECORDING";
    public static final String VOX_PERMISSIONS_REQUESTED = "VOX_PERMISSIONS_REQUESTED";
    public static final String VOX_PERMISSION_GRANT_RATE = "VOX_PERMISSION_GRANT_RATE";
    public static final String VOX_TAP_TO_SCRIM = "VOX_TAP_TO_SCRIM_VISIBLE_LATENCY";
    public static final String VOX_TAP_TO_VOICE_RECORD_START = "VOX_TAP_TO_VOICE_RECORD_START";
    public static final String WAKE_NOTIFICATION_LATENCY = "WAKE_NOTIFICATION_LATENCY";

    private VoiceMetricsConstants() {
        throw new RuntimeException("Attempt to create an instance of this class, crashing it!");
    }

    public static void recordPermissionsMetrics(String[] strArr, int[] iArr, MetricsService metricsService, String str) {
        metricsService.recordEvent(VOX_PERMISSIONS_REQUESTED, str, null);
        for (int i = 0; i < strArr.length; i++) {
            boolean z = true;
            String format = String.format(Locale.getDefault(), "%s:%s", VOX_PERMISSION_GRANT_RATE, strArr[i]);
            if (iArr[i] != 0) {
                z = false;
            }
            metricsService.recordPercentOccurrence(format, str, z, null);
        }
    }
}

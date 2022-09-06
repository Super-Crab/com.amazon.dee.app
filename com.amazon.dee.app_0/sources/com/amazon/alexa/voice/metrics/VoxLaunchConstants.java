package com.amazon.alexa.voice.metrics;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.model.VoiceService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class VoxLaunchConstants {
    public static final String DEVICE_ASSIST = "DEVICE_ASSIST";
    public static final String DRIVE_MODE_TAP = "DRIVE_MODE_TAP";
    public static final String INTENT = "INTENT";
    public static final String IN_APP_WAKEWORD = "WAKEWORD";
    public static final String JASPER_CARD = "jasperhomevoxcard";
    public static final String JASPER_HEADER = "jasperhomeheader";
    public static final Map<String, String> LAUNCH_TYPE_METHOD_MAP = new HashMap();
    public static final String PUSH_BUTTON = "PUSH_BUTTON";
    public static final String QUICK_ACTIONS_WIDGET = "QUICK_ACTIONS_WIDGET";
    public static final String REQUEST_PERMISSIONS = "REQUEST_PERMISSIONS";
    public static final String ROUTE = "ROUTE";
    public static final String SETTINGS = "SETTINGS";
    public static final String SHORTCUT = "SHORTCUT";
    public static final String UNKNOWN = "UNKNOWN";
    public static final String VOICE_ENROLLMENT = "VOICE_ENROLLMENT";
    public static final String VOICE_INGRESS = "VOICE_INGRESS";
    public static final String VOX_LAUNCH_DEVICE_ASSIST = "VOX_LAUNCH_DEVICE_ASSIST";
    public static final String VOX_LAUNCH_DRIVE_MODE_TAP = "VOX_LAUNCH_DRIVE_MODE_TAP";
    public static final String VOX_LAUNCH_INGRESS_BUTTON = "VOX_LAUNCH_INGRESS";
    public static final String VOX_LAUNCH_LISTEN_ENROLLMENT = "VOX_LAUNCH_LISTEN_ENROLLMENT";
    public static final String VOX_LAUNCH_LISTEN_INTENT = "VOX_LAUNCH_LISTEN_INTENT";
    public static final String VOX_LAUNCH_NOTIFICATION = "VOX_LAUNCH_NOTIFICATION";
    public static final String VOX_LAUNCH_PUSHBUTTON = "VOX_LAUNCH_PUSHBUTTON";
    public static final String VOX_LAUNCH_QUICK_ACTIONS_WIDGET = "VOX_LAUNCH_QUICK_ACTIONS_WIDGET";
    public static final String VOX_LAUNCH_REQUEST_PERMISSIONS = "VOX_LAUNCH_REQUEST_PERMISSIONS";
    public static final String VOX_LAUNCH_ROUTE = "VOX_LAUNCH_ROUTE";
    public static final String VOX_LAUNCH_SETTINGS = "VOX_LAUNCH_SETTINGS";
    public static final String VOX_LAUNCH_SHORTCUT = "VOX_LAUNCH_SHORTCUT";
    @VisibleForTesting
    static final Map<VoiceService.InvocationType, String> VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP;
    public static final String VOX_LAUNCH_WAKEWORD = "VOX_LAUNCH_WAKEWORD";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface VoxLaunchTypes {
    }

    static {
        LAUNCH_TYPE_METHOD_MAP.put(VoxMetricEventName.INGRESS_IN_APP_WAKEWORD_OCCUR, IN_APP_WAKEWORD);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_SETTINGS, SETTINGS);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_SHORTCUT, SHORTCUT);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_QUICK_ACTIONS_WIDGET, "QUICK_ACTIONS_WIDGET");
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_DEVICE_ASSIST, DEVICE_ASSIST);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_INGRESS_BUTTON, VOICE_INGRESS);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_PUSHBUTTON, PUSH_BUTTON);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_REQUEST_PERMISSIONS, REQUEST_PERMISSIONS);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_ROUTE, ROUTE);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_DRIVE_MODE_TAP, DRIVE_MODE_TAP);
        LAUNCH_TYPE_METHOD_MAP.put(VOX_LAUNCH_LISTEN_ENROLLMENT, VOICE_ENROLLMENT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP = new HashMap();
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.SHORTCUT, SHORTCUT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.QUICK_ACTIONS_WIDGET, "QUICK_ACTIONS_WIDGET");
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.INTENT, INTENT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.VOICE_COMMAND_INTENT, INTENT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.ANDROID_VOICE_SEARCH_INTENT, INTENT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.PUSH_BUTTON, PUSH_BUTTON);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.DEVICE_ASSISTANT, DEVICE_ASSIST);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.APP_INGRESS, VOICE_INGRESS);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.ROUTE, ROUTE);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.VOICE_ENROLLMENT, VOICE_ENROLLMENT);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.DRIVE_MODE_TAP, DRIVE_MODE_TAP);
        VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.put(VoiceService.InvocationType.UNKNOWN, "UNKNOWN");
    }

    private VoxLaunchConstants() {
    }

    public static String getLaunchType(VoiceService.InvocationType invocationType) {
        return VOX_LAUNCH_TYPE_FROM_INVOCATION_MAP.get(invocationType);
    }
}

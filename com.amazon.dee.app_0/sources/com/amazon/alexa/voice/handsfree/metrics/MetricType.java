package com.amazon.alexa.voice.handsfree.metrics;
/* loaded from: classes11.dex */
public enum MetricType {
    ALEXA_APP_SIGNIN_NOTIFICATION_METADATA_CALLED("AlexaAppSignInNotificationMetadataCalled"),
    PROFILE_SELECTION_NOTIFICATION_METADATA_CALLED("AlexaAppUpdateNotificationMetadataCalled"),
    HANDS_FREE_INTRO_METADATA_CALLED("HandsFreeIntroNotificationMetadataCalled"),
    ALEXA_APP_AUDIO_PERMISSION_METADATA_CALLED("AlexaAppAudioPermissionMetadataCalled"),
    ALEXA_APP_LOCATION_PERMISSION_METADATA_CALLED("AlexaAppLocationPermissionMetadataCalled"),
    HANDS_FREE_PERMISSIONS_NOTIFICATION_METADATA_CALLED("HandsFreePermissionsNotificationMetadataCalled"),
    LANGUAGE_SELECTOR_NOTIFICATION_METADATA_CALLED("LanguageSelectorNotificationMetadataCalled"),
    USER_VOICE_RECOGNITION_ENROLLMENT_NOTIFICATION_METADATA_CALLED("UserVoiceRecognitionEnrollmentNotificationMetadataCalled"),
    LOCK_SCREEN_SETTING_NOTIFICATION_METADATA_CALLED("LockScreenSettingNotificationMetadataCalled"),
    MANUFACTURER_NOT_SUPPORTED("ManufacturerNotSupported"),
    HANDS_FREE_VOX_SETTINGS_ROUTE_NO_VOICE_APP("HandsFreeVoxSettingsRouteNoVoiceApp"),
    NO_ROUTE_FOR_DEVICE("NoRouteForDevice"),
    SETTINGS_INTENT_DOES_NOT_RESOLVE("SettingsIntentDoesNotResolve"),
    SETTINGS_NO_PROFILE("SettingsNoProfile"),
    MANUFACTURER_NOT_DETECTED("ManufacturerNotDetected"),
    ALEXA_AUDIO_PERMISSION_ONLY_THIS_TIME("AlexaAudioPermissionOnlyThisTime"),
    ALEXA_LOCATION_PERMISSION_ONLY_THIS_TIME("AlexaLocationPermissionOnlyThisTime"),
    PARTNER_AUDIO_PERMISSION_ONLY_THIS_TIME("PartnerAudioPermissionOnlyThisTime");
    
    private String mValue;

    MetricType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }
}

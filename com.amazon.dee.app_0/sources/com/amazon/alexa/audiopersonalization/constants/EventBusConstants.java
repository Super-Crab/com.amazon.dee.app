package com.amazon.alexa.audiopersonalization.constants;
/* loaded from: classes6.dex */
public final class EventBusConstants {
    public static final String CHANNEL_KEY = "channel";
    public static final String DURATION_KEY = "duration";
    public static final String EVENT_TYPE_ACCESSORY_BUDS_IN_EAR_REQUEST = "enhancedMedia::accessory::budsInEarRequest";
    public static final String EVENT_TYPE_ACCESSORY_BUDS_IN_EAR_RESPONSE = "enhancedMedia::accessory::budsInEarResponse";
    public static final String EVENT_TYPE_ACCESSORY_ERROR = "enhancedMedia::accessory::error";
    public static final String EVENT_TYPE_APP_START = "enhancedMedia::appState::start";
    public static final String EVENT_TYPE_APP_STATE_ERROR = "enhancedMedia::appState::error";
    public static final String EVENT_TYPE_ASSESSMENT_ERROR = "enhancedMedia::audioAssessment::error";
    public static final String EVENT_TYPE_ASSESSMENT_MODE_RESULT = "enhancedMedia::audioAssessment::getAssessmentModeResponse";
    public static final String EVENT_TYPE_AUDIO_PROFILE_RESULT = "enhancedMedia::settings::audioProfileResult";
    public static final String EVENT_TYPE_DELETE_AUDIO_PROFILE = "enhancedMedia::audioAssessment::delete";
    public static final String EVENT_TYPE_FEATURE_STATUS_RESULT = "enhancedMedia::settings::featureStatusResult";
    public static final String EVENT_TYPE_GET_ASSESSMENT_MODE = "enhancedMedia::audioAssessment::getAssessmentModeRequest";
    public static final String EVENT_TYPE_GET_AUDIO_PROFILE = "enhancedMedia::settings::requestAudioProfile";
    public static final String EVENT_TYPE_GET_FEATURE_STATUS = "enhancedMedia::settings::requestFeatureStatus";
    public static final String EVENT_TYPE_GET_MPE_SUPPORT = "enhancedMedia::appState::requestFeatureSupportForMPE";
    public static final String EVENT_TYPE_GET_PERSONALIZATION_LEVEL = "enhancedMedia::settings::requestPersonalizationLevel";
    public static final String EVENT_TYPE_MPE_SUPPORT_RESULT = "enhancedMedia::appState::featureSupportResultMPE";
    public static final String EVENT_TYPE_PAUSE_ASSESSMENT = "enhancedMedia::audioAssessment::pause";
    public static final String EVENT_TYPE_PERSONALIZATION_LEVEL_RESULT = "enhancedMedia::settings::personalizationLevelResult";
    public static final String EVENT_TYPE_RESUME_ASSESSMENT = "enhancedMedia::audioAssessment::resume";
    public static final String EVENT_TYPE_SESSION_DISCONNECT = "enhancedMedia::accessory::disconnect";
    public static final String EVENT_TYPE_SETTINGS_ERROR = "enhancedMedia::settings::error";
    public static final String EVENT_TYPE_SET_ASSESSMENT_MODE = "enhancedMedia::audioAssessment::setAssessmentMode";
    public static final String EVENT_TYPE_SET_AUDIO_PROFILE = "enhancedMedia::audioAssessment::setAudioProfile";
    public static final String EVENT_TYPE_SET_AUDIO_PROFILE_SUCCESS = "enhancedMedia::audioAssessment::setAudioProfileSuccess";
    public static final String EVENT_TYPE_SET_FEATURE_STATUS = "enhancedMedia::settings::setFeatureStatus";
    public static final String EVENT_TYPE_SET_PERSONALIZATION_LEVEL = "enhancedMedia::settings::setPersonalizationLevel";
    public static final String EVENT_TYPE_TONE_ERROR = "enhancedMedia::tone::error";
    public static final String EVENT_TYPE_TONE_PLAY = "enhancedMedia::tone::play";
    public static final String EVENT_TYPE_TONE_STOP = "enhancedMedia::tone::stop";
    public static final String FADE_KEY = "fade";
    public static final String FREQUENCY_KEY = "frequency";
    public static final String JSON_KEY_ASSESSMENT_MODE = "assessmentMode";
    public static final String JSON_KEY_ASSESSMENT_MODE_LEGACY = "status";
    public static final String JSON_KEY_AUDIO_PROFILE = "audiogram";
    public static final String JSON_KEY_AUDIO_PROFILE_STATUS = "hasAudioProfile";
    public static final String JSON_KEY_DEVICE_ADDRESS = "deviceAddress";
    public static final String JSON_KEY_FEATURE_STATUS = "status";
    public static final String JSON_KEY_FEATURE_SUPPORTS_MPE = "supportsMPE";
    public static final String JSON_KEY_IN_EAR_STATUS = "inEar";
    public static final String JSON_KEY_PERSONALIZATION_LEVEL = "personalizationLevel";
    public static final String REQUEST_ID_KEY = "requestId";
    public static final int VALUE_ASSESSMENT_MODE_ABORTED = 3;
    public static final int VALUE_ASSESSMENT_MODE_COMPLETED = 2;
    public static final int VALUE_ASSESSMENT_MODE_INIT = 0;
    public static final int VALUE_ASSESSMENT_MODE_STARTED = 1;
    public static final String VOLUME_KEY = "volume";

    private EventBusConstants() {
    }
}

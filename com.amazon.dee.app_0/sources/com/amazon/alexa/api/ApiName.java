package com.amazon.alexa.api;

import com.google.auto.value.AutoValue;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ApiName {
    public static final ApiName zZm = new AutoValue_ApiName("");
    public static final ApiName BIo = zZm(AlexaServicesMessageType.SET_LOCALES);
    public static final ApiName zQM = zZm(AlexaServicesMessageType.SEND_ALEXA_EVENT);
    public static final ApiName zyO = zZm(AlexaServicesMessageType.REGISTER_DRIVE_MODE_LISTENER);
    public static final ApiName jiA = zZm(AlexaServicesMessageType.DEREGISTER_DRIVE_MODE_LISTENER);
    public static final ApiName Qle = zZm(AlexaServicesMessageType.SET_DRIVE_MODE_ENABLED);
    public static final ApiName JTe = zZm(AlexaServicesMessageType.SET_DRIVE_MODE_STATE);
    public static final ApiName LPk = zZm(AlexaServicesMessageType.SET_DRIVE_MODE_THEME);
    public static final ApiName yPL = zZm(AlexaServicesMessageType.REGISTER_TEXT_RESPONSE_LISTENER);
    public static final ApiName Mlj = zZm(AlexaServicesMessageType.DEREGISTER_TEXT_RESPONSE_LISTENER);
    public static final ApiName zzR = zZm(AlexaServicesMessageType.SEND_TEXT_MESSAGE);
    public static final ApiName lOf = zZm(AlexaServicesMessageType.SCHEDULE_VISUAL_TASK);
    public static final ApiName dMe = zZm(AlexaServicesMessageType.UNSCHEDULE_VISUAL_TASK);
    public static final ApiName uzr = zZm(AlexaServicesMessageType.REGISTER_MEDIA_PLAYBACK_LISTENER);
    public static final ApiName HvC = zZm(AlexaServicesMessageType.DEREGISTER_MEDIA_PLAYBACK_LISTENER);
    public static final ApiName vkx = zZm(AlexaServicesMessageType.REGISTER_WAKE_WORD_LISTENER);
    public static final ApiName wDP = zZm(AlexaServicesMessageType.DEREGISTER_WAKE_WORD_LISTENER);
    public static final ApiName noQ = zZm(AlexaServicesMessageType.SET_WAKE_WORDS);
    public static final ApiName Qgh = zZm(AlexaServicesMessageType.SET_MEDIA_NOTIFICATION_CONTENT_INTENT);
    public static final ApiName Tbw = zZm(AlexaServicesMessageType.ENABLE_EXTERNAL_CAPABILITY_AGENT);
    public static final ApiName XWf = zZm(AlexaServicesMessageType.DISABLE_EXTERNAL_CAPABILITY_AGENT);
    public static final ApiName NXS = zZm(AlexaServicesMessageType.REGISTER_EXPECT_TEXT_LISTENER);
    public static final ApiName uuO = zZm(AlexaServicesMessageType.DEREGISTER_EXPECT_TEXT_LISTENER);

    public static ApiName zZm(AlexaServicesMessageType alexaServicesMessageType) {
        return new AutoValue_ApiName(alexaServicesMessageType.name());
    }
}

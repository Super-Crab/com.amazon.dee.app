package com.amazon.alexa.accessory.speechapi.speech;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: UserSpeechProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0013\u0014J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "", "onDialogFinished", "", "onDialogRequestDenied", "onDialogRequested", "dialogTurn", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurn;", "onDialogStarted", "onDialogTurnFinished", "onDialogTurnRequested", "nextDialogTurn", "Lcom/amazon/alexa/accessory/speechapi/speech/NextDialogTurn;", "onDialogTurnStarted", "pauseWakeWordDetection", "resumeWakeWordDetection", "setWakeWordDetectionEnabled", "enabled", "", DatabaseHelper.requestedScope_Scope, "SupportedInitiationType", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface UserSpeechProvider {

    /* compiled from: UserSpeechProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$Scope;", "", "(Ljava/lang/String;I)V", "EXTERNAL_DEVICE", "SYSTEM", "APPLICATION", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum Scope {
        EXTERNAL_DEVICE,
        SYSTEM,
        APPLICATION
    }

    /* compiled from: UserSpeechProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider$SupportedInitiationType;", "", "(Ljava/lang/String;I)V", "WAKE_WORD", "TAP_TO_TALK", "PUSH_TO_TALK", "SERVER", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum SupportedInitiationType {
        WAKE_WORD,
        TAP_TO_TALK,
        PUSH_TO_TALK,
        SERVER
    }

    void onDialogFinished();

    void onDialogRequestDenied();

    void onDialogRequested(@NotNull DialogTurn dialogTurn);

    void onDialogStarted();

    void onDialogTurnFinished();

    void onDialogTurnRequested(@NotNull NextDialogTurn nextDialogTurn);

    void onDialogTurnStarted();

    void pauseWakeWordDetection();

    void resumeWakeWordDetection();

    void setWakeWordDetectionEnabled(boolean z);
}

package com.amazon.alexa.api.compat;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaUserSpeechProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&J\b\u0010\t\u001a\u00020\u0003H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\u0003H&J\b\u0010\u000f\u001a\u00020\u0003H&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H&¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "", "onDialogFinished", "", "onDialogRequestDenied", "onDialogRequested", "alexaDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "onDialogStarted", "onDialogTurnFinished", "onDialogTurnRequested", "alexaNextDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "onDialogTurnStarted", "pauseWakeWordDetection", "resumeWakeWordDetection", "setWakeWordDetectionEnabled", "enabled", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaUserSpeechProvider {
    void onDialogFinished();

    void onDialogRequestDenied();

    void onDialogRequested(@NotNull AlexaDialogTurn alexaDialogTurn);

    void onDialogStarted();

    void onDialogTurnFinished();

    void onDialogTurnRequested(@NotNull AlexaNextDialogTurn alexaNextDialogTurn);

    void onDialogTurnStarted();

    void pauseWakeWordDetection();

    void resumeWakeWordDetection();

    void setWakeWordDetectionEnabled(boolean z);
}

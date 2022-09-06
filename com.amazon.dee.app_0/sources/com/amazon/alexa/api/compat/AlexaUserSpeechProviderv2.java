package com.amazon.alexa.api.compat;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaUserSpeechProviderv2.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0012\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H&J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\u0012\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H&J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH&¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderv2;", "", "onDialogFinished", "", "dialogData", "Lcom/amazon/alexa/api/compat/DialogData;", "onDialogRequestDenied", "deniedReason", "Lcom/amazon/alexa/api/compat/DialogRequestDeniedReason;", "onDialogRequested", "alexaDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "onDialogStarted", "onDialogTurnFinished", "dialogTurnData", "Lcom/amazon/alexa/api/compat/DialogTurnData;", "onDialogTurnRequested", "alexaNextDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "onDialogTurnStarted", "pauseWakeWordDetection", AlexaMetricsConstants.EventConstants.WAKE_WORD, "", "resumeWakeWordDetection", "setWakeWordDetectionEnabled", "enabled", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaUserSpeechProviderv2 {
    void onDialogFinished(@Nullable DialogData dialogData);

    void onDialogRequestDenied(@Nullable DialogRequestDeniedReason dialogRequestDeniedReason);

    void onDialogRequested(@NotNull AlexaDialogTurn alexaDialogTurn);

    void onDialogStarted(@Nullable DialogData dialogData);

    void onDialogTurnFinished(@Nullable DialogTurnData dialogTurnData);

    void onDialogTurnRequested(@Nullable AlexaNextDialogTurn alexaNextDialogTurn);

    void onDialogTurnStarted(@Nullable DialogTurnData dialogTurnData);

    void pauseWakeWordDetection(@Nullable String str);

    void resumeWakeWordDetection(@Nullable String str);

    void setWakeWordDetectionEnabled(boolean z);
}

package com.amazon.alexa.api.compat;

import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaUserSpeechProviderAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010\u0011\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaUserSpeechProviderAdapter;", "Lcom/amazon/alexa/api/AlexaUserSpeechProvider;", "compatUserSpeechProvider", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "(Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;)V", "getCompatUserSpeechProvider", "()Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "onDialogFinished", "", "dialogData", "Lcom/amazon/alexa/api/DialogData;", "onDialogRequestDenied", "deniedReason", "Lcom/amazon/alexa/api/DialogRequestDeniedReason;", "onDialogRequested", "alexaDialogTurn", "Lcom/amazon/alexa/api/AlexaDialogTurn;", "onDialogStarted", "onDialogTurnFinished", "dialogTurnData", "Lcom/amazon/alexa/api/DialogTurnData;", "onDialogTurnRequested", "Lcom/amazon/alexa/api/AlexaNextDialogTurn;", "onDialogTurnStarted", "pauseWakeWordDetection", AlexaMetricsConstants.EventConstants.WAKE_WORD, "", "resumeWakeWordDetection", "setWakeWordDetectionEnabled", "enabled", "", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaUserSpeechProviderAdapter implements com.amazon.alexa.api.AlexaUserSpeechProvider {
    @NotNull
    private final AlexaUserSpeechProvider compatUserSpeechProvider;

    public AlexaUserSpeechProviderAdapter(@NotNull AlexaUserSpeechProvider compatUserSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(compatUserSpeechProvider, "compatUserSpeechProvider");
        this.compatUserSpeechProvider = compatUserSpeechProvider;
    }

    @NotNull
    public final AlexaUserSpeechProvider getCompatUserSpeechProvider() {
        return this.compatUserSpeechProvider;
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogFinished(@Nullable com.amazon.alexa.api.DialogData dialogData) {
        this.compatUserSpeechProvider.onDialogFinished();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequestDenied(@Nullable com.amazon.alexa.api.DialogRequestDeniedReason dialogRequestDeniedReason) {
        this.compatUserSpeechProvider.onDialogRequestDenied();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequested(@NotNull com.amazon.alexa.api.AlexaDialogTurn alexaDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaDialogTurn, "alexaDialogTurn");
        AlexaUserSpeechProvider alexaUserSpeechProvider = this.compatUserSpeechProvider;
        String dialogTurnId = alexaDialogTurn.getDialogTurnId();
        Intrinsics.checkExpressionValueIsNotNull(dialogTurnId, "alexaDialogTurn.dialogTurnId");
        alexaUserSpeechProvider.onDialogRequested(new AlexaDialogTurnAdapter(alexaDialogTurn, dialogTurnId));
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogStarted(@Nullable com.amazon.alexa.api.DialogData dialogData) {
        this.compatUserSpeechProvider.onDialogStarted();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnFinished(@Nullable com.amazon.alexa.api.DialogTurnData dialogTurnData) {
        this.compatUserSpeechProvider.onDialogTurnFinished();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnRequested(@NotNull com.amazon.alexa.api.AlexaNextDialogTurn alexaDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaDialogTurn, "alexaDialogTurn");
        AlexaUserSpeechProvider alexaUserSpeechProvider = this.compatUserSpeechProvider;
        String dialogTurnId = alexaDialogTurn.getDialogTurnId();
        Intrinsics.checkExpressionValueIsNotNull(dialogTurnId, "alexaDialogTurn.dialogTurnId");
        alexaUserSpeechProvider.onDialogTurnRequested(new AlexaNextDialogTurnAdapter(alexaDialogTurn, dialogTurnId));
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnStarted(@Nullable com.amazon.alexa.api.DialogTurnData dialogTurnData) {
        this.compatUserSpeechProvider.onDialogTurnStarted();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void pauseWakeWordDetection(@Nullable String str) {
        this.compatUserSpeechProvider.pauseWakeWordDetection();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void resumeWakeWordDetection(@Nullable String str) {
        this.compatUserSpeechProvider.resumeWakeWordDetection();
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        this.compatUserSpeechProvider.setWakeWordDetectionEnabled(z);
    }
}

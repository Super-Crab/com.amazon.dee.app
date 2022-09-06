package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.api.compat.AlexaDialogTurn;
import com.amazon.alexa.api.compat.AlexaNextDialogTurn;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxUserSpeechProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0016J\b\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u0011\u001a\u00020\u0006H\u0016J\b\u0010\u0012\u001a\u00020\u0006H\u0016J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxUserSpeechProvider;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "(Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;)V", "onDialogFinished", "", "onDialogRequestDenied", "onDialogRequested", "alexaDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "onDialogStarted", "onDialogTurnFinished", "onDialogTurnRequested", "alexaNextDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "onDialogTurnStarted", "pauseWakeWordDetection", "resumeWakeWordDetection", "setWakeWordDetectionEnabled", "wakeWordDetectionEnabled", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxUserSpeechProvider implements AlexaUserSpeechProvider {
    private final UserSpeechProvider userSpeechProvider;

    public VoxUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        this.userSpeechProvider = userSpeechProvider;
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogFinished() {
        this.userSpeechProvider.onDialogFinished();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequestDenied() {
        this.userSpeechProvider.onDialogRequestDenied();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogRequested(@NotNull AlexaDialogTurn alexaDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaDialogTurn, "alexaDialogTurn");
        this.userSpeechProvider.onDialogRequested(new VoxDialogTurn(alexaDialogTurn));
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogStarted() {
        this.userSpeechProvider.onDialogStarted();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnFinished() {
        this.userSpeechProvider.onDialogTurnFinished();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnRequested(@NotNull AlexaNextDialogTurn alexaNextDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaNextDialogTurn, "alexaNextDialogTurn");
        this.userSpeechProvider.onDialogTurnRequested(new VoxNextDialogTurn(alexaNextDialogTurn));
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void onDialogTurnStarted() {
        this.userSpeechProvider.onDialogTurnStarted();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void pauseWakeWordDetection() {
        this.userSpeechProvider.pauseWakeWordDetection();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void resumeWakeWordDetection() {
        this.userSpeechProvider.resumeWakeWordDetection();
    }

    @Override // com.amazon.alexa.api.compat.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        this.userSpeechProvider.setWakeWordDetectionEnabled(z);
    }
}

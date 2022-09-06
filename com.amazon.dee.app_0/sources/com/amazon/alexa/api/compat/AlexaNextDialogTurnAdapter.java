package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaNextDialogTurnAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J,\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaNextDialogTurnAdapter;", "Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "noncompatDialogTurn", "Lcom/amazon/alexa/api/AlexaNextDialogTurn;", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "(Lcom/amazon/alexa/api/AlexaNextDialogTurn;Ljava/lang/String;)V", "getDialogTurnId", "()Ljava/lang/String;", "getNoncompatDialogTurn", "()Lcom/amazon/alexa/api/AlexaNextDialogTurn;", "startTurn", "", "audioSink", "Lcom/amazon/alexa/api/AlexaAudioSink;", "stopCallback", "Lcom/amazon/alexa/api/AlexaDialogTurnStopCallback;", "audioMetadata", "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "metricsCallback", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaNextDialogTurnAdapter implements AlexaNextDialogTurn {
    @NotNull
    private final String dialogTurnId;
    @NotNull
    private final com.amazon.alexa.api.AlexaNextDialogTurn noncompatDialogTurn;

    public AlexaNextDialogTurnAdapter(@NotNull com.amazon.alexa.api.AlexaNextDialogTurn noncompatDialogTurn, @NotNull String dialogTurnId) {
        Intrinsics.checkParameterIsNotNull(noncompatDialogTurn, "noncompatDialogTurn");
        Intrinsics.checkParameterIsNotNull(dialogTurnId, "dialogTurnId");
        this.noncompatDialogTurn = noncompatDialogTurn;
        this.dialogTurnId = dialogTurnId;
    }

    @Override // com.amazon.alexa.api.compat.AlexaNextDialogTurn
    @NotNull
    public String getDialogTurnId() {
        return this.dialogTurnId;
    }

    @NotNull
    public final com.amazon.alexa.api.AlexaNextDialogTurn getNoncompatDialogTurn() {
        return this.noncompatDialogTurn;
    }

    @Override // com.amazon.alexa.api.compat.AlexaNextDialogTurn
    public void startTurn(@NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioSink, stopCallback);
    }

    @Override // com.amazon.alexa.api.compat.AlexaNextDialogTurn
    public void startTurn(@NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioSink, stopCallback, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null);
    }

    @Override // com.amazon.alexa.api.compat.AlexaNextDialogTurn
    public void startTurn(@NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioSink, stopCallback, alexaAudioMetadata);
    }

    @Override // com.amazon.alexa.api.compat.AlexaNextDialogTurn
    public void startTurn(@NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioSink, stopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null);
    }
}

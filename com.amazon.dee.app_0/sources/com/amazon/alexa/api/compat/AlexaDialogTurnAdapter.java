package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaDialogTurnAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J4\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J4\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J>\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J4\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaDialogTurnAdapter;", "Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "noncompatDialogTurn", "Lcom/amazon/alexa/api/AlexaDialogTurn;", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "(Lcom/amazon/alexa/api/AlexaDialogTurn;Ljava/lang/String;)V", "getDialogTurnId", "()Ljava/lang/String;", "getNoncompatDialogTurn", "()Lcom/amazon/alexa/api/AlexaDialogTurn;", "startTurn", "", "audioMetadata", "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "audioSink", "Lcom/amazon/alexa/api/AlexaAudioSink;", "dataSink", "Lcom/amazon/alexa/api/AlexaDataSink;", "stopCallback", "Lcom/amazon/alexa/api/AlexaDialogTurnStopCallback;", "dialogExtras", "Lcom/amazon/alexa/api/AlexaDialogExtras;", "metricsCallback", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaDialogTurnAdapter implements AlexaDialogTurn {
    @NotNull
    private final String dialogTurnId;
    @NotNull
    private final com.amazon.alexa.api.AlexaDialogTurn noncompatDialogTurn;

    public AlexaDialogTurnAdapter(@NotNull com.amazon.alexa.api.AlexaDialogTurn noncompatDialogTurn, @NotNull String dialogTurnId) {
        Intrinsics.checkParameterIsNotNull(noncompatDialogTurn, "noncompatDialogTurn");
        Intrinsics.checkParameterIsNotNull(dialogTurnId, "dialogTurnId");
        this.noncompatDialogTurn = noncompatDialogTurn;
        this.dialogTurnId = dialogTurnId;
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    @NotNull
    public String getDialogTurnId() {
        return this.dialogTurnId;
    }

    @NotNull
    public final com.amazon.alexa.api.AlexaDialogTurn getNoncompatDialogTurn() {
        return this.noncompatDialogTurn;
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, stopCallback);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, stopCallback, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, stopCallback, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, stopCallback, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback stopCallback) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, alexaDataSink, stopCallback);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, alexaDataSink, stopCallback, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, alexaDataSink, stopCallback, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null);
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurn
    public void startTurn(@NotNull AlexaAudioMetadata audioMetadata, @NotNull AlexaAudioSink audioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback stopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @Nullable AlexaDialogExtras alexaDialogExtras) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(stopCallback, "stopCallback");
        this.noncompatDialogTurn.startTurn(audioMetadata, audioSink, alexaDataSink, stopCallback, alexaDialogTurnMetricsCallback != null ? new AlexaDialogTurnMetricsCallbackAdapter(alexaDialogTurnMetricsCallback) : null, alexaDialogExtras);
    }
}

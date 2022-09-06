package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaNextDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH&J,\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "getDialogTurnId", "()Ljava/lang/String;", "startTurn", "", "audioSink", "Lcom/amazon/alexa/api/AlexaAudioSink;", "stopCallback", "Lcom/amazon/alexa/api/AlexaDialogTurnStopCallback;", "audioMetadata", "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "metricsCallback", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaNextDialogTurn {
    @NotNull
    String getDialogTurnId();

    void startTurn(@NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata);

    void startTurn(@NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);
}

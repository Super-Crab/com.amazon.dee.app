package com.amazon.alexa.api.compat;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J4\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J4\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J>\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J \u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH&J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&J4\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "", AccessoryMetricsConstants.DIALOG_TURN_ID, "", "getDialogTurnId", "()Ljava/lang/String;", "startTurn", "", "audioMetadata", "Lcom/amazon/alexa/api/AlexaAudioMetadata;", "audioSink", "Lcom/amazon/alexa/api/AlexaAudioSink;", "dataSink", "Lcom/amazon/alexa/api/AlexaDataSink;", "stopCallback", "Lcom/amazon/alexa/api/AlexaDialogTurnStopCallback;", "dialogExtras", "Lcom/amazon/alexa/api/AlexaDialogExtras;", "metricsCallback", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlexaDialogTurn {
    @NotNull
    String getDialogTurnId();

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @Nullable AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NotNull AlexaAudioMetadata alexaAudioMetadata, @NotNull AlexaAudioSink alexaAudioSink, @NotNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @Nullable AlexaDialogExtras alexaDialogExtras);
}

package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface AlexaDialogTurn {
    String getDialogTurnId();

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @NonNull AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogExtras alexaDialogExtras);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @NonNull AlexaDialogExtras alexaDialogExtras);
}

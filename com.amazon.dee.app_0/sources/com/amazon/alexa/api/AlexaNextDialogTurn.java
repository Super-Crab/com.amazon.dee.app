package com.amazon.alexa.api;

import com.amazon.alexa.client.annotations.NonNull;
/* loaded from: classes6.dex */
public interface AlexaNextDialogTurn {
    String getDialogTurnId();

    void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback);

    void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaAudioMetadata alexaAudioMetadata);

    void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);

    void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback);
}

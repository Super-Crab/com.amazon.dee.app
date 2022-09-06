package com.amazon.alexa.accessory.speechapi.speech;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: NextDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/NextDialogTurn;", "", "getDialogTurnId", "", "startTurn", "", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface NextDialogTurn {
    @NotNull
    String getDialogTurnId();

    void startTurn(@NotNull AccessorySink accessorySink, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull AccessoryAudioMetadata accessoryAudioMetadata, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback);
}

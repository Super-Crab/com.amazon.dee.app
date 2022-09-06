package com.amazon.alexa.accessory.speechapi.speech;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J:\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurn;", "", "getDialogTurnId", "", "startTurn", "", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dataSink", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "dialogExtras", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface DialogTurn {
    @NotNull
    String getDialogTurnId();

    void startTurn(@NotNull AccessoryAudioMetadata accessoryAudioMetadata, @NotNull AccessorySink accessorySink, @Nullable AccessorySink accessorySink2, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback, @NotNull DialogExtras dialogExtras);
}

package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn;
import com.amazon.alexa.accessory.speechapi.voicesdk.mapper.SpeechApiModelMapper;
import com.amazon.alexa.api.compat.AlexaNextDialogTurn;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxNextDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxNextDialogTurn;", "Lcom/amazon/alexa/accessory/speechapi/speech/NextDialogTurn;", "alexaNextDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "(Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;)V", "getAlexaNextDialogTurn", "()Lcom/amazon/alexa/api/compat/AlexaNextDialogTurn;", "getDialogTurnId", "", "startTurn", "", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxNextDialogTurn implements NextDialogTurn {
    @NotNull
    private final AlexaNextDialogTurn alexaNextDialogTurn;

    public VoxNextDialogTurn(@NotNull AlexaNextDialogTurn alexaNextDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaNextDialogTurn, "alexaNextDialogTurn");
        this.alexaNextDialogTurn = alexaNextDialogTurn;
    }

    @NotNull
    public final AlexaNextDialogTurn getAlexaNextDialogTurn() {
        return this.alexaNextDialogTurn;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn
    @NotNull
    public String getDialogTurnId() {
        return this.alexaNextDialogTurn.getDialogTurnId();
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.NextDialogTurn
    public void startTurn(@NotNull AccessorySink audioSink, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull AccessoryAudioMetadata audioMetadata, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(dialogTurnMetricsCallback, "dialogTurnMetricsCallback");
        this.alexaNextDialogTurn.startTurn(((VoxAudioSink) audioSink).getAudioSink(), new VoxDialogTurnStopCallback(dialogTurnStopCallback), SpeechApiModelMapper.from(audioMetadata), new VoxDialogTurnMetricsCallback(dialogTurnMetricsCallback));
    }
}

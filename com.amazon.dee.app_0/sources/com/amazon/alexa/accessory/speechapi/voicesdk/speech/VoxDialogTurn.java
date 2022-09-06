package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.AccessoryAudioMetadata;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogExtras;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurn;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.voicesdk.mapper.SpeechApiModelMapper;
import com.amazon.alexa.api.compat.AlexaDialogTurn;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: VoxDialogTurn.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J:\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxDialogTurn;", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurn;", "alexaDialogTurn", "Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "(Lcom/amazon/alexa/api/compat/AlexaDialogTurn;)V", "getAlexaDialogTurn", "()Lcom/amazon/alexa/api/compat/AlexaDialogTurn;", "getDialogTurnId", "", "startTurn", "", "audioMetadata", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessoryAudioMetadata;", "audioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dataSink", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "dialogExtras", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogExtras;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxDialogTurn implements DialogTurn {
    @NotNull
    private final AlexaDialogTurn alexaDialogTurn;

    public VoxDialogTurn(@NotNull AlexaDialogTurn alexaDialogTurn) {
        Intrinsics.checkParameterIsNotNull(alexaDialogTurn, "alexaDialogTurn");
        this.alexaDialogTurn = alexaDialogTurn;
    }

    @NotNull
    public final AlexaDialogTurn getAlexaDialogTurn() {
        return this.alexaDialogTurn;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurn
    @NotNull
    public String getDialogTurnId() {
        return this.alexaDialogTurn.getDialogTurnId();
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.DialogTurn
    public void startTurn(@NotNull AccessoryAudioMetadata audioMetadata, @NotNull AccessorySink audioSink, @Nullable AccessorySink accessorySink, @NotNull DialogTurnStopCallback dialogTurnStopCallback, @NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback, @NotNull DialogExtras dialogExtras) {
        Intrinsics.checkParameterIsNotNull(audioMetadata, "audioMetadata");
        Intrinsics.checkParameterIsNotNull(audioSink, "audioSink");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Intrinsics.checkParameterIsNotNull(dialogTurnMetricsCallback, "dialogTurnMetricsCallback");
        Intrinsics.checkParameterIsNotNull(dialogExtras, "dialogExtras");
        VoxDataSink voxDataSink = (VoxDataSink) accessorySink;
        this.alexaDialogTurn.startTurn(SpeechApiModelMapper.from(audioMetadata), ((VoxAudioSink) audioSink).getAudioSink(), voxDataSink != null ? voxDataSink.getDataSink() : null, new VoxDialogTurnStopCallback(dialogTurnStopCallback), new VoxDialogTurnMetricsCallback(dialogTurnMetricsCallback), SpeechApiModelMapper.from(dialogExtras));
    }
}

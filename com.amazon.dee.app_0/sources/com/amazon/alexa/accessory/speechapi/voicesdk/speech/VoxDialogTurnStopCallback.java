package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxDialogTurnStopCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxDialogTurnStopCallback;", "Lcom/amazon/alexa/api/AlexaDialogTurnStopCallback;", "dialogTurnStopCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "(Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;)V", "stopRecording", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxDialogTurnStopCallback implements AlexaDialogTurnStopCallback {
    private final DialogTurnStopCallback dialogTurnStopCallback;

    public VoxDialogTurnStopCallback(@NotNull DialogTurnStopCallback dialogTurnStopCallback) {
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        this.dialogTurnStopCallback = dialogTurnStopCallback;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
    public void stopRecording() {
        this.dialogTurnStopCallback.stopRecording();
    }
}

package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.DialogTurnMetricsCallback;
import com.amazon.alexa.accessory.speechapi.voicesdk.mapper.SpeechApiModelMapper;
import com.amazon.alexa.api.compat.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxDialogTurnMetricsCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxDialogTurnMetricsCallback;", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "dialogTurnMetricsCallback", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;", "(Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnMetricsCallback;)V", "onUserPerceivedLatencyData", "", "userPerceivedLatencyData", "Lcom/amazon/alexa/api/compat/metrics/UserPerceivedLatencyData;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxDialogTurnMetricsCallback implements AlexaDialogTurnMetricsCallback {
    private final DialogTurnMetricsCallback dialogTurnMetricsCallback;

    public VoxDialogTurnMetricsCallback(@NotNull DialogTurnMetricsCallback dialogTurnMetricsCallback) {
        Intrinsics.checkParameterIsNotNull(dialogTurnMetricsCallback, "dialogTurnMetricsCallback");
        this.dialogTurnMetricsCallback = dialogTurnMetricsCallback;
    }

    @Override // com.amazon.alexa.api.compat.AlexaDialogTurnMetricsCallback
    public void onUserPerceivedLatencyData(@NotNull UserPerceivedLatencyData userPerceivedLatencyData) {
        Intrinsics.checkParameterIsNotNull(userPerceivedLatencyData, "userPerceivedLatencyData");
        this.dialogTurnMetricsCallback.onDialogTurnUserPerceivedLatencyData(SpeechApiModelMapper.from(userPerceivedLatencyData));
    }
}

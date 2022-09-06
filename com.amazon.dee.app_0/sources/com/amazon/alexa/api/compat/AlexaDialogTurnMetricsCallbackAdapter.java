package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.UserPerceivedLatencyData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlexaDialogTurnMetricsCallbackAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallbackAdapter;", "Lcom/amazon/alexa/api/AlexaDialogTurnMetricsCallback;", "compatListener", "Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "(Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/AlexaDialogTurnMetricsCallback;", "onUserPerceivedLatencyData", "", "userPerceivedLatencyData", "Lcom/amazon/alexa/api/UserPerceivedLatencyData;", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlexaDialogTurnMetricsCallbackAdapter implements com.amazon.alexa.api.AlexaDialogTurnMetricsCallback {
    @NotNull
    private final AlexaDialogTurnMetricsCallback compatListener;

    public AlexaDialogTurnMetricsCallbackAdapter(@NotNull AlexaDialogTurnMetricsCallback compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlexaDialogTurnMetricsCallback getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurnMetricsCallback
    public void onUserPerceivedLatencyData(@NotNull UserPerceivedLatencyData userPerceivedLatencyData) {
        Intrinsics.checkParameterIsNotNull(userPerceivedLatencyData, "userPerceivedLatencyData");
        this.compatListener.onUserPerceivedLatencyData(com.amazon.alexa.api.compat.metrics.UserPerceivedLatencyData.Companion.convertToCompat(userPerceivedLatencyData));
    }
}

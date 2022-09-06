package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Singleton;
/* compiled from: DialogTurnProvider.java */
@Singleton
/* loaded from: classes.dex */
public class snw {
    public OGm zZm(AlexaClientEventBus alexaClientEventBus, esV esv, Lzl lzl, LjN ljN, XWx xWx, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        return new OGm(alexaClientEventBus, esv, lzl, ljN, xWx, alexaDialogTurnStopCallback, alexaDialogTurnMetricsCallback);
    }
}

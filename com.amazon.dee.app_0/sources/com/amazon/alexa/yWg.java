package com.amazon.alexa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.AlexaNextDialogTurn;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
/* compiled from: StartNextDialogTurnCallbacks.java */
/* loaded from: classes.dex */
public class yWg extends QEa implements AlexaNextDialogTurn {
    public yWg(AlexaClientEventBus alexaClientEventBus, esV esv, NEe nEe, shl shlVar, yaw yawVar, snw snwVar, iKQ ikq, LjN ljN, XWx xWx) {
        super(alexaClientEventBus, esv, nEe, shlVar, yawVar, snwVar, ikq, ljN, xWx);
    }

    @VisibleForTesting
    public DialogRequestIdentifier BIo() {
        return this.zQM.LPk();
    }

    @Override // com.amazon.alexa.QEa, com.amazon.alexa.api.AlexaNextDialogTurn
    public String getDialogTurnId() {
        return this.yPL.getValue();
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        zZm(alexaAudioSink, alexaDialogTurnStopCallback, null);
    }

    @Override // com.amazon.alexa.QEa
    public boolean zZm() {
        return false;
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaAudioMetadata alexaAudioMetadata) {
        zZm(alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null);
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaAudioMetadata alexaAudioMetadata, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(false, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, null, null);
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(@NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(false, alexaAudioSink, alexaDialogTurnStopCallback, null, alexaDialogTurnMetricsCallback, null, null);
    }
}

package com.amazon.alexa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioMetadata;
import com.amazon.alexa.api.AlexaAudioSink;
import com.amazon.alexa.api.AlexaDataSink;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.AlexaDialogTurn;
import com.amazon.alexa.api.AlexaDialogTurnMetricsCallback;
import com.amazon.alexa.api.AlexaDialogTurnStopCallback;
import com.amazon.alexa.api.DialogExtras;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
/* compiled from: StartDialogTurnCallbacks.java */
/* loaded from: classes.dex */
public class Jpo extends QEa implements AlexaDialogTurn {
    public Jpo(AlexaClientEventBus alexaClientEventBus, esV esv, NEe nEe, shl shlVar, yaw yawVar, snw snwVar, iKQ ikq, LjN ljN, XWx xWx) {
        super(alexaClientEventBus, esv, nEe, shlVar, yawVar, snwVar, ikq, ljN, xWx);
    }

    @VisibleForTesting
    public DialogRequestIdentifier BIo() {
        return this.zQM.LPk();
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null, null, DialogExtras.zZm);
    }

    @Override // com.amazon.alexa.QEa
    public boolean zZm() {
        return true;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, null, DialogExtras.zZm);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null, alexaDataSink, DialogExtras.zZm);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, alexaDataSink, null);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogExtras alexaDialogExtras) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null, alexaDataSink, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @NonNull AlexaDialogExtras alexaDialogExtras) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, alexaDataSink, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogExtras alexaDialogExtras) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null, null, alexaDialogExtras);
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, @NonNull AlexaDialogExtras alexaDialogExtras) {
        zZm(true, alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, alexaDialogTurnMetricsCallback, null, alexaDialogExtras);
    }
}

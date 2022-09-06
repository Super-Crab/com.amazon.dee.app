package com.amazon.alexa.api;

import android.os.IBinder;
import com.amazon.alexa.api.AlexaUserSpeechProviderMessageProcessor;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
class AlexaDialogTurnMessageSender extends t implements AlexaDialogTurn {
    private static final String TAG = "AlexaDialogTurnMessageSender";

    /* loaded from: classes6.dex */
    static class StartTurnMessagePayload extends BaseMessagePayload {
        StartTurnMessagePayload(ExtendedClient extendedClient, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> messageReceiver, @Nullable MessageReceiver<ApiType_DialogTurnMetricsCallbackMessageType> messageReceiver2, @Nullable AlexaDialogExtras alexaDialogExtras) {
            super(extendedClient);
            add(AlexaDialogTurnArgumentKey.AUDIO_META_DATA, alexaAudioMetadata);
            add(AlexaDialogTurnArgumentKey.AUDIO_STREAM, alexaAudioSink.getReadDescriptor());
            add(AlexaDialogTurnArgumentKey.DIALOG_TURN_STOP_CALLBACK, messageReceiver.getMessenger().getBinder());
            if (alexaDataSink != null) {
                add(AlexaDialogTurnArgumentKey.DATA_STREAM, alexaDataSink.getReadDescriptor());
            }
            if (messageReceiver2 != null) {
                add(AlexaDialogTurnArgumentKey.METRICS_CALLBACK, messageReceiver2.getMessenger().getBinder());
            }
            if (alexaDialogExtras != null) {
                add((Bundles.Key) AlexaDialogTurnArgumentKey.DIALOG_EXTRAS, alexaDialogExtras.getBundle());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogTurnMessageSender(String str, IBinder iBinder, ExtendedClient extendedClient, MessageReceiversManager messageReceiversManager, AlexaUserSpeechProviderMessageProcessor.DialogRequestErrorCallback dialogRequestErrorCallback) {
        super(str, extendedClient, iBinder, messageReceiversManager, dialogRequestErrorCallback);
    }

    @Override // com.amazon.alexa.api.t
    protected String getTag() {
        return TAG;
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, alexaDataSink, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), null, null));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDataSink alexaDataSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogExtras alexaDialogExtras) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, alexaDataSink, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), null, alexaDialogExtras));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, alexaDataSink, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback), null));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDataSink alexaDataSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, AlexaDialogExtras alexaDialogExtras) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, alexaDataSink, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback), alexaDialogExtras));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, null, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), null, null));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogExtras alexaDialogExtras) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, null, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), null, alexaDialogExtras));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(@NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @NonNull AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, null, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback), null));
    }

    @Override // com.amazon.alexa.api.AlexaDialogTurn
    public void startTurn(AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback, AlexaDialogExtras alexaDialogExtras) {
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, new StartTurnMessagePayload(getClient(), alexaAudioMetadata, alexaAudioSink, null, createStopCallbackMessageReceiver(alexaDialogTurnStopCallback), createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback), alexaDialogExtras));
    }
}

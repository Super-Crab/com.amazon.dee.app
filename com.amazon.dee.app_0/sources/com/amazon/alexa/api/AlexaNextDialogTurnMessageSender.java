package com.amazon.alexa.api;

import android.os.IBinder;
import com.amazon.alexa.api.AlexaUserSpeechProviderMessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.Nullable;
/* loaded from: classes6.dex */
class AlexaNextDialogTurnMessageSender extends t implements AlexaNextDialogTurn {
    private static final String TAG = "AlexaNextDialogTurnMessageSender";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class StartNextTurnMessagePayload extends BaseMessagePayload {
        StartNextTurnMessagePayload(ExtendedClient extendedClient, AlexaAudioSink alexaAudioSink, MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaDialogTurnArgumentKey.AUDIO_STREAM, alexaAudioSink.getReadDescriptor());
            add(AlexaDialogTurnArgumentKey.DIALOG_TURN_STOP_CALLBACK, messageReceiver.getMessenger().getBinder());
        }

        public StartNextTurnMessagePayload(ExtendedClient extendedClient, AlexaAudioSink alexaAudioSink, MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> messageReceiver, AlexaAudioMetadata alexaAudioMetadata) {
            this(extendedClient, alexaAudioSink, messageReceiver);
            add(AlexaDialogTurnArgumentKey.AUDIO_META_DATA, alexaAudioMetadata);
        }

        StartNextTurnMessagePayload(ExtendedClient extendedClient, AlexaAudioSink alexaAudioSink, MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> messageReceiver, AlexaAudioMetadata alexaAudioMetadata, MessageReceiver<ApiType_DialogTurnMetricsCallbackMessageType> messageReceiver2) {
            this(extendedClient, alexaAudioSink, messageReceiver, messageReceiver2);
            add(AlexaDialogTurnArgumentKey.AUDIO_META_DATA, alexaAudioMetadata);
        }

        StartNextTurnMessagePayload(ExtendedClient extendedClient, AlexaAudioSink alexaAudioSink, MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> messageReceiver, MessageReceiver<ApiType_DialogTurnMetricsCallbackMessageType> messageReceiver2) {
            this(extendedClient, alexaAudioSink, messageReceiver);
            add(AlexaDialogTurnArgumentKey.METRICS_CALLBACK, messageReceiver2.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaNextDialogTurnMessageSender(String str, IBinder iBinder, ExtendedClient extendedClient, MessageReceiversManager messageReceiversManager, AlexaUserSpeechProviderMessageProcessor.DialogRequestErrorCallback dialogRequestErrorCallback) {
        super(str, extendedClient, iBinder, messageReceiversManager, dialogRequestErrorCallback);
    }

    @Override // com.amazon.alexa.api.t
    protected String getTag() {
        return TAG;
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        startTurn(alexaAudioSink, alexaDialogTurnStopCallback, (AlexaDialogTurnMetricsCallback) null);
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata) {
        startTurn(alexaAudioSink, alexaDialogTurnStopCallback, alexaAudioMetadata, null);
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaAudioMetadata alexaAudioMetadata, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        StartNextTurnMessagePayload startNextTurnMessagePayload;
        MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> createStopCallbackMessageReceiver = createStopCallbackMessageReceiver(alexaDialogTurnStopCallback);
        if (alexaDialogTurnMetricsCallback == null) {
            startNextTurnMessagePayload = new StartNextTurnMessagePayload(getClient(), alexaAudioSink, createStopCallbackMessageReceiver, alexaAudioMetadata);
        } else {
            startNextTurnMessagePayload = new StartNextTurnMessagePayload(getClient(), alexaAudioSink, createStopCallbackMessageReceiver, alexaAudioMetadata, createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback));
        }
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, startNextTurnMessagePayload);
    }

    @Override // com.amazon.alexa.api.AlexaNextDialogTurn
    public void startTurn(AlexaAudioSink alexaAudioSink, AlexaDialogTurnStopCallback alexaDialogTurnStopCallback, @Nullable AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        StartNextTurnMessagePayload startNextTurnMessagePayload;
        MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> createStopCallbackMessageReceiver = createStopCallbackMessageReceiver(alexaDialogTurnStopCallback);
        if (alexaDialogTurnMetricsCallback == null) {
            startNextTurnMessagePayload = new StartNextTurnMessagePayload(getClient(), alexaAudioSink, createStopCallbackMessageReceiver);
        } else {
            startNextTurnMessagePayload = new StartNextTurnMessagePayload(getClient(), alexaAudioSink, createStopCallbackMessageReceiver, createMetricsCallbackMessageReceiver(alexaDialogTurnMetricsCallback));
        }
        tryToSendMessage(AlexaDialogTurnMessageType.START_TURN, startNextTurnMessagePayload);
    }
}

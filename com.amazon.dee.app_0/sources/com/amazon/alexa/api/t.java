package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.amazon.alexa.api.AlexaUserSpeechProviderMessageProcessor;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Collections;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class t extends AlexaBidirectionalMessageSender<AlexaDialogTurnMessageType> {
    private final ExtendedClient client;
    private final AlexaUserSpeechProviderMessageProcessor.DialogRequestErrorCallback dialogRequestErrorCallback;
    private final String dialogTurnId;
    private at<String> dialogTurnTask;
    private MessageReceiver<ApiType_DialogTurnMetricsCallbackMessageType> metricsCallbackMessageReceiver;
    private MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> stopCallbackMessageReceiver;

    /* loaded from: classes6.dex */
    private class a extends MessageProcessor<AlexaDialogTurnMessageType> {
        private a() {
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public AlexaDialogTurnMessageType mo845getMessageType(Message message) {
            try {
                return AlexaDialogTurnMessageType.fromOrdinal(message.what);
            } catch (IllegalArgumentException e) {
                Log.e(t.this.getTag(), "Unrecognized message type", e);
                return AlexaDialogTurnMessageType.UNKNOWN;
            }
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: a */
        public void processMessage(AlexaDialogTurnMessageType alexaDialogTurnMessageType, Bundle bundle, @Nullable Messenger messenger) {
            if (AlexaDialogTurnMessageType.GET_DIALOG_TURN_ID == alexaDialogTurnMessageType) {
                t.this.onDialogTurnId(Bundles.getString(bundle, AlexaDialogTurnArgumentKey.DIALOG_TURN_ID));
                return;
            }
            String tag = t.this.getTag();
            Log.w(tag, "Unsupported message " + alexaDialogTurnMessageType);
        }
    }

    public t(String str, ExtendedClient extendedClient, IBinder iBinder, MessageReceiversManager messageReceiversManager, AlexaUserSpeechProviderMessageProcessor.DialogRequestErrorCallback dialogRequestErrorCallback) {
        super(iBinder, messageReceiversManager);
        Preconditions.notNull(str, "dialog turn id is null");
        Preconditions.notNull(extendedClient, "client is null");
        Preconditions.notNull(dialogRequestErrorCallback, "dialog request error callback is null");
        this.dialogTurnId = str;
        this.client = extendedClient;
        this.dialogRequestErrorCallback = dialogRequestErrorCallback;
    }

    private void abandonAudio(Bundle bundle) {
        abandonSink(bundle, AlexaDialogTurnArgumentKey.AUDIO_STREAM);
        abandonSink(bundle, AlexaDialogTurnArgumentKey.DATA_STREAM);
    }

    private void abandonSink(Bundle bundle, AlexaDialogTurnArgumentKey alexaDialogTurnArgumentKey) {
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getOptionalParcelable(bundle, alexaDialogTurnArgumentKey, ParcelFileDescriptor.class);
        if (parcelFileDescriptor != null) {
            new AlexaDataSink(parcelFileDescriptor, null).close();
        }
    }

    private void discardMetricsCallbackMessageReceiver() {
        if (this.metricsCallbackMessageReceiver != null) {
            getMessageReceiversManager().removeMessageReceiver(this.metricsCallbackMessageReceiver);
            this.metricsCallbackMessageReceiver = null;
        }
    }

    private void discardStopCallbackMessageReceiver() {
        if (this.stopCallbackMessageReceiver != null) {
            getMessageReceiversManager().removeMessageReceiver(this.stopCallbackMessageReceiver);
            this.stopCallbackMessageReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageReceiver<ApiType_DialogTurnMetricsCallbackMessageType> createMetricsCallbackMessageReceiver(AlexaDialogTurnMetricsCallback alexaDialogTurnMetricsCallback) {
        discardMetricsCallbackMessageReceiver();
        this.metricsCallbackMessageReceiver = getMessageReceiversManager().createMessageReceiver(new ApiType_DialogTurnMetricsCallbackProcessor(alexaDialogTurnMetricsCallback));
        return this.metricsCallbackMessageReceiver;
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<AlexaDialogTurnMessageType> createResponseProcessor() {
        return new a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageReceiver<ApiType_DialogTurnStopCallbackMessageType> createStopCallbackMessageReceiver(AlexaDialogTurnStopCallback alexaDialogTurnStopCallback) {
        discardStopCallbackMessageReceiver();
        this.stopCallbackMessageReceiver = getMessageReceiversManager().createMessageReceiver(new ApiType_DialogTurnStopCallbackProcessor(alexaDialogTurnStopCallback));
        return this.stopCallbackMessageReceiver;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ExtendedClient getClient() {
        return this.client;
    }

    public String getDialogTurnId() {
        return this.dialogTurnId;
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<AlexaDialogTurnMessageType> getExpectedMessageTypes() {
        return Collections.singleton(AlexaDialogTurnMessageType.GET_DIALOG_TURN_ID);
    }

    protected abstract String getTag();

    void onDialogTurnId(String str) {
        at<String> atVar = this.dialogTurnTask;
        if (atVar != null) {
            atVar.setResult(str);
            this.dialogTurnTask = null;
        }
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender, com.amazon.alexa.api.Releasable
    public void release() {
        discardStopCallbackMessageReceiver();
        discardMetricsCallbackMessageReceiver();
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void tryToSendMessage(AlexaDialogTurnMessageType alexaDialogTurnMessageType, BaseMessagePayload baseMessagePayload) {
        try {
            sendMessage(alexaDialogTurnMessageType, baseMessagePayload.getBundle());
        } catch (Exception e) {
            Log.e(getTag(), "Failed to send startTurn message", e);
            this.dialogRequestErrorCallback.onError(e);
            abandonAudio(baseMessagePayload.getBundle());
        }
    }
}

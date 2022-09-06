package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaUserSpeechProviderMessageSender extends AlexaMessageSender<AlexaUserSpeechProviderMessageType> implements AlexaUserSpeechProvider {
    private static final String TAG = "AlexaUserSpeechProviderMessageSender";
    private final ExtendedClient client;
    private MessageReceiver<AlexaDialogTurnMessageType> dialogTurnMessageReceiver;
    private final MessageReceiversManager messageReceiversManager;

    /* loaded from: classes6.dex */
    static class DialogDataMessagePayload extends BaseMessagePayload {
        DialogDataMessagePayload(ExtendedClient extendedClient, DialogData dialogData) {
            super(extendedClient);
            add((Bundles.Key) AlexaUserSpeechProviderArgumentKey.DIALOG_DATA, BundleTransformer.getDefaultInstance().toBundle(dialogData));
        }
    }

    /* loaded from: classes6.dex */
    static class DialogRequestDeniedMessagePayload extends BaseMessagePayload {
        DialogRequestDeniedMessagePayload(ExtendedClient extendedClient, DialogRequestDeniedReason dialogRequestDeniedReason) {
            super(extendedClient);
            add((Bundles.Key) AlexaUserSpeechProviderArgumentKey.DIALOG_REQUEST_DENIED_REASON, BundleTransformer.getDefaultInstance().toBundle(dialogRequestDeniedReason));
        }
    }

    /* loaded from: classes6.dex */
    static class DialogTurnDataMessagePayload extends BaseMessagePayload {
        DialogTurnDataMessagePayload(ExtendedClient extendedClient, DialogTurnData dialogTurnData) {
            super(extendedClient);
            add((Bundles.Key) AlexaUserSpeechProviderArgumentKey.DIALOG_TURN_DATA, BundleTransformer.getDefaultInstance().toBundle(dialogTurnData));
        }
    }

    /* loaded from: classes6.dex */
    static class DialogTurnMessagePayload extends BaseMessagePayload {
        DialogTurnMessagePayload(ExtendedClient extendedClient, String str, MessageReceiver<AlexaDialogTurnMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN_ID, str);
            add(AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    static class NextDialogTurnMessagePayload extends BaseMessagePayload {
        NextDialogTurnMessagePayload(ExtendedClient extendedClient, String str, MessageReceiver<AlexaDialogTurnMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN_ID, str);
            add(AlexaUserSpeechProviderArgumentKey.ALEXA_NEXT_DIALOG_TURN, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    static class WakeWordEnabledMessagePayload extends BaseMessagePayload {
        WakeWordEnabledMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaUserSpeechProviderArgumentKey.WAKE_WORD_ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    static class WakeWordMessagePayload extends BaseMessagePayload {
        WakeWordMessagePayload(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(AlexaUserSpeechProviderArgumentKey.WAKE_WORD, str);
        }
    }

    public AlexaUserSpeechProviderMessageSender(IBinder iBinder, ExtendedClient extendedClient, MessageReceiversManager messageReceiversManager) {
        super(iBinder);
        Preconditions.notNull(extendedClient, "client is null");
        Preconditions.notNull(messageReceiversManager, "messageReceiversManager is null");
        this.client = extendedClient;
        this.messageReceiversManager = messageReceiversManager;
    }

    private void disposeDialogTurnMessageReceiver() {
        MessageReceiver<AlexaDialogTurnMessageType> messageReceiver = this.dialogTurnMessageReceiver;
        if (messageReceiver != null) {
            this.messageReceiversManager.removeMessageReceiver(messageReceiver);
            this.dialogTurnMessageReceiver = null;
        }
    }

    MessageReceiver<AlexaDialogTurnMessageType> createDialogTurnMessageReceiver(AlexaDialogTurn alexaDialogTurn) {
        disposeDialogTurnMessageReceiver();
        MessageReceiversManager messageReceiversManager = this.messageReceiversManager;
        this.dialogTurnMessageReceiver = messageReceiversManager.createMessageReceiver(new AlexaDialogTurnMessageProcessor(alexaDialogTurn, messageReceiversManager));
        return this.dialogTurnMessageReceiver;
    }

    MessageReceiver<AlexaDialogTurnMessageType> createNextDialogTurnMessageReceiver(AlexaNextDialogTurn alexaNextDialogTurn) {
        disposeDialogTurnMessageReceiver();
        MessageReceiversManager messageReceiversManager = this.messageReceiversManager;
        this.dialogTurnMessageReceiver = messageReceiversManager.createMessageReceiver(new AlexaNextDialogTurnMessageProcessor(alexaNextDialogTurn, messageReceiversManager));
        return this.dialogTurnMessageReceiver;
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogFinished(DialogData dialogData) {
        disposeDialogTurnMessageReceiver();
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_FINISHED, new DialogDataMessagePayload(this.client, dialogData).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogFinished message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequestDenied(DialogRequestDeniedReason dialogRequestDeniedReason) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_REQUEST_DENIED, new DialogRequestDeniedMessagePayload(this.client, dialogRequestDeniedReason).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogRequestDenied message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequested(AlexaDialogTurn alexaDialogTurn) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_REQUESTED, new DialogTurnMessagePayload(this.client, alexaDialogTurn.getDialogTurnId(), createDialogTurnMessageReceiver(alexaDialogTurn)).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogRequested message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogStarted(DialogData dialogData) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_STARTED, new DialogDataMessagePayload(this.client, dialogData).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogStarted message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnFinished(DialogTurnData dialogTurnData) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_FINISHED, new DialogTurnDataMessagePayload(this.client, dialogTurnData).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogTurnFinished message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_REQUESTED, new NextDialogTurnMessagePayload(this.client, alexaNextDialogTurn.getDialogTurnId(), createNextDialogTurnMessageReceiver(alexaNextDialogTurn)).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogTurnRequested message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnStarted(DialogTurnData dialogTurnData) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_STARTED, new DialogTurnDataMessagePayload(this.client, dialogTurnData).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send onDialogTurnStarted message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void pauseWakeWordDetection(String str) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.PAUSE_WAKEWORD_DETECTION, new WakeWordMessagePayload(this.client, str).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send pauseWakeWordDetection message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void resumeWakeWordDetection(String str) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.RESUME_WAKEWORD_DETECTION, new WakeWordMessagePayload(this.client, str).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send resumeWakeWordDetection message");
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
        try {
            sendMessage(AlexaUserSpeechProviderMessageType.SET_WAKE_WORD_DETECTION_ENABLED, new WakeWordEnabledMessagePayload(this.client, z).getBundle());
        } catch (RemoteException unused) {
            Log.e(TAG, "Failed to send setWakeWordDetectionEnabled message");
        }
    }
}

package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.DialogRequestDeniedReason;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaUserSpeechProviderMessageProcessor extends MessageProcessor<AlexaUserSpeechProviderMessageType> {
    private static final String TAG = "AlexaUserSpeechProviderMessageProcessor";
    private final AlexaUserSpeechProvider alexaUserSpeechProvider;
    private AlexaDialogTurnMessageSender dialogTurnMessageSender;
    private final Provider<MessageReceiversManager> messageReceiversManager;
    private AlexaNextDialogTurnMessageSender nextDialogTurnMessageSender;

    /* renamed from: com.amazon.alexa.api.AlexaUserSpeechProviderMessageProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType = new int[AlexaUserSpeechProviderMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.SET_WAKE_WORD_DETECTION_ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_REQUESTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_REQUEST_DENIED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_STARTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_REQUESTED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_STARTED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_TURN_FINISHED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.ON_DIALOG_FINISHED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.PAUSE_WAKEWORD_DETECTION.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaUserSpeechProviderMessageType[AlexaUserSpeechProviderMessageType.RESUME_WAKEWORD_DETECTION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface DialogRequestErrorCallback {
        void onError(Exception exc);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class ErrorCallback implements DialogRequestErrorCallback {
        ErrorCallback() {
        }

        @Override // com.amazon.alexa.api.AlexaUserSpeechProviderMessageProcessor.DialogRequestErrorCallback
        public void onError(Exception exc) {
            AlexaUserSpeechProviderMessageProcessor.this.alexaUserSpeechProvider.onDialogRequestDenied(new DialogRequestDeniedReason(DialogRequestDeniedReason.Reason.INTERNAL_CLIENT_ERROR_MESSAGING, "Exception while sending dialog turn", exc));
            AlexaUserSpeechProviderMessageProcessor.this.releaseResources();
        }
    }

    public AlexaUserSpeechProviderMessageProcessor(AlexaUserSpeechProvider alexaUserSpeechProvider, Provider<MessageReceiversManager> provider) {
        Preconditions.notNull(alexaUserSpeechProvider, "alexaUserSpeechProvider is null");
        Preconditions.notNull(provider, "messageReceiversManager is null");
        this.messageReceiversManager = provider;
        this.alexaUserSpeechProvider = alexaUserSpeechProvider;
    }

    private AlexaDialogTurn createAlexaDialogTurn(ExtendedClient extendedClient, String str, IBinder iBinder) {
        releaseDialogTurnMessageSender();
        this.dialogTurnMessageSender = new AlexaDialogTurnMessageSender(str, iBinder, extendedClient, this.messageReceiversManager.mo2864get(), new ErrorCallback());
        return this.dialogTurnMessageSender;
    }

    private AlexaNextDialogTurn createAlexaNextDialogTurn(ExtendedClient extendedClient, String str, IBinder iBinder) {
        releaseNextDialogTurnMessageSender();
        this.nextDialogTurnMessageSender = new AlexaNextDialogTurnMessageSender(str, iBinder, extendedClient, this.messageReceiversManager.mo2864get(), new ErrorCallback());
        return this.nextDialogTurnMessageSender;
    }

    private void onDialogFinished(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaUserSpeechProviderArgumentKey.DIALOG_DATA);
        this.alexaUserSpeechProvider.onDialogFinished((DialogData) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, DialogData.class));
        releaseResources();
    }

    private void onDialogRequestDenied(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaUserSpeechProviderArgumentKey.DIALOG_REQUEST_DENIED_REASON);
        this.alexaUserSpeechProvider.onDialogRequestDenied((DialogRequestDeniedReason) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, DialogRequestDeniedReason.class));
        releaseResources();
    }

    private void onDialogRequested(Bundle bundle) {
        this.alexaUserSpeechProvider.onDialogRequested(createAlexaDialogTurn(Bundles.getClient(bundle), Bundles.getString(bundle, AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN_ID), Bundles.getBinder(bundle, AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN)));
    }

    private void onDialogStarted(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaUserSpeechProviderArgumentKey.DIALOG_DATA);
        this.alexaUserSpeechProvider.onDialogStarted((DialogData) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, DialogData.class));
    }

    private void onDialogTurnFinished(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaUserSpeechProviderArgumentKey.DIALOG_TURN_DATA);
        this.alexaUserSpeechProvider.onDialogTurnFinished((DialogTurnData) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, DialogTurnData.class));
    }

    private void onDialogTurnRequested(Bundle bundle) {
        this.alexaUserSpeechProvider.onDialogTurnRequested(createAlexaNextDialogTurn(Bundles.getClient(bundle), Bundles.getString(bundle, AlexaUserSpeechProviderArgumentKey.ALEXA_DIALOG_TURN_ID), Bundles.getBinder(bundle, AlexaUserSpeechProviderArgumentKey.ALEXA_NEXT_DIALOG_TURN)));
    }

    private void onDialogTurnStarted(Bundle bundle) {
        Bundle bundle2 = Bundles.getBundle(bundle, AlexaUserSpeechProviderArgumentKey.DIALOG_TURN_DATA);
        this.alexaUserSpeechProvider.onDialogTurnStarted((DialogTurnData) BundleTransformer.getDefaultInstance().getFromBundle(bundle2, DialogTurnData.class));
    }

    private void pauseWakeWordDetection(Bundle bundle) {
        this.alexaUserSpeechProvider.pauseWakeWordDetection(Bundles.getString(bundle, AlexaUserSpeechProviderArgumentKey.WAKE_WORD));
    }

    private void releaseDialogTurnMessageSender() {
        AlexaDialogTurnMessageSender alexaDialogTurnMessageSender = this.dialogTurnMessageSender;
        if (alexaDialogTurnMessageSender != null) {
            alexaDialogTurnMessageSender.release();
            this.dialogTurnMessageSender = null;
        }
    }

    private void releaseNextDialogTurnMessageSender() {
        AlexaNextDialogTurnMessageSender alexaNextDialogTurnMessageSender = this.nextDialogTurnMessageSender;
        if (alexaNextDialogTurnMessageSender != null) {
            alexaNextDialogTurnMessageSender.release();
            this.nextDialogTurnMessageSender = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseResources() {
        releaseDialogTurnMessageSender();
        releaseNextDialogTurnMessageSender();
    }

    private void resumeWakeWordDetection(Bundle bundle) {
        this.alexaUserSpeechProvider.resumeWakeWordDetection(Bundles.getString(bundle, AlexaUserSpeechProviderArgumentKey.WAKE_WORD));
    }

    private void setWakeWordEnabled(Bundle bundle) {
        this.alexaUserSpeechProvider.setWakeWordDetectionEnabled(Bundles.getBoolean(bundle, AlexaUserSpeechProviderArgumentKey.WAKE_WORD_ENABLED));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaUserSpeechProviderMessageType mo845getMessageType(Message message) {
        try {
            return AlexaUserSpeechProviderMessageType.fromOrdinal(message.what);
        } catch (IllegalStateException e) {
            Log.e(TAG, "Unrecognized message type, ", e);
            return AlexaUserSpeechProviderMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaUserSpeechProviderMessageType alexaUserSpeechProviderMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundles.getClient(bundle);
        switch (alexaUserSpeechProviderMessageType.ordinal()) {
            case 1:
                setWakeWordEnabled(bundle);
                return;
            case 2:
                onDialogRequested(bundle);
                return;
            case 3:
                onDialogRequestDenied(bundle);
                return;
            case 4:
                onDialogStarted(bundle);
                return;
            case 5:
                onDialogTurnRequested(bundle);
                return;
            case 6:
                onDialogTurnStarted(bundle);
                return;
            case 7:
                onDialogTurnFinished(bundle);
                return;
            case 8:
                onDialogFinished(bundle);
                return;
            case 9:
                pauseWakeWordDetection(bundle);
                return;
            case 10:
                resumeWakeWordDetection(bundle);
                return;
            default:
                String str = TAG;
                Log.w(str, "Unsupported message " + alexaUserSpeechProviderMessageType);
                return;
        }
    }
}

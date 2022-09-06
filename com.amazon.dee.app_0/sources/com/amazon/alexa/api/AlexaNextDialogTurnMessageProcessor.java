package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaNextDialogTurnMessageProcessor extends MessageProcessor<AlexaDialogTurnMessageType> {
    private static final String TAG = "AlexaNextDialogTurnMessageProcessor";
    private final AlexaNextDialogTurn dialogTurn;
    private final MessageReceiversManager messageReceiversManager;

    /* renamed from: com.amazon.alexa.api.AlexaNextDialogTurnMessageProcessor$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaDialogTurnMessageType = new int[AlexaDialogTurnMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaDialogTurnMessageType[AlexaDialogTurnMessageType.START_TURN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaDialogTurnMessageType[AlexaDialogTurnMessageType.GET_DIALOG_TURN_ID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class DialogTurnIdMessagePayload extends BaseMessagePayload {
        DialogTurnIdMessagePayload(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(AlexaDialogTurnArgumentKey.DIALOG_TURN_ID, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaNextDialogTurnMessageProcessor(AlexaNextDialogTurn alexaNextDialogTurn, MessageReceiversManager messageReceiversManager) {
        Preconditions.notNull(alexaNextDialogTurn, "dialogTurn is null");
        this.dialogTurn = alexaNextDialogTurn;
        this.messageReceiversManager = messageReceiversManager;
    }

    private String onGetDialogTurnId(Bundle bundle) {
        return this.dialogTurn.getDialogTurnId();
    }

    private void onStartTurn(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaAudioSink alexaAudioSink = new AlexaAudioSink((ParcelFileDescriptor) Bundles.getParcelable(bundle, AlexaDialogTurnArgumentKey.AUDIO_STREAM, ParcelFileDescriptor.class), null);
        ApiType_DialogTurnStopCallbackSender apiType_DialogTurnStopCallbackSender = new ApiType_DialogTurnStopCallbackSender(client, Bundles.getBinder(bundle, AlexaDialogTurnArgumentKey.DIALOG_TURN_STOP_CALLBACK), this.messageReceiversManager);
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getOptionalParcelable(bundle, AlexaDialogTurnArgumentKey.AUDIO_META_DATA, AlexaAudioMetadata.class);
        IBinder optionalBinder = Bundles.getOptionalBinder(bundle, AlexaDialogTurnArgumentKey.METRICS_CALLBACK);
        if (optionalBinder == null) {
            AlexaNextDialogTurn alexaNextDialogTurn = this.dialogTurn;
            if (alexaAudioMetadata != null) {
                alexaNextDialogTurn.startTurn(alexaAudioSink, apiType_DialogTurnStopCallbackSender, alexaAudioMetadata);
                return;
            } else {
                alexaNextDialogTurn.startTurn(alexaAudioSink, apiType_DialogTurnStopCallbackSender);
                return;
            }
        }
        ApiType_DialogTurnMetricsCallbackSender apiType_DialogTurnMetricsCallbackSender = new ApiType_DialogTurnMetricsCallbackSender(client, optionalBinder, this.messageReceiversManager);
        AlexaNextDialogTurn alexaNextDialogTurn2 = this.dialogTurn;
        if (alexaAudioMetadata != null) {
            alexaNextDialogTurn2.startTurn(alexaAudioSink, apiType_DialogTurnStopCallbackSender, alexaAudioMetadata, apiType_DialogTurnMetricsCallbackSender);
        } else {
            alexaNextDialogTurn2.startTurn(alexaAudioSink, apiType_DialogTurnStopCallbackSender, apiType_DialogTurnMetricsCallbackSender);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaDialogTurnMessageType mo845getMessageType(Message message) {
        try {
            return AlexaDialogTurnMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaDialogTurnMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaDialogTurnMessageType alexaDialogTurnMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int ordinal = alexaDialogTurnMessageType.ordinal();
        if (ordinal == 1) {
            onStartTurn(bundle);
        } else if (ordinal != 2) {
            String str = TAG;
            Log.w(str, "Unsupported message " + alexaDialogTurnMessageType);
        } else {
            try {
                reply(messenger, alexaDialogTurnMessageType, new DialogTurnIdMessagePayload(Bundles.getClient(bundle), onGetDialogTurnId(bundle)).getBundle());
            } catch (RemoteException unused) {
                String str2 = TAG;
                Log.e(str2, "Failed to send a response to " + alexaDialogTurnMessageType);
            }
        }
    }
}

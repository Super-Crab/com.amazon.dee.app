package com.amazon.alexa.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.BOa;
import com.amazon.alexa.api.AlexaAudioProviderManagerV1;
import com.amazon.alexa.api.AlexaDialogControllerProxyV2;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.peZ;
import com.amazon.alexa.qSf;
import com.amazon.alexa.utils.validation.Preconditions;
/* loaded from: classes6.dex */
public class AlexaAudioProviderManagerV2 extends AlexaAudioProviderManagerV1 {
    public static final String TAG = "AlexaAudioProviderManagerV2";

    /* renamed from: com.amazon.alexa.api.AlexaAudioProviderManagerV2$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType = new int[AlexaAudioProviderManagerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.START_DIALOG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.START_DIALOG_WITH_METADATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.CONTINUE_DIALOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.STOP_DIALOG_TURN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AlexaAudioProviderManagerV2(Context context, AccountManager accountManager, peZ pez) {
        super(context, accountManager, pez);
    }

    private boolean isClientCorrectVersion(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V1_1_0);
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderManagerV1
    public void continueDialog(Bundle bundle) throws RemoteException {
        String dialogIdentifier;
        if (!isClientCorrectVersion(bundle)) {
            super.continueDialog(bundle);
            return;
        }
        ExtendedClient client = Bundles.getClient(bundle);
        qSf zZm = qSf.zZm(AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY)).getDialogIdentifier());
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        if (!this.dialogControllers.containsKey(zZm)) {
            Log.e(TAG, "Attempting to continue dialog from an unknown controller. Start the dialogfirst.");
            dropAudio(parcelFileDescriptor);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_CONTINUE_INVALID_DIALOG, null);
            return;
        }
        DialogControllerProxyWrapper dialogControllerProxyWrapper = this.dialogControllers.get(zZm);
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, AlexaAudioMetadata.class);
        StringBuilder sb = new StringBuilder();
        sb.append("continueDialog: ");
        sb.append(client);
        sb.append(" ");
        AlexaDialogControllerProxy alexaDialogControllerProxy = dialogControllerProxyWrapper.BIo;
        if (alexaDialogControllerProxy != null) {
            dialogIdentifier = alexaDialogControllerProxy.getDialogIdentifier();
        } else {
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = dialogControllerProxyWrapper.zQM;
            if (alexaDialogControllerProxyV2 != null) {
                dialogIdentifier = alexaDialogControllerProxyV2.getDialogIdentifier();
            } else {
                throw new IllegalStateException("Both proxy and proxyV2 are null");
            }
        }
        sb.append(dialogIdentifier);
        sb.append(" ");
        sb.append(dialogControllerProxyWrapper.getDialogTurnIdentifier());
        sb.toString();
        AlexaDialogExtras alexaDialogExtras = dialogControllerProxyWrapper.zZm;
        if (!shouldAcceptAudio(alexaDialogExtras, false)) {
            Log.i(TAG, "On the lock screen. Dropping audio");
            dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, true);
            showUI(alexaAudioMetadata, alexaDialogExtras);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.SCREEN_LOCKED, alexaDialogExtras.getInvocationType(), dialogControllerProxyWrapper.getDialogTurnIdentifier());
            return;
        }
        ensureConnection();
        if (this.alexaServicesConnection.isConnected()) {
            setActiveSubClient(client);
            AlexaServices.Recognizer.continueDialog(this.alexaServicesConnection, (AlexaDialogControllerProxyV2) dialogControllerProxyWrapper, alexaAudioMetadata, parcelFileDescriptor);
            return;
        }
        Log.i(TAG, "Not connected to Alexa. Dropping audio");
        dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, false);
        reportError(AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, alexaDialogExtras.getInvocationType(), dialogControllerProxyWrapper.getDialogTurnIdentifier());
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderManagerV1
    public void startDialog(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            super.startDialog(bundle);
            return;
        }
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaDialogControllerProxyV2 asInterface = AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY));
        Preconditions.notNull(asInterface, "dialog controller is null");
        qSf zZm = qSf.zZm(asInterface.getDialogIdentifier());
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AudioProviderManagerArgumentKey.DIALOG_EXTRAS);
        DialogExtras dialogExtras = new DialogExtras(optionalBundle);
        DialogControllerProxyWrapper dialogControllerProxyWrapper = new DialogControllerProxyWrapper(dialogExtras, asInterface, new AlexaAudioProviderManagerV1.DialogControllerCleanupCallback(zZm));
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, AlexaAudioMetadata.class);
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        ParcelFileDescriptor parcelFileDescriptor2 = (ParcelFileDescriptor) Bundles.getOptionalParcelable(bundle, AudioProviderManagerArgumentKey.METADATA_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        if (parcelFileDescriptor2 != null) {
            String str = "startDialogWithMetadata: " + client + " " + zZm.getValue();
        } else {
            String str2 = "startDialog: " + client + " " + zZm.getValue();
        }
        if (!shouldAcceptAudio(dialogExtras, BOa.zZm(alexaAudioMetadata))) {
            Log.i(TAG, "On the lock screen. Dropping audio");
            dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, false);
            showUI(alexaAudioMetadata, dialogExtras);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.SCREEN_LOCKED, dialogExtras.getInvocationType());
            return;
        }
        ensureConnection();
        if (this.alexaServicesConnection.isConnected()) {
            setActiveSubClient(client);
            AlexaServices.Recognizer.startDialog(this.alexaServicesConnection, dialogControllerProxyWrapper, alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, optionalBundle);
            return;
        }
        Log.i(TAG, "Not connected to Alexa. Dropping audio");
        dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, false);
        reportError(AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, dialogExtras.getInvocationType());
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderManagerV1
    public void stopDialogTurn(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            super.stopDialogTurn(bundle);
            return;
        }
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaDialogControllerProxyV2 asInterface = AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY));
        String str = "stopDialog: " + client;
        ensureConnection();
        setActiveSubClient(client);
        AlexaServices.Recognizer.stopDialogTurn(this.alexaServicesConnection, asInterface);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaAudioProviderManagerV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaAudioProviderManagerMessageType alexaAudioProviderManagerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        try {
            int ordinal = alexaAudioProviderManagerMessageType.ordinal();
            if (ordinal != 2) {
                if (ordinal == 3) {
                    continueDialog(bundle);
                } else if (ordinal == 4) {
                    stopDialogTurn(bundle);
                } else if (ordinal != 11) {
                    super.processMessage(alexaAudioProviderManagerMessageType, bundle, messenger);
                }
            }
            startDialog(bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message! ", e);
        }
    }
}

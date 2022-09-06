package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.UYN;
import com.amazon.alexa.api.AlexaDialogControllerProxyV2;
import java.util.Collections;
/* loaded from: classes6.dex */
public class AlexaServicesV2 extends AlexaServicesV1 {
    public static final String TAG = "AlexaServicesV2";

    /* renamed from: com.amazon.alexa.api.AlexaServicesV2$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_RECOGNIZING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_DIALOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_DIALOG_WITH_METADATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.CONTINUE_DIALOG.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP_DIALOG_TURN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.ON_CLIENT_CONNECT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CLIENT_CONNECTION_CONTROLLER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public AlexaServicesV2(AlexaClient alexaClient, UYN uyn) {
        super(alexaClient, uyn);
    }

    private LegacyUserSpeechProvider createLegacyUserSpeechProvider(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2, AlexaDialogExtras alexaDialogExtras) {
        return this.legacyUserSpeechProviders.createLegacyUserSpeechProvider(alexaDialogControllerProxyV2, Collections.emptySet(), alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, alexaDialogExtras);
    }

    private void deregisterClientConnectionController(Bundle bundle) throws RemoteException {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterClientConnectionController(client, new ClientConnectionControllerMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CLIENT_CONNECTION_CONTROLLER), client));
    }

    private boolean isClientCorrectVersion(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V1_1_0);
    }

    private void onClientConnect(Bundle bundle) throws RemoteException {
        ExtendedClient client = getClient(bundle);
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.CLIENT_REQUIRES_FOREGROUND);
        boolean z2 = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.ALLOWS_BACKGROUND_ACTIVITY_STARTS);
        this.alexaClient.onClientConnect(client, new ClientConnectionControllerMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CLIENT_CONNECTION_CONTROLLER), client), z, z2, Bundles.getBoolean(bundle, AlexaServicesArgumentKey.FORCE_CAPABILITIES_REFRESH));
    }

    @Override // com.amazon.alexa.api.AlexaServicesV1
    public void continueDialog(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            super.continueDialog(bundle);
            return;
        }
        this.legacyUserSpeechProviders.continueDialog(AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY)), (ParcelFileDescriptor) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class));
    }

    @Override // com.amazon.alexa.api.AlexaServicesV1
    public void startDialog(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            super.startDialog(bundle);
            return;
        }
        ExtendedClient client = getClient(bundle);
        AlexaDialogControllerProxyV2 asInterface = AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY));
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_META_DATA, AlexaAudioMetadata.class);
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.DIALOG_EXTRAS);
        ParcelFileDescriptor parcelFileDescriptor2 = (ParcelFileDescriptor) Bundles.getOptionalParcelable(bundle, AlexaServicesArgumentKey.METADATA_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        DialogExtras dialogExtras = new DialogExtras(optionalBundle);
        LegacyUserSpeechProvider createLegacyUserSpeechProvider = createLegacyUserSpeechProvider(asInterface, alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, dialogExtras);
        this.alexaClient.registerUserSpeechProvider(client, createLegacyUserSpeechProvider, createLegacyUserSpeechProvider.getUserSpeechProviderMetadata());
        this.alexaClient.requestDialog(client, createLegacyUserSpeechProvider, AlexaDialogRequest.builder().setInvocationType(dialogExtras.getInvocationType()).setLaunchType(dialogExtras.getLaunchType()).build());
    }

    @Override // com.amazon.alexa.api.AlexaServicesV1
    public void startRecognizing(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            this.alexaClient.startRecognizing(getClient(bundle), DialogExtras.zZm);
            return;
        }
        ExtendedClient client = getClient(bundle);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.DIALOG_EXTRAS);
        if (optionalBundle != null) {
            this.alexaClient.startRecognizing(client, new DialogExtras(optionalBundle));
        } else {
            this.alexaClient.startRecognizing(client, DialogExtras.zZm);
        }
    }

    @Override // com.amazon.alexa.api.AlexaServicesV1
    public void stopDialogTurn(Bundle bundle) throws RemoteException {
        if (!isClientCorrectVersion(bundle)) {
            super.stopDialogTurn(bundle);
            return;
        }
        this.legacyUserSpeechProviders.stopDialogTurn(AlexaDialogControllerProxyV2.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY)));
    }

    @VisibleForTesting
    public AlexaServicesV2(AlexaClient alexaClient, UYN uyn, LegacyUserSpeechProviders legacyUserSpeechProviders) {
        super(alexaClient, uyn, legacyUserSpeechProviders);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        try {
            int ordinal = alexaServicesMessageType.ordinal();
            if (ordinal == 3) {
                startRecognizing(bundle);
            } else if (ordinal == 46 || ordinal == 5) {
                startDialog(bundle);
            } else if (ordinal == 6) {
                continueDialog(bundle);
            } else if (ordinal == 7) {
                stopDialogTurn(bundle);
            } else if (ordinal == 49) {
                onClientConnect(bundle);
            } else if (ordinal != 50) {
                super.processMessage(alexaServicesMessageType, bundle, messenger);
            } else {
                deregisterClientConnectionController(bundle);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message! ", e);
            this.errorReporter.zZm.notifyHandledException(new UYN.zZm(e));
        }
    }
}

package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Messenger;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.UYN;
/* loaded from: classes6.dex */
public class AlexaServicesApisV1 extends AlexaServicesV2 {
    public final CGv userSpeechProviderFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.AlexaServicesApisV1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_USER_SPEECH_PROVIDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_USER_SPEECH_PROVIDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REQUEST_DIALOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public AlexaServicesApisV1(AlexaClient alexaClient, UYN uyn, CGv cGv) {
        super(alexaClient, uyn);
        this.userSpeechProviderFactory = cGv;
    }

    private void deregisterUserSpeechProvider(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterUserSpeechProvider(client, this.userSpeechProviderFactory.zZm(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_SPEECH_PROVIDER), client));
    }

    private boolean isClientCorrectVersion(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_0_0);
    }

    private void registerUserSpeechProvider(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        IBinder binder = Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_SPEECH_PROVIDER);
        this.alexaClient.registerUserSpeechProvider(client, this.userSpeechProviderFactory.zZm(binder, client), (AlexaUserSpeechProviderMetadata) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, AlexaServicesArgumentKey.USER_SPEECH_PROVIDER_METADATA), AlexaUserSpeechProviderMetadata.class));
    }

    private void requestDialog(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.requestDialog(client, this.userSpeechProviderFactory.zZm(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_SPEECH_PROVIDER), client), new AlexaDialogRequest(Bundles.getBundle(bundle, AlexaServicesArgumentKey.DIALOG_REQUEST)));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientCorrectVersion(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
            return;
        }
        int i = AnonymousClass1.$SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[alexaServicesMessageType.ordinal()];
        if (i == 1) {
            registerUserSpeechProvider(bundle);
        } else if (i == 2) {
            deregisterUserSpeechProvider(bundle);
        } else if (i != 3) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
        } else {
            requestDialog(bundle);
        }
    }
}

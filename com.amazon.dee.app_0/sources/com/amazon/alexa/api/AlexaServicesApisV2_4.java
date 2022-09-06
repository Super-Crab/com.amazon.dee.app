package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.Mlj;
import com.amazon.alexa.UYN;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public class AlexaServicesApisV2_4 extends AlexaServicesApisV2_3_1 {
    public static final String TAG = "AlexaServicesApisV2_4";
    public final MessageReceiversManager messageReceiversManager;

    /* renamed from: com.amazon.alexa.api.AlexaServicesApisV2_4$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ATTENTION_SYSTEM_LISTENER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ATTENTION_SYSTEM_LISTENER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_LOCALES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_LOCALES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_BASE_URLS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class CurrentLocalesMessagePayload extends BaseMessagePayload {
        public CurrentLocalesMessagePayload(ExtendedClient extendedClient, List<String> list) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CURRENT_LOCALES, list);
        }
    }

    public AlexaServicesApisV2_4(AlexaClient alexaClient, UYN uyn, CGv cGv, Mlj mlj, AlexaVisualTaskFactory alexaVisualTaskFactory, MessageReceiversManager messageReceiversManager) {
        super(alexaClient, uyn, cGv, mlj, alexaVisualTaskFactory, messageReceiversManager);
        this.messageReceiversManager = messageReceiversManager;
    }

    private void deregisterAttentionSystemListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAttentionSystemListener(client, new ApiType_AttentionSystemListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.ATTENTION_SYSTEM_LISTENER), this.messageReceiversManager));
    }

    private void getLocales(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        ExtendedClient client = getClient(bundle);
        List<String> locales = this.alexaClient.getLocales(client);
        if (locales == null) {
            locales = Collections.emptyList();
        }
        try {
            reply(messenger, alexaServicesMessageType, new CurrentLocalesMessagePayload(client, locales).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message!", e);
        }
    }

    private boolean isClientVersionSupported(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_4_0);
    }

    private void registerAttentionSystemListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAttentionSystemListener(client, new ApiType_AttentionSystemListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.ATTENTION_SYSTEM_LISTENER), this.messageReceiversManager));
    }

    private void setBaseURLs(Bundle bundle) {
        this.alexaClient.setBaseURLs(getClient(bundle), new AlexaBaseURLs(Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.BASE_URLS)));
    }

    private void setLocales(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        ArrayList<String> stringArrayList = Bundles.getStringArrayList(bundle, AlexaServicesArgumentKey.CURRENT_LOCALES);
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.FORCE_LOCALE_UPDATE);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.API_CALLBACK);
        if (optionalBundle != null) {
            this.alexaClient.setLocales(client, stringArrayList, new ApiCallback(optionalBundle), z);
            return;
        }
        this.alexaClient.setLocales(client, stringArrayList, z);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesApisV2_3_1, com.amazon.alexa.api.AlexaServicesApisV2_3, com.amazon.alexa.api.AlexaServiceApisV2_2_1, com.amazon.alexa.api.AlexaServiceApisV2_2, com.amazon.alexa.api.AlexaServicesApisV2_1, com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientVersionSupported(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
            return;
        }
        switch (alexaServicesMessageType.ordinal()) {
            case 105:
                registerAttentionSystemListener(bundle);
                return;
            case 106:
                deregisterAttentionSystemListener(bundle);
                return;
            case 107:
                setLocales(bundle);
                return;
            case 108:
                getLocales(alexaServicesMessageType, bundle, messenger);
                return;
            case 109:
            default:
                super.processMessage(alexaServicesMessageType, bundle, messenger);
                return;
            case 110:
                setBaseURLs(bundle);
                return;
        }
    }
}

package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.GvA;
import com.amazon.alexa.UYN;
/* loaded from: classes6.dex */
public class AlexaServicesApisV2_1 extends AlexaServicesApisV1 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.AlexaServicesApisV2_1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_CARD_RENDERER_LISTENER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CARD_RENDERER_LISTENER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AlexaServicesApisV2_1(AlexaClient alexaClient, UYN uyn, CGv cGv) {
        super(alexaClient, uyn, cGv);
    }

    private void deregisterCardRendererListener(Bundle bundle) {
        if (!isClientCorrectVersion(bundle)) {
            super.deregisterAlexaCardRendererListener(bundle);
            return;
        }
        this.alexaClient.deregisterAlexaCardRendererListener(getClient(bundle), new GvA(new AlexaCardListenerMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CARD_LISTENER))));
    }

    private boolean isClientCorrectVersion(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_1_0);
    }

    private void registerCardRendererListener(Bundle bundle) {
        if (!isClientCorrectVersion(bundle)) {
            super.registerAlexaCardRendererListener(bundle);
            return;
        }
        this.alexaClient.registerAlexaCardRendererListener(getClient(bundle), new GvA(new AlexaCardListenerMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CARD_LISTENER))));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[alexaServicesMessageType.ordinal()];
        if (i == 1) {
            registerCardRendererListener(bundle);
        } else if (i != 2) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
        } else {
            deregisterCardRendererListener(bundle);
        }
    }
}

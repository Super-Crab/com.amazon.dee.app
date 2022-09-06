package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.Mlj;
import com.amazon.alexa.UYN;
import java.util.List;
/* loaded from: classes6.dex */
public class AlexaServiceApisV2_2_1 extends AlexaServiceApisV2_2 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.AlexaServiceApisV2_2_1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SEND_ALEXA_EVENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public AlexaServiceApisV2_2_1(AlexaClient alexaClient, UYN uyn, CGv cGv, Mlj mlj) {
        super(alexaClient, uyn, cGv, mlj);
    }

    private boolean isClientVersionSupported(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_2_1);
    }

    private void sendAlexaEvent(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        AlexaEvent alexaEvent = (AlexaEvent) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.ALEXA_EVENT, AlexaEvent.class);
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.REQUIRES_CONTEXTS);
        List<AlexaContext> optionalParcelableList = Bundles.getOptionalParcelableList(bundle, AlexaServicesArgumentKey.ALEXA_CONTEXTS, AlexaContext.class);
        if (optionalParcelableList != null) {
            this.alexaClient.sendAlexaEvent(client, alexaEvent, optionalParcelableList);
        } else {
            this.alexaClient.sendAlexaEvent(client, alexaEvent, z);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServiceApisV2_2, com.amazon.alexa.api.AlexaServicesApisV2_1, com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientVersionSupported(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
        } else if (AnonymousClass1.$SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[alexaServicesMessageType.ordinal()] != 1) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
        } else {
            sendAlexaEvent(bundle);
        }
    }
}

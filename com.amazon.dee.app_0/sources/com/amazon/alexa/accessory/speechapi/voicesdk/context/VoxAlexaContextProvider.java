package com.amazon.alexa.accessory.speechapi.voicesdk.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.context.MessageContext;
import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.api.AlexaContext;
import com.amazon.alexa.api.AlexaContextProvider;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxAlexaContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/context/VoxAlexaContextProvider;", "Lcom/amazon/alexa/api/AlexaContextProvider;", "messageContextProvider", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "(Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;)V", "getAlexaContext", "Lcom/amazon/alexa/api/AlexaContext;", "toAlexaContext", "messageContext", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContext;", "Companion", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxAlexaContextProvider implements AlexaContextProvider {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VoxAlexaContextProvider:";
    private final MessageContextProvider messageContextProvider;

    /* compiled from: VoxAlexaContextProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/context/VoxAlexaContextProvider$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public VoxAlexaContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        this.messageContextProvider = messageContextProvider;
    }

    private final AlexaContext toAlexaContext(MessageContext messageContext) {
        MessageHeader messageHeader = messageContext.getMessageHeader();
        return new AlexaContext(AlexaHeader.builder().setNamespace(messageHeader.getNamespace()).setName(messageHeader.getName()).setCorrelationToken(messageHeader.getCorrelationToken()).setMessageId(messageHeader.getMessageId()).setPayloadVersion(messageHeader.getPayloadVersion()).build(), new AlexaPayload(messageContext.getMessagePayload().getPayload()));
    }

    @Override // com.amazon.alexa.api.AlexaContextProvider
    @NotNull
    public AlexaContext getAlexaContext() {
        Logger.d("VoxAlexaContextProvider: getAlexaContext");
        return toAlexaContext(this.messageContextProvider.getMessageContext());
    }
}

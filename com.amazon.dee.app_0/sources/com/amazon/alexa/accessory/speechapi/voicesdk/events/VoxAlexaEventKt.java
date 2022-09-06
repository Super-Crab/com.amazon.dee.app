package com.amazon.alexa.accessory.speechapi.voicesdk.events;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.alexa.api.AlexaHeader;
import com.amazon.alexa.api.AlexaPayload;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxAlexaEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toAlexaEvent", "Lcom/amazon/alexa/api/AlexaEvent;", "Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxAlexaEventKt {
    @NotNull
    public static final AlexaEvent toAlexaEvent(@NotNull MessageEvent toAlexaEvent) {
        Intrinsics.checkParameterIsNotNull(toAlexaEvent, "$this$toAlexaEvent");
        Logger.d("VoxAlexaEvent: MessageEvent.toAlexaEvent");
        MessageHeader messageHeader = toAlexaEvent.getMessageHeader();
        return new AlexaEvent(AlexaHeader.builder().setNamespace(messageHeader.getNamespace()).setName(messageHeader.getName()).setCorrelationToken(messageHeader.getCorrelationToken()).setMessageId(messageHeader.getMessageId()).setPayloadVersion(messageHeader.getPayloadVersion()).build(), new AlexaPayload(toAlexaEvent.getMessagePayload().getPayload()));
    }
}

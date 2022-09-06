package com.amazon.alexa.accessory.speechapi.csm.events;

import amazon.speech.simclient.event.EventMetadata;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.context.MessageHeader;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toCsmEvent", "Lcom/amazon/alexa/accessory/speechapi/csm/events/CsmEvent;", "Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmEventKt {
    @NotNull
    public static final CsmEvent toCsmEvent(@NotNull MessageEvent toCsmEvent) {
        Intrinsics.checkParameterIsNotNull(toCsmEvent, "$this$toCsmEvent");
        Logger.d("CsmEvent: MessageEvent.toCsmEvent");
        MessageHeader messageHeader = toCsmEvent.getMessageHeader();
        return new CsmEvent(new EventMetadata(messageHeader.getMessageId(), messageHeader.getNamespace(), messageHeader.getName()), toCsmEvent.getMessagePayload().getPayload());
    }
}

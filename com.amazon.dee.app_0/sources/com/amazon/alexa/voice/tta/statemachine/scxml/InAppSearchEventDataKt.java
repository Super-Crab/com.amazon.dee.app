package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.scxml.SCXMLContext;
import com.amazon.scxml.SCXMLEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchEventData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0000¨\u0006\u0004"}, d2 = {"getTypedEventData", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchEventData;", "Lcom/amazon/scxml/SCXMLContext;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", "AlexaMobileAndroidVoice-TTA_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class InAppSearchEventDataKt {
    @Nullable
    public static final InAppSearchEventData getTypedEventData(@NotNull SCXMLContext<InAppSearchContext> getTypedEventData) {
        Intrinsics.checkParameterIsNotNull(getTypedEventData, "$this$getTypedEventData");
        SCXMLEvent sCXMLEvent = getTypedEventData.get_event();
        Object data = sCXMLEvent != null ? sCXMLEvent.getData() : null;
        if (!(data instanceof InAppSearchEventData)) {
            data = null;
        }
        return (InAppSearchEventData) data;
    }
}

package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.scxml.SCXMLContext;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: NativeEvaluators.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0014\u0010\b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/NativeEvaluators;", "Lcom/amazon/scxml/logging/Loggable;", "()V", "isMultiturnFlagSet", "", "context", "Lcom/amazon/scxml/SCXMLContext;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", "simbaCallContextsMatch", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class NativeEvaluators extends Loggable {
    public final boolean isMultiturnFlagSet(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        boolean isMultiturn = context.getUserContext().isMultiturn();
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "isMultiturnFlagSet, result = " + isMultiturn);
        return isMultiturn;
    }

    public final boolean simbaCallContextsMatch(@NotNull SCXMLContext<InAppSearchContext> context) {
        SIMBACallContext callContext;
        SIMBACallContext currentCallContext;
        Intrinsics.checkParameterIsNotNull(context, "context");
        InAppSearchEventData typedEventData = InAppSearchEventDataKt.getTypedEventData(context);
        if (typedEventData == null || (callContext = typedEventData.getCallContext()) == null || (currentCallContext = context.getUserContext().getCurrentCallContext()) == null) {
            return false;
        }
        boolean areEqual = Intrinsics.areEqual(callContext.getUuid(), currentCallContext.getUuid());
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "simbaCallContextsMatch, result = " + areEqual);
        return areEqual;
    }
}

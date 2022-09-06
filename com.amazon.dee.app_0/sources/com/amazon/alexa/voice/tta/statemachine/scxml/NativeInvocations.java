package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.scxml.SCXMLContext;
import com.amazon.scxml.logging.Loggable;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: NativeInvocations.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u000e\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u000f\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0010\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0011\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0012\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0013\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0014\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0015\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0016\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0017\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0018\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0014\u0010\u0019\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/NativeInvocations;", "Lcom/amazon/scxml/logging/Loggable;", "()V", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS, "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;", "getActions", "()Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;", "setActions", "(Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;)V", "callSIMBA", "", "context", "Lcom/amazon/scxml/SCXMLContext;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", "cancelExistingSIMBACall", "clearIsMultiturnFlag", "notifyNoSimbaResults", "removeExistingAvsResponse", "sendTextToAVS", "setIsMultiturnFlag", "showExistingAvsResponse", "showSIMBAResults", "storeNewAvsTextResponse", "storeNewRenderCardDir", "storeUserUtterance", "tryRenderCardDirective", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class NativeInvocations extends Loggable {
    @Nullable
    private InAppSearchStateMachineActions actions;

    public final void callSIMBA(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "callSIMBA");
        String currentUtterance = context.getUserContext().getCurrentUtterance();
        context.getUserContext().setCurrentUtterance("");
        TextResponse currentAvsTextResponseForSIMBA = context.getUserContext().getCurrentAvsTextResponseForSIMBA();
        if (currentAvsTextResponseForSIMBA != null) {
            context.getUserContext().setCurrentAvsTextResponseForSIMBA(null);
            SIMBACallContext sIMBACallContext = new SIMBACallContext(currentAvsTextResponseForSIMBA, currentUtterance);
            context.getUserContext().setCurrentCallContext(sIMBACallContext);
            InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
            if (inAppSearchStateMachineActions == null) {
                return;
            }
            inAppSearchStateMachineActions.callSIMBA(sIMBACallContext);
        }
    }

    public final void cancelExistingSIMBACall(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "cancelExistingSIMBACall");
        SIMBACallContext currentCallContext = context.getUserContext().getCurrentCallContext();
        InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
        if (inAppSearchStateMachineActions != null) {
            inAppSearchStateMachineActions.notifySIMBACallCancelled(currentCallContext);
        }
        context.getUserContext().setCurrentCallContext(null);
    }

    public final void clearIsMultiturnFlag(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "clearIsMultiturnFlag");
        context.getUserContext().setMultiturn(false);
    }

    @Nullable
    public final InAppSearchStateMachineActions getActions() {
        return this.actions;
    }

    public final void notifyNoSimbaResults(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "notifyNoSimbaResults");
        InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
        if (inAppSearchStateMachineActions != null) {
            inAppSearchStateMachineActions.notifyNoSimbaResults();
        }
    }

    public final void removeExistingAvsResponse(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "removeExistingAvsResponse");
        context.getUserContext().setCurrentAvsTextResponseForDisplay(null);
        context.getUserContext().setCurrentTtaResponseCard(null);
        context.getUserContext().setCurrentCallContext(null);
    }

    public final void sendTextToAVS(@NotNull SCXMLContext<InAppSearchContext> context) {
        CharSequence trim;
        InAppSearchStateMachineActions inAppSearchStateMachineActions;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "sendTextToAVS");
        String currentUtterance = context.getUserContext().getCurrentUtterance();
        if (currentUtterance != null) {
            trim = StringsKt__StringsKt.trim((CharSequence) currentUtterance);
            if (Intrinsics.areEqual(trim.toString(), "") || (inAppSearchStateMachineActions = this.actions) == null) {
                return;
            }
            inAppSearchStateMachineActions.showUserUtterance(currentUtterance);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }

    public final void setActions(@Nullable InAppSearchStateMachineActions inAppSearchStateMachineActions) {
        this.actions = inAppSearchStateMachineActions;
    }

    public final void setIsMultiturnFlag(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "setIsMultiturnFlag");
        context.getUserContext().setMultiturn(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.amazon.alexa.voice.ui.tta.TtaResponseCard, com.amazon.alexa.api.compat.TextResponse] */
    public final void showExistingAvsResponse(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "showExistingAvsResponse");
        try {
            TextResponse currentAvsTextResponseForDisplay = context.getUserContext().getCurrentAvsTextResponseForDisplay();
            if (currentAvsTextResponseForDisplay != null) {
                InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
                if (inAppSearchStateMachineActions != null) {
                    inAppSearchStateMachineActions.showExistingAvsTextResponse(currentAvsTextResponseForDisplay);
                }
                Loggable.Companion.getLog().d(getTAG(), "showed avs TextResponse");
                TtaResponseCard currentTtaResponseCard = context.getUserContext().getCurrentTtaResponseCard();
                if (currentTtaResponseCard != null) {
                    InAppSearchStateMachineActions inAppSearchStateMachineActions2 = this.actions;
                    if (inAppSearchStateMachineActions2 != null) {
                        inAppSearchStateMachineActions2.renderCardData(currentTtaResponseCard);
                    }
                    Loggable.Companion.getLog().d(getTAG(), "rendered card data together with AVS TextResponse");
                }
            }
        } finally {
            context.getUserContext().setCurrentAvsTextResponseForDisplay(null);
            context.getUserContext().setCurrentTtaResponseCard(null);
        }
    }

    public final void showSIMBAResults(@NotNull SCXMLContext<InAppSearchContext> context) {
        List<SearchResult> emptyList;
        List<SearchResult> emptyList2;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "showSIMBAResults");
        InAppSearchContext userContext = context.getUserContext();
        InAppSearchEventData typedEventData = InAppSearchEventDataKt.getTypedEventData(context);
        if (typedEventData == null || (emptyList = typedEventData.getSimbaResults()) == null) {
            emptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        userContext.setCurrentSIMBAResults(emptyList);
        List<SearchResult> currentSIMBAResults = context.getUserContext().getCurrentSIMBAResults();
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        context.getUserContext().setCurrentSIMBAResults(emptyList2);
        SIMBACallContext currentCallContext = context.getUserContext().getCurrentCallContext();
        if (currentCallContext == null) {
            Loggable.Companion.getLog().e("voice-tta", "expected non-nil callContext");
        }
        if (currentSIMBAResults.isEmpty()) {
            InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
            if (inAppSearchStateMachineActions == null) {
                return;
            }
            inAppSearchStateMachineActions.notifyNoSimbaResults();
            return;
        }
        InAppSearchStateMachineActions inAppSearchStateMachineActions2 = this.actions;
        if (inAppSearchStateMachineActions2 == null) {
            return;
        }
        inAppSearchStateMachineActions2.showSIMBAResults(currentSIMBAResults, currentCallContext);
    }

    public final void storeNewAvsTextResponse(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "storeNewAvsTextResponse");
        InAppSearchEventData typedEventData = InAppSearchEventDataKt.getTypedEventData(context);
        TextResponse avsTextResponse = typedEventData != null ? typedEventData.getAvsTextResponse() : null;
        context.getUserContext().setCurrentAvsTextResponseForDisplay(avsTextResponse);
        context.getUserContext().setCurrentAvsTextResponseForSIMBA(avsTextResponse);
    }

    public final void storeNewRenderCardDir(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "storeNewRenderCardDir");
        InAppSearchContext userContext = context.getUserContext();
        InAppSearchEventData typedEventData = InAppSearchEventDataKt.getTypedEventData(context);
        userContext.setCurrentTtaResponseCard(typedEventData != null ? typedEventData.getTtaResponseCard() : null);
    }

    public final void storeUserUtterance(@NotNull SCXMLContext<InAppSearchContext> context) {
        String str;
        CharSequence trim;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "storeUserUtterance");
        InAppSearchContext userContext = context.getUserContext();
        InAppSearchEventData typedEventData = InAppSearchEventDataKt.getTypedEventData(context);
        if (typedEventData == null || (str = typedEventData.getUserText()) == null) {
            str = "";
        }
        trim = StringsKt__StringsKt.trim((CharSequence) str);
        userContext.setCurrentUtterance(trim.toString());
    }

    public final void tryRenderCardDirective(@NotNull SCXMLContext<InAppSearchContext> context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Loggable.Companion.getLog().i(getTAG(), "tryRenderCardDirective");
        if (context.getUserContext().getCurrentAvsTextResponseForDisplay() != null) {
            return;
        }
        TtaResponseCard currentTtaResponseCard = context.getUserContext().getCurrentTtaResponseCard();
        if (currentTtaResponseCard != null) {
            context.getUserContext().setCurrentTtaResponseCard(null);
            InAppSearchStateMachineActions inAppSearchStateMachineActions = this.actions;
            if (inAppSearchStateMachineActions != null) {
                inAppSearchStateMachineActions.renderCardData(currentTtaResponseCard);
            }
            Loggable.Companion.getLog().d(getTAG(), "rendered card data");
            return;
        }
        Loggable.Companion.getLog().d(getTAG(), "did not render card data");
    }
}

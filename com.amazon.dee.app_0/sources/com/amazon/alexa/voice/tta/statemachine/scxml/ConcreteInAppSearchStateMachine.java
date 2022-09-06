package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.scxml.SCXMLStateMachine;
import com.amazon.scxml.logging.Loggable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchStateMachineBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0012\u0010\f\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J \u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0015\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\u0016\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/ConcreteInAppSearchStateMachine;", "Lcom/amazon/scxml/logging/Loggable;", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachine;", "scxml", "Lcom/amazon/scxml/SCXMLStateMachine;", "(Lcom/amazon/scxml/SCXMLStateMachine;)V", "getProcessor", "handleGotAvsTextResponse", "", "textResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "handleGotExpectTextDirective", "handleGotRenderCardDirective", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "handleGotSIMBAResults", "simbaResults", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "callContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "handleSIMBAError", "handleSIMBATimeout", "handleWillSendText", "text", "", "start", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final class ConcreteInAppSearchStateMachine extends Loggable implements InAppSearchStateMachine {
    private final SCXMLStateMachine scxml;

    public ConcreteInAppSearchStateMachine(@NotNull SCXMLStateMachine scxml) {
        Intrinsics.checkParameterIsNotNull(scxml, "scxml");
        this.scxml = scxml;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    @NotNull
    public SCXMLStateMachine getProcessor() {
        return this.scxml;
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleGotAvsTextResponse(@NotNull TextResponse textResponse) {
        Intrinsics.checkParameterIsNotNull(textResponse, "textResponse");
        Loggable.Companion.getLog().i(getTAG(), "handleGotAvsTextResponse");
        this.scxml.sendEvent("gotAvsTextResponse", new InAppSearchEventData(textResponse, null, null, null, null, 30, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleGotExpectTextDirective() {
        Loggable.Companion.getLog().i(getTAG(), "handleGotExpectTextDirective");
        this.scxml.sendEvent("gotExpectTextDir", new InAppSearchEventData(null, null, null, null, null, 31, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleGotRenderCardDirective(@Nullable TtaResponseCard ttaResponseCard) {
        Loggable.Companion.getLog().i(getTAG(), "handleGotRenderCardDirective");
        this.scxml.sendEvent("gotRenderCardDirective", new InAppSearchEventData(null, null, ttaResponseCard, null, null, 27, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleGotSIMBAResults(@NotNull List<SearchResult> simbaResults, @Nullable SIMBACallContext sIMBACallContext) {
        Intrinsics.checkParameterIsNotNull(simbaResults, "simbaResults");
        Loggable.Companion.getLog().i(getTAG(), "handleGotSIMBAResults");
        if (simbaResults.isEmpty()) {
            Loggable.Companion.getLog().i(getTAG(), "simbaResults.isEmpty() == true");
            this.scxml.sendEvent("onEmptySIMBAResults", new InAppSearchEventData(null, null, null, null, sIMBACallContext, 15, null));
            return;
        }
        Loggable.Companion.getLog().i(getTAG(), "simbaResults.isEmpty() == false");
        this.scxml.sendEvent("gotSIMBAResults", new InAppSearchEventData(null, simbaResults, null, null, sIMBACallContext, 13, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleSIMBAError(@Nullable SIMBACallContext sIMBACallContext) {
        Loggable.Companion.getLog().i(getTAG(), "handleSIMBAError");
        this.scxml.sendEvent("onSIMBAError", new InAppSearchEventData(null, null, null, null, sIMBACallContext, 15, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleSIMBATimeout(@Nullable SIMBACallContext sIMBACallContext) {
        Loggable.Companion.getLog().i(getTAG(), "handleSIMBATimeout");
        this.scxml.sendEvent("onSIMBATimeout", new InAppSearchEventData(null, null, null, null, sIMBACallContext, 15, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void handleWillSendText(@NotNull String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        Loggable.Companion.getLog().i(getTAG(), "handleWillSendText");
        this.scxml.sendEvent("willSendText", new InAppSearchEventData(null, null, null, text, null, 23, null));
    }

    @Override // com.amazon.alexa.voice.tta.statemachine.scxml.InAppSearchStateMachine
    public void start() {
        this.scxml.start();
    }
}

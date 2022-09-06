package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.amazon.scxml.SCXMLStateMachine;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0005H&J\u0012\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J \u0010\f\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u0012\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0012\u0010\u0013\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H&J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0005H&¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachine;", "", "getProcessor", "Lcom/amazon/scxml/SCXMLStateMachine;", "handleGotAvsTextResponse", "", "textResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "handleGotExpectTextDirective", "handleGotRenderCardDirective", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "handleGotSIMBAResults", "simbaResults", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "callContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "handleSIMBAError", "handleSIMBATimeout", "handleWillSendText", "text", "", "start", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface InAppSearchStateMachine {
    @NotNull
    SCXMLStateMachine getProcessor();

    void handleGotAvsTextResponse(@NotNull TextResponse textResponse);

    void handleGotExpectTextDirective();

    void handleGotRenderCardDirective(@Nullable TtaResponseCard ttaResponseCard);

    void handleGotSIMBAResults(@NotNull List<SearchResult> list, @Nullable SIMBACallContext sIMBACallContext);

    void handleSIMBAError(@Nullable SIMBACallContext sIMBACallContext);

    void handleSIMBATimeout(@Nullable SIMBACallContext sIMBACallContext);

    void handleWillSendText(@NotNull String str);

    void start();
}

package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchStateMachineActions.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J \u0010\u000e\u001a\u00020\u00032\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H&¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchStateMachineActions;", "", "callSIMBA", "", "callContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "notifyNoSimbaResults", "notifySIMBACallCancelled", "renderCardData", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD, "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "showExistingAvsTextResponse", "textResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "showSIMBAResults", "results", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "showUserUtterance", "text", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface InAppSearchStateMachineActions {
    void callSIMBA(@NotNull SIMBACallContext sIMBACallContext);

    void notifyNoSimbaResults();

    void notifySIMBACallCancelled(@Nullable SIMBACallContext sIMBACallContext);

    void renderCardData(@NotNull TtaResponseCard ttaResponseCard);

    void showExistingAvsTextResponse(@NotNull TextResponse textResponse);

    void showSIMBAResults(@NotNull List<SearchResult> list, @Nullable SIMBACallContext sIMBACallContext);

    void showUserUtterance(@NotNull String str);
}

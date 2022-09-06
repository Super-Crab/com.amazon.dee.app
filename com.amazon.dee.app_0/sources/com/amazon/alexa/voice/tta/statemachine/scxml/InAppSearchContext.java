package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchContext.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010'\"\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchContext;", "", "()V", "currentAvsTextResponseForDisplay", "Lcom/amazon/alexa/api/compat/TextResponse;", "getCurrentAvsTextResponseForDisplay", "()Lcom/amazon/alexa/api/compat/TextResponse;", "setCurrentAvsTextResponseForDisplay", "(Lcom/amazon/alexa/api/compat/TextResponse;)V", "currentAvsTextResponseForSIMBA", "getCurrentAvsTextResponseForSIMBA", "setCurrentAvsTextResponseForSIMBA", "currentCallContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "getCurrentCallContext", "()Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "setCurrentCallContext", "(Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;)V", "currentSIMBAResults", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "getCurrentSIMBAResults", "()Ljava/util/List;", "setCurrentSIMBAResults", "(Ljava/util/List;)V", "currentTtaResponseCard", "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "getCurrentTtaResponseCard", "()Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "setCurrentTtaResponseCard", "(Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;)V", "currentUtterance", "", "getCurrentUtterance", "()Ljava/lang/String;", "setCurrentUtterance", "(Ljava/lang/String;)V", "isMultiturn", "", "()Z", "setMultiturn", "(Z)V", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class InAppSearchContext {
    @Nullable
    private TextResponse currentAvsTextResponseForDisplay;
    @Nullable
    private TextResponse currentAvsTextResponseForSIMBA;
    @Nullable
    private SIMBACallContext currentCallContext;
    @NotNull
    private List<SearchResult> currentSIMBAResults;
    @Nullable
    private TtaResponseCard currentTtaResponseCard;
    @NotNull
    private String currentUtterance = "";
    private boolean isMultiturn;

    public InAppSearchContext() {
        List<SearchResult> emptyList;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.currentSIMBAResults = emptyList;
    }

    @Nullable
    public final TextResponse getCurrentAvsTextResponseForDisplay() {
        return this.currentAvsTextResponseForDisplay;
    }

    @Nullable
    public final TextResponse getCurrentAvsTextResponseForSIMBA() {
        return this.currentAvsTextResponseForSIMBA;
    }

    @Nullable
    public final SIMBACallContext getCurrentCallContext() {
        return this.currentCallContext;
    }

    @NotNull
    public final List<SearchResult> getCurrentSIMBAResults() {
        return this.currentSIMBAResults;
    }

    @Nullable
    public final TtaResponseCard getCurrentTtaResponseCard() {
        return this.currentTtaResponseCard;
    }

    @NotNull
    public final String getCurrentUtterance() {
        return this.currentUtterance;
    }

    public final boolean isMultiturn() {
        return this.isMultiturn;
    }

    public final void setCurrentAvsTextResponseForDisplay(@Nullable TextResponse textResponse) {
        this.currentAvsTextResponseForDisplay = textResponse;
    }

    public final void setCurrentAvsTextResponseForSIMBA(@Nullable TextResponse textResponse) {
        this.currentAvsTextResponseForSIMBA = textResponse;
    }

    public final void setCurrentCallContext(@Nullable SIMBACallContext sIMBACallContext) {
        this.currentCallContext = sIMBACallContext;
    }

    public final void setCurrentSIMBAResults(@NotNull List<SearchResult> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.currentSIMBAResults = list;
    }

    public final void setCurrentTtaResponseCard(@Nullable TtaResponseCard ttaResponseCard) {
        this.currentTtaResponseCard = ttaResponseCard;
    }

    public final void setCurrentUtterance(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.currentUtterance = str;
    }

    public final void setMultiturn(boolean z) {
        this.isMultiturn = z;
    }
}

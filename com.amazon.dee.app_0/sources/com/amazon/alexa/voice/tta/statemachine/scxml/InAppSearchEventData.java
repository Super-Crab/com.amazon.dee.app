package com.amazon.alexa.voice.tta.statemachine.scxml;

import com.amazon.alexa.api.compat.TextResponse;
import com.amazon.alexa.voice.tta.simba.SearchResult;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: InAppSearchEventData.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\fHÆ\u0003JK\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\nHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006$"}, d2 = {"Lcom/amazon/alexa/voice/tta/statemachine/scxml/InAppSearchEventData;", "", "avsTextResponse", "Lcom/amazon/alexa/api/compat/TextResponse;", "simbaResults", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "ttaResponseCard", "Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "userText", "", "callContext", "Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "(Lcom/amazon/alexa/api/compat/TextResponse;Ljava/util/List;Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;Ljava/lang/String;Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;)V", "getAvsTextResponse", "()Lcom/amazon/alexa/api/compat/TextResponse;", "getCallContext", "()Lcom/amazon/alexa/voice/tta/statemachine/scxml/SIMBACallContext;", "getSimbaResults", "()Ljava/util/List;", "getTtaResponseCard", "()Lcom/amazon/alexa/voice/ui/tta/TtaResponseCard;", "getUserText", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class InAppSearchEventData {
    @Nullable
    private final TextResponse avsTextResponse;
    @Nullable
    private final SIMBACallContext callContext;
    @Nullable
    private final List<SearchResult> simbaResults;
    @Nullable
    private final TtaResponseCard ttaResponseCard;
    @Nullable
    private final String userText;

    public InAppSearchEventData() {
        this(null, null, null, null, null, 31, null);
    }

    public InAppSearchEventData(@Nullable TextResponse textResponse, @Nullable List<SearchResult> list, @Nullable TtaResponseCard ttaResponseCard, @Nullable String str, @Nullable SIMBACallContext sIMBACallContext) {
        this.avsTextResponse = textResponse;
        this.simbaResults = list;
        this.ttaResponseCard = ttaResponseCard;
        this.userText = str;
        this.callContext = sIMBACallContext;
    }

    public static /* synthetic */ InAppSearchEventData copy$default(InAppSearchEventData inAppSearchEventData, TextResponse textResponse, List list, TtaResponseCard ttaResponseCard, String str, SIMBACallContext sIMBACallContext, int i, Object obj) {
        if ((i & 1) != 0) {
            textResponse = inAppSearchEventData.avsTextResponse;
        }
        List<SearchResult> list2 = list;
        if ((i & 2) != 0) {
            list2 = inAppSearchEventData.simbaResults;
        }
        List list3 = list2;
        if ((i & 4) != 0) {
            ttaResponseCard = inAppSearchEventData.ttaResponseCard;
        }
        TtaResponseCard ttaResponseCard2 = ttaResponseCard;
        if ((i & 8) != 0) {
            str = inAppSearchEventData.userText;
        }
        String str2 = str;
        if ((i & 16) != 0) {
            sIMBACallContext = inAppSearchEventData.callContext;
        }
        return inAppSearchEventData.copy(textResponse, list3, ttaResponseCard2, str2, sIMBACallContext);
    }

    @Nullable
    public final TextResponse component1() {
        return this.avsTextResponse;
    }

    @Nullable
    public final List<SearchResult> component2() {
        return this.simbaResults;
    }

    @Nullable
    public final TtaResponseCard component3() {
        return this.ttaResponseCard;
    }

    @Nullable
    public final String component4() {
        return this.userText;
    }

    @Nullable
    public final SIMBACallContext component5() {
        return this.callContext;
    }

    @NotNull
    public final InAppSearchEventData copy(@Nullable TextResponse textResponse, @Nullable List<SearchResult> list, @Nullable TtaResponseCard ttaResponseCard, @Nullable String str, @Nullable SIMBACallContext sIMBACallContext) {
        return new InAppSearchEventData(textResponse, list, ttaResponseCard, str, sIMBACallContext);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof InAppSearchEventData)) {
                return false;
            }
            InAppSearchEventData inAppSearchEventData = (InAppSearchEventData) obj;
            return Intrinsics.areEqual(this.avsTextResponse, inAppSearchEventData.avsTextResponse) && Intrinsics.areEqual(this.simbaResults, inAppSearchEventData.simbaResults) && Intrinsics.areEqual(this.ttaResponseCard, inAppSearchEventData.ttaResponseCard) && Intrinsics.areEqual(this.userText, inAppSearchEventData.userText) && Intrinsics.areEqual(this.callContext, inAppSearchEventData.callContext);
        }
        return true;
    }

    @Nullable
    public final TextResponse getAvsTextResponse() {
        return this.avsTextResponse;
    }

    @Nullable
    public final SIMBACallContext getCallContext() {
        return this.callContext;
    }

    @Nullable
    public final List<SearchResult> getSimbaResults() {
        return this.simbaResults;
    }

    @Nullable
    public final TtaResponseCard getTtaResponseCard() {
        return this.ttaResponseCard;
    }

    @Nullable
    public final String getUserText() {
        return this.userText;
    }

    public int hashCode() {
        TextResponse textResponse = this.avsTextResponse;
        int i = 0;
        int hashCode = (textResponse != null ? textResponse.hashCode() : 0) * 31;
        List<SearchResult> list = this.simbaResults;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        TtaResponseCard ttaResponseCard = this.ttaResponseCard;
        int hashCode3 = (hashCode2 + (ttaResponseCard != null ? ttaResponseCard.hashCode() : 0)) * 31;
        String str = this.userText;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        SIMBACallContext sIMBACallContext = this.callContext;
        if (sIMBACallContext != null) {
            i = sIMBACallContext.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InAppSearchEventData(avsTextResponse=");
        outline107.append(this.avsTextResponse);
        outline107.append(", simbaResults=");
        outline107.append(this.simbaResults);
        outline107.append(", ttaResponseCard=");
        outline107.append(this.ttaResponseCard);
        outline107.append(", userText=");
        outline107.append(this.userText);
        outline107.append(", callContext=");
        outline107.append(this.callContext);
        outline107.append(")");
        return outline107.toString();
    }

    public /* synthetic */ InAppSearchEventData(TextResponse textResponse, List list, TtaResponseCard ttaResponseCard, String str, SIMBACallContext sIMBACallContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : textResponse, (i & 2) != 0 ? null : list, (i & 4) != 0 ? null : ttaResponseCard, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : sIMBACallContext);
    }
}

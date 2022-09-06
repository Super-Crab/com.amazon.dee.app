package com.amazon.alexa.voice.tta.typing;

import com.amazon.alexa.voice.tta.search.SearchInteractor;
import com.amazon.alexa.voice.tta.suggestions.TtaSuggestionsInteractor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TtaInteractorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/voice/tta/typing/TtaInteractorProvider;", "", "typingInteractor", "Lcom/amazon/alexa/voice/tta/typing/TypingInteractor;", "searchInteractor", "Lcom/amazon/alexa/voice/tta/search/SearchInteractor;", "suggestionsInteractor", "Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionsInteractor;", "(Lcom/amazon/alexa/voice/tta/typing/TypingInteractor;Lcom/amazon/alexa/voice/tta/search/SearchInteractor;Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionsInteractor;)V", "getSearchInteractor", "()Lcom/amazon/alexa/voice/tta/search/SearchInteractor;", "getSuggestionsInteractor", "()Lcom/amazon/alexa/voice/tta/suggestions/TtaSuggestionsInteractor;", "getTypingInteractor", "()Lcom/amazon/alexa/voice/tta/typing/TypingInteractor;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class TtaInteractorProvider {
    @NotNull
    private final SearchInteractor searchInteractor;
    @NotNull
    private final TtaSuggestionsInteractor suggestionsInteractor;
    @NotNull
    private final TypingInteractor typingInteractor;

    public TtaInteractorProvider(@NotNull TypingInteractor typingInteractor, @NotNull SearchInteractor searchInteractor, @NotNull TtaSuggestionsInteractor suggestionsInteractor) {
        Intrinsics.checkParameterIsNotNull(typingInteractor, "typingInteractor");
        Intrinsics.checkParameterIsNotNull(searchInteractor, "searchInteractor");
        Intrinsics.checkParameterIsNotNull(suggestionsInteractor, "suggestionsInteractor");
        this.typingInteractor = typingInteractor;
        this.searchInteractor = searchInteractor;
        this.suggestionsInteractor = suggestionsInteractor;
    }

    public static /* synthetic */ TtaInteractorProvider copy$default(TtaInteractorProvider ttaInteractorProvider, TypingInteractor typingInteractor, SearchInteractor searchInteractor, TtaSuggestionsInteractor ttaSuggestionsInteractor, int i, Object obj) {
        if ((i & 1) != 0) {
            typingInteractor = ttaInteractorProvider.typingInteractor;
        }
        if ((i & 2) != 0) {
            searchInteractor = ttaInteractorProvider.searchInteractor;
        }
        if ((i & 4) != 0) {
            ttaSuggestionsInteractor = ttaInteractorProvider.suggestionsInteractor;
        }
        return ttaInteractorProvider.copy(typingInteractor, searchInteractor, ttaSuggestionsInteractor);
    }

    @NotNull
    public final TypingInteractor component1() {
        return this.typingInteractor;
    }

    @NotNull
    public final SearchInteractor component2() {
        return this.searchInteractor;
    }

    @NotNull
    public final TtaSuggestionsInteractor component3() {
        return this.suggestionsInteractor;
    }

    @NotNull
    public final TtaInteractorProvider copy(@NotNull TypingInteractor typingInteractor, @NotNull SearchInteractor searchInteractor, @NotNull TtaSuggestionsInteractor suggestionsInteractor) {
        Intrinsics.checkParameterIsNotNull(typingInteractor, "typingInteractor");
        Intrinsics.checkParameterIsNotNull(searchInteractor, "searchInteractor");
        Intrinsics.checkParameterIsNotNull(suggestionsInteractor, "suggestionsInteractor");
        return new TtaInteractorProvider(typingInteractor, searchInteractor, suggestionsInteractor);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TtaInteractorProvider)) {
                return false;
            }
            TtaInteractorProvider ttaInteractorProvider = (TtaInteractorProvider) obj;
            return Intrinsics.areEqual(this.typingInteractor, ttaInteractorProvider.typingInteractor) && Intrinsics.areEqual(this.searchInteractor, ttaInteractorProvider.searchInteractor) && Intrinsics.areEqual(this.suggestionsInteractor, ttaInteractorProvider.suggestionsInteractor);
        }
        return true;
    }

    @NotNull
    public final SearchInteractor getSearchInteractor() {
        return this.searchInteractor;
    }

    @NotNull
    public final TtaSuggestionsInteractor getSuggestionsInteractor() {
        return this.suggestionsInteractor;
    }

    @NotNull
    public final TypingInteractor getTypingInteractor() {
        return this.typingInteractor;
    }

    public int hashCode() {
        TypingInteractor typingInteractor = this.typingInteractor;
        int i = 0;
        int hashCode = (typingInteractor != null ? typingInteractor.hashCode() : 0) * 31;
        SearchInteractor searchInteractor = this.searchInteractor;
        int hashCode2 = (hashCode + (searchInteractor != null ? searchInteractor.hashCode() : 0)) * 31;
        TtaSuggestionsInteractor ttaSuggestionsInteractor = this.suggestionsInteractor;
        if (ttaSuggestionsInteractor != null) {
            i = ttaSuggestionsInteractor.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TtaInteractorProvider(typingInteractor=");
        outline107.append(this.typingInteractor);
        outline107.append(", searchInteractor=");
        outline107.append(this.searchInteractor);
        outline107.append(", suggestionsInteractor=");
        outline107.append(this.suggestionsInteractor);
        outline107.append(")");
        return outline107.toString();
    }
}

package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.voice.ui.tta.TtaMessageResponseListener;
import com.amazon.alexa.voice.ui.tta.search.ItemRoute;
import com.amazon.alexa.voice.ui.tta.search.ResultItem;
import com.amazon.alexa.voice.ui.tta.search.TtaResultHandler;
import com.amazon.alexa.voice.ui.tta.search.TtaResultListener;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: DefaultTtaResultHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\t\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/voice/tta/search/DefaultTtaResultHandler;", "Lcom/amazon/alexa/voice/ui/tta/search/TtaResultHandler;", "()V", "interactorReference", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/voice/tta/search/SearchInteractor;", "initialize", "", "searchInteractor", "onResultSelected", "itemRoute", "Lcom/amazon/alexa/voice/ui/tta/search/ItemRoute;", "resultItem", "Lcom/amazon/alexa/voice/ui/tta/search/ResultItem;", "setResponseListener", "ttaMessageResponseListener", "Lcom/amazon/alexa/voice/ui/tta/TtaMessageResponseListener;", "setResultListener", "ttaResultListener", "Lcom/amazon/alexa/voice/ui/tta/search/TtaResultListener;", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class DefaultTtaResultHandler implements TtaResultHandler {
    private WeakReference<SearchInteractor> interactorReference;

    public final void initialize(@NotNull SearchInteractor searchInteractor) {
        Intrinsics.checkParameterIsNotNull(searchInteractor, "searchInteractor");
        this.interactorReference = new WeakReference<>(searchInteractor);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaResultHandler
    public void onResultSelected(@NotNull ResultItem resultItem) {
        SearchInteractor searchInteractor;
        Intrinsics.checkParameterIsNotNull(resultItem, "resultItem");
        WeakReference<SearchInteractor> weakReference = this.interactorReference;
        if (weakReference == null || (searchInteractor = weakReference.get()) == null) {
            return;
        }
        searchInteractor.onResultSelected(resultItem);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaResultHandler
    public void setResponseListener(@NotNull TtaMessageResponseListener ttaMessageResponseListener) {
        SearchInteractor searchInteractor;
        Intrinsics.checkParameterIsNotNull(ttaMessageResponseListener, "ttaMessageResponseListener");
        WeakReference<SearchInteractor> weakReference = this.interactorReference;
        if (weakReference == null || (searchInteractor = weakReference.get()) == null) {
            return;
        }
        searchInteractor.setResponseListener(ttaMessageResponseListener);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaResultHandler
    public void setResultListener(@NotNull TtaResultListener ttaResultListener) {
        SearchInteractor searchInteractor;
        Intrinsics.checkParameterIsNotNull(ttaResultListener, "ttaResultListener");
        WeakReference<SearchInteractor> weakReference = this.interactorReference;
        if (weakReference == null || (searchInteractor = weakReference.get()) == null) {
            return;
        }
        searchInteractor.setResultListener(ttaResultListener);
    }

    @Override // com.amazon.alexa.voice.ui.tta.search.TtaResultHandler
    public void onResultSelected(@NotNull ItemRoute itemRoute) {
        SearchInteractor searchInteractor;
        Intrinsics.checkParameterIsNotNull(itemRoute, "itemRoute");
        WeakReference<SearchInteractor> weakReference = this.interactorReference;
        if (weakReference == null || (searchInteractor = weakReference.get()) == null) {
            return;
        }
        searchInteractor.onResultSelected(itemRoute);
    }
}

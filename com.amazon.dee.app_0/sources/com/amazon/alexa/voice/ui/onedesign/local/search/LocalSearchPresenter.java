package com.amazon.alexa.voice.ui.onedesign.local.search;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract;
import com.amazon.alexa.voice.ui.onedesign.space.EmptySpace;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes11.dex */
public final class LocalSearchPresenter implements LocalSearchContract.Presenter {
    private Disposable disposable;
    private final CardInteractionTracker interactionTracker;
    private final LocalSearchContract.Interactor interactor;
    private final LocalSearchPresentationHelper localSearchPresentationHelper;
    private final CardMetricsInteractor metricsInteractor;
    private final LocalSearchContract.View view;

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchPresenter$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$onedesign$local$search$LocalSearchSortKey = new int[LocalSearchSortKey.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$onedesign$local$search$LocalSearchSortKey[LocalSearchSortKey.RELEVANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    static class LocalSearchPresentationHelper {
        private final Resources resources;

        LocalSearchPresentationHelper(Resources resources) {
            this.resources = resources;
        }

        String getDataSourceName(List<? extends LocalCardModel.BusinessModel> list) {
            return list.get(0).getDataSource();
        }

        boolean hasSingleDataSource(List<? extends LocalCardModel.BusinessModel> list) {
            HashSet hashSet = new HashSet();
            for (LocalCardModel.BusinessModel businessModel : list) {
                hashSet.add(businessModel.getDataSource());
            }
            return hashSet.size() == 1;
        }

        CharSequence makeSubTitle(String str) {
            if (LocalSearchSortKey.from(str).ordinal() != 2) {
                return this.resources.getText(R.string.voice_ui_od_local_nearby);
            }
            return this.resources.getText(R.string.voice_ui_od_local_top_rated);
        }
    }

    public LocalSearchPresenter(LocalSearchContract.View view, LocalSearchContract.Interactor interactor, Resources resources, CardMetricsInteractor cardMetricsInteractor) {
        this(view, interactor, cardMetricsInteractor, new CardInteractionTracker(), new LocalSearchPresentationHelper(resources));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void itemClicked(LocalSearchItemModel localSearchItemModel) {
        this.interactor.showBusiness((LocalCardModel.BusinessModel) localSearchItemModel.getTag());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void start() {
        LocalCard card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(card.getTitle());
        this.view.setSubTitle(this.localSearchPresentationHelper.makeSubTitle(card.getSortKey()));
        Observable startWithItem = Observable.fromIterable(card.getBusinessList()).map($$Lambda$LocalSearchPresenter$7qYWBsexoS4tyowgpic76sfHB7Y.INSTANCE).cast(Object.class).startWithItem(new EmptySpace());
        final LocalSearchContract.View view = this.view;
        view.getClass();
        this.disposable = startWithItem.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.local.search.-$$Lambda$LsCWTO1u1-fA-nwGJrRnO-3AfdI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                LocalSearchContract.View.this.add(obj);
            }
        });
        this.metricsInteractor.reportCardShown(LocalCard.class.getSimpleName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(this.interactor.getCard().getClass().getSimpleName(), this.interactionTracker.wasInteracted());
    }

    @VisibleForTesting
    public LocalSearchPresenter(LocalSearchContract.View view, LocalSearchContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, CardInteractionTracker cardInteractionTracker, LocalSearchPresentationHelper localSearchPresentationHelper) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
        this.interactionTracker = cardInteractionTracker;
        this.localSearchPresentationHelper = localSearchPresentationHelper;
        this.disposable = null;
    }
}

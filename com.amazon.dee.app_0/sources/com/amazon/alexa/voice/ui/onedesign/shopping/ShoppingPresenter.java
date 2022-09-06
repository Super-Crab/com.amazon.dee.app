package com.amazon.alexa.voice.ui.onedesign.shopping;

import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract;
import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItem;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.Collections;
import java.util.Map;
/* loaded from: classes11.dex */
public final class ShoppingPresenter implements ShoppingContract.Presenter {
    private static final Map<String, Integer> MARKETPLACES_REQUIRING_LEGAL_TEXT = Collections.singletonMap("IT", Integer.valueOf(R.string.voice_ui_od_shopping_italy_legal_text));
    private final ShoppingContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final ShoppingContract.View view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    public ShoppingPresenter(ShoppingContract.View view, ShoppingContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, Resources resources) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
        this.resources = resources;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    public /* synthetic */ ShoppingItem lambda$start$0$ShoppingPresenter(int i, ProductModel productModel) throws Throwable {
        return new ShoppingItem.Builder().product(productModel).legalText(this.resources.getString(i)).build();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void start() {
        this.view.floodBackgroundToStatusBar();
        ShoppingCard card = this.interactor.getCard();
        String userMarketplace = this.interactor.getUserMarketplace();
        final int intValue = MARKETPLACES_REQUIRING_LEGAL_TEXT.containsKey(userMarketplace) ? MARKETPLACES_REQUIRING_LEGAL_TEXT.get(userMarketplace).intValue() : R.string.voice_ui_od_empty;
        Observable map = Observable.fromIterable(card.getProductList()).map(new Function() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.-$$Lambda$ShoppingPresenter$4t8psgvCWUeDnSxsz2GXNY5YM2E
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return ShoppingPresenter.this.lambda$start$0$ShoppingPresenter(intValue, (ProductModel) obj);
            }
        });
        final ShoppingContract.View view = this.view;
        view.getClass();
        this.disposable = map.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.-$$Lambda$yhdZKDlQwsZmkfVYVSp_6eLqy3Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                ShoppingContract.View.this.add((ShoppingItem) obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}

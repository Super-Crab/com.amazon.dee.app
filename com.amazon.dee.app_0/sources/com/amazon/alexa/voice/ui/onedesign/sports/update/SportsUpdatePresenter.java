package com.amazon.alexa.voice.ui.onedesign.sports.update;

import com.amazon.alexa.voice.ui.onedesign.header.SingleLineHeader;
import com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class SportsUpdatePresenter implements SportsUpdateContract.Presenter {
    private Disposable disposable = null;
    private final SportsUpdateContract.Interactor interactor;
    private final SportsUpdateContract.View view;

    public SportsUpdatePresenter(SportsUpdateContract.View view, SportsUpdateContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Presenter
    public void start() {
        SportsUpdateCard card = this.interactor.getCard();
        Observable startWithItem = Observable.fromIterable(card.getGameList()).cast(Object.class).startWithItem(new SingleLineHeader(this.view.appendTitle(card.getTitle())));
        final SportsUpdateContract.View view = this.view;
        view.getClass();
        this.disposable = startWithItem.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.sports.update.-$$Lambda$vBan1FKb7NQH2uTWVNfqfhm5WcU
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SportsUpdateContract.View.this.add(obj);
            }
        });
    }
}

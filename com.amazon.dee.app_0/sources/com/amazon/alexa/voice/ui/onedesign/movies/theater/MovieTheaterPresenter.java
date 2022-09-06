package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes11.dex */
public final class MovieTheaterPresenter implements MovieTheaterContract.Presenter {
    private final MovieTheaterContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final MovieTheaterContract.View view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    public MovieTheaterPresenter(MovieTheaterContract.View view, MovieTheaterContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getTheater().getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MovieTheaterTime lambda$start$0(MoviesCardModel.TimeModel timeModel) throws Throwable {
        return new MovieTheaterTime(timeModel.getTime());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void backClicked() {
        this.interactor.close();
        this.metricsInteractor.reportNavigationToInternalCard(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void locationClicked() {
        this.interactor.openLocation();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void start() {
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(this.interactor.getTitle());
        this.view.setSubTitle(this.interactor.getSubTitle());
        MoviesCard.Theater theater = this.interactor.getTheater();
        this.view.setTheater(theater.getName(), theater.getLocation());
        Observable cast = Observable.fromIterable(theater.getTimeList()).map($$Lambda$MovieTheaterPresenter$djIg8GgBR1Q_bBFz6qxHr2a0Iw.INSTANCE).cast(Object.class);
        final MovieTheaterContract.View view = this.view;
        view.getClass();
        this.disposable = cast.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.theater.-$$Lambda$RDE6-M8KEastJ6JTCDO5p2y6OWw
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MovieTheaterContract.View.this.addItem(obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}

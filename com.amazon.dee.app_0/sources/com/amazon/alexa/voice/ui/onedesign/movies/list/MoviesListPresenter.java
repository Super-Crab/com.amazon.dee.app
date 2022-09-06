package com.amazon.alexa.voice.ui.onedesign.movies.list;

import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesRatingText;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListPoster;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
/* loaded from: classes11.dex */
public final class MoviesListPresenter implements MoviesListContract.Presenter {
    private static final String IMBD_ATTRIBUTION = "IMDb";
    private final MoviesListContract.Interactor interactor;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final MoviesListContract.View view;
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    public MoviesListPresenter(MoviesListContract.View view, Resources resources, MoviesListContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.resources = resources;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getCard().getClass().getSimpleName();
    }

    private CharSequence makeRatingContentDescription(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_movie_rating_content_description, charSequence);
    }

    private CharSequence makeReviewScoreContentDescription(MoviesCardModel.RatingModel ratingModel) {
        return this.resources.getString(R.string.voice_ui_od_movie_review_score_content_description, Double.valueOf(ratingModel.getAverage()), Double.valueOf(ratingModel.getMaximum()));
    }

    private CharSequence makeReviewScoreText(MoviesCardModel.RatingModel ratingModel) {
        return MoviesRatingText.fromRating(ratingModel);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    public /* synthetic */ MoviesListPoster lambda$start$0$MoviesListPresenter(MoviesCardModel.MovieModel movieModel) throws Throwable {
        return new MoviesListPoster.Builder().title(movieModel.getName()).reviewScore(makeReviewScoreText(movieModel.getImdbRating())).reviewScoreContentDescription(makeReviewScoreContentDescription(movieModel.getImdbRating())).rating(movieModel.getRating()).ratingContentDescription(makeRatingContentDescription(movieModel.getRating())).imageUrl(movieModel.getImageUrl()).tag(movieModel).build();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void linkClicked(MoviesListLinkModel moviesListLinkModel) {
        this.interactor.openLink((String) moviesListLinkModel.getTag());
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void posterClicked(MoviesListPosterModel moviesListPosterModel) {
        this.interactor.openMovieShowtimes((MoviesCard.Movie) moviesListPosterModel.getTag());
        this.metricsInteractor.reportNavigationToInternalCard(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void start() {
        MoviesCard card = this.interactor.getCard();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(card.getTitle());
        this.view.setSubTitle(card.getSubTitle());
        Observable map = Observable.fromIterable(card.getMovieList()).map(new Function() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$MoviesListPresenter$XdA1C0l-bbLXWycaHOVXfd7Yq38
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MoviesListPresenter.this.lambda$start$0$MoviesListPresenter((MoviesCardModel.MovieModel) obj);
            }
        });
        if (IMBD_ATTRIBUTION.equals(card.getAttribution())) {
            this.view.setTitleAttributionImage(R.drawable.ic_od_provider_imdb_24dp);
            Observable concatWith = map.cast(Object.class).concatWith(Observable.just(new MoviesListLink(card.getLinkText(), card.getLinkUrl())));
            final MoviesListContract.View view = this.view;
            view.getClass();
            this.disposable = concatWith.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$e0vDjU17kOh36EwSLtFIAEP-Aoc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MoviesListContract.View.this.add(obj);
                }
            });
        } else {
            Observable cast = map.cast(Object.class);
            final MoviesListContract.View view2 = this.view;
            view2.getClass();
            this.disposable = cast.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$e0vDjU17kOh36EwSLtFIAEP-Aoc
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    MoviesListContract.View.this.add(obj);
                }
            });
        }
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}

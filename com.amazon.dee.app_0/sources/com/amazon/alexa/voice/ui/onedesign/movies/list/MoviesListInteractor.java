package com.amazon.alexa.voice.ui.onedesign.movies.list;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract;
/* loaded from: classes11.dex */
public final class MoviesListInteractor implements MoviesListContract.Interactor {
    private final MoviesCard card;
    private final MoviesListContract.Mediator mediator;

    public MoviesListInteractor(MoviesCard moviesCard, MoviesListContract.Mediator mediator) {
        this.card = moviesCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Interactor
    public MoviesCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Interactor
    public void openLink(String str) {
        this.mediator.openLinkUrl(str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Interactor
    public void openMovieShowtimes(MoviesCard.Movie movie) {
        this.mediator.openMovie(this.card.getSubTitle(), movie);
    }
}

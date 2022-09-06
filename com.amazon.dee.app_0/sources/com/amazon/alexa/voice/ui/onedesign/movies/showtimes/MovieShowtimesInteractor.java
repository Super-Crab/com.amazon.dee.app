package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract;
/* loaded from: classes11.dex */
public final class MovieShowtimesInteractor implements MovieShowtimesContract.Interactor {
    private final CharSequence date;
    private final MovieShowtimesContract.Mediator mediator;
    private final MoviesCard.Movie movie;

    public MovieShowtimesInteractor(CharSequence charSequence, MoviesCard.Movie movie, MovieShowtimesContract.Mediator mediator) {
        this.date = charSequence;
        this.movie = movie;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Interactor
    public CharSequence getDate() {
        return this.date;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Interactor
    public MoviesCard.Movie getMovie() {
        return this.movie;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Interactor
    public void openLink() {
        this.mediator.openLinkUrl(this.movie.getLinkUrl());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Interactor
    public void openTheater(MoviesCard.Theater theater) {
        this.mediator.openTheater(this.movie.getName(), this.date, theater);
    }
}

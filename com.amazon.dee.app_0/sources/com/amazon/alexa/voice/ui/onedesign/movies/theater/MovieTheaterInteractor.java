package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract;
/* loaded from: classes11.dex */
public final class MovieTheaterInteractor implements MovieTheaterContract.Interactor {
    private final MovieTheaterContract.Mediator mediator;
    private final CharSequence subTitle;
    private final MoviesCard.Theater theater;
    private final CharSequence title;

    public MovieTheaterInteractor(CharSequence charSequence, CharSequence charSequence2, MoviesCard.Theater theater, MovieTheaterContract.Mediator mediator) {
        this.title = charSequence;
        this.subTitle = charSequence2;
        this.mediator = mediator;
        this.theater = theater;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Interactor
    public CharSequence getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Interactor
    public MoviesCard.Theater getTheater() {
        return this.theater;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Interactor
    public CharSequence getTitle() {
        return this.title;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.Interactor
    public void openLocation() {
        this.mediator.openLocation(String.valueOf(this.theater.getLocation()));
    }
}

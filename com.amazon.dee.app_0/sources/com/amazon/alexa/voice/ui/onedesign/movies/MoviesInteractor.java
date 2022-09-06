package com.amazon.alexa.voice.ui.onedesign.movies;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract;
/* loaded from: classes11.dex */
public final class MoviesInteractor implements MoviesContract.Interactor {
    private final MoviesContract.Mediator mediator;

    public MoviesInteractor(MoviesContract.Mediator mediator) {
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Interactor
    public void showChildIfNeeded() {
        this.mediator.showChildIfNeeded();
    }
}

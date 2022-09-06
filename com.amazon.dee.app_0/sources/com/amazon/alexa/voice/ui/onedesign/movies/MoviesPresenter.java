package com.amazon.alexa.voice.ui.onedesign.movies;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract;
/* loaded from: classes11.dex */
public final class MoviesPresenter implements MoviesContract.Presenter {
    private final MoviesContract.Interactor interactor;

    public MoviesPresenter(MoviesContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Presenter
    public void start() {
        this.interactor.showChildIfNeeded();
    }
}

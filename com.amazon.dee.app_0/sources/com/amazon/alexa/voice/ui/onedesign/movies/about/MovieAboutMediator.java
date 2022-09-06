package com.amazon.alexa.voice.ui.onedesign.movies.about;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesController;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideEndOutTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideStartInTransition;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MovieAboutMediator implements MovieAboutContract.Mediator {
    private final ViewController controller;

    public MovieAboutMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Mediator
    public void openLinkUrl(String str) {
        IntentUtils.openLinkUrl(this.controller, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Mediator
    public void openShowtimes(CharSequence charSequence, MoviesCard.Movie movie) {
        this.controller.getRouter().pushController(new ControllerTransaction(MovieShowtimesController.create(charSequence, movie), new SlideEndOutTransition(), new SlideStartInTransition()));
    }
}

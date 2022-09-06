package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract;
import com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterController;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideEndOutTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideStartInTransition;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MovieShowtimesMediator implements MovieShowtimesContract.Mediator {
    private final ViewController controller;

    public MovieShowtimesMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Mediator
    public void close() {
        this.controller.getRouter().popCurrentController();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Mediator
    public void openLinkUrl(String str) {
        IntentUtils.openLinkUrl(this.controller, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Mediator
    public void openTheater(CharSequence charSequence, CharSequence charSequence2, MoviesCard.Theater theater) {
        this.controller.getRouter().pushController(new ControllerTransaction(MovieTheaterController.create(charSequence, charSequence2, theater), new SlideEndOutTransition(), new SlideStartInTransition()));
    }
}

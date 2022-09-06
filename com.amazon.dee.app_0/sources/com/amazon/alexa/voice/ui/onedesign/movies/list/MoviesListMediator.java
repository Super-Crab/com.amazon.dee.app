package com.amazon.alexa.voice.ui.onedesign.movies.list;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutController;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideEndOutTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideStartInTransition;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MoviesListMediator implements MoviesListContract.Mediator {
    private final ViewController controller;

    public MoviesListMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Mediator
    public void close() {
        ViewController parentController = this.controller.getParentController();
        parentController.getRouter().popController(parentController);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Mediator
    public void openLinkUrl(String str) {
        IntentUtils.openLinkUrl(this.controller, str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.Mediator
    public void openMovie(CharSequence charSequence, MoviesCard.Movie movie) {
        this.controller.getRouter().pushController(new ControllerTransaction(MovieAboutController.create(movie, charSequence, true), new SlideEndOutTransition(), new SlideStartInTransition()));
    }
}

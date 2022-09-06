package com.amazon.alexa.voice.ui.onedesign.movies;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract;
import com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutController;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListController;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
import java.util.List;
/* loaded from: classes11.dex */
public final class MoviesMediator implements MoviesContract.Mediator {
    private final MoviesCard card;
    private final ViewController controller;

    public MoviesMediator(ViewController viewController, MoviesCard moviesCard) {
        this.controller = viewController;
        this.card = moviesCard;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MoviesContract.Mediator
    public void showChildIfNeeded() {
        ViewController create;
        Router childRouter = this.controller.getChildRouter(MoviesContract.ROUTER);
        if (childRouter.getRootController() != null) {
            return;
        }
        List<? extends MoviesCardModel.MovieModel> movieList = this.card.getMovieList();
        if (movieList.size() == 1) {
            create = MovieAboutController.create((MoviesCard.Movie) movieList.get(0), this.card.getSubTitle(), false);
        } else {
            create = MoviesListController.create(this.card);
        }
        childRouter.pushController(new ControllerTransaction(create));
    }
}

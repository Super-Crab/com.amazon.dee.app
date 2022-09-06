package com.amazon.alexa.voice.ui.onedesign.movies.list;

import androidx.annotation.DrawableRes;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
/* loaded from: classes11.dex */
public interface MoviesListContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        MoviesCard getCard();

        void openLink(String str);

        void openMovieShowtimes(MoviesCard.Movie movie);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openLinkUrl(String str);

        void openMovie(CharSequence charSequence, MoviesCard.Movie movie);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void end();

        void interacted();

        void linkClicked(MoviesListLinkModel moviesListLinkModel);

        void posterClicked(MoviesListPosterModel moviesListPosterModel);

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        void floodBackgroundToStatusBar();

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);

        void setTitleAttributionImage(@DrawableRes int i);
    }
}

package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import java.util.List;
/* loaded from: classes11.dex */
public interface MovieShowtimesContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        CharSequence getDate();

        MoviesCard.Movie getMovie();

        void openLink();

        void openTheater(MoviesCard.Theater theater);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openLinkUrl(String str);

        void openTheater(CharSequence charSequence, CharSequence charSequence2, MoviesCard.Theater theater);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void backClicked();

        void end();

        void interacted();

        void linkClicked();

        void start();

        void theaterClicked(MovieShowtimesTheaterModel movieShowtimesTheaterModel);

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setContent(List<? extends MovieShowtimesTheaterModel> list);

        void setSubTitle(CharSequence charSequence);

        void setTitle(CharSequence charSequence);
    }
}

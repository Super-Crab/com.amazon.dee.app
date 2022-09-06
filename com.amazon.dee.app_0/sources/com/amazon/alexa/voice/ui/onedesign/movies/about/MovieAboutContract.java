package com.amazon.alexa.voice.ui.onedesign.movies.about;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
/* loaded from: classes11.dex */
public interface MovieAboutContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void backClicked();

        void close();

        void closeClicked();

        CharSequence getDate();

        MoviesCard.Movie getMovie();

        void openLink();

        void openShowtimes();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openLinkUrl(String str);

        void openShowtimes(CharSequence charSequence, MoviesCard.Movie movie);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void backClicked();

        void close();

        void closeClicked();

        void interacted();

        void linkClicked();

        void showTimesClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setCast(CharSequence charSequence, CharSequence charSequence2);

        void setDescription(CharSequence charSequence);

        void setLinkText(CharSequence charSequence);

        void setPosterImageUrl(String str);

        void setShowtimesText(CharSequence charSequence);

        void setSubTitle(CharSequence charSequence, CharSequence charSequence2);

        void setTitle(CharSequence charSequence);
    }
}

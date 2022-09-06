package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
/* loaded from: classes11.dex */
public interface MovieTheaterContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        CharSequence getSubTitle();

        MoviesCard.Theater getTheater();

        CharSequence getTitle();

        void openLocation();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openLocation(String str);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void backClicked();

        void end();

        void interacted();

        void locationClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void addItem(Object obj);

        void floodBackgroundToStatusBar();

        void setSubTitle(CharSequence charSequence);

        void setTheater(CharSequence charSequence, CharSequence charSequence2);

        void setTitle(CharSequence charSequence);
    }
}

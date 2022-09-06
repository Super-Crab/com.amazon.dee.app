package com.amazon.alexa.voice.ui.onedesign.movies;
/* loaded from: classes11.dex */
public interface MoviesContract {
    public static final String ROUTER = "movies";

    /* loaded from: classes11.dex */
    public interface Interactor {
        void dismiss();

        void showChildIfNeeded();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void dismiss();

        void showChildIfNeeded();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void dismiss();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
    }
}

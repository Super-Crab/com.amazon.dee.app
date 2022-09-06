package com.amazon.alexa.voice.ui.onedesign.sports.update;
/* loaded from: classes11.dex */
public interface SportsUpdateContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        SportsUpdateCard getCard();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void end();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        CharSequence appendTitle(CharSequence charSequence);
    }
}

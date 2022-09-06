package com.amazon.alexa.voice.ui.onedesign.shopping;
/* loaded from: classes11.dex */
public interface ShoppingContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        ShoppingCard getCard();

        String getUserMarketplace();
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

        void interacted();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        void floodBackgroundToStatusBar();
    }
}

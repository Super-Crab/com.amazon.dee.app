package com.amazon.alexa.voice.ui.onedesign.traffic;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface TrafficContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        TrafficCardModel getCard();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void openLocation(String str);
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

        void setTitle(@NonNull CharSequence charSequence);
    }
}

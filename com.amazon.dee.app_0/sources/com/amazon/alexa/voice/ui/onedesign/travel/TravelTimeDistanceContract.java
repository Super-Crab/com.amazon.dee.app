package com.amazon.alexa.voice.ui.onedesign.travel;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface TravelTimeDistanceContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        TravelTimeDistanceModel getCard();
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

        void setPrimaryLabel(@NonNull CharSequence charSequence);

        void setRoadSegment(@NonNull CharSequence charSequence);

        void setSecondaryLabel(@NonNull CharSequence charSequence);

        void setSubTitle(@NonNull CharSequence charSequence);

        void setTitle(@NonNull CharSequence charSequence);

        void setTrafficCondition(int i);
    }
}

package com.amazon.alexa.voice.ui.onedesign.standard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface StandardContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        StandardCardModel getCard();

        void openLink();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void openLinkUrl(@NonNull CharSequence charSequence);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void interacted();

        void linkClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setContent(@NonNull CharSequence charSequence);

        void setImageAttribution(@Nullable CharSequence charSequence);

        void setImageUrl(@Nullable CharSequence charSequence);

        void setLinkText(@Nullable CharSequence charSequence);

        void setSubTitle(@Nullable CharSequence charSequence);

        void setTitle(@Nullable CharSequence charSequence);

        void setVisibleLayout(int i);
    }
}

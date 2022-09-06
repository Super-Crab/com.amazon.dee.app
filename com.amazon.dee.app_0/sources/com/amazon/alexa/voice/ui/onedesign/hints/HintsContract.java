package com.amazon.alexa.voice.ui.onedesign.hints;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface HintsContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void dismiss();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void dismiss();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void doneClicked();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setDoneButtonText(@NonNull CharSequence charSequence);

        void setShoppingHint(@NonNull CharSequence charSequence);

        void setSunsetHint(@NonNull CharSequence charSequence);

        void setTitle(@NonNull CharSequence charSequence);

        void setWeatherHint(@NonNull CharSequence charSequence);
    }
}

package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface HandsfreePrimerContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void deferPrimer();

        void learnMoreClicked(@NonNull String str);

        void requestPermissions();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openLearnMore(@NonNull CharSequence charSequence);

        void requestPermissions();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void allowClicked();

        void laterClicked();

        void learnMoreClicked();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setAllowButtonText(@NonNull CharSequence charSequence);

        void setHandsfreeSettingsInfo(CharSequence charSequence);

        void setLaterButtonText(@NonNull CharSequence charSequence);

        void setRationale(CharSequence charSequence, CharSequence charSequence2);

        void setTitle(@NonNull CharSequence charSequence);

        void setUsage(CharSequence charSequence, CharSequence charSequence2);
    }
}

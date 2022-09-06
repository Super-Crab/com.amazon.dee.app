package com.amazon.alexa.voice.ui.onedesign.ftue.settings;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface GoToSettingsContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void openSettings();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void openSettings();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void settingsClicked();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setContent(@NonNull CharSequence charSequence);

        void setLinkText(@NonNull CharSequence charSequence);

        void setTitle(@NonNull CharSequence charSequence);
    }
}

package com.amazon.alexa.voice.ui.onedesign.ftue.primer;

import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public interface PrimerContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void askForOsPermissions();

        void close();

        void deferPrimer();

        void validatePermissions();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        @NonNull
        String[] getMinimumRequiredPermissions();

        @NonNull
        String[] getPermissions();

        void notifyVoicePermissionGrantedListener();

        void showPermissionSettings();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void allowClicked();

        void closeClicked();

        void laterClicked();

        void permissionsResultReceived();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setAllowButtonText(@NonNull CharSequence charSequence);

        void setLaterButtonText(@NonNull CharSequence charSequence);

        void setPermissionsListText(@NonNull CharSequence charSequence);

        void setPermissionsRationale(@NonNull CharSequence charSequence);

        void setPermissionsTitle(@NonNull CharSequence charSequence);
    }
}

package com.amazon.alexa.voice.ui.driveMode.local;
/* loaded from: classes11.dex */
public interface DriveModeLocalContract {
    public static final String ROUTER = "local";

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        void showChildIfNeeded();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void showChildIfNeeded();
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
    }
}

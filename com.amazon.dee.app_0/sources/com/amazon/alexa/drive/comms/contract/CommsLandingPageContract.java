package com.amazon.alexa.drive.comms.contract;
/* loaded from: classes7.dex */
public interface CommsLandingPageContract {

    /* loaded from: classes7.dex */
    public interface Interactor {
        void onAnnouncementCardClick();

        void onCallCardClick();

        void onDropInCardClick();
    }

    /* loaded from: classes7.dex */
    public interface Presenter {
        void initialize(View view);

        void onAnnouncementCardClick();

        void onCallCardClick();

        void onDropInCardClick();
    }

    /* loaded from: classes7.dex */
    public interface View {
    }
}

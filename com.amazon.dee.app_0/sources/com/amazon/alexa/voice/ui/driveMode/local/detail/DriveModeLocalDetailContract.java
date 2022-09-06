package com.amazon.alexa.voice.ui.driveMode.local.detail;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
/* loaded from: classes11.dex */
public interface DriveModeLocalDetailContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void back();

        void callBusiness();

        void close();

        LocalCardModel.BusinessModel getBusiness();

        void showLocation();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dialPhoneNumber(String str);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void addressClicked();

        void backClicked();

        void closeClicked();

        void interacted();

        void phoneNumberClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setAddress(CharSequence charSequence, CharSequence charSequence2);

        void setDataProvider(CharSequence charSequence);

        void setDescription(CharSequence charSequence, CharSequence charSequence2);

        void setDistance(CharSequence charSequence, CharSequence charSequence2);

        void setName(CharSequence charSequence);

        void setOpeningHours(CharSequence charSequence, CharSequence charSequence2, String str);

        void setProviderLogoAndDescription(String str, CharSequence charSequence, boolean z);

        void setRating(CharSequence charSequence, String str, CharSequence charSequence2, boolean z);

        void setReviewCount(CharSequence charSequence, CharSequence charSequence2, boolean z);
    }
}

package com.amazon.alexa.voice.ui.onedesign.local.detail;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
/* loaded from: classes11.dex */
public interface LocalDetailContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void back();

        void callBusiness();

        void close();

        LocalCardModel.BusinessModel getBusiness();

        void openLink();

        void showLocation();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dialPhoneNumber(String str);

        void mapAddress(String str);

        void mapGeoLocation(String str) throws IllegalArgumentException;

        void openLinkUrl(String str);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void addressClicked();

        void backClicked();

        void closeClicked();

        void interacted();

        void linkClicked();

        void phoneNumberClicked();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void floodBackgroundToStatusBar();

        void setAddress(CharSequence charSequence, CharSequence charSequence2);

        void setDescription(CharSequence charSequence, CharSequence charSequence2);

        void setDistance(CharSequence charSequence, CharSequence charSequence2);

        void setImageUrl(String str);

        void setLinkText(CharSequence charSequence);

        void setMergedProviderInfo(String str);

        void setName(CharSequence charSequence);

        void setOpeningHours(CharSequence charSequence, CharSequence charSequence2);

        void setPhoneNumber(CharSequence charSequence, CharSequence charSequence2);

        void setProviderLogoAndDescription(String str, CharSequence charSequence);

        void setRating(CharSequence charSequence, String str, CharSequence charSequence2);

        void setReviewCount(CharSequence charSequence, CharSequence charSequence2);
    }
}

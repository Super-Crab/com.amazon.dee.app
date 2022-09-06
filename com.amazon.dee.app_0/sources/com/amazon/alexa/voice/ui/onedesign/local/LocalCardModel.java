package com.amazon.alexa.voice.ui.onedesign.local;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface LocalCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface BusinessModel extends Parcelable {
        @NonNull
        CharSequence getAddress();

        @NonNull
        List<CharSequence> getCategories();

        String getDataSource();

        @NonNull
        CharSequence getDistanceUnit();

        float getDistanceValue();

        CharSequence getGeoLocation();

        @NonNull
        String getImageUrl();

        String getLinkUrl();

        String getMergedProviderInfo();

        @NonNull
        CharSequence getName();

        @NonNull
        List<CharSequence> getOpeningHours();

        CharSequence getPhoneNumber();

        @NonNull
        String getProviderLogoUrl();

        float getRating();

        int getReviewCount();

        String getSpecialBusinessHoursDisplayMessage();

        String getSpecialBusinessHoursDisplayType();

        CharSequence getSpendiness();

        @NonNull
        String getStarRatingUrl();

        String goToActionText();

        String goToActionUrl();
    }

    @NonNull
    List<? extends BusinessModel> getBusinessList();

    @NonNull
    String getSortKey();

    @NonNull
    CharSequence getTitle();
}

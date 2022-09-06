package com.amazon.alexa.voice.ui.onedesign.local.search;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface LocalSearchItemModel {
    boolean displayProviderInfo();

    CharSequence getAddress();

    @NonNull
    List<CharSequence> getCategories();

    String getDataSource();

    @NonNull
    CharSequence getDistanceUnit();

    float getDistanceValue();

    String getImageUrl();

    float getRating();

    int getReviewCount();

    CharSequence getSpendiness();

    String getStarRatingUrl();

    @NonNull
    Object getTag();

    @NonNull
    CharSequence getTitle();
}

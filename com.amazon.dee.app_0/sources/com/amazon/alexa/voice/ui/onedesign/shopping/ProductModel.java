package com.amazon.alexa.voice.ui.onedesign.shopping;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface ProductModel extends Parcelable {
    long getAvailableDate();

    @NonNull
    String getImageUrl();

    @NonNull
    String getLinkUrl();

    @NonNull
    CharSequence getName();

    CharSequence getPrice();

    boolean getPrimeAvailable();

    float getRating();

    int getReviewCount();

    @Nullable
    CharSequence getShippedAndSoldBy();

    @Nullable
    CharSequence getUnitPriceText();
}

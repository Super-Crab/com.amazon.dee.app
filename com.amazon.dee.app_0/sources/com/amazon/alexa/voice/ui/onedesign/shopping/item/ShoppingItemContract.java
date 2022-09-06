package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public interface ShoppingItemContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        ShoppingItemModel getShoppingItem();

        void openLink();
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void openLinkUrl(String str);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void linkClicked();

        void start();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void setAvailabilityText(CharSequence charSequence);

        void setImageUrl(String str);

        void setLegalText(CharSequence charSequence);

        void setLinkText(CharSequence charSequence);

        void setName(CharSequence charSequence);

        void setPriceInfoText(CharSequence charSequence);

        void setPriceText(CharSequence charSequence);

        void setPrimeAvailable(boolean z);

        void setRating(float f);

        void setReviewCountText(CharSequence charSequence);

        void setShippedAndSoldByText(@Nullable CharSequence charSequence);

        void setUnitPriceText(@Nullable CharSequence charSequence);
    }
}

package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.shopping.ProductModel;
import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class ShoppingItemPresenter implements ShoppingItemContract.Presenter {
    private final ShoppingItemContract.Interactor interactor;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final ShoppingItemContract.View view;

    public ShoppingItemPresenter(ShoppingItemContract.View view, ShoppingItemContract.Interactor interactor, Resources resources, Locale locale, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.locale = locale;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private String getCardName() {
        return this.interactor.getClass().getSimpleName();
    }

    private CharSequence makeDisplayDateText(long j) {
        if (j == -1) {
            return null;
        }
        return this.resources.getString(R.string.voice_ui_od_shopping_get_it_by, new SimpleDateFormat("EEEE, MMM d", this.locale).format(new Date(j)));
    }

    private CharSequence makeReviewCountText(int i) {
        if (i == 0) {
            return null;
        }
        return this.resources.getString(R.string.voice_ui_od_shopping_review_count, NumberFormat.getNumberInstance().format(i));
    }

    @Nullable
    private CharSequence makeShippedAndSoldByText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            return this.resources.getString(R.string.voice_ui_od_shopping_ships_and_sold_by, charSequence);
        }
        return null;
    }

    @Nullable
    private CharSequence makeUnitPriceText(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            return "(" + ((Object) charSequence) + ")";
        }
        return null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.Presenter
    public void linkClicked() {
        this.interactor.openLink();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.Presenter
    public void start() {
        ShoppingItemModel shoppingItem = this.interactor.getShoppingItem();
        ProductModel product = shoppingItem.getProduct();
        CharSequence legalText = shoppingItem.getLegalText();
        this.view.setName(product.getName());
        this.view.setImageUrl(product.getImageUrl());
        this.view.setRating(product.getRating());
        this.view.setReviewCountText(makeReviewCountText(product.getReviewCount()));
        this.view.setPriceText(product.getPrice());
        this.view.setUnitPriceText(makeUnitPriceText(product.getUnitPriceText()));
        this.view.setPriceInfoText(this.resources.getString(R.string.voice_ui_od_shopping_price_info));
        this.view.setPrimeAvailable(product.getPrimeAvailable());
        this.view.setAvailabilityText(makeDisplayDateText(product.getAvailableDate()));
        this.view.setShippedAndSoldByText(makeShippedAndSoldByText(product.getShippedAndSoldBy()));
        this.view.setLegalText(legalText);
        this.view.setLinkText(this.resources.getString(R.string.voice_ui_od_shopping_product_detail));
    }
}

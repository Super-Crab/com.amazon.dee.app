package com.amazon.alexa.voice.ui.onedesign.shopping.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.bumptech.glide.Glide;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class ShoppingItemViewHolder extends BindableViewHolder<ShoppingItemModel> implements ShoppingItemContract.View {
    private final TextView deliveryInfoView;
    private final TextView legalTextView;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private ShoppingItemContract.Presenter presenter;
    private final TextView priceInfoView;
    private final TextView priceView;
    private final ImageView primeView;
    private final ImageView productImageView;
    private final TextView productTitleView;
    private final RatingBar ratingView;
    private final TextView reviewCountView;
    private final TextView shippedAndSoldByView;
    private final TextView shopAmazonLinkText;
    private final TextView unitPriceView;

    public ShoppingItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Locale locale, CardMetricsInteractor cardMetricsInteractor) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_shopping_item, viewGroup, false));
        this.locale = locale;
        this.metricsInteractor = cardMetricsInteractor;
        this.productImageView = (ImageView) this.itemView.findViewById(R.id.image);
        this.productTitleView = (TextView) this.itemView.findViewById(R.id.productTitle);
        this.ratingView = (RatingBar) this.itemView.findViewById(R.id.ratingStar);
        this.reviewCountView = (TextView) this.itemView.findViewById(R.id.reviewCount);
        this.priceView = (TextView) this.itemView.findViewById(R.id.price);
        this.unitPriceView = (TextView) this.itemView.findViewById(R.id.unit_price);
        this.priceInfoView = (TextView) this.itemView.findViewById(R.id.priceInfo);
        this.primeView = (ImageView) this.itemView.findViewById(R.id.prime);
        this.deliveryInfoView = (TextView) this.itemView.findViewById(R.id.deliveryInfo);
        this.shippedAndSoldByView = (TextView) this.itemView.findViewById(R.id.shippedAndSoldBy);
        this.legalTextView = (TextView) this.itemView.findViewById(R.id.legalText);
        this.itemView.findViewById(R.id.shopAmazonLink).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.shopping.item.-$$Lambda$ShoppingItemViewHolder$_vz5Y2DLywCp0syxLzlHk6DLvbQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShoppingItemViewHolder.this.lambda$new$0$ShoppingItemViewHolder(view);
            }
        });
        this.shopAmazonLinkText = (TextView) this.itemView.findViewById(R.id.shopAmazonLinkText);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.productTitleView, this.priceView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.reviewCountView, this.unitPriceView, this.priceInfoView, this.deliveryInfoView, this.shippedAndSoldByView, this.legalTextView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.shopAmazonLinkText);
    }

    public /* synthetic */ void lambda$new$0$ShoppingItemViewHolder(View view) {
        this.presenter.linkClicked();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setAvailabilityText(CharSequence charSequence) {
        if (charSequence == null) {
            this.deliveryInfoView.setVisibility(8);
            return;
        }
        this.deliveryInfoView.setVisibility(0);
        this.deliveryInfoView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setImageUrl(String str) {
        Glide.with(this.productImageView.getContext().getApplicationContext()).clear(this.productImageView);
        if (str != null) {
            Glide.with(this.productImageView.getContext().getApplicationContext()).mo6762load(str).into(this.productImageView);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setLegalText(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.legalTextView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setLinkText(CharSequence charSequence) {
        this.shopAmazonLinkText.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setName(CharSequence charSequence) {
        this.productTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setPriceInfoText(CharSequence charSequence) {
        this.priceInfoView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setPriceText(CharSequence charSequence) {
        this.priceView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setPrimeAvailable(boolean z) {
        if (z) {
            this.primeView.setVisibility(0);
        } else {
            this.primeView.setVisibility(8);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setRating(float f) {
        if (f == 0.0f) {
            this.ratingView.setVisibility(8);
            return;
        }
        this.ratingView.setVisibility(0);
        this.ratingView.setRating(f);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setReviewCountText(CharSequence charSequence) {
        if (charSequence == null) {
            this.reviewCountView.setVisibility(8);
            return;
        }
        this.reviewCountView.setVisibility(0);
        this.reviewCountView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setShippedAndSoldByText(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.shippedAndSoldByView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.item.ShoppingItemContract.View
    public void setUnitPriceText(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.unitPriceView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(ShoppingItemModel shoppingItemModel) {
        this.presenter = new ShoppingItemPresenter(this, new ShoppingItemInteractor(shoppingItemModel, new ShoppingItemMediator(this.itemView.getContext())), new AndroidResources(this.itemView.getContext(), this.locale), this.locale, this.metricsInteractor);
        this.presenter.start();
    }
}

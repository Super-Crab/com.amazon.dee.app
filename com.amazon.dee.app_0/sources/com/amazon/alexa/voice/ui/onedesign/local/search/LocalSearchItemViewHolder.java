package com.amazon.alexa.voice.ui.onedesign.local.search;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchAdapter;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
/* loaded from: classes11.dex */
public final class LocalSearchItemViewHolder extends BindableViewHolder<LocalSearchItemModel> {
    private final ImageView bussinessIconView;
    private final TextView dataSource;
    private final TextView descriptionView;
    private LocalSearchItemModel model;
    private final ImageView ratingView;
    private final Resources resources;
    private final TextView reviewCount;
    private final TextView titleView;

    public LocalSearchItemViewHolder(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, final LocalSearchAdapter.OnItemClickListener onItemClickListener, Resources resources) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.resources = resources;
        this.bussinessIconView = (ImageView) this.itemView.findViewById(R.id.businessImage);
        this.titleView = (TextView) this.itemView.findViewById(R.id.businessTitle);
        this.descriptionView = (TextView) this.itemView.findViewById(R.id.businessDescription);
        this.ratingView = (ImageView) this.itemView.findViewById(R.id.businessRating);
        this.reviewCount = (TextView) this.itemView.findViewById(R.id.businessReviewCount);
        this.dataSource = (TextView) this.itemView.findViewById(R.id.dataSource);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.titleView, this.descriptionView, this.reviewCount, this.dataSource);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.search.-$$Lambda$LocalSearchItemViewHolder$_b-QE_NZXdZPWbLKHPQfgLotLuY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalSearchItemViewHolder.this.lambda$new$0$LocalSearchItemViewHolder(onItemClickListener, view);
            }
        });
    }

    private static CharSequence buildDescription(CharSequence charSequence, List<CharSequence> list) {
        CharSequence concat = charSequence != null ? TextUtils.concat(charSequence, " • ") : "";
        int i = 0;
        while (i < list.size()) {
            concat = i == 0 ? TextUtils.concat(concat, list.get(i)) : TextUtils.concat(concat, ", ", list.get(i));
            i++;
        }
        return concat;
    }

    private CharSequence buildDistance(float f, CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_local_distance_format, Float.valueOf(f), charSequence);
    }

    public /* synthetic */ void lambda$new$0$LocalSearchItemViewHolder(LocalSearchAdapter.OnItemClickListener onItemClickListener, View view) {
        onItemClickListener.onItemClicked(this.model);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(LocalSearchItemModel localSearchItemModel) {
        this.model = localSearchItemModel;
        if (localSearchItemModel.getImageUrl() == null) {
            Glide.with(this.bussinessIconView.getContext().getApplicationContext()).clear(this.bussinessIconView);
        } else {
            Glide.with(this.bussinessIconView.getContext().getApplicationContext()).mo6762load(localSearchItemModel.getImageUrl()).mo1615apply(new RequestOptions().mo1574circleCrop().mo1600override(this.bussinessIconView.getWidth(), this.bussinessIconView.getHeight())).into(this.bussinessIconView);
        }
        this.titleView.setText(localSearchItemModel.getTitle());
        this.descriptionView.setText(TextUtils.concat(buildDistance(localSearchItemModel.getDistanceValue(), localSearchItemModel.getDistanceUnit()), " • ", buildDescription(localSearchItemModel.getSpendiness(), localSearchItemModel.getCategories())));
        if (URLUtil.isValidUrl(localSearchItemModel.getStarRatingUrl())) {
            Glide.with(this.ratingView).mo6762load(localSearchItemModel.getStarRatingUrl()).into(this.ratingView);
        }
        this.dataSource.setText(localSearchItemModel.getDataSource());
        String num = Integer.toString(localSearchItemModel.getReviewCount());
        this.reviewCount.setText(TextUtils.concat("(", num, ") "));
        this.reviewCount.setContentDescription(this.resources.getString(R.string.voice_ui_od_local_review_description, num));
    }
}

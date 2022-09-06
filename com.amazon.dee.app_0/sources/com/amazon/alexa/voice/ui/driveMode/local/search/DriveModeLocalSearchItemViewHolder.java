package com.amazon.alexa.voice.ui.driveMode.local.search;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;
import com.amazon.alexa.voice.ui.driveMode.R;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchAdapter;
import com.amazon.alexa.voice.ui.driveMode.util.Fonts;
import com.amazon.alexa.voice.ui.driveMode.widget.DriveModeBindableViewHolder;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.bumptech.glide.Glide;
/* loaded from: classes11.dex */
public final class DriveModeLocalSearchItemViewHolder extends DriveModeBindableViewHolder<LocalSearchItemModel> {
    private final TextView address;
    private final TextView dataSource;
    private final TextView distance;
    private final TextView localSearchIndex;
    private LocalSearchItemModel model;
    private final ImageView ratingView;
    private final Resources resources;
    private final TextView reviewCount;
    private final TextView titleView;

    public DriveModeLocalSearchItemViewHolder(LayoutInflater layoutInflater, int i, ViewGroup viewGroup, final DriveModeLocalSearchAdapter.OnItemClickListener onItemClickListener, Resources resources) {
        super(layoutInflater.inflate(i, viewGroup, false));
        this.resources = resources;
        this.localSearchIndex = (TextView) this.itemView.findViewById(R.id.dm_localSearchIndex);
        this.titleView = (TextView) this.itemView.findViewById(R.id.dm_businessTitle);
        this.ratingView = (ImageView) this.itemView.findViewById(R.id.dm_businessRating);
        this.address = (TextView) this.itemView.findViewById(R.id.dm_businessAddress);
        this.distance = (TextView) this.itemView.findViewById(R.id.dm_businessDistance);
        this.reviewCount = (TextView) this.itemView.findViewById(R.id.dm_businessReviewCount);
        this.dataSource = (TextView) this.itemView.findViewById(R.id.dm_dataSource);
        Fonts.EMBER_BOLD.apply(this.titleView, this.localSearchIndex);
        Fonts.EMBER_REGULAR.apply(this.reviewCount, this.dataSource, this.address, this.distance);
        this.itemView.setBackgroundResource(R.drawable.poi_list_item_bg);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.driveMode.local.search.-$$Lambda$DriveModeLocalSearchItemViewHolder$-_Ur7GXmIUr_N3kytwfq2T0-5RA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DriveModeLocalSearchItemViewHolder.this.lambda$new$0$DriveModeLocalSearchItemViewHolder(onItemClickListener, view);
            }
        });
    }

    private CharSequence buildDistance(float f, CharSequence charSequence) {
        return this.resources.getString(com.amazon.alexa.vox.ui.onedesign.R.string.voice_ui_od_local_distance_format, Float.valueOf(f), charSequence);
    }

    public /* synthetic */ void lambda$new$0$DriveModeLocalSearchItemViewHolder(DriveModeLocalSearchAdapter.OnItemClickListener onItemClickListener, View view) {
        onItemClickListener.onItemClicked(this.model);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.widget.DriveModeBindableViewHolder
    public void bind(LocalSearchItemModel localSearchItemModel, int i) {
        this.model = localSearchItemModel;
        this.localSearchIndex.setText(String.valueOf(i));
        this.titleView.setText(localSearchItemModel.getTitle());
        this.distance.setText(buildDistance(localSearchItemModel.getDistanceValue(), localSearchItemModel.getDistanceUnit()));
        if (URLUtil.isValidUrl(localSearchItemModel.getStarRatingUrl())) {
            Glide.with(this.ratingView).mo6762load(localSearchItemModel.getStarRatingUrl()).into(this.ratingView);
        }
        this.dataSource.setText(localSearchItemModel.getDataSource());
        String num = Integer.toString(localSearchItemModel.getReviewCount());
        this.reviewCount.setText(TextUtils.concat("(", num, ") "));
        this.reviewCount.setContentDescription(this.resources.getString(com.amazon.alexa.vox.ui.onedesign.R.string.voice_ui_od_local_review_description, num));
        if (localSearchItemModel.getDataSource().equals("")) {
            this.address.setText(localSearchItemModel.getAddress().toString().split("\n")[0]);
            this.address.setVisibility(0);
            this.dataSource.setVisibility(8);
            this.reviewCount.setVisibility(8);
            this.ratingView.setVisibility(8);
        }
    }
}

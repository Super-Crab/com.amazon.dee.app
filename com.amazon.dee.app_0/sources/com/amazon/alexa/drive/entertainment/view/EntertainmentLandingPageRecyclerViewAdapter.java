package com.amazon.alexa.drive.entertainment.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardList;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.util.DriveModeAnimationUtils;
import com.amazon.alexa.drive.weblab.WeblabManager;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class EntertainmentLandingPageRecyclerViewAdapter extends RecyclerView.Adapter {
    private static final String TAG = "EntertainmentLandingPageRecyclerViewAdapter";
    private static final int TYPE_DATA = 1;
    private static final int TYPE_LOADING = 0;
    private Context mContext;
    private DriveModeThemeManager mDriveModeThemeManager;
    private EntertainmentCardList mEntertainmentCardList;
    private EntertainmentMetrics mEntertainmentMetrics;
    private OnEntertainmentCardClickListener mOnEntertainmentCardClickListener;
    private WeblabManager mWeblabManager;

    /* loaded from: classes7.dex */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mAlbumArtView;
        private TextView mTrackSubTitle;
        private TextView mTrackTitle;

        public ItemViewHolder(View view) {
            super(view);
            this.mAlbumArtView = (ImageView) view.findViewById(R.id.entertainment_list_item_album_art);
            this.mTrackTitle = (TextView) view.findViewById(R.id.entertainment_list_item_title);
            this.mTrackSubTitle = (TextView) view.findViewById(R.id.entertainment_list_item_sub_title);
        }

        public ImageView getAlbumArtView() {
            return this.mAlbumArtView;
        }

        public TextView getSubTitle() {
            return this.mTrackSubTitle;
        }

        public TextView getTitle() {
            return this.mTrackTitle;
        }

        public void recycle() {
            String unused = EntertainmentLandingPageRecyclerViewAdapter.TAG;
            Glide.with(EntertainmentLandingPageRecyclerViewAdapter.this.mContext).clear(this.mAlbumArtView);
        }
    }

    /* loaded from: classes7.dex */
    public class LoadingItemViewHolder extends RecyclerView.ViewHolder {
        public LoadingItemViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes7.dex */
    public interface OnEntertainmentCardClickListener {
        void onEntertainmentCardClick(EntertainmentCardItem entertainmentCardItem);
    }

    public EntertainmentLandingPageRecyclerViewAdapter(Context context, EntertainmentCardList entertainmentCardList, OnEntertainmentCardClickListener onEntertainmentCardClickListener, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager, WeblabManager weblabManager) {
        Log.i(TAG, "EntertainmentLandingPageRecyclerViewAdapter Constructor");
        this.mContext = context;
        this.mEntertainmentCardList = entertainmentCardList;
        this.mOnEntertainmentCardClickListener = onEntertainmentCardClickListener;
        this.mEntertainmentMetrics = entertainmentMetrics;
        this.mDriveModeThemeManager = driveModeThemeManager;
        this.mWeblabManager = weblabManager;
    }

    private void loadImageToView(String str, final ItemViewHolder itemViewHolder) {
        RequestOptions requestOptions = new RequestOptions();
        DrawableCrossFadeFactory build = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        requestOptions.mo1612transforms(new CenterCrop(), new RoundedCorners((int) this.mContext.getResources().getDimension(R.dimen.entertainment_landing_page_recycler_view_item_corner_radius)));
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(this.mContext).mo6762load(str).mo1615apply(requestOptions).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageRecyclerViewAdapter.2
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                String str2 = EntertainmentLandingPageRecyclerViewAdapter.TAG;
                Log.i(str2, "onLoadFailed " + glideException);
                ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(EntertainmentLandingPageRecyclerViewAdapter.this.mContext, EntertainmentLandingPageRecyclerViewAdapter.this.mDriveModeThemeManager.getTheme());
                itemViewHolder.getAlbumArtView().setImageDrawable(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str2 = EntertainmentLandingPageRecyclerViewAdapter.TAG;
                Log.i(str2, "onResourceReady " + drawable);
                return false;
            }
        }).mo1632transition(DrawableTransitionOptions.withCrossFade(build)).into(itemViewHolder.getAlbumArtView());
    }

    void bind(final EntertainmentCardItem entertainmentCardItem, final OnEntertainmentCardClickListener onEntertainmentCardClickListener, ItemViewHolder itemViewHolder, final int i) {
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        itemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageRecyclerViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(EntertainmentLandingPageRecyclerViewAdapter.TAG, "onClick ");
                EntertainmentLandingPageRecyclerViewAdapter.this.mEntertainmentMetrics.logMusicHistorySelected(autoModeType, Integer.toString(i));
                onEntertainmentCardClickListener.onEntertainmentCardClick(entertainmentCardItem);
            }
        });
        List<JSONObject> albumArtUrls = entertainmentCardItem.getAlbumArtUrls();
        if (albumArtUrls != null && albumArtUrls.size() > 0) {
            loadImageToView(((JSONObject) GeneratedOutlineSupport1.outline24(albumArtUrls, -1)).optString("url", ""), itemViewHolder);
        } else {
            loadImageToView("", itemViewHolder);
        }
        itemViewHolder.getTitle().setText(entertainmentCardItem.getTitle());
        itemViewHolder.getSubTitle().setText(entertainmentCardItem.getSubTitle());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getItemCount ");
        outline107.append(this.mEntertainmentCardList.getEntertainmentCardItemList().size());
        outline107.toString();
        return this.mEntertainmentCardList.getEntertainmentCardItemList().size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        EntertainmentCardItem entertainmentCardItem = (EntertainmentCardItem) this.mEntertainmentCardList.getEntertainmentCardItemList().get(i);
        return (entertainmentCardItem == null || !entertainmentCardItem.isLoading()) ? 1 : 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == 1) {
            bind((EntertainmentCardItem) this.mEntertainmentCardList.getEntertainmentCardItemList().get(i), this.mOnEntertainmentCardClickListener, (ItemViewHolder) viewHolder, i);
        } else if (getItemViewType(i) != 0) {
        } else {
            DriveModeAnimationUtils.loadingPulseAnimation(viewHolder.itemView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        if (i == 1) {
            if (this.mWeblabManager.isAutoMode_2_0_WeblabEnabled()) {
                inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.entertainment_list_item_v2, viewGroup, false);
            } else {
                inflate = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.entertainment_list_item, viewGroup, false);
            }
            return new ItemViewHolder(inflate);
        }
        return new LoadingItemViewHolder(LayoutInflater.from(contextThemeWrapper).inflate(R.layout.entertainment_list_item_loading, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        if (viewHolder instanceof LoadingItemViewHolder) {
            viewHolder.itemView.clearAnimation();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        super.onViewRecycled(viewHolder);
        if (viewHolder instanceof ItemViewHolder) {
            ((ItemViewHolder) viewHolder).recycle();
        }
    }
}

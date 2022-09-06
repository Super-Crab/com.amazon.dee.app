package com.amazon.alexa.drive.entertainment.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.dependency.DriveModeDependencies;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.entertainment.model.MediaErrorPayload;
import com.amazon.alexa.drive.entertainment.repository.EntertainmentDataRepository;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageRecyclerViewAdapter;
import com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.metrics.TTCFRecordOnce;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drive.util.DriveModeAnimationUtils;
import com.amazon.alexa.drive.view.ViewManagerViewController;
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
import javax.inject.Inject;
import org.json.JSONException;
import org.json.JSONObject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class EntertainmentLandingPageViewController extends ViewManagerViewController implements EntertainmentLandingPageContract.View {
    private static final String TAG = "EntertainmentLandingPageViewController";
    EntertainmentLandingPageContract.EntertainmentCardListChangeListener entertainmentCardListChangeListener = new AnonymousClass1();
    @Inject
    DriveModeThemeManager mDriveModeThemeManager;
    @Inject
    EntertainmentDataRepository mEntertainmentDataRepository;
    @Inject
    EntertainmentLandingPageContract.Presenter mEntertainmentLandingPagePresenter;
    EntertainmentLandingPageRecyclerViewAdapter mEntertainmentLandingPageRecyclerViewAdapter;
    @Inject
    EntertainmentMetrics mEntertainmentMetrics;
    private Handler mHandler;
    @Inject
    NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    @Inject
    TTCFRecordOnce mTtcfRecordOnce;
    @Inject
    WeblabManager mWeblabManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass1 implements EntertainmentLandingPageContract.EntertainmentCardListChangeListener {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onItemRangeChanged$0$EntertainmentLandingPageViewController$1(int i, int i2) {
            EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter().notifyItemRangeChanged(i, i2);
        }

        public /* synthetic */ void lambda$onItemRangeInserted$1$EntertainmentLandingPageViewController$1(int i, int i2) {
            EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter().notifyItemRangeInserted(i, i2);
        }

        public /* synthetic */ void lambda$onItemRangeRemoved$2$EntertainmentLandingPageViewController$1(int i, int i2) {
            EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter().notifyItemRangeRemoved(i, i2);
        }

        @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentCardListChangeListener
        public void onItemRangeChanged(List list, final int i, final int i2) {
            String str = EntertainmentLandingPageViewController.TAG;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("onItemRangeChanged positionStart", i, " itemCount ", i2, "list size ");
            outline110.append(list.size());
            outline110.append(" getEntertainmentLandingPageRecyclerViewAdapter ");
            outline110.append(EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter());
            outline110.append(" isAttached ");
            outline110.append(EntertainmentLandingPageViewController.this.isAttached());
            Log.i(str, outline110.toString());
            if (EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter() == null || !EntertainmentLandingPageViewController.this.isAttached()) {
                return;
            }
            EntertainmentLandingPageViewController.this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$1$RV3y2Pw3ZVCav79yuogjFgidVLs
                @Override // java.lang.Runnable
                public final void run() {
                    EntertainmentLandingPageViewController.AnonymousClass1.this.lambda$onItemRangeChanged$0$EntertainmentLandingPageViewController$1(i, i2);
                }
            });
        }

        @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentCardListChangeListener
        public void onItemRangeInserted(List list, final int i, final int i2) {
            String str = EntertainmentLandingPageViewController.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onItemRangeInserted ");
            outline107.append(EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter());
            outline107.append(" isAttached ");
            outline107.append(EntertainmentLandingPageViewController.this.isAttached());
            Log.i(str, outline107.toString());
            if (EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter() == null || !EntertainmentLandingPageViewController.this.isAttached()) {
                return;
            }
            EntertainmentLandingPageViewController.this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$1$rfc0nK61Qsl6U42k74-7hG5gyoE
                @Override // java.lang.Runnable
                public final void run() {
                    EntertainmentLandingPageViewController.AnonymousClass1.this.lambda$onItemRangeInserted$1$EntertainmentLandingPageViewController$1(i, i2);
                }
            });
        }

        @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentCardListChangeListener
        public void onItemRangeRemoved(List list, final int i, final int i2) {
            String str = EntertainmentLandingPageViewController.TAG;
            StringBuilder outline110 = GeneratedOutlineSupport1.outline110("onItemRangeRemoved positionStart ", i, " itemCount ", i2, " observableList size ");
            outline110.append(list.size());
            outline110.append(" getEntertainmentLandingPageRecyclerViewAdapter ");
            outline110.append(EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter());
            outline110.append(" isAttached ");
            outline110.append(EntertainmentLandingPageViewController.this.isAttached());
            Log.i(str, outline110.toString());
            if (EntertainmentLandingPageViewController.this.getEntertainmentLandingPageRecyclerViewAdapter() == null || !EntertainmentLandingPageViewController.this.isAttached()) {
                return;
            }
            EntertainmentLandingPageViewController.this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$1$1fXLAxN6vsoKdMIQkRAOeJ848g4
                @Override // java.lang.Runnable
                public final void run() {
                    EntertainmentLandingPageViewController.AnonymousClass1.this.lambda$onItemRangeRemoved$2$EntertainmentLandingPageViewController$1(i, i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNowPlayingCardView(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
        if (entertainmentCardItem == null || !this.mEntertainmentLandingPagePresenter.isNowPlayingItemActive()) {
            return;
        }
        Log.i(TAG, "updateNowPlayingCardView ");
        if (getView() == null) {
            return;
        }
        View view = getView();
        ImageView imageView = (ImageView) view.findViewById(R.id.now_playing_card_album_art);
        RelativeLayout relativeLayout = (RelativeLayout) getView().findViewById(R.id.now_playing_card_container);
        if (this.mEntertainmentLandingPagePresenter.isNowPlayingItemActive() && relativeLayout.getVisibility() != 0) {
            relativeLayout.setVisibility(0);
        }
        if (relativeLayout.getAnimation() != null) {
            relativeLayout.clearAnimation();
        }
        List<JSONObject> albumArtUrls = entertainmentCardItem.getAlbumArtUrls();
        if (albumArtUrls != null && albumArtUrls.size() > 0) {
            loadImageToView(((JSONObject) GeneratedOutlineSupport1.outline25(albumArtUrls, 1)).optString("url", ""), imageView);
        }
        ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.now_playing_card_button);
        ((TextView) view.findViewById(R.id.now_playing_card_track_title)).setText(entertainmentCardItem.getTitle());
        ((TextView) view.findViewById(R.id.now_playing_card_track_sub_title)).setText(entertainmentCardItem.getSubTitle());
        toggleButton.setChecked(alexaPlayerInfoState == AlexaPlayerInfoState.PLAYING || alexaPlayerInfoState == AlexaPlayerInfoState.BUFFERING);
        if (toggleButton.isChecked()) {
            toggleButton.setContentDescription(getView().getResources().getString(R.string.content_desc_media_pause_button));
        } else {
            toggleButton.setContentDescription(getView().getResources().getString(R.string.content_desc_media_play_button));
        }
        if (!toggleButton.isChecked() && !this.mNetworkConnectivityManager.isNetworkConnected()) {
            toggleButton.setClickable(false);
            toggleButton.setEnabled(false);
            return;
        }
        toggleButton.setClickable(true);
        toggleButton.setEnabled(true);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController
    protected DriveModeThemeManager getDriveModeThemeManager() {
        return this.mDriveModeThemeManager;
    }

    EntertainmentLandingPageContract.EntertainmentCardListChangeListener getEntertainmentCardListChangeListener() {
        return this.entertainmentCardListChangeListener;
    }

    EntertainmentLandingPageRecyclerViewAdapter getEntertainmentLandingPageRecyclerViewAdapter() {
        return this.mEntertainmentLandingPageRecyclerViewAdapter;
    }

    Subscription getNetworkStatusSubscription() {
        return this.mNetworkStatusSubscription;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.dm_title_entertainment);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        String str = TAG;
        Log.i(str, "handleNetworkConnectionChange " + z);
        if (getView() != null) {
            View view = getView();
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.dm_no_network_banner);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.entertainment_list_container);
            CardView cardView = (CardView) view.findViewById(R.id.entertainment_landing_page_card_view);
            CardView cardView2 = (CardView) view.findViewById(R.id.network_error_npc);
            ToggleButton toggleButton = (ToggleButton) view.findViewById(R.id.now_playing_card_button);
            ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(R.id.default_container);
            if (z) {
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
                cardView.setVisibility(0);
                cardView2.setVisibility(8);
                toggleButton.setClickable(true);
                toggleButton.setEnabled(true);
                this.mEntertainmentLandingPagePresenter.requestEntertainmentCards();
                return;
            }
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
            constraintLayout.setVisibility(8);
            if (this.mEntertainmentLandingPagePresenter.isMediaPlaying() && this.mEntertainmentLandingPagePresenter.isRecentlyPlayedDataFetchComplete()) {
                cardView.setVisibility(0);
                cardView2.setVisibility(8);
                return;
            }
            cardView.setVisibility(8);
            cardView2.setVisibility(0);
        }
    }

    void initNetworkConnectionManager() {
        handleNetworkConnectionChange(this.mNetworkConnectivityManager.isNetworkConnected());
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$qH012h2GEO4z5E_OdjrkVxL280Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntertainmentLandingPageViewController.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    public /* synthetic */ void lambda$makeView$0$EntertainmentLandingPageViewController(ToggleButton toggleButton, String str, View view) {
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onClick ");
        outline107.append(toggleButton.isChecked());
        Log.i(str2, outline107.toString());
        if (toggleButton.isChecked()) {
            this.mEntertainmentMetrics.logNPCButtonSelected(str, EntertainmentMetrics.Location.ENT, EntertainmentMetrics.Button.PLAY);
            if (this.mNetworkConnectivityManager.isNetworkConnected()) {
                this.mEntertainmentLandingPagePresenter.onNowPlayingCardButtonClick(true);
                toggleButton.setClickable(false);
                DriverDistractionLog.logTouch(LogConstants.ENTERTAINMENT, LogConstants.PAUSE_BUTTON);
                return;
            }
            Log.e(TAG, "No network connection to start playback");
            return;
        }
        this.mEntertainmentMetrics.logNPCButtonSelected(str, EntertainmentMetrics.Location.ENT, EntertainmentMetrics.Button.PAUSE);
        this.mEntertainmentLandingPagePresenter.onNowPlayingCardButtonClick(false);
        toggleButton.setClickable(false);
        if (!this.mNetworkConnectivityManager.isNetworkConnected()) {
            toggleButton.setEnabled(false);
        }
        DriverDistractionLog.logTouch(LogConstants.ENTERTAINMENT, LogConstants.PLAY_BUTTON);
    }

    public /* synthetic */ void lambda$makeView$1$EntertainmentLandingPageViewController() {
        this.mEntertainmentLandingPagePresenter.requestEntertainmentCards();
    }

    public /* synthetic */ void lambda$onAttach$2$EntertainmentLandingPageViewController(EntertainmentCardItem entertainmentCardItem) {
        Log.i(TAG, "OnEntertainment Item Clicked ");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(LogConstants.MEDIA_TITLE, entertainmentCardItem.getTitle());
            jSONObject.put(LogConstants.MEDIA_SUBTITLE, entertainmentCardItem.getSubTitle());
            DriverDistractionLog.logTouch(LogConstants.ACTION_SELECT_PLAYLIST, jSONObject.toString());
        } catch (JSONException unused) {
            Log.e(TAG, "Error creating user study log");
        }
        this.mEntertainmentLandingPagePresenter.onEntertainmentCardClick(entertainmentCardItem);
    }

    public /* synthetic */ void lambda$onMediaError$3$EntertainmentLandingPageViewController() {
        if (getView() != null) {
            ((SwipeRefreshLayout) getView().findViewById(R.id.entertainment_landing_page_swipe_refresh_view)).setRefreshing(false);
            if (this.mEntertainmentLandingPagePresenter.isEntertainmentDataFetched() && this.mEntertainmentLandingPagePresenter.getCurrentEntertainmentDataSize() != 0) {
                getView().findViewById(R.id.default_container).setVisibility(8);
                getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(0);
                return;
            }
            getView().findViewById(R.id.default_container).setVisibility(0);
            getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(8);
        }
    }

    void loadImageToView(String str, ImageView imageView) {
        Log.i(TAG, "loadImageToView");
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getView().getContext(), this.mDriveModeThemeManager.getTheme());
        Drawable drawable = contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.mo1612transforms(new CenterCrop(), new RoundedCorners((int) getView().getContext().getResources().getDimension(R.dimen.entertainment_landing_page_recycler_view_item_corner_radius)));
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        requestOptions.mo1585error(drawable);
        Glide.with(getView().getContext().getApplicationContext()).mo6762load(str).mo1615apply(requestOptions).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                String str2 = EntertainmentLandingPageViewController.TAG;
                Log.e(str2, "onLoadFailed " + glideException);
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable2, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str2 = EntertainmentLandingPageViewController.TAG;
                Log.i(str2, "onResourceReady " + drawable2);
                return false;
            }
        }).mo1632transition(DrawableTransitionOptions.withCrossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build())).into(imageView);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        Log.i(TAG, "makeView");
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(new ContextThemeWrapper(layoutInflater.getContext(), this.mDriveModeThemeManager.getTheme()));
        ViewGroup viewGroup2 = (ViewGroup) super.makeView(cloneInContext, viewGroup);
        this.mHandler = new Handler(Looper.getMainLooper());
        View inflate = cloneInContext.inflate(R.layout.view_controller_entertainment_landing_page, viewGroup, false);
        Log.i(TAG, "layout inflation complete");
        final ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.now_playing_card_button);
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        toggleButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$2-7FOs5RZ5TAjFaOiGm-5YK4koU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EntertainmentLandingPageViewController.this.lambda$makeView$0$EntertainmentLandingPageViewController(toggleButton, autoModeType, view);
            }
        });
        inflate.findViewById(R.id.now_playing_card_container).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(EntertainmentLandingPageViewController.TAG, "onClick");
                EntertainmentLandingPageViewController.this.mEntertainmentMetrics.logNPCSelected(autoModeType, EntertainmentMetrics.Location.ENT);
                if (EntertainmentLandingPageViewController.this.mNetworkConnectivityManager.isNetworkConnected()) {
                    if (!EntertainmentLandingPageViewController.this.mEntertainmentLandingPagePresenter.isNowPlayingItemActive()) {
                        EntertainmentLandingPageViewController.this.mEntertainmentLandingPagePresenter.onNowPlayingCardButtonClick(true);
                    }
                    EntertainmentLandingPageViewController.this.mEntertainmentLandingPagePresenter.onNowPlayingCardClick();
                    DriverDistractionLog.logTouch(LogConstants.ENTERTAINMENT, LogConstants.MUSIC_PLAYING_CARD);
                }
            }
        });
        ((SwipeRefreshLayout) inflate.findViewById(R.id.entertainment_landing_page_swipe_refresh_view)).setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$t-zDofG6xm09izzTt0o484gSYVM
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                EntertainmentLandingPageViewController.this.lambda$makeView$1$EntertainmentLandingPageViewController();
            }
        });
        viewGroup2.addView(inflate);
        return viewGroup2;
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAttach ");
        outline107.append(getView());
        Log.i(str, outline107.toString());
        initNetworkConnectionManager();
        if (getView() != null) {
            if (this.mEntertainmentLandingPagePresenter.isEntertainmentDataFetched()) {
                if (this.mEntertainmentLandingPagePresenter.getCurrentEntertainmentDataSize() == 0) {
                    getView().findViewById(R.id.default_container).setVisibility(0);
                    getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(8);
                    this.mEntertainmentMetrics.logMusicHistoryEmptyDisplayed();
                } else {
                    getView().findViewById(R.id.default_container).setVisibility(8);
                    getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(0);
                    this.mEntertainmentMetrics.logMusicHistoryDisplayed();
                }
            }
            if (this.mEntertainmentLandingPagePresenter.isRecentlyPlayedDataFetchComplete()) {
                this.mTtcfRecordOnce.markComplete(TTCFRecordOnce.ENTERTAINMENT_MAIN);
            }
            if (this.mEntertainmentLandingPagePresenter.isNowPlayingItemActive()) {
                getView().findViewById(R.id.entertainment_landing_page_card_view).setVisibility(0);
            }
            RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.entertainment_cards);
            recyclerView.setHasFixedSize(true);
            RecyclerViewMarginDecoration recyclerViewMarginDecoration = new RecyclerViewMarginDecoration();
            recyclerViewMarginDecoration.setDrawable(getView().getContext().getResources().getDrawable(R.drawable.entertainment_item_list_divider, getView().getContext().getTheme()));
            recyclerView.addItemDecoration(recyclerViewMarginDecoration);
            recyclerView.setNestedScrollingEnabled(false);
            this.mEntertainmentDataRepository.getEntertainmentCardList().addEntertainmentCardItemListListener(getEntertainmentCardListChangeListener());
            this.mEntertainmentLandingPageRecyclerViewAdapter = new EntertainmentLandingPageRecyclerViewAdapter(getView().getContext().getApplicationContext(), this.mEntertainmentDataRepository.getEntertainmentCardList(), new EntertainmentLandingPageRecyclerViewAdapter.OnEntertainmentCardClickListener() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$FADV3wE7uu_e4DVVhtAtcjeuJCI
                @Override // com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageRecyclerViewAdapter.OnEntertainmentCardClickListener
                public final void onEntertainmentCardClick(EntertainmentCardItem entertainmentCardItem) {
                    EntertainmentLandingPageViewController.this.lambda$onAttach$2$EntertainmentLandingPageViewController(entertainmentCardItem);
                }
            }, this.mEntertainmentMetrics, this.mDriveModeThemeManager, this.mWeblabManager);
            getEntertainmentLandingPageRecyclerViewAdapter().setHasStableIds(true);
            recyclerView.setAdapter(getEntertainmentLandingPageRecyclerViewAdapter());
            recyclerView.setLayoutManager(new EntertainmentLandingPageLinearLayoutManager(getView().getContext()));
            if (!this.mEntertainmentLandingPagePresenter.isEntertainmentDataFetched()) {
                DriveModeAnimationUtils.loadingPulseAnimation((RelativeLayout) getView().findViewById(R.id.now_playing_card_container));
            }
            this.mEntertainmentLandingPagePresenter.initialize(this);
        }
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(@NonNull Context context) {
        super.onCreate(context);
        Log.i(TAG, "onCreate");
        DriveModeDependencies.getDriveModeRootComponent().inject(this);
    }

    @Override // com.amazon.alexa.drive.view.ViewManagerViewController, com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        super.onDetach(view);
        Log.i(TAG, "onDetach");
        this.mEntertainmentLandingPagePresenter.cleanUp();
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
        this.mEntertainmentDataRepository.getEntertainmentCardList().removeEntertainmentCardItemListListener(getEntertainmentCardListChangeListener());
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.View
    public void onEntertainmentDataListFetchComplete(int i) {
        String str = TAG;
        Log.i(str, "onEntertainmentDataListFetchComplete " + i);
        if (getView() != null) {
            ((SwipeRefreshLayout) getView().findViewById(R.id.entertainment_landing_page_swipe_refresh_view)).setRefreshing(false);
            if (i == 0) {
                this.mEntertainmentMetrics.logMusicHistoryEmptyDisplayed();
                getView().findViewById(R.id.default_container).setVisibility(0);
                getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(8);
            } else {
                this.mEntertainmentMetrics.logMusicHistoryDisplayed();
                getEntertainmentLandingPageRecyclerViewAdapter().notifyDataSetChanged();
                getView().findViewById(R.id.default_container).setVisibility(8);
                getView().findViewById(R.id.entertainment_landing_page_nested_scroll_view).setVisibility(0);
            }
            if (!this.mEntertainmentLandingPagePresenter.isRecentlyPlayedDataFetchComplete()) {
                return;
            }
            this.mTtcfRecordOnce.markComplete(TTCFRecordOnce.ENTERTAINMENT_MAIN);
        }
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.View
    public void onMediaError(MediaErrorPayload mediaErrorPayload) {
        Log.i(TAG, "onMediaError");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.-$$Lambda$EntertainmentLandingPageViewController$M0wh-x5Opw4IBsOX4qc7MqQllJM
            @Override // java.lang.Runnable
            public final void run() {
                EntertainmentLandingPageViewController.this.lambda$onMediaError$3$EntertainmentLandingPageViewController();
            }
        });
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onRestoreViewState(@NonNull View view, @NonNull Bundle bundle) {
        Log.i(TAG, "onRestoreViewState");
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onSaveViewState(@NonNull View view, @NonNull Bundle bundle) {
        Log.i(TAG, "onSaveViewState");
    }

    @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.View
    public void updateNowPlayingCard(final EntertainmentCardItem entertainmentCardItem, final AlexaPlayerInfoState alexaPlayerInfoState) {
        Log.i(TAG, "updateNowPlayingCard ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.view.EntertainmentLandingPageViewController.4
            @Override // java.lang.Runnable
            public void run() {
                EntertainmentLandingPageViewController.this.updateNowPlayingCardView(entertainmentCardItem, alexaPlayerInfoState);
            }
        });
    }
}

package com.amazon.alexa.drive.entertainment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ContextThemeWrapper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract;
import com.amazon.alexa.drive.entertainment.contract.NowPlayingScreenContract;
import com.amazon.alexa.drive.entertainment.model.EntertainmentCardItem;
import com.amazon.alexa.drive.metrics.DriveModeMetricsHelper;
import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.drive.network.NetworkConnectivityManager;
import com.amazon.alexa.drive.service.DefaultDriveModeService;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.amazon.alexa.drivemode.api.DefaultDriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DoubleIconsCard;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeCardChangeListener;
import com.amazon.alexa.drivemode.api.DriveModeCardsProvider;
import com.amazon.alexa.drivemode.api.DriveModeService;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import com.amazon.alexa.mode.ModeConstants;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
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
import com.google.common.base.Preconditions;
import dagger.Lazy;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
/* loaded from: classes7.dex */
public class EntertainmentManager {
    private static final int ENTERTAINMENT_CARD_ID = 0;
    private static final String TAG = "EntertainmentManager";
    private boolean isDoubleCardRightIconDisabled;
    private boolean isEntertainmentCardAdded;
    private SimpleMultiFilterSubscriber mAutoEgressEventSubscriber;
    private Context mContext;
    private DefaultDriveModeCardsProvider mDefaultDriveModeCardsProvider;
    private DoubleIconsCard mDoubleIconCard;
    private DefaultDriveModeService mDriveModeService;
    private DriveModeThemeManager mDriveModeThemeManager;
    private EntertainmentLandingPageContract.EntertainmentInteractor mEntertainmentInteractor;
    private EntertainmentMetrics mEntertainmentMetrics;
    private EventBus mEventBus;
    private Handler mHandler;
    private Lazy<DriveModeCardsProvider> mLazyDriveModeCardsProvider;
    private SimpleMultiFilterSubscriber mModeChangeEventSubscriber;
    private NetworkConnectivityManager mNetworkConnectivityManager;
    private Subscription mNetworkStatusSubscription;
    private AlexaPlayerInfoState mNowPlayingAlexaPlayerInfoState;
    private EntertainmentCardItem mNowPlayingEntertainmentItem;
    private NowPlayingScreenContract.Interactor mNowPlayingScreenInteractor;
    private EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener onUpdateNowPlayingCardListener;

    public EntertainmentManager(@NonNull Context context, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor, NowPlayingScreenContract.Interactor interactor, NetworkConnectivityManager networkConnectivityManager, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager) {
        Log.i(TAG, "EntertainmentManager Constructor");
        this.mContext = context.getApplicationContext();
        this.mEntertainmentInteractor = entertainmentInteractor;
        this.mEntertainmentInteractor.requestEntertainmentCards();
        this.mNowPlayingScreenInteractor = interactor;
        this.mDriveModeThemeManager = driveModeThemeManager;
        initDriveModeService();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mNetworkConnectivityManager = networkConnectivityManager;
        this.mEntertainmentMetrics = entertainmentMetrics;
        this.mEventBus = (EventBus) GeneratedOutlineSupport1.outline21(EventBus.class);
        Preconditions.checkNotNull(this.mEventBus);
        this.mModeChangeEventSubscriber = new SimpleMultiFilterSubscriber();
        getModeChangeEventSubscriber().subscribe(new EventTypeMessageFilter(ModeConstants.MODE_SWITCHED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManager$B4gQKcmdQ-hTqNJqD8008WsrRIU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EntertainmentManager.this.lambda$new$0$EntertainmentManager(message);
            }
        });
        this.mAutoEgressEventSubscriber = new SimpleMultiFilterSubscriber();
        getModeChangeEventSubscriber().subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_STATE_MACHINE_AUTO_EGRESS), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManager$lKjTxNOXAZQh1IipBR7THZtBOsc
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EntertainmentManager.this.lambda$new$1$EntertainmentManager(message);
            }
        });
        this.mEventBus.subscribe(getModeChangeEventSubscriber());
        this.mEventBus.subscribe(getAutoEgressEventSubscriber());
    }

    private void initDriveModeCardProvider() {
        Log.i(TAG, "initDriveModeCardProvider");
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        this.mDoubleIconCard = new DoubleIconsCard("", "", contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()), null) { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManager.1
            @Override // com.amazon.alexa.drivemode.api.DoubleIconsCard, com.amazon.alexa.drivemode.api.DriveModeCard
            public boolean allowIconTinting() {
                return false;
            }

            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public DriveModeCard.CardDomain getCardDomain() {
                return DriveModeCard.CardDomain.ENTERTAINMENT;
            }

            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public int getCardId() {
                return 0;
            }

            @Override // com.amazon.alexa.drivemode.api.DoubleIconsCard
            public void onCardClicked() {
                Log.i(EntertainmentManager.TAG, "onCardClicked");
                DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.MUSIC_PLAYING_CARD);
                EntertainmentManager.this.mEntertainmentMetrics.logNPCSelected(autoModeType, "Home");
                if (EntertainmentManager.this.mNetworkConnectivityManager.isNetworkConnected()) {
                    if (!EntertainmentManager.this.mEntertainmentInteractor.isNowPlayingItemActive()) {
                        EntertainmentManager.this.mEntertainmentInteractor.requestPlay();
                    }
                    EntertainmentManager.this.mEntertainmentInteractor.navigateToNowPlayingScreen();
                    return;
                }
                Log.i(EntertainmentManager.TAG, "No network connected");
            }

            @Override // com.amazon.alexa.drivemode.api.DoubleIconsCard
            public void onHintClicked() {
            }

            @Override // com.amazon.alexa.drivemode.api.DoubleIconsCard
            public void onRightIconClicked() {
                String str = EntertainmentManager.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onRightIconClicked ");
                outline107.append(EntertainmentManager.this.isDoubleCardRightIconDisabled);
                Log.i(str, outline107.toString());
                ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(EntertainmentManager.this.mContext, EntertainmentManager.this.mDriveModeThemeManager.getTheme());
                if (!EntertainmentManager.this.isDoubleCardRightIconDisabled()) {
                    if (EntertainmentManager.this.getNowPlayingAlexaPlayerInfoState() == AlexaPlayerInfoState.PLAYING) {
                        EntertainmentManager.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.PAUSE);
                        if (EntertainmentManager.this.mNetworkConnectivityManager.isNetworkConnected()) {
                            EntertainmentManager.this.getDoubleIconCard().setRightIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper2.getTheme()));
                        } else {
                            EntertainmentManager.this.getDoubleIconCard().setRightIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_play_icon_disabled, contextThemeWrapper2.getTheme()));
                        }
                        EntertainmentManager.this.getDoubleIconCard().setRightIconContentDescription(contextThemeWrapper2.getResources().getString(R.string.content_desc_media_play_button));
                        EntertainmentManager.this.updateEntertainmentNowPlayingCard();
                        EntertainmentManager.this.mEntertainmentInteractor.requestPause();
                        DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.PAUSE_BUTTON);
                    } else {
                        EntertainmentManager.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.PLAY);
                        EntertainmentManager.this.getDoubleIconCard().setRightIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_pause_icon, contextThemeWrapper2.getTheme()));
                        EntertainmentManager.this.getDoubleIconCard().setRightIconContentDescription(contextThemeWrapper2.getResources().getString(R.string.content_desc_media_pause_button));
                        EntertainmentManager.this.updateEntertainmentNowPlayingCard();
                        EntertainmentManager.this.mEntertainmentInteractor.requestPlay();
                        DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.PLAY_BUTTON);
                    }
                    EntertainmentManager.this.isDoubleCardRightIconDisabled = true;
                }
            }
        };
        this.mLazyDriveModeCardsProvider = new Lazy() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManager$4Su1B5HMx4Veu6wz7bkEiHOHqnM
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return EntertainmentManager.this.lambda$initDriveModeCardProvider$3$EntertainmentManager();
            }
        };
        if (getDefaultDriveModeService() != null) {
            getDefaultDriveModeService().addCardsProvider(this.mLazyDriveModeCardsProvider);
        }
    }

    private void initDriveModeService() {
        Log.i(TAG, "initDriveModeService");
        this.mDriveModeService = (DefaultDriveModeService) ComponentRegistry.getInstance().get(DriveModeService.class).orNull();
    }

    private void initEntertainmentLandingPageInteractor() {
        this.onUpdateNowPlayingCardListener = createOnUpdateNowPlayingCardListener();
        this.mEntertainmentInteractor.addOnUpdateNowPlayingCardListener(getOnUpdateNowPlayingCardListener());
        this.mEntertainmentInteractor.requestNowPlayingCardItem();
    }

    EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener createOnUpdateNowPlayingCardListener() {
        return new EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManager$E4EuibemvoioA5du0i1T8OLMsRk
            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener
            public final void updateNowPlayingCard(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
                EntertainmentManager.this.lambda$createOnUpdateNowPlayingCardListener$2$EntertainmentManager(entertainmentCardItem, alexaPlayerInfoState);
            }
        };
    }

    public void deinit() {
        Log.i(TAG, "deinit");
        removeEntertainmentNowPlayingCard();
        removeCardProvider();
        this.mEntertainmentInteractor.removeOnUpdateNowPlayingCardListener(getOnUpdateNowPlayingCardListener());
        if (!getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
    }

    public void destroy() {
        Log.i(TAG, "destroy");
        this.mEntertainmentInteractor.destroy();
        this.mNowPlayingScreenInteractor.destroy();
        if (getNetworkStatusSubscription() != null && !getNetworkStatusSubscription().isUnsubscribed()) {
            getNetworkStatusSubscription().unsubscribe();
        }
        if (getModeChangeEventSubscriber() != null) {
            this.mEventBus.unsubscribe(getModeChangeEventSubscriber());
        }
        if (getAutoEgressEventSubscriber() != null) {
            this.mEventBus.unsubscribe(getAutoEgressEventSubscriber());
        }
    }

    SimpleMultiFilterSubscriber getAutoEgressEventSubscriber() {
        return this.mAutoEgressEventSubscriber;
    }

    DefaultDriveModeCardsProvider getDefaultDriveModeCardsProvider() {
        return this.mDefaultDriveModeCardsProvider;
    }

    DefaultDriveModeService getDefaultDriveModeService() {
        return this.mDriveModeService;
    }

    DoubleIconsCard getDoubleIconCard() {
        return this.mDoubleIconCard;
    }

    Lazy<DriveModeCardsProvider> getLazyDriveModeCardsProvider() {
        return this.mLazyDriveModeCardsProvider;
    }

    SimpleMultiFilterSubscriber getModeChangeEventSubscriber() {
        return this.mModeChangeEventSubscriber;
    }

    Subscription getNetworkStatusSubscription() {
        return this.mNetworkStatusSubscription;
    }

    AlexaPlayerInfoState getNowPlayingAlexaPlayerInfoState() {
        return this.mNowPlayingAlexaPlayerInfoState;
    }

    EntertainmentCardItem getNowPlayingEntertainmentItem() {
        return this.mNowPlayingEntertainmentItem;
    }

    EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener getOnUpdateNowPlayingCardListener() {
        return this.onUpdateNowPlayingCardListener;
    }

    void handleAutoEgressEvent() {
        Log.i(TAG, "handleAutoEgressEvent");
        if (this.mEntertainmentInteractor.isMediaPlaying()) {
            this.mEntertainmentInteractor.requestPause();
        }
    }

    void handleModeChangeEvent(String str) {
        GeneratedOutlineSupport1.outline163("handleModeChangeEvent ", str, TAG);
        if (str == null || !str.equals(ModeName.MAIN_MODE)) {
            return;
        }
        destroy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleNetworkConnectionChange(boolean z) {
        String str = TAG;
        Log.i(str, "handleNetworkConnectionChange " + z);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        if (z) {
            if (getNowPlayingEntertainmentItem() != null && !this.mEntertainmentInteractor.isMediaPlaying() && this.mEntertainmentInteractor.isNowPlayingItemActive()) {
                getDoubleIconCard().setRightIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper.getTheme()));
            }
            this.isDoubleCardRightIconDisabled = false;
            updateEntertainmentNowPlayingCard();
        } else if (this.mEntertainmentInteractor.isMediaPlaying()) {
        } else {
            removeEntertainmentNowPlayingCard();
        }
    }

    public void init() {
        Log.i(TAG, "init");
        initDriveModeCardProvider();
        initEntertainmentLandingPageInteractor();
        initNetworkConnectionManager();
    }

    void initNetworkConnectionManager() {
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$fS261RERABzFJ3DoTO8q5qkEd9Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntertainmentManager.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    boolean isDoubleCardRightIconDisabled() {
        return this.isDoubleCardRightIconDisabled;
    }

    boolean isEntertainmentCardAdded() {
        return this.isEntertainmentCardAdded;
    }

    public /* synthetic */ void lambda$createOnUpdateNowPlayingCardListener$2$EntertainmentManager(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
        String str = TAG;
        Log.i(str, "onUpdateNowPlayingCardListener " + alexaPlayerInfoState);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        this.isDoubleCardRightIconDisabled = false;
        if (alexaPlayerInfoState == AlexaPlayerInfoState.PLAYING || alexaPlayerInfoState == AlexaPlayerInfoState.DONE) {
            this.mNowPlayingEntertainmentItem = entertainmentCardItem;
            this.mNowPlayingAlexaPlayerInfoState = alexaPlayerInfoState;
            getDoubleIconCard().setTitle(entertainmentCardItem.getTitle());
            getDoubleIconCard().setSubtitle(entertainmentCardItem.getSubTitle());
            if (this.mEntertainmentInteractor.isNowPlayingItemActive()) {
                if (alexaPlayerInfoState == AlexaPlayerInfoState.PLAYING) {
                    getDoubleIconCard().setRightIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_pause_icon, contextThemeWrapper.getTheme()));
                    getDoubleIconCard().setRightIconContentDescription(contextThemeWrapper.getResources().getString(R.string.content_desc_media_pause_button));
                } else {
                    getDoubleIconCard().setRightIconContentDescription(contextThemeWrapper.getResources().getString(R.string.content_desc_media_play_button));
                    if (this.mNetworkConnectivityManager.isNetworkConnected()) {
                        getDoubleIconCard().setRightIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper.getTheme()));
                    } else {
                        getDoubleIconCard().setRightIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon_disabled, contextThemeWrapper.getTheme()));
                        this.isDoubleCardRightIconDisabled = true;
                    }
                }
            } else {
                getDoubleIconCard().setRightIcon(null);
            }
            getDoubleIconCard().setLeftIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
            if (entertainmentCardItem.getAlbumArtUrls().size() > 0) {
                String optString = entertainmentCardItem.getAlbumArtUrls().get(entertainmentCardItem.getAlbumArtUrls().size() - 1).optString("url", "");
                if (!optString.isEmpty()) {
                    loadAlbumArtImage(optString);
                }
            }
            updateEntertainmentNowPlayingCard();
        }
    }

    public /* synthetic */ DriveModeCardsProvider lambda$initDriveModeCardProvider$3$EntertainmentManager() {
        this.mDefaultDriveModeCardsProvider = new DefaultDriveModeCardsProvider() { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManager.2
            @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
            public void provideCards() {
                Log.i(EntertainmentManager.TAG, "provideCards");
                EntertainmentManager.this.updateEntertainmentNowPlayingCard();
            }
        };
        return this.mDefaultDriveModeCardsProvider;
    }

    public /* synthetic */ void lambda$loadAlbumArtImage$4$EntertainmentManager(String str) {
        final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        RequestOptions requestOptions = new RequestOptions();
        DrawableCrossFadeFactory build = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        requestOptions.mo1612transforms(new CenterCrop(), new RoundedCorners((int) this.mContext.getResources().getDimension(R.dimen.now_playing_card_album_art_corner_radius)));
        requestOptions.mo1602placeholder(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(this.mContext).mo1636asDrawable().mo6762load(str).mo1615apply(requestOptions).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManager.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                String str2 = EntertainmentManager.TAG;
                Log.e(str2, "onLoadFailed " + glideException);
                EntertainmentManager.this.getDoubleIconCard().setLeftIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
                EntertainmentManager.this.updateEntertainmentNowPlayingCard();
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str2 = EntertainmentManager.TAG;
                Log.i(str2, "onResourceReady " + drawable);
                EntertainmentManager.this.getDoubleIconCard().setLeftIcon(drawable);
                EntertainmentManager.this.updateEntertainmentNowPlayingCard();
                return false;
            }
        }).mo1632transition(DrawableTransitionOptions.withCrossFade(build)).submit();
    }

    public /* synthetic */ void lambda$new$0$EntertainmentManager(Message message) {
        handleModeChangeEvent(message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$new$1$EntertainmentManager(Message message) {
        handleAutoEgressEvent();
    }

    void loadAlbumArtImage(final String str) {
        Log.i(TAG, "loadAlbumArtImage ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManager$VJc6Gz-isaLOEpTc7P_1rq1Vyz8
            @Override // java.lang.Runnable
            public final void run() {
                EntertainmentManager.this.lambda$loadAlbumArtImage$4$EntertainmentManager(str);
            }
        });
    }

    public void removeCardProvider() {
        if (getDefaultDriveModeService() != null) {
            Log.i(TAG, "removeCardProvider");
            getDefaultDriveModeService().getProviders().remove(this.mDefaultDriveModeCardsProvider);
        }
    }

    void removeEntertainmentNowPlayingCard() {
        Log.i(TAG, "removeEntertainmentNowPlayingCard");
        if (getDefaultDriveModeCardsProvider() == null || getDefaultDriveModeCardsProvider().getCardChangeListener() == null) {
            return;
        }
        DriveModeCardChangeListener cardChangeListener = getDefaultDriveModeCardsProvider().getCardChangeListener();
        if (isEntertainmentCardAdded()) {
            cardChangeListener.removeCard(getDoubleIconCard());
        }
        this.isEntertainmentCardAdded = false;
    }

    void updateEntertainmentNowPlayingCard() {
        Log.i(TAG, "updateEntertainmentNowPlayingCard");
        if (getDefaultDriveModeCardsProvider() == null || getDefaultDriveModeCardsProvider().getCardChangeListener() == null) {
            return;
        }
        DriveModeCardChangeListener cardChangeListener = getDefaultDriveModeCardsProvider().getCardChangeListener();
        if (getNowPlayingEntertainmentItem() == null) {
            return;
        }
        Log.i(TAG, "addCard mNowPlayingEntertainmentItem is not null");
        if (!this.mNetworkConnectivityManager.isNetworkConnected() && !this.mEntertainmentInteractor.isMediaPlaying()) {
            return;
        }
        if (isEntertainmentCardAdded()) {
            cardChangeListener.updateCard(getDoubleIconCard());
            return;
        }
        cardChangeListener.addCard(getDoubleIconCard());
        this.isEntertainmentCardAdded = true;
    }
}

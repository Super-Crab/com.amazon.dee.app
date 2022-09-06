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
public class EntertainmentManagerV2 {
    private static final int ENTERTAINMENT_CARD_ID = 0;
    private static final String TAG = "EntertainmentManagerV2";
    private boolean isEntertainmentCardAdded;
    private SimpleMultiFilterSubscriber mAutoEgressEventSubscriber;
    private Context mContext;
    private DefaultDriveModeCardsProvider mDefaultDriveModeCardsProvider;
    private DefaultDriveModeService mDriveModeService;
    private DriveModeThemeManager mDriveModeThemeManager;
    private EntertainmentCard mEntertainmentCard;
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

    public EntertainmentManagerV2(@NonNull Context context, EntertainmentLandingPageContract.EntertainmentInteractor entertainmentInteractor, NowPlayingScreenContract.Interactor interactor, NetworkConnectivityManager networkConnectivityManager, EntertainmentMetrics entertainmentMetrics, DriveModeThemeManager driveModeThemeManager) {
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
        getModeChangeEventSubscriber().subscribeFilter(new EventTypeMessageFilter(ModeConstants.MODE_SWITCHED_EVENT), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManagerV2$_LKN-th-jF2XNgOgPPi63QUQJMo
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EntertainmentManagerV2.this.lambda$new$0$EntertainmentManagerV2(message);
            }
        });
        this.mAutoEgressEventSubscriber = new SimpleMultiFilterSubscriber();
        getModeChangeEventSubscriber().subscribeFilter(new EventTypeMessageFilter(Constants.DRIVE_MODE_STATE_MACHINE_AUTO_EGRESS), new MessageHandler() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManagerV2$-Za9UwBprNao-GtaHN3xsxhbCaA
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                EntertainmentManagerV2.this.lambda$new$1$EntertainmentManagerV2(message);
            }
        });
        this.mEventBus.subscribe(getModeChangeEventSubscriber());
        this.mEventBus.subscribe(getAutoEgressEventSubscriber());
    }

    private void initDriveModeCardProvider() {
        Log.i(TAG, "initDriveModeCardProvider");
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        final String autoModeType = DriveModeMetricsHelper.getAutoModeType();
        this.mEntertainmentCard = new EntertainmentCard("", "", contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()), null, contextThemeWrapper.getResources().getDrawable(R.drawable.ic_previous, contextThemeWrapper.getTheme()), contextThemeWrapper.getResources().getDrawable(R.drawable.ic_next, contextThemeWrapper.getTheme())) { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManagerV2.1
            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public DriveModeCard.CardDomain getCardDomain() {
                return DriveModeCard.CardDomain.ENTERTAINMENT;
            }

            @Override // com.amazon.alexa.drivemode.api.DriveModeCard
            public int getCardId() {
                return 0;
            }

            @Override // com.amazon.alexa.drive.entertainment.EntertainmentCard
            public void onCardClicked() {
                Log.i("TAG", "entertainment card clicked");
                DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.MUSIC_PLAYING_CARD);
                EntertainmentManagerV2.this.mEntertainmentMetrics.logNPCSelected(autoModeType, "Home");
                if (EntertainmentManagerV2.this.mNetworkConnectivityManager.isNetworkConnected()) {
                    if (!EntertainmentManagerV2.this.mEntertainmentInteractor.isNowPlayingItemActive()) {
                        EntertainmentManagerV2.this.mEntertainmentInteractor.requestPlay();
                    }
                    EntertainmentManagerV2.this.mEntertainmentInteractor.navigateToNowPlayingScreen();
                    return;
                }
                Log.i(EntertainmentManagerV2.TAG, "No network connected");
            }

            @Override // com.amazon.alexa.drive.entertainment.EntertainmentCard
            public void onNextButtonPressed() {
                Log.i(EntertainmentManagerV2.TAG, "next song button pressed");
                if (EntertainmentManagerV2.this.getNowPlayingAlexaPlayerInfoState() == AlexaPlayerInfoState.PLAYING) {
                    EntertainmentManagerV2.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.NEXT);
                    EntertainmentManagerV2.this.mEntertainmentInteractor.requestNext();
                    EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                    DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.NEXT_BUTTON);
                }
            }

            @Override // com.amazon.alexa.drive.entertainment.EntertainmentCard
            public void onPlayPauseButtonPressed() {
                Log.i(EntertainmentManagerV2.TAG, "on play/pause button clicked");
                ContextThemeWrapper contextThemeWrapper2 = new ContextThemeWrapper(EntertainmentManagerV2.this.mContext, EntertainmentManagerV2.this.mDriveModeThemeManager.getTheme());
                if (EntertainmentManagerV2.this.getNowPlayingAlexaPlayerInfoState() == AlexaPlayerInfoState.PLAYING) {
                    EntertainmentManagerV2.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.PAUSE);
                    if (EntertainmentManagerV2.this.mNetworkConnectivityManager.isNetworkConnected()) {
                        EntertainmentManagerV2.this.getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper2.getTheme()));
                    } else {
                        EntertainmentManagerV2.this.getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_play_icon_disabled, contextThemeWrapper2.getTheme()));
                    }
                    EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                    EntertainmentManagerV2.this.mEntertainmentInteractor.requestPause();
                    DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.PAUSE_BUTTON);
                    return;
                }
                EntertainmentManagerV2.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.PLAY);
                EntertainmentManagerV2.this.getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper2.getResources().getDrawable(R.drawable.now_playing_card_pause_icon, contextThemeWrapper2.getTheme()));
                EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                EntertainmentManagerV2.this.mEntertainmentInteractor.requestPlay();
                DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.PLAY_BUTTON);
            }

            @Override // com.amazon.alexa.drive.entertainment.EntertainmentCard
            public void onPreviousButtonPressed() {
                Log.i(EntertainmentManagerV2.TAG, "previous song button pressed");
                if (EntertainmentManagerV2.this.getNowPlayingAlexaPlayerInfoState() == AlexaPlayerInfoState.PLAYING) {
                    EntertainmentManagerV2.this.mEntertainmentMetrics.logNPCButtonSelected(autoModeType, "Home", EntertainmentMetrics.Button.PREV);
                    EntertainmentManagerV2.this.mEntertainmentInteractor.requestPrevious();
                    EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                    DriverDistractionLog.logTouch(LogConstants.LANDING_PAGE, LogConstants.PREVIOUS_BUTTON);
                }
            }
        };
        this.mLazyDriveModeCardsProvider = new Lazy() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManagerV2$lnPSMCdFt1wviB5QcxOqMzneim0
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return EntertainmentManagerV2.this.lambda$initDriveModeCardProvider$3$EntertainmentManagerV2();
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
        return new EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManagerV2$y1cpnAUAnV4DrKypW-6MKFkhMgk
            @Override // com.amazon.alexa.drive.entertainment.contract.EntertainmentLandingPageContract.EntertainmentInteractor.OnUpdateNowPlayingCardListener
            public final void updateNowPlayingCard(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
                EntertainmentManagerV2.this.lambda$createOnUpdateNowPlayingCardListener$2$EntertainmentManagerV2(entertainmentCardItem, alexaPlayerInfoState);
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

    EntertainmentCard getEntertainmentCard() {
        return this.mEntertainmentCard;
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
                getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper.getTheme()));
            }
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
        this.mNetworkStatusSubscription = this.mNetworkConnectivityManager.getNetworkConnectivityStream().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$wkfJoQW68trWIs_5ic9oW4uxw6Q
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                EntertainmentManagerV2.this.handleNetworkConnectionChange(((Boolean) obj).booleanValue());
            }
        });
    }

    boolean isEntertainmentCardAdded() {
        return this.isEntertainmentCardAdded;
    }

    public /* synthetic */ void lambda$createOnUpdateNowPlayingCardListener$2$EntertainmentManagerV2(EntertainmentCardItem entertainmentCardItem, AlexaPlayerInfoState alexaPlayerInfoState) {
        String str = TAG;
        Log.i(str, "onUpdateNowPlayingCardListener " + alexaPlayerInfoState);
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        if (alexaPlayerInfoState == AlexaPlayerInfoState.PLAYING || alexaPlayerInfoState == AlexaPlayerInfoState.DONE) {
            this.mNowPlayingEntertainmentItem = entertainmentCardItem;
            this.mNowPlayingAlexaPlayerInfoState = alexaPlayerInfoState;
            getEntertainmentCard().setTitle(entertainmentCardItem.getTitle());
            getEntertainmentCard().setSubtitle(entertainmentCardItem.getSubTitle());
            if (this.mEntertainmentInteractor.isNowPlayingItemActive()) {
                if (alexaPlayerInfoState == AlexaPlayerInfoState.PLAYING) {
                    getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_pause_icon, contextThemeWrapper.getTheme()));
                } else if (this.mNetworkConnectivityManager.isNetworkConnected()) {
                    getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper.getTheme()));
                } else {
                    getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon_disabled, contextThemeWrapper.getTheme()));
                }
            } else {
                getEntertainmentCard().setPlayPauseIcon(contextThemeWrapper.getResources().getDrawable(R.drawable.now_playing_card_play_icon, contextThemeWrapper.getTheme()));
            }
            getEntertainmentCard().setAlbumArtImage(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
            if (entertainmentCardItem.getAlbumArtUrls().size() > 0) {
                String optString = entertainmentCardItem.getAlbumArtUrls().get(entertainmentCardItem.getAlbumArtUrls().size() - 1).optString("url", "");
                if (!optString.isEmpty()) {
                    loadAlbumArtImage(optString);
                }
            }
            updateEntertainmentNowPlayingCard();
        }
    }

    public /* synthetic */ DriveModeCardsProvider lambda$initDriveModeCardProvider$3$EntertainmentManagerV2() {
        this.mDefaultDriveModeCardsProvider = new DefaultDriveModeCardsProvider() { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManagerV2.2
            @Override // com.amazon.alexa.drivemode.api.DriveModeCardsProvider
            public void provideCards() {
                Log.i(EntertainmentManagerV2.TAG, "provideCards");
                EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
            }
        };
        return this.mDefaultDriveModeCardsProvider;
    }

    public /* synthetic */ void lambda$loadAlbumArtImage$4$EntertainmentManagerV2(String str) {
        final ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(this.mContext, this.mDriveModeThemeManager.getTheme());
        RequestOptions requestOptions = new RequestOptions();
        DrawableCrossFadeFactory build = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        requestOptions.mo1612transforms(new CenterCrop(), new RoundedCorners((int) this.mContext.getResources().getDimension(R.dimen.now_playing_card_album_art_corner_radius)));
        requestOptions.mo1602placeholder(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
        requestOptions.mo1578diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        Glide.with(this.mContext).mo1636asDrawable().mo6762load(str).mo1615apply(requestOptions).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.alexa.drive.entertainment.EntertainmentManagerV2.3
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                String str2 = EntertainmentManagerV2.TAG;
                Log.e(str2, "onLoadFailed " + glideException);
                EntertainmentManagerV2.this.getEntertainmentCard().setAlbumArtImage(contextThemeWrapper.getResources().getDrawable(R.drawable.dm_ic_music, contextThemeWrapper.getTheme()));
                EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                String str2 = EntertainmentManagerV2.TAG;
                Log.i(str2, "onResourceReady " + drawable);
                EntertainmentManagerV2.this.getEntertainmentCard().setAlbumArtImage(drawable);
                EntertainmentManagerV2.this.updateEntertainmentNowPlayingCard();
                return false;
            }
        }).mo1632transition(DrawableTransitionOptions.withCrossFade(build)).submit();
    }

    public /* synthetic */ void lambda$new$0$EntertainmentManagerV2(Message message) {
        handleModeChangeEvent(message.getPayloadAsString());
    }

    public /* synthetic */ void lambda$new$1$EntertainmentManagerV2(Message message) {
        handleAutoEgressEvent();
    }

    void loadAlbumArtImage(final String str) {
        Log.i(TAG, "loadAlbumArtImage ");
        this.mHandler.post(new Runnable() { // from class: com.amazon.alexa.drive.entertainment.-$$Lambda$EntertainmentManagerV2$LiZI0KAQF20mCcE-lyprR2OvqoM
            @Override // java.lang.Runnable
            public final void run() {
                EntertainmentManagerV2.this.lambda$loadAlbumArtImage$4$EntertainmentManagerV2(str);
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
            cardChangeListener.removeCard(getEntertainmentCard());
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
            cardChangeListener.updateCard(getEntertainmentCard());
            return;
        }
        cardChangeListener.addCard(getEntertainmentCard());
        this.isEntertainmentCardAdded = true;
    }
}

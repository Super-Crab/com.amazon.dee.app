package com.amazon.dee.app.ui.nowplaying;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.ui.onedesign.transitions.DismissContentTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.PushContentTransition;
import com.amazon.alexa.voice.ui.player.PlaybackController;
import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import com.amazon.alexa.voice.ui.player.PlayerCardService;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import com.amazon.alexa.voice.ui.player.PlayerContract;
import com.amazon.alexa.voice.ui.player.PlayerController;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.core.MainActivityLifecycleService;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.regulator.Component;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.transitions.InstantTransition;
import com.dee.app.metrics.MetricsService;
/* loaded from: classes12.dex */
public class NowPlayingViewModel implements MainActivityLifecycleObserver, CardRenderInterface {
    private Activity activity;
    private PlayerContract.Collapsible collapsible;
    private IdentityService identityService;
    private volatile boolean isInitialized;
    private Observable.OnPropertyChangedCallback isPlayerActivePropertyChangeCallBack;
    private MainActivityLifecycleService mainActivityLifecycleService;
    private MetricsService metricsService;
    private PlayerCardUpdater nativeNowPlayingCardUpdater;
    private Observable.OnPropertyChangedCallback nativeNowPlayingPropertyChangeCallBack;
    private NowPlayingHandler nowPlayingHandler;
    private CardDismissListener onCardDismissListener;
    private boolean playerCollapsed;
    private Router playerRouter;
    private VoicePlaybackEventTranslator voicePlaybackEventTranslator;
    private VoiceService voiceService;
    private final ObservableBoolean isPlayerVisible = new ObservableBoolean(false);
    private final ObservableBoolean isPlayerActive = new ObservableBoolean(false);
    private final ObservableBoolean hasRemotePlayback = new ObservableBoolean(false);
    private final ObservableBoolean hasLocalPlayback = new ObservableBoolean(false);
    private PlayerCardService playerCardService = new PlayerCardService() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$NowPlayingViewModel$gpNmEMhSEdnmp1iyz_PHFNLngM4
        @Override // com.amazon.alexa.voice.ui.player.PlayerCardService
        public final void onPlayerCollapsibleAvailable(PlayerContract.Collapsible collapsible) {
            NowPlayingViewModel.this.lambda$new$0$NowPlayingViewModel(collapsible);
        }
    };
    private Router.OnTransactionListener nowPlayingCardTransactionListener = new Router.OnTransactionAdapter() { // from class: com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel.1
        @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
        public void onAfterTransition(ControllerTransaction controllerTransaction) {
            if ((NowPlayingViewModel.this.playerRouter.getRootController() instanceof PlayerController) || NowPlayingViewModel.this.onCardDismissListener == null) {
                return;
            }
            NowPlayingViewModel.this.onCardDismissListener.onCardDismissed();
        }
    };

    public NowPlayingViewModel(Activity activity, MainActivityLifecycleService mainActivityLifecycleService, PlayerCardUpdater playerCardUpdater, MetricsService metricsService, VoiceService voiceService, IdentityService identityService) {
        this.activity = activity;
        this.mainActivityLifecycleService = mainActivityLifecycleService;
        this.nativeNowPlayingCardUpdater = playerCardUpdater;
        this.metricsService = metricsService;
        this.voiceService = voiceService;
        this.identityService = identityService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordNativePlayerVisibleMetric() {
        this.metricsService.recordEvent(AlexaMetricsConstants.MetricEvents.NATIVE_NOW_PLAYING_UI, "Entertainment", null);
    }

    @Override // com.amazon.dee.app.ui.nowplaying.CardRenderInterface
    public void displayNativeNowPlayingCard(final PlayerCardModel playerCardModel) {
        this.activity.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$NowPlayingViewModel$N8KlafO8_H-VZTpceDhl566ohvc
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingViewModel.this.lambda$displayNativeNowPlayingCard$2$NowPlayingViewModel(playerCardModel);
            }
        });
    }

    public void initialize(Bundle bundle, NowPlayingHandler nowPlayingHandler, FrameLayout frameLayout) {
        if (this.isInitialized) {
            return;
        }
        boolean z = true;
        this.isInitialized = true;
        this.nowPlayingHandler = nowPlayingHandler;
        Component component = new Component();
        component.provide((Class<? extends Class>) PlaybackController.class, (Class) this.voiceService.getPlaybackController()).register();
        component.provide((Class<? extends Class>) PlayerCardUpdater.class, (Class) this.nativeNowPlayingCardUpdater).register();
        component.provide((Class<? extends Class>) PlayerCardService.class, (Class) this.playerCardService).register();
        if (bundle == null || !bundle.getBoolean("playerCollapsed")) {
            z = false;
        }
        this.playerCollapsed = z;
        this.playerRouter = new Router(this.activity, component, bundle == null ? null : (Bundle) bundle.getParcelable("playerState"));
        this.playerRouter.attach(frameLayout);
        this.playerRouter.addOnPopTransactionListener(this.nowPlayingCardTransactionListener);
        this.nativeNowPlayingPropertyChangeCallBack = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (((ObservableBoolean) observable).get()) {
                    NowPlayingViewModel.this.recordNativePlayerVisibleMetric();
                }
            }
        };
        this.isPlayerVisible.addOnPropertyChangedCallback(this.nativeNowPlayingPropertyChangeCallBack);
        this.isPlayerActivePropertyChangeCallBack = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.nowplaying.NowPlayingViewModel.3
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                NowPlayingViewModel.this.updatePlayerVisibility();
            }
        };
        this.isPlayerActive.addOnPropertyChangedCallback(this.isPlayerActivePropertyChangeCallBack);
        updatePlayerVisibility();
        this.voicePlaybackEventTranslator = new VoicePlaybackEventTranslator(this.voiceService, this, this.hasLocalPlayback, this.isPlayerActive, this.isPlayerVisible, this.identityService, this.activity.getResources().getInteger(R.integer.time_to_wait_for_next_audio_item));
        this.mainActivityLifecycleService.addObserver(this);
    }

    public ObservableBoolean isPlayerVisible() {
        return this.isPlayerVisible;
    }

    public /* synthetic */ void lambda$displayNativeNowPlayingCard$2$NowPlayingViewModel(PlayerCardModel playerCardModel) {
        if (this.playerRouter.getRootController() instanceof PlayerController) {
            this.nativeNowPlayingCardUpdater.updateCard(playerCardModel);
        } else {
            this.playerRouter.replaceCurrentController(new ControllerTransaction(PlayerController.create(playerCardModel, true, true), new DismissContentTransition(), this.playerRouter.hasRootController() ? new InstantTransition() : new PushContentTransition()));
        }
    }

    public /* synthetic */ void lambda$new$0$NowPlayingViewModel(PlayerContract.Collapsible collapsible) {
        this.collapsible = collapsible;
    }

    public /* synthetic */ void lambda$onActivityDestroy$3$NowPlayingViewModel() {
        this.playerRouter.detach();
        this.playerRouter = null;
    }

    public /* synthetic */ void lambda$removeNativeNowplayingCard$1$NowPlayingViewModel() {
        this.playerRouter.popCurrentController();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityCreated() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityDestroy() {
        this.mainActivityLifecycleService.removeObserver(this);
        this.isPlayerVisible.removeOnPropertyChangedCallback(this.nativeNowPlayingPropertyChangeCallBack);
        this.isPlayerActive.removeOnPropertyChangedCallback(this.isPlayerActivePropertyChangeCallBack);
        this.voicePlaybackEventTranslator.release();
        this.activity.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$NowPlayingViewModel$IbUEclYw8WMpWP_RJqGWWCAekUk
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingViewModel.this.lambda$onActivityDestroy$3$NowPlayingViewModel();
            }
        });
        this.nowPlayingHandler = null;
        this.voicePlaybackEventTranslator = null;
        this.isInitialized = false;
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityPause() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityResume() {
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStart() {
        if (!this.isInitialized) {
            return;
        }
        this.voicePlaybackEventTranslator.init();
    }

    @Override // com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserver
    public void onActivityStop() {
        if (!this.isInitialized) {
            return;
        }
        this.voicePlaybackEventTranslator.release();
    }

    @Override // com.amazon.dee.app.ui.nowplaying.CardRenderInterface
    public void removeNativeNowplayingCard() {
        this.activity.runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$NowPlayingViewModel$bEPv2hwMg64U-ARqkKmGVxQA9RM
            @Override // java.lang.Runnable
            public final void run() {
                NowPlayingViewModel.this.lambda$removeNativeNowplayingCard$1$NowPlayingViewModel();
            }
        });
    }

    public void restoreState(Bundle bundle) {
        if (bundle == null || this.playerRouter.isAttached()) {
            return;
        }
        this.playerCollapsed = bundle.getBoolean("playerCollapsed");
        this.playerRouter.restoreInstanceState((Bundle) bundle.getParcelable("playerState"));
    }

    public Bundle saveState() {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        this.playerRouter.saveInstanceState(bundle2);
        bundle.putParcelable("playerState", bundle2);
        bundle.putBoolean("playerCollapsed", this.playerCollapsed);
        return bundle;
    }

    @Override // com.amazon.dee.app.ui.nowplaying.CardRenderInterface
    public void setOnCardDismissListener(CardDismissListener cardDismissListener) {
        this.onCardDismissListener = cardDismissListener;
    }

    public void setRemoteAudioPlaying(boolean z) {
        this.hasRemotePlayback.set(z);
    }

    public void updatePlayerVisibility() {
        PlayerContract.Collapsible collapsible;
        if (!this.isInitialized) {
            return;
        }
        boolean z = this.isPlayerActive.get();
        if (!this.nowPlayingHandler.isCurrentTabNowPlaying() && (collapsible = this.collapsible) != null) {
            collapsible.collapse();
        }
        this.isPlayerVisible.set(z);
    }
}

package com.amazon.dee.app.ui.nowplaying;

import android.os.Handler;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.alexa.voice.nowplaying.AudioPlayerListener;
import com.amazon.alexa.voice.nowplaying.SimpleAudioPlayerListener;
import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class VoicePlaybackEventTranslator {
    private final AudioPlayerListener audioPlayerListener;
    private final CardRenderInterface cardRenderInterface;
    private IdentityService identityService;
    private final ObservableBoolean isCardSupported;
    private final AtomicBoolean isDismissingCard;
    private final ObservableBoolean isPlayerActive;
    private final ObservableBoolean isPlayerVisible;
    private Disposable playerCardChangeSubscription;
    private final Observable.OnPropertyChangedCallback playerIsActiveCallBack;
    private final Observable.OnPropertyChangedCallback playerViewChangeCallback;
    private final ObservableBoolean shouldPlayerBeActive;
    private final VoiceService voiceService;
    private static final List<String> UNSUPPORTED_CHANNELS_NOW_PLAYING = Arrays.asList("Domain:Application:DailyBriefing", "Domain:Phatic:AlexaOriginals");
    static final String TAG = VoicePlaybackEventTranslator.class.getSimpleName();

    /* loaded from: classes12.dex */
    private static class NowPlayingListener extends SimpleAudioPlayerListener {
        private final Handler handler;
        private final ObservableBoolean hasLocalPlayback;
        private final AtomicBoolean isDismissingCard;
        private final Runnable playerInActiveRunnable;
        private final ObservableBoolean shouldPlayerBeActive;
        private final long timeToWaitForNextAudioItemMs;

        NowPlayingListener(ObservableBoolean observableBoolean, final ObservableBoolean observableBoolean2, AtomicBoolean atomicBoolean, long j, Handler handler) {
            this.hasLocalPlayback = observableBoolean;
            this.shouldPlayerBeActive = observableBoolean2;
            this.isDismissingCard = atomicBoolean;
            this.timeToWaitForNextAudioItemMs = j;
            this.handler = handler;
            this.playerInActiveRunnable = new Runnable() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$VoicePlaybackEventTranslator$NowPlayingListener$47AjTnwtt_WuLjC7c5AovKcpxKw
                @Override // java.lang.Runnable
                public final void run() {
                    ObservableBoolean.this.set(false);
                }
            };
        }

        @Override // com.amazon.alexa.voice.nowplaying.SimpleAudioPlayerListener, com.amazon.alexa.voice.nowplaying.AudioPlayerListener
        public void onError() {
            this.hasLocalPlayback.set(false);
            this.isDismissingCard.set(false);
            this.handler.postDelayed(this.playerInActiveRunnable, 0L);
        }

        @Override // com.amazon.alexa.voice.nowplaying.SimpleAudioPlayerListener, com.amazon.alexa.voice.nowplaying.AudioPlayerListener
        public void onPaused() {
            this.hasLocalPlayback.set(false);
            this.shouldPlayerBeActive.set(!this.isDismissingCard.get());
        }

        @Override // com.amazon.alexa.voice.nowplaying.SimpleAudioPlayerListener, com.amazon.alexa.voice.nowplaying.AudioPlayerListener
        public void onPlaybackEnded() {
            this.hasLocalPlayback.set(false);
            if (this.isDismissingCard.get()) {
                this.shouldPlayerBeActive.set(false);
            } else {
                this.handler.postDelayed(this.playerInActiveRunnable, this.timeToWaitForNextAudioItemMs);
            }
        }

        @Override // com.amazon.alexa.voice.nowplaying.SimpleAudioPlayerListener, com.amazon.alexa.voice.nowplaying.AudioPlayerListener
        public void onPlaying() {
            this.handler.removeCallbacks(this.playerInActiveRunnable);
            this.hasLocalPlayback.set(true);
            this.shouldPlayerBeActive.set(true);
            this.isDismissingCard.set(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoicePlaybackEventTranslator(VoiceService voiceService, CardRenderInterface cardRenderInterface, ObservableBoolean observableBoolean, ObservableBoolean observableBoolean2, ObservableBoolean observableBoolean3, IdentityService identityService, int i) {
        this(voiceService, cardRenderInterface, observableBoolean, observableBoolean2, observableBoolean3, identityService, i, new Handler());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nowPlayingPlayerModelChanged(PlayerCardModel playerCardModel) {
        if (!this.isPlayerVisible.get()) {
            return;
        }
        if (!shouldCardBeRendered(playerCardModel)) {
            this.cardRenderInterface.removeNativeNowplayingCard();
        } else if (!this.isPlayerActive.get()) {
            this.cardRenderInterface.removeNativeNowplayingCard();
        } else {
            this.cardRenderInterface.displayNativeNowPlayingCard(playerCardModel);
        }
    }

    private boolean shouldCardBeRendered(PlayerCardModel playerCardModel) {
        if (playerCardModel != null && playerCardModel.getAudioItemId() != null) {
            for (String str : UNSUPPORTED_CHANNELS_NOW_PLAYING) {
                if (playerCardModel.getAudioItemId().contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void init() {
        this.cardRenderInterface.setOnCardDismissListener(new CardDismissListener() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$VoicePlaybackEventTranslator$3pFhF-fF6xbSQtIWC_AxP9EPkAo
            @Override // com.amazon.dee.app.ui.nowplaying.CardDismissListener
            public final void onCardDismissed() {
                VoicePlaybackEventTranslator.this.lambda$init$0$VoicePlaybackEventTranslator();
            }
        });
        this.voiceService.addAudioPlayerListener(this.audioPlayerListener);
        this.playerCardChangeSubscription = this.voiceService.getNowPlayingCardManager().onPlayerCard().subscribe(new Consumer() { // from class: com.amazon.dee.app.ui.nowplaying.-$$Lambda$VoicePlaybackEventTranslator$S6r5b6mrTRo7VuLjxdYAFkxn-AQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                VoicePlaybackEventTranslator.this.lambda$init$1$VoicePlaybackEventTranslator((PlayerCardModel) obj);
            }
        });
        this.isPlayerVisible.addOnPropertyChangedCallback(this.playerViewChangeCallback);
        nowPlayingPlayerModelChanged(this.voiceService.getNowPlayingCardManager().getPlayerCard());
    }

    public /* synthetic */ void lambda$init$0$VoicePlaybackEventTranslator() {
        this.isDismissingCard.set(true);
        this.shouldPlayerBeActive.set(false);
    }

    public /* synthetic */ void lambda$init$1$VoicePlaybackEventTranslator(PlayerCardModel playerCardModel) throws Throwable {
        this.isCardSupported.set(shouldCardBeRendered(playerCardModel));
        nowPlayingPlayerModelChanged(playerCardModel);
    }

    public void release() {
        this.voiceService.removeAudioPlayerListener(this.audioPlayerListener);
        Disposable disposable = this.playerCardChangeSubscription;
        if (disposable != null) {
            disposable.dispose();
            this.playerCardChangeSubscription = null;
        }
        this.isPlayerVisible.removeOnPropertyChangedCallback(this.playerViewChangeCallback);
        this.cardRenderInterface.setOnCardDismissListener(null);
    }

    @VisibleForTesting
    VoicePlaybackEventTranslator(final VoiceService voiceService, CardRenderInterface cardRenderInterface, ObservableBoolean observableBoolean, ObservableBoolean observableBoolean2, ObservableBoolean observableBoolean3, IdentityService identityService, int i, Handler handler) {
        this.playerIsActiveCallBack = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.nowplaying.VoicePlaybackEventTranslator.1
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i2) {
                VoicePlaybackEventTranslator.this.isPlayerActive.set(VoicePlaybackEventTranslator.this.shouldPlayerBeActive.get() && VoicePlaybackEventTranslator.this.isCardSupported.get());
            }
        };
        this.voiceService = voiceService;
        this.cardRenderInterface = cardRenderInterface;
        this.isPlayerActive = observableBoolean2;
        this.isPlayerVisible = observableBoolean3;
        this.isCardSupported = new ObservableBoolean(false);
        this.isDismissingCard = new AtomicBoolean(false);
        this.identityService = identityService;
        this.shouldPlayerBeActive = new ObservableBoolean(false);
        this.shouldPlayerBeActive.addOnPropertyChangedCallback(this.playerIsActiveCallBack);
        this.isCardSupported.addOnPropertyChangedCallback(this.playerIsActiveCallBack);
        this.audioPlayerListener = new NowPlayingListener(observableBoolean, this.shouldPlayerBeActive, this.isDismissingCard, i, handler);
        this.playerViewChangeCallback = new Observable.OnPropertyChangedCallback() { // from class: com.amazon.dee.app.ui.nowplaying.VoicePlaybackEventTranslator.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i2) {
                VoicePlaybackEventTranslator.this.nowPlayingPlayerModelChanged(voiceService.getNowPlayingCardManager().getPlayerCard());
            }
        };
    }
}

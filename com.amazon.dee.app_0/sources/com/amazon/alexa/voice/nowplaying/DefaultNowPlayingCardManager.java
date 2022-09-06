package com.amazon.alexa.voice.nowplaying;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.voice.R;
import com.amazon.alexa.voice.nowplaying.PlayerInfoPayload;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceNowPlayingEventBusModule;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.amazon.alexa.voice.ui.player.PlayerCard;
import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class DefaultNowPlayingCardManager implements AlexaServicesConnection.ConnectionListener, AlexaPlayerInfoCardListener, PlayerCardUpdater, NowPlayingCardManager {
    private static final String TAG = "DefaultNowPlayingCardManager";
    private final AlexaServicesConnection alexaServicesConnection;
    private final BehaviorSubject<PlayerCardModel> audioPlayerInfoObservable;
    private ClearCardRunnable clearCardRunnable;
    private final AtomicReference<String> currentAudioItem;
    private final Handler handler;
    private final PlayerInfoCardCache playerInfoCardCache;
    private final PlayerInfoPayload.Factory playerInfoFactory;
    private final VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule;
    private final int waitForNextAudioItemTimeoutMilliseconds;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class ClearCardRunnable implements Runnable {
        private ClearCardRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DefaultNowPlayingCardManager.this.updateCard(new PlayerCard.Builder().build());
        }
    }

    public DefaultNowPlayingCardManager(Context context, AlexaServicesConnection alexaServicesConnection, @NonNull VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        this(context, alexaServicesConnection, new PlayerInfoPayload.Factory(), new PlayerInfoCardCache(), voiceNowPlayingEventBusModule);
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void clearCardData() {
        updateCard(new PlayerCard.Builder().build());
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public PlayerCardModel getPlayerCard() {
        return this.audioPlayerInfoObservable.getValue();
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void initialize() {
        this.alexaServicesConnection.registerListener(this);
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onAudioItemStateChanged(@NonNull AlexaPlayerInfoState alexaPlayerInfoState, @NonNull String str, long j) {
        if (!alexaPlayerInfoState.equals(AlexaPlayerInfoState.CANCELLED)) {
            this.handler.removeCallbacks(this.clearCardRunnable);
            this.currentAudioItem.set(str);
            PlayerCardModel playerCardModel = this.playerInfoCardCache.get(str);
            if (playerCardModel == null) {
                this.handler.postDelayed(this.clearCardRunnable, this.waitForNextAudioItemTimeoutMilliseconds);
            } else {
                updateCard(playerCardModel);
            }
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        AlexaServices.Cards.registerPlayerInfoCardListener(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        AlexaServices.Cards.deregisterPlayerInfoCardListener(this.alexaServicesConnection, this);
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public Observable<PlayerCardModel> onPlayerCard() {
        return this.audioPlayerInfoObservable;
    }

    @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
    public void onReceivedPlayerInfoCard(@NonNull String str, boolean z) {
        try {
            PlayerInfoPayload create = this.playerInfoFactory.create(new JSONObject(str));
            PlayerCard build = new PlayerCard.Builder().audioItemId(create.getMediaId()).details(create.getAlbum()).description(create.getArtist()).title(create.getTitle()).providerLogoId(create.getProviderLogoId()).providerName(create.getProviderName()).artImageUrl(create.getArtImageUrl()).backgroundImageUrl(create.getBackgroundImageUrl()).playbackSource(create.getPlaybackSource()).build();
            String mediaId = create.getMediaId();
            if (!this.playerInfoCardCache.contains(mediaId)) {
                this.playerInfoCardCache.put(build);
            }
            if (!this.currentAudioItem.get().equals(mediaId)) {
                return;
            }
            updateCard(build);
        } catch (Exception e) {
            Logger.debug("Unable to parse player info data: " + e);
        }
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void release() {
        this.alexaServicesConnection.deregisterListener(this);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardUpdater
    public Observable<PlayerCardModel> subscribeToCardUpdate() {
        return this.audioPlayerInfoObservable;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardUpdater
    public void updateCard(PlayerCardModel playerCardModel) {
        this.handler.removeCallbacks(this.clearCardRunnable);
        this.audioPlayerInfoObservable.onNext(playerCardModel);
        if (playerCardModel.getAudioItemId() != null) {
            this.voiceNowPlayingEventBusModule.broadcastVoxPlaybackInfo(VoiceBridgePayloadUtil.createPlayerInfoPayload(playerCardModel));
        }
    }

    @VisibleForTesting
    DefaultNowPlayingCardManager(@NonNull Context context, @NonNull AlexaServicesConnection alexaServicesConnection, @NonNull PlayerInfoPayload.Factory factory, @NonNull PlayerInfoCardCache playerInfoCardCache, @NonNull VoiceNowPlayingEventBusModule voiceNowPlayingEventBusModule) {
        this.alexaServicesConnection = alexaServicesConnection;
        this.playerInfoFactory = factory;
        this.playerInfoCardCache = playerInfoCardCache;
        this.voiceNowPlayingEventBusModule = voiceNowPlayingEventBusModule;
        this.audioPlayerInfoObservable = BehaviorSubject.create();
        this.currentAudioItem = new AtomicReference<>("");
        this.clearCardRunnable = new ClearCardRunnable();
        this.handler = new Handler(Looper.getMainLooper());
        this.waitForNextAudioItemTimeoutMilliseconds = context.getResources().getInteger(R.integer.time_to_wait_for_next_audio_item);
    }
}

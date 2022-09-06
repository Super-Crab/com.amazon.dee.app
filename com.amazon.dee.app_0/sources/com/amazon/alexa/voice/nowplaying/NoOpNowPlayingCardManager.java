package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public class NoOpNowPlayingCardManager implements NowPlayingCardManager {
    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void clearCardData() {
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public PlayerCardModel getPlayerCard() {
        return null;
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void initialize() {
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public Observable<PlayerCardModel> onPlayerCard() {
        return Observable.empty();
    }

    @Override // com.amazon.alexa.voice.nowplaying.NowPlayingCardManager
    public void release() {
    }
}

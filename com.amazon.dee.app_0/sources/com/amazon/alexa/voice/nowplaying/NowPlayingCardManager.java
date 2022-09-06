package com.amazon.alexa.voice.nowplaying;

import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface NowPlayingCardManager {
    void clearCardData();

    PlayerCardModel getPlayerCard();

    void initialize();

    Observable<PlayerCardModel> onPlayerCard();

    void release();
}

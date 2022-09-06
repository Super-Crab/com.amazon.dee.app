package com.amazon.alexa.voice.ui.player;

import io.reactivex.rxjava3.core.Observable;
/* loaded from: classes11.dex */
public interface PlayerCardUpdater {
    Observable<PlayerCardModel> subscribeToCardUpdate();

    void updateCard(PlayerCardModel playerCardModel);
}

package com.amazon.alexa.voice.ui.superbowl;

import com.amazon.alexa.voice.ui.player.PlayerCardModel;
import com.amazon.alexa.voice.ui.player.PlayerCardUpdater;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes11.dex */
public class AudioPlayerCardUpdater implements PlayerCardUpdater {
    BehaviorSubject<PlayerCardModel> cardUpdaterBehaviorSubject = BehaviorSubject.create();

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardUpdater
    public Observable<PlayerCardModel> subscribeToCardUpdate() {
        return this.cardUpdaterBehaviorSubject;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerCardUpdater
    public void updateCard(PlayerCardModel playerCardModel) {
        this.cardUpdaterBehaviorSubject.onNext(playerCardModel);
    }
}

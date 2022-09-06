package com.amazon.dee.app.ui.nowplaying;

import com.amazon.alexa.voice.ui.player.PlayerCardModel;
/* loaded from: classes12.dex */
public interface CardRenderInterface {
    void displayNativeNowPlayingCard(PlayerCardModel playerCardModel);

    void removeNativeNowplayingCard();

    void setOnCardDismissListener(CardDismissListener cardDismissListener);
}

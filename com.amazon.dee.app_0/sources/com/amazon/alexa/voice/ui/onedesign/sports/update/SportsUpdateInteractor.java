package com.amazon.alexa.voice.ui.onedesign.sports.update;

import com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract;
/* loaded from: classes11.dex */
public final class SportsUpdateInteractor implements SportsUpdateContract.Interactor {
    private final SportsUpdateCard card;
    private final SportsUpdateContract.Mediator mediator;

    public SportsUpdateInteractor(SportsUpdateCard sportsUpdateCard, SportsUpdateContract.Mediator mediator) {
        this.card = sportsUpdateCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Interactor
    public SportsUpdateCard getCard() {
        return this.card;
    }
}

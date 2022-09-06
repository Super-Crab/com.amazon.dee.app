package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract;
/* loaded from: classes11.dex */
public final class SportsScoresInteractor implements SportsScoresContract.Interactor {
    private final SportsScoresCard card;
    private final SportsScoresContract.Mediator mediator;

    public SportsScoresInteractor(SportsScoresCard sportsScoresCard, SportsScoresContract.Mediator mediator) {
        this.card = sportsScoresCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Interactor
    public SportsScoresCard getCard() {
        return this.card;
    }
}

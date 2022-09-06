package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class SportsScoresMediator implements SportsScoresContract.Mediator {
    private final ViewController controller;

    public SportsScoresMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}

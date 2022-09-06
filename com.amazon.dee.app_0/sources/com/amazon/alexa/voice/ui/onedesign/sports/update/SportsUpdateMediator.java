package com.amazon.alexa.voice.ui.onedesign.sports.update;

import com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class SportsUpdateMediator implements SportsUpdateContract.Mediator {
    private final ViewController controller;

    public SportsUpdateMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.sports.update.SportsUpdateContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}

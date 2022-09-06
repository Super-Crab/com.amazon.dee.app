package com.amazon.alexa.voice.ui.player;

import com.amazon.alexa.voice.ui.player.PlayerContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class PlayerMediator implements PlayerContract.Mediator {
    private final ViewController controller;

    public PlayerMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.player.PlayerContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}

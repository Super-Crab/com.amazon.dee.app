package com.amazon.alexa.voice.ui.onedesign.travel;

import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class TravelTimeDistanceMediator implements TravelTimeDistanceContract.Mediator {
    private final ViewController controller;

    public TravelTimeDistanceMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}

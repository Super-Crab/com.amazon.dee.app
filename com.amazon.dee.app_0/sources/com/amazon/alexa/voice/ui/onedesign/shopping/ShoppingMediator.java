package com.amazon.alexa.voice.ui.onedesign.shopping;

import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class ShoppingMediator implements ShoppingContract.Mediator {
    private final ViewController controller;

    public ShoppingMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }
}

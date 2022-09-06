package com.amazon.alexa.voice.ui.onedesign.hints;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.hints.HintsContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class HintsMediator implements HintsContract.Mediator {
    private final ViewController controller;

    public HintsMediator(@NonNull ViewController viewController) {
        Preconditions.nonNull(viewController, "controller must be non-null.");
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.hints.HintsContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller);
    }
}

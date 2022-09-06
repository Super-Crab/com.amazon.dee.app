package com.amazon.alexa.voice.ui.onedesign.tta;

import com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract;
import com.amazon.alexa.voice.ui.tta.TtaNavigator;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class TtaScreenMediator implements TtaScreenContract.Mediator {
    private final ViewController controller;

    public TtaScreenMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.tta.TtaScreenContract.Mediator
    public void openVoiceScrim() {
        ((TtaNavigator) this.controller.getComponent().get(TtaNavigator.class)).openVoiceScrim(this.controller.getContext());
    }
}

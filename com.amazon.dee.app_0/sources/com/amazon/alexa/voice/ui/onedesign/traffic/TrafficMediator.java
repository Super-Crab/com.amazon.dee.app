package com.amazon.alexa.voice.ui.onedesign.traffic;

import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class TrafficMediator implements TrafficContract.Mediator {
    private final ViewController controller;

    public TrafficMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.Mediator
    public void openLocation(String str) {
        ViewController viewController = this.controller;
        IntentUtils.openLinkUrl(viewController, "geo:0,0?q=" + str);
    }
}

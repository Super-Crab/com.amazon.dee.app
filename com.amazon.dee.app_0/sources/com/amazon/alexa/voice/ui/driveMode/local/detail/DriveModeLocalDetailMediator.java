package com.amazon.alexa.voice.ui.driveMode.local.detail;

import com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeIntentUtils;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class DriveModeLocalDetailMediator implements DriveModeLocalDetailContract.Mediator {
    private static final String DIAL_PHONE_NUMBER_PREFIX = "tel:";
    private final ViewController controller;

    public DriveModeLocalDetailMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailContract.Mediator
    public void dialPhoneNumber(String str) {
        ViewController viewController = this.controller;
        DriveModeIntentUtils.makePhoneCall(viewController, DIAL_PHONE_NUMBER_PREFIX + str);
    }
}

package com.amazon.alexa.voice.ui.onedesign.local.detail;

import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ViewController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public final class LocalDetailMediator implements LocalDetailContract.Mediator {
    private final ViewController controller;

    public LocalDetailMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Mediator
    public void dialPhoneNumber(String str) {
        ViewController viewController = this.controller;
        IntentUtils.openLinkUrl(viewController, "tel:" + str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Mediator
    public void mapAddress(String str) {
        ViewController viewController = this.controller;
        IntentUtils.openLinkUrl(viewController, "geo:0,0?q=" + str);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Mediator
    public void mapGeoLocation(String str) throws IllegalArgumentException {
        String[] split = str.split(":");
        if (split.length >= 2 && split[1].split(",").length >= 2) {
            ViewController viewController = this.controller;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("google.navigation:q=");
            outline107.append(split[1]);
            IntentUtils.openLinkUrl(viewController, outline107.toString());
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid geo location", str));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailContract.Mediator
    public void openLinkUrl(String str) {
        IntentUtils.openLinkUrl(this.controller, str);
    }
}

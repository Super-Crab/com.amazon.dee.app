package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.util.IntentUtils;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public abstract class HandsfreePrimerMediator implements HandsfreePrimerContract.Mediator {
    private final ViewController controller;

    public HandsfreePrimerMediator(@NonNull ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    public ViewController getController() {
        return this.controller;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Mediator
    public void openLearnMore(CharSequence charSequence) {
        IntentUtils.openLinkUrl(this.controller, charSequence.toString());
    }
}

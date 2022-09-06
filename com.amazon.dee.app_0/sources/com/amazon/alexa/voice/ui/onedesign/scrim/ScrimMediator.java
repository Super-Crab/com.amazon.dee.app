package com.amazon.alexa.voice.ui.onedesign.scrim;

import android.util.Log;
import com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract;
import com.amazon.alexa.voice.ui.tta.TypingPrimerDisplayer;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class ScrimMediator implements ScrimContract.Mediator {
    private static final String TAG = "ScrimMediator";
    private final ViewController controller;

    public ScrimMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.scrim.ScrimContract.Mediator
    public void openTTA() {
        if (this.controller.getComponent().isRegistered(TypingPrimerDisplayer.class)) {
            ((TypingPrimerDisplayer) this.controller.getComponent().get(TypingPrimerDisplayer.class)).showTypingView();
        } else {
            Log.e(TAG, "Could not open TTA -- TypingPrimerDisplayer not registered");
        }
    }
}

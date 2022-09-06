package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.permissions.HandsFreeSettings;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class ReturningPrimerMediator extends HandsfreePrimerMediator {
    public ReturningPrimerMediator(@NonNull ViewController viewController) {
        super(viewController);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerMediator, com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Mediator
    public void close() {
        ViewController controller = getController();
        controller.getRouter().popController(controller);
        ((OnReturningPrimerDismissedListener) controller.getComponent().get(OnReturningPrimerDismissedListener.class)).onReturningPrimerDismissed();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.Mediator
    public void requestPermissions() {
        ((HandsFreeSettings) getController().getComponent().get(HandsFreeSettings.class)).enableHandsfreePermissions(true);
        close();
    }
}

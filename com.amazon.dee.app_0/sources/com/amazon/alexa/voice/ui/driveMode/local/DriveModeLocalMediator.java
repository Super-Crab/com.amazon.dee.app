package com.amazon.alexa.voice.ui.driveMode.local;

import com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract;
import com.amazon.alexa.voice.ui.driveMode.local.detail.DriveModeLocalDetailController;
import com.amazon.alexa.voice.ui.driveMode.local.search.DriveModeLocalSearchController;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class DriveModeLocalMediator implements DriveModeLocalContract.Mediator {
    private final LocalCard card;
    private final ViewController controller;

    public DriveModeLocalMediator(ViewController viewController, LocalCard localCard) {
        this.controller = viewController;
        this.card = localCard;
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.driveMode.local.DriveModeLocalContract.Mediator
    public void showChildIfNeeded() {
        ViewController create;
        Router childRouter = this.controller.getChildRouter("local");
        if (childRouter.getRootController() != null) {
            return;
        }
        if (this.card.getBusinessList().size() == 1) {
            create = DriveModeLocalDetailController.create(this.card.getBusinessList().get(0), false);
        } else {
            create = DriveModeLocalSearchController.create(this.card);
        }
        childRouter.pushController(new ControllerTransaction(create));
    }
}

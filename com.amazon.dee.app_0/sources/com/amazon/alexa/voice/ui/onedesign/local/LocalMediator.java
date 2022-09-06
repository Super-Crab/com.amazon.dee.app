package com.amazon.alexa.voice.ui.onedesign.local;

import com.amazon.alexa.voice.ui.onedesign.local.LocalContract;
import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailController;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchController;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class LocalMediator implements LocalContract.Mediator {
    private final LocalCard card;
    private final ViewController controller;

    public LocalMediator(ViewController viewController, LocalCard localCard) {
        this.controller = viewController;
        this.card = localCard;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.LocalContract.Mediator
    public void showChildIfNeeded() {
        ViewController create;
        Router childRouter = this.controller.getChildRouter("local");
        if (childRouter.getRootController() != null) {
            return;
        }
        if (this.card.getBusinessList().size() == 1) {
            create = LocalDetailController.create(this.card.getBusinessList().get(0), false);
        } else {
            create = LocalSearchController.create(this.card);
        }
        childRouter.pushController(new ControllerTransaction(create));
    }
}

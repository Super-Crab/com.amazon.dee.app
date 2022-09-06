package com.amazon.alexa.voice.ui.onedesign.local.search;

import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.detail.LocalDetailController;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideEndOutTransition;
import com.amazon.alexa.voice.ui.onedesign.transitions.SlideStartInTransition;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class LocalSearchMediator implements LocalSearchContract.Mediator {
    private final ViewController controller;

    public LocalSearchMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.Mediator
    public void openBusiness(LocalCardModel.BusinessModel businessModel) {
        this.controller.getRouter().pushController(new ControllerTransaction(LocalDetailController.create(businessModel, true), new SlideEndOutTransition(), new SlideStartInTransition()));
    }
}

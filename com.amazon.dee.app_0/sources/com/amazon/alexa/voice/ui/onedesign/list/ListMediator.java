package com.amazon.alexa.voice.ui.onedesign.list;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.onedesign.list.ListContract;
import com.amazon.alexa.voice.ui.routing.Navigator;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class ListMediator implements ListContract.Mediator {
    private final ViewController controller;

    public ListMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.list.ListContract.Mediator
    public void openSpecialList(@NonNull ListType listType) {
        ((Navigator) this.controller.getComponent().get(Navigator.class)).navigateToSpecialList(listType.toString());
    }
}

package com.amazon.alexa.voice.ui.onedesign.calendar;

import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.transitions.InstantTransition;
/* loaded from: classes11.dex */
public final class CalendarMediator implements CalendarContract.Mediator {
    private final ViewController controller;

    public CalendarMediator(ViewController viewController) {
        this.controller = viewController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Mediator
    public void close() {
        this.controller.getRouter().popController(this.controller);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Mediator
    public void dismiss() {
        this.controller.getRouter().popController(this.controller, new InstantTransition());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Mediator
    public void openEvent(CalendarCard.Event event) {
    }
}

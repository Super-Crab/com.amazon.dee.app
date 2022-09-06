package com.amazon.alexa.voice.ui.onedesign.calendar;

import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract;
/* loaded from: classes11.dex */
public final class CalendarInteractor implements CalendarContract.Interactor {
    private final CalendarCard card;
    private final CalendarContract.Mediator mediator;

    public CalendarInteractor(CalendarCard calendarCard, CalendarContract.Mediator mediator) {
        this.card = calendarCard;
        this.mediator = mediator;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Interactor
    public void close() {
        this.mediator.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Interactor
    public void dismiss() {
        this.mediator.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Interactor
    public CalendarCard getCard() {
        return this.card;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Interactor
    public void showEvent(CalendarCard.Event event) {
        this.mediator.openEvent(event);
    }
}

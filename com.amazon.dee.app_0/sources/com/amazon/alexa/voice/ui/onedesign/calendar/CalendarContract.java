package com.amazon.alexa.voice.ui.onedesign.calendar;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard;
/* loaded from: classes11.dex */
public interface CalendarContract {

    /* loaded from: classes11.dex */
    public interface Interactor {
        void close();

        void dismiss();

        CalendarCard getCard();

        void showEvent(CalendarCard.Event event);
    }

    /* loaded from: classes11.dex */
    public interface Mediator {
        void close();

        void dismiss();

        void openEvent(CalendarCard.Event event);
    }

    /* loaded from: classes11.dex */
    public interface Presenter {
        void closeClicked();

        void dismiss();

        void end();

        void eventClicked(CalendarEventModel calendarEventModel);

        void interacted();

        void start();

        void viewDestroyed();
    }

    /* loaded from: classes11.dex */
    public interface View {
        void add(Object obj);

        void floodBackgroundToStatusBar();

        void setTitle(@Nullable CharSequence charSequence);
    }
}

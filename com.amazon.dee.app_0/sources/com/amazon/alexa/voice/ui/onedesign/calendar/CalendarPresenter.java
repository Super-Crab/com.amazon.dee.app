package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarEvent;
import com.amazon.alexa.voice.ui.onedesign.util.DateUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class CalendarPresenter implements CalendarContract.Presenter {
    private final CalendarContract.Interactor interactor;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private final CalendarContract.View view;
    private final DateDeduplicator dedupe = new DateDeduplicator();
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private Disposable disposable = null;

    public CalendarPresenter(CalendarContract.View view, CalendarContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, Locale locale) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
        this.locale = locale;
    }

    private String getCardName() {
        return this.interactor.getClass().getSimpleName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CalendarEventModel makeCalendarEvent(CalendarCardModel.EventModel eventModel) {
        CalendarEvent.Builder tag = new CalendarEvent.Builder().name(eventModel.getName()).location(eventModel.getLocation()).id(eventModel.getId()).provider(eventModel.getProvider()).tag(eventModel);
        Date date = new Date(eventModel.getEndTime());
        Date date2 = new Date(eventModel.getStartTime());
        if (!eventModel.getAllDay()) {
            tag.times(makeTimesFromDates(date2, date));
        }
        Date lastSeenDate = this.dedupe.getLastSeenDate();
        Date nextDate = this.dedupe.getNextDate(date2);
        CharSequence charSequence = null;
        CharSequence month = lastSeenDate != null ? DateUtils.getMonth(lastSeenDate, this.locale) : null;
        CharSequence makeDayFromDate = makeDayFromDate(date2);
        CharSequence dayOfMonth = DateUtils.getDayOfMonth(date2);
        CharSequence month2 = DateUtils.getMonth(date2, this.locale);
        if (!TextUtils.isEmpty(month) && !month.equals(month2)) {
            charSequence = month2;
        } else if (nextDate == null) {
            makeDayFromDate = null;
            dayOfMonth = null;
        }
        return tag.dayOfWeek(makeDayFromDate).dayOfMonth(dayOfMonth).month(charSequence).build();
    }

    private CharSequence makeDayFromDate(Date date) {
        return new SimpleDateFormat("EEEE", this.locale).format(date).toUpperCase(this.locale).substring(0, 3);
    }

    private CharSequence makeHoursMinutesFromDate(Date date) {
        return new SimpleDateFormat("h:mm a", this.locale).format(date);
    }

    private CharSequence makeMonthYearFromDate(Date date) {
        return new SimpleDateFormat("MMM d", this.locale).format(date).toUpperCase(this.locale);
    }

    private CharSequence makeTimesFromDates(Date date, Date date2) {
        if (DateUtils.areSameCalendarDay(date, date2)) {
            return TextUtils.concat(makeHoursMinutesFromDate(date), " - ", makeHoursMinutesFromDate(date2));
        }
        return makeHoursMinutesFromDate(date);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void closeClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void dismiss() {
        this.interactor.dismiss();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void eventClicked(CalendarEventModel calendarEventModel) {
        this.interactor.showEvent((CalendarCard.Event) calendarEventModel.getTag());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void start() {
        CalendarCard card = this.interactor.getCard();
        this.dedupe.reset();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(DateUtils.getMonth(new Date(card.getEventList().get(0).getStartTime()), this.locale));
        Observable flatMap = Observable.fromIterable(card.getEventList()).map(new Function() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.-$$Lambda$CalendarPresenter$XVFHYY1no9CutcMGGNCiTR9dGw4
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                CalendarEventModel makeCalendarEvent;
                makeCalendarEvent = CalendarPresenter.this.makeCalendarEvent((CalendarCardModel.EventModel) obj);
                return makeCalendarEvent;
            }
        }).cast(Object.class).flatMap(new Function<Object, ObservableSource<?>>() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.CalendarPresenter.1
            private boolean found;

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public ObservableSource<?> mo10358apply(@NonNull Object obj) throws Exception {
                if (obj instanceof CalendarEventModel) {
                    CalendarEventModel calendarEventModel = (CalendarEventModel) obj;
                    if (calendarEventModel.getDayOfMonth() != null && calendarEventModel.getDayOfWeek() != null) {
                        if (this.found) {
                            return Observable.just(new CalendarSeparator(), obj);
                        }
                        this.found = true;
                    }
                }
                return Observable.just(obj);
            }
        });
        final CalendarContract.View view = this.view;
        view.getClass();
        this.disposable = flatMap.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.-$$Lambda$kEjYPJV1W_oczqKzfpy6pverKfI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CalendarContract.View.this.add(obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}

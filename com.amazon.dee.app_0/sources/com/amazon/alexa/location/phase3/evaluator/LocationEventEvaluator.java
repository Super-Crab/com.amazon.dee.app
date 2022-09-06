package com.amazon.alexa.location.phase3.evaluator;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.location.TriggeringGeofenceInfo;
import com.amazon.alexa.location.phase3.evaluator.TriggerEventEvaluator;
import com.amazon.alexa.location.utils.Clock;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class LocationEventEvaluator {
    private static final int LOCATION_EVENT_EXIPIRY_TIME_IN_MILLI_SECONDS = 360000;
    private final Clock clock;
    @NonNull
    private final Context context;
    private final LocationEventQueue eventQueue;
    private final LazyComponent<Mobilytics> mobilytics;
    private final TriggerEventEvaluator triggerEvaluator;
    private static final String TAG = "LocationEventEvaluator";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);
    private final PublishSubject<TriggeringGeofenceInfo> triggerSubject = PublishSubject.create();
    private final PublishSubject<Boolean> needMoreDataSubject = PublishSubject.create();

    public LocationEventEvaluator(LocationEventQueue locationEventQueue, TriggerEventEvaluator triggerEventEvaluator, LazyComponent<Mobilytics> lazyComponent, Clock clock, @NonNull Context context) {
        this.eventQueue = locationEventQueue;
        this.triggerEvaluator = triggerEventEvaluator;
        this.mobilytics = lazyComponent;
        this.clock = clock;
        this.context = context;
    }

    public void clearAllEvents() {
        this.eventQueue.clear();
    }

    public Completable evaluateEvent(final LocationEvent locationEvent) {
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        String format = String.format(MobilyticsUtil.MetricsID.EVENT_EVALUATED, locationEvent.type);
        String str = COMPONENT_NAME;
        mo10268get.recordOccurrence(format, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        WriteToFile.dumpAsJson(this.context, "evaluateEvent", locationEvent);
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.location.phase3.evaluator.-$$Lambda$LocationEventEvaluator$Ztl7ApW6fHgKDGZ3r150f0fld6I
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                LocationEventEvaluator.this.lambda$evaluateEvent$0$LocationEventEvaluator(locationEvent, completableEmitter);
            }
        });
    }

    public Subject<Boolean> getNeedMoreDataSubject() {
        return this.needMoreDataSubject;
    }

    public Subject<TriggeringGeofenceInfo> getTriggerSubject() {
        return this.triggerSubject;
    }

    public /* synthetic */ void lambda$evaluateEvent$0$LocationEventEvaluator(LocationEvent locationEvent, CompletableEmitter completableEmitter) throws Throwable {
        this.eventQueue.removeExpiredEvents(this.clock.millis() - 360000);
        this.eventQueue.add(locationEvent);
        Iterator<TriggerEventEvaluator.EvaluatedTrigger> it2 = this.triggerEvaluator.evaluate(this.eventQueue).values().iterator();
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (!it2.hasNext()) {
                break;
            }
            TriggerEventEvaluator.EvaluatedTrigger next = it2.next();
            if (this.triggerEvaluator.shouldFire(next)) {
                WriteToFile.dumpAsJson(this.context, "alsTrigger", next);
                this.triggerSubject.onNext(next.toTriggeringInfo());
            } else {
                if (!z && !this.triggerEvaluator.needMoreData(next)) {
                    z2 = false;
                }
                z = z2;
            }
        }
        if (z) {
            WriteToFile.dumpAsJson(this.context, "needMoreData", true);
            this.needMoreDataSubject.onNext(true);
        }
        completableEmitter.onComplete();
    }
}

package com.amazon.alexa.eventing;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import com.amazon.alexa.eventing.Event;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
@Deprecated
/* loaded from: classes7.dex */
public class Event<T> implements EventEmitter<T>, EventSource<T> {
    final LinkedList<EventHandler<T>> handlers = new LinkedList<>();
    final LinkedList<Observer<T>> observers = new LinkedList<>();

    /* loaded from: classes7.dex */
    public static class Observer<T> {
        public void onSubscribed(@NonNull EventHandler<T> eventHandler) {
        }

        public void onUnsubscribed(@NonNull EventHandler<T> eventHandler) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface ObserverAction<T> {
        void apply(Observer<T> observer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doFire */
    public void lambda$fire$3$Event(@NonNull EventArgs<T> eventArgs) {
        ArrayList arrayList;
        synchronized (this.handlers) {
            arrayList = new ArrayList(this.handlers);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ((EventHandler) it2.next()).onEvent(eventArgs);
            if (eventArgs.isCanceled()) {
                return;
            }
        }
    }

    @Override // com.amazon.alexa.eventing.EventSource
    public void fire(@NonNull final EventArgs<T> eventArgs) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            lambda$fire$3$Event(eventArgs);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.alexa.eventing.-$$Lambda$Event$h6yY3WLWhlqPiQ6AH5nNxcM4ZEs
                @Override // java.lang.Runnable
                public final void run() {
                    Event.this.lambda$fire$3$Event(eventArgs);
                }
            });
        }
    }

    public boolean isUnsubscribed() {
        boolean isEmpty;
        synchronized (this.handlers) {
            isEmpty = this.handlers.isEmpty();
        }
        return isEmpty;
    }

    public /* synthetic */ void lambda$subscribe$2$Event(final EventHandler eventHandler) {
        synchronized (this.handlers) {
            this.handlers.remove(eventHandler);
        }
        notifyObservers(new ObserverAction() { // from class: com.amazon.alexa.eventing.-$$Lambda$Event$w1FQLy5vkLHvCjYAbXG8qtk5FY8
            @Override // com.amazon.alexa.eventing.Event.ObserverAction
            public final void apply(Event.Observer observer) {
                observer.onUnsubscribed(EventHandler.this);
            }
        });
    }

    void notifyObservers(ObserverAction<T> observerAction) {
        ArrayList arrayList;
        synchronized (this.observers) {
            arrayList = new ArrayList(this.observers);
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            observerAction.apply((Observer) it2.next());
        }
    }

    public void registerObserver(Observer<T> observer) {
        synchronized (this.observers) {
            this.observers.add(observer);
        }
    }

    @Override // com.amazon.alexa.eventing.EventEmitter
    public EventSubscription subscribe(@NonNull final EventHandler<T> eventHandler) {
        synchronized (this.handlers) {
            this.handlers.add(eventHandler);
        }
        notifyObservers(new ObserverAction() { // from class: com.amazon.alexa.eventing.-$$Lambda$Event$GAI2_ghgue2nM17yBzAL4VoyPMQ
            @Override // com.amazon.alexa.eventing.Event.ObserverAction
            public final void apply(Event.Observer observer) {
                observer.onSubscribed(EventHandler.this);
            }
        });
        return new BooleanSubscription(new Runnable() { // from class: com.amazon.alexa.eventing.-$$Lambda$Event$Xb0ugBN2ylA4WD-t3Jc7MjLH9DY
            @Override // java.lang.Runnable
            public final void run() {
                Event.this.lambda$subscribe$2$Event(eventHandler);
            }
        });
    }

    public void unregisterObserver(Observer<T> observer) {
        synchronized (this.observers) {
            this.observers.remove(observer);
        }
    }
}

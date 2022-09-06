package com.amazon.alexa.eventing;

import java.util.HashSet;
import java.util.Iterator;
@Deprecated
/* loaded from: classes7.dex */
public class EventSubscriptions implements EventSubscription {
    HashSet<EventSubscription> subscriptions;
    volatile boolean unsubscribed;

    public void add(EventSubscription eventSubscription) {
        if (!eventSubscription.isUnsubscribed()) {
            boolean z = true;
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed) {
                        if (this.subscriptions == null) {
                            this.subscriptions = new HashSet<>(4);
                        }
                        this.subscriptions.add(eventSubscription);
                        z = false;
                    }
                }
            }
            if (!z) {
                return;
            }
            eventSubscription.unsubscribe();
        }
    }

    @Override // com.amazon.alexa.eventing.EventSubscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void remove(EventSubscription eventSubscription) {
        if (!eventSubscription.isUnsubscribed()) {
            boolean z = false;
            if (!this.unsubscribed) {
                synchronized (this) {
                    if (!this.unsubscribed && this.subscriptions != null) {
                        z = this.subscriptions.remove(eventSubscription);
                    }
                    return;
                }
            }
            if (!z) {
                return;
            }
            eventSubscription.unsubscribe();
        }
    }

    @Override // com.amazon.alexa.eventing.EventSubscription
    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                if (this.subscriptions != null) {
                    Iterator<EventSubscription> it2 = this.subscriptions.iterator();
                    while (it2.hasNext()) {
                        it2.next().unsubscribe();
                    }
                    this.subscriptions = null;
                }
                this.unsubscribed = true;
            }
        }
    }
}

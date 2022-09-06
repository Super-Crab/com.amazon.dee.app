package com.amazon.alexa.eventing;

import java.util.concurrent.atomic.AtomicReference;
@Deprecated
/* loaded from: classes7.dex */
public class BooleanSubscription implements EventSubscription {
    final AtomicReference<Runnable> unsubscribeRef;

    public BooleanSubscription(Runnable runnable) {
        this.unsubscribeRef = new AtomicReference<>(runnable);
    }

    @Override // com.amazon.alexa.eventing.EventSubscription
    public boolean isUnsubscribed() {
        return this.unsubscribeRef.get() == null;
    }

    @Override // com.amazon.alexa.eventing.EventSubscription
    public void unsubscribe() {
        Runnable andSet;
        if (this.unsubscribeRef.get() == null || (andSet = this.unsubscribeRef.getAndSet(null)) == null) {
            return;
        }
        andSet.run();
    }
}

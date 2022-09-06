package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class ArrayCompositeSubscription extends AtomicReferenceArray<Subscription> implements Disposable {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int capacity) {
        super(capacity);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        Subscription andSet;
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                Subscription subscription = get(i);
                SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
                if (subscription != subscriptionHelper && (andSet = getAndSet(i, subscriptionHelper)) != SubscriptionHelper.CANCELLED && andSet != null) {
                    andSet.cancel();
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }

    public Subscription replaceResource(int index, Subscription resource) {
        Subscription subscription;
        do {
            subscription = get(index);
            if (subscription == SubscriptionHelper.CANCELLED) {
                if (resource == null) {
                    return null;
                }
                resource.cancel();
                return null;
            }
        } while (!compareAndSet(index, subscription, resource));
        return subscription;
    }

    public boolean setResource(int index, Subscription resource) {
        Subscription subscription;
        do {
            subscription = get(index);
            if (subscription == SubscriptionHelper.CANCELLED) {
                if (resource == null) {
                    return false;
                }
                resource.cancel();
                return false;
            }
        } while (!compareAndSet(index, subscription, resource));
        if (subscription != null) {
            subscription.cancel();
            return true;
        }
        return true;
    }
}

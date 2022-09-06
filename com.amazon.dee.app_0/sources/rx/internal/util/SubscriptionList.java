package rx.internal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import rx.Subscription;
import rx.exceptions.Exceptions;
/* loaded from: classes5.dex */
public final class SubscriptionList implements Subscription {
    private List<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public SubscriptionList() {
    }

    private static void unsubscribeFromAll(Collection<Subscription> collection) {
        if (collection == null) {
            return;
        }
        ArrayList arrayList = null;
        for (Subscription subscription : collection) {
            try {
                subscription.unsubscribe();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        Exceptions.throwIfAny(arrayList);
    }

    public void add(Subscription subscription) {
        if (subscription.isUnsubscribed()) {
            return;
        }
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed) {
                    List list = this.subscriptions;
                    if (list == null) {
                        list = new LinkedList();
                        this.subscriptions = list;
                    }
                    list.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    public void clear() {
        List<Subscription> list;
        if (!this.unsubscribed) {
            synchronized (this) {
                list = this.subscriptions;
                this.subscriptions = null;
            }
            unsubscribeFromAll(list);
        }
    }

    public boolean hasSubscriptions() {
        boolean z = false;
        if (!this.unsubscribed) {
            synchronized (this) {
                if (!this.unsubscribed && this.subscriptions != null && !this.subscriptions.isEmpty()) {
                    z = true;
                }
            }
            return z;
        }
        return false;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.unsubscribed;
    }

    public void remove(Subscription subscription) {
        if (!this.unsubscribed) {
            synchronized (this) {
                List<Subscription> list = this.subscriptions;
                if (!this.unsubscribed && list != null) {
                    boolean remove = list.remove(subscription);
                    if (!remove) {
                        return;
                    }
                    subscription.unsubscribe();
                }
            }
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (!this.unsubscribed) {
            synchronized (this) {
                if (this.unsubscribed) {
                    return;
                }
                this.unsubscribed = true;
                List<Subscription> list = this.subscriptions;
                this.subscriptions = null;
                unsubscribeFromAll(list);
            }
        }
    }

    public SubscriptionList(Subscription... subscriptionArr) {
        this.subscriptions = new LinkedList(Arrays.asList(subscriptionArr));
    }

    public SubscriptionList(Subscription subscription) {
        this.subscriptions = new LinkedList();
        this.subscriptions.add(subscription);
    }
}

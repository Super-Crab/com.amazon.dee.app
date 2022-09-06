package io.reactivex.rxjava3.internal.subscriptions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public enum SubscriptionHelper implements Subscription {
    CANCELLED;

    public static boolean cancel(AtomicReference<Subscription> field) {
        Subscription andSet;
        Subscription subscription = field.get();
        SubscriptionHelper subscriptionHelper = CANCELLED;
        if (subscription == subscriptionHelper || (andSet = field.getAndSet(subscriptionHelper)) == CANCELLED) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.cancel();
        return true;
    }

    public static void deferredRequest(AtomicReference<Subscription> field, AtomicLong requested, long n) {
        Subscription subscription = field.get();
        if (subscription != null) {
            subscription.request(n);
        } else if (!validate(n)) {
        } else {
            BackpressureHelper.add(requested, n);
            Subscription subscription2 = field.get();
            if (subscription2 == null) {
                return;
            }
            long andSet = requested.getAndSet(0L);
            if (andSet == 0) {
                return;
            }
            subscription2.request(andSet);
        }
    }

    public static boolean deferredSetOnce(AtomicReference<Subscription> field, AtomicLong requested, Subscription s) {
        if (setOnce(field, s)) {
            long andSet = requested.getAndSet(0L);
            if (andSet == 0) {
                return true;
            }
            s.request(andSet);
            return true;
        }
        return false;
    }

    public static boolean replace(AtomicReference<Subscription> field, Subscription s) {
        Subscription subscription;
        do {
            subscription = field.get();
            if (subscription == CANCELLED) {
                if (s == null) {
                    return false;
                }
                s.cancel();
                return false;
            }
        } while (!field.compareAndSet(subscription, s));
        return true;
    }

    public static void reportMoreProduced(long n) {
        RxJavaPlugins.onError(new ProtocolViolationException(GeneratedOutlineSupport1.outline56("More produced than requested: ", n)));
    }

    public static void reportSubscriptionSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean set(AtomicReference<Subscription> field, Subscription s) {
        Subscription subscription;
        do {
            subscription = field.get();
            if (subscription == CANCELLED) {
                if (s == null) {
                    return false;
                }
                s.cancel();
                return false;
            }
        } while (!field.compareAndSet(subscription, s));
        if (subscription != null) {
            subscription.cancel();
            return true;
        }
        return true;
    }

    public static boolean setOnce(AtomicReference<Subscription> field, Subscription s) {
        Objects.requireNonNull(s, "s is null");
        if (!field.compareAndSet(null, s)) {
            s.cancel();
            if (field.get() == CANCELLED) {
                return false;
            }
            reportSubscriptionSet();
            return false;
        }
        return true;
    }

    public static boolean validate(Subscription current, Subscription next) {
        if (next == null) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        } else if (current == null) {
            return true;
        } else {
            next.cancel();
            reportSubscriptionSet();
            return false;
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
    }

    public static boolean validate(long n) {
        if (n <= 0) {
            RxJavaPlugins.onError(new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n > 0 required but it was ", n)));
            return false;
        }
        return true;
    }

    public static boolean setOnce(AtomicReference<Subscription> field, Subscription s, long request) {
        if (setOnce(field, s)) {
            s.request(request);
            return true;
        }
        return false;
    }
}

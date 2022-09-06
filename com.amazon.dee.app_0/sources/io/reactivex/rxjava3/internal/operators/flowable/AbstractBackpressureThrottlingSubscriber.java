package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
abstract class AbstractBackpressureThrottlingSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -5050301752721603566L;
    volatile boolean cancelled;
    volatile boolean done;
    final Subscriber<? super R> downstream;
    Throwable error;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<R> current = new AtomicReference<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractBackpressureThrottlingSubscriber(Subscriber<? super R> downstream) {
        this.downstream = downstream;
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() != 0) {
                return;
            }
            this.current.lazySet(null);
        }
    }

    boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, AtomicReference<R> q) {
        if (this.cancelled) {
            q.lazySet(null);
            return true;
        } else if (!d) {
            return false;
        } else {
            Throwable th = this.error;
            if (th != null) {
                q.lazySet(null);
                a.onError(th);
                return true;
            } else if (!empty) {
                return false;
            } else {
                a.onComplete();
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drain() {
        boolean z;
        if (getAndIncrement() != 0) {
            return;
        }
        Subscriber<? super R> subscriber = this.downstream;
        AtomicLong atomicLong = this.requested;
        AtomicReference<R> atomicReference = this.current;
        int i = 1;
        do {
            long j = 0;
            while (true) {
                z = false;
                if (j == atomicLong.get()) {
                    break;
                }
                boolean z2 = this.done;
                Object obj = (R) atomicReference.getAndSet(null);
                boolean z3 = obj == null;
                if (checkTerminated(z2, z3, subscriber, atomicReference)) {
                    return;
                }
                if (z3) {
                    break;
                }
                subscriber.onNext(obj);
                j++;
            }
            if (j == atomicLong.get()) {
                boolean z4 = this.done;
                if (atomicReference.get() == null) {
                    z = true;
                }
                if (checkTerminated(z4, z, subscriber, atomicReference)) {
                    return;
                }
            }
            if (j != 0) {
                BackpressureHelper.produced(atomicLong, j);
            }
            i = addAndGet(-i);
        } while (i != 0);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable t) {
        this.error = t;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public abstract void onNext(T t);

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.upstream, s)) {
            this.upstream = s;
            this.downstream.onSubscribe(this);
            s.request(Long.MAX_VALUE);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.add(this.requested, n);
            drain();
        }
    }
}

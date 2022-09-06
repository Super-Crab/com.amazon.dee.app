package io.reactivex.rxjava3.subscribers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.observers.BaseTestConsumer;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public class TestSubscriber<T> extends BaseTestConsumer<T, TestSubscriber<T>> implements FlowableSubscriber<T>, Subscription {
    private volatile boolean cancelled;
    private final Subscriber<? super T> downstream;
    private final AtomicLong missedRequested;
    private final AtomicReference<Subscription> upstream;

    /* loaded from: classes3.dex */
    enum EmptySubscriber implements FlowableSubscriber<Object> {
        INSTANCE;

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable t) {
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(Object t) {
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription s) {
        }
    }

    public TestSubscriber() {
        this(EmptySubscriber.INSTANCE, Long.MAX_VALUE);
    }

    @NonNull
    public static <T> TestSubscriber<T> create() {
        return new TestSubscriber<>();
    }

    @Override // org.reactivestreams.Subscription
    public final void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            SubscriptionHelper.cancel(this.upstream);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.observers.BaseTestConsumer, io.reactivex.rxjava3.disposables.Disposable
    public final void dispose() {
        cancel();
    }

    public final boolean hasSubscription() {
        return this.upstream.get() != null;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.observers.BaseTestConsumer, io.reactivex.rxjava3.disposables.Disposable
    public final boolean isDisposed() {
        return this.cancelled;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.upstream.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.lastThread = Thread.currentThread();
            this.completions++;
            this.downstream.onComplete();
        } finally {
            this.done.countDown();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(@NonNull Throwable t) {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.upstream.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            this.lastThread = Thread.currentThread();
            if (t == null) {
                this.errors.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.errors.add(t);
            }
            this.downstream.onError(t);
        } finally {
            this.done.countDown();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(@NonNull T t) {
        if (!this.checkSubscriptionOnce) {
            this.checkSubscriptionOnce = true;
            if (this.upstream.get() == null) {
                this.errors.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        this.lastThread = Thread.currentThread();
        this.values.add(t);
        if (t == null) {
            this.errors.add(new NullPointerException("onNext received a null value"));
        }
        this.downstream.onNext(t);
    }

    protected void onStart() {
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(@NonNull Subscription s) {
        this.lastThread = Thread.currentThread();
        if (s == null) {
            this.errors.add(new NullPointerException("onSubscribe received a null Subscription"));
        } else if (!this.upstream.compareAndSet(null, s)) {
            s.cancel();
            if (this.upstream.get() == SubscriptionHelper.CANCELLED) {
                return;
            }
            List<Throwable> list = this.errors;
            list.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + s));
        } else {
            this.downstream.onSubscribe(s);
            long andSet = this.missedRequested.getAndSet(0L);
            if (andSet != 0) {
                s.request(andSet);
            }
            onStart();
        }
    }

    @Override // org.reactivestreams.Subscription
    public final void request(long n) {
        SubscriptionHelper.deferredRequest(this.upstream, this.missedRequested, n);
    }

    public final TestSubscriber<T> requestMore(long n) {
        request(n);
        return this;
    }

    public TestSubscriber(long initialRequest) {
        this(EmptySubscriber.INSTANCE, initialRequest);
    }

    @NonNull
    public static <T> TestSubscriber<T> create(long initialRequested) {
        return new TestSubscriber<>(initialRequested);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.observers.BaseTestConsumer
    /* renamed from: assertSubscribed  reason: collision with other method in class */
    public final TestSubscriber<T> mo10366assertSubscribed() {
        if (this.upstream.get() != null) {
            return this;
        }
        throw fail("Not subscribed!");
    }

    public TestSubscriber(@NonNull Subscriber<? super T> downstream) {
        this(downstream, Long.MAX_VALUE);
    }

    public static <T> TestSubscriber<T> create(@NonNull Subscriber<? super T> delegate) {
        return new TestSubscriber<>(delegate);
    }

    public TestSubscriber(@NonNull Subscriber<? super T> actual, long initialRequest) {
        if (initialRequest >= 0) {
            this.downstream = actual;
            this.upstream = new AtomicReference<>();
            this.missedRequested = new AtomicLong(initialRequest);
            return;
        }
        throw new IllegalArgumentException("Negative initial request not allowed");
    }
}

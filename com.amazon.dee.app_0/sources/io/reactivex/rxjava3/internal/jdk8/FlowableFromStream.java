package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class FlowableFromStream<T> extends Flowable<T> {
    final Stream<T> stream;

    /* loaded from: classes3.dex */
    static abstract class AbstractStreamSubscription<T> extends AtomicLong implements QueueSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        volatile boolean cancelled;
        AutoCloseable closeable;
        Iterator<T> iterator;
        boolean once;

        AbstractStreamSubscription(Iterator<T> iterator, AutoCloseable closeable) {
            this.iterator = iterator;
            this.closeable = closeable;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            request(1L);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.iterator = null;
            AutoCloseable autoCloseable = this.closeable;
            this.closeable = null;
            if (autoCloseable != null) {
                FlowableFromStream.closeSafely(autoCloseable);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            Iterator<T> it2 = this.iterator;
            if (it2 != null) {
                if (!this.once || it2.hasNext()) {
                    return false;
                }
                clear();
                return true;
            }
            return true;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean offer(@NonNull T value) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public T mo10355poll() {
            Iterator<T> it2 = this.iterator;
            if (it2 == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!it2.hasNext()) {
                clear();
                return null;
            }
            return (T) Objects.requireNonNull(this.iterator.next(), "The Stream's Iterator.next() returned a null value");
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (!SubscriptionHelper.validate(n) || BackpressureHelper.add(this, n) != 0) {
                return;
            }
            run(n);
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 1) != 0) {
                lazySet(Long.MAX_VALUE);
                return 1;
            }
            return 0;
        }

        abstract void run(long n);

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean offer(@NonNull T v1, @NonNull T v2) {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class StreamConditionalSubscription<T> extends AbstractStreamSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        final ConditionalSubscriber<? super T> downstream;

        StreamConditionalSubscription(ConditionalSubscriber<? super T> downstream, Iterator<T> iterator, AutoCloseable closeable) {
            super(iterator, closeable);
            this.downstream = downstream;
        }

        @Override // io.reactivex.rxjava3.internal.jdk8.FlowableFromStream.AbstractStreamSubscription
        public void run(long n) {
            Iterator<T> it2 = this.iterator;
            ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
            long j = n;
            long j2 = 0;
            while (!this.cancelled) {
                try {
                    if (conditionalSubscriber.tryOnNext((Object) Objects.requireNonNull(it2.next(), "The Stream's Iterator returned a null value"))) {
                        j2++;
                    }
                    if (this.cancelled) {
                        continue;
                    } else {
                        try {
                            if (!it2.hasNext()) {
                                conditionalSubscriber.onComplete();
                                this.cancelled = true;
                            } else if (j2 != j) {
                                continue;
                            } else {
                                j = get();
                                if (j2 != j) {
                                    continue;
                                } else if (compareAndSet(j, 0L)) {
                                    return;
                                } else {
                                    j = get();
                                }
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            conditionalSubscriber.onError(th);
                            this.cancelled = true;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    conditionalSubscriber.onError(th2);
                    this.cancelled = true;
                }
            }
            clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class StreamSubscription<T> extends AbstractStreamSubscription<T> {
        private static final long serialVersionUID = -9082954702547571853L;
        final Subscriber<? super T> downstream;

        StreamSubscription(Subscriber<? super T> downstream, Iterator<T> iterator, AutoCloseable closeable) {
            super(iterator, closeable);
            this.downstream = downstream;
        }

        @Override // io.reactivex.rxjava3.internal.jdk8.FlowableFromStream.AbstractStreamSubscription
        public void run(long n) {
            Iterator<T> it2 = this.iterator;
            Subscriber<? super T> subscriber = this.downstream;
            long j = n;
            long j2 = 0;
            while (!this.cancelled) {
                try {
                    subscriber.onNext((Object) Objects.requireNonNull(it2.next(), "The Stream's Iterator returned a null value"));
                    if (this.cancelled) {
                        continue;
                    } else {
                        try {
                            if (!it2.hasNext()) {
                                subscriber.onComplete();
                                this.cancelled = true;
                            } else {
                                j2++;
                                if (j2 != j) {
                                    continue;
                                } else {
                                    j = get();
                                    if (j2 != j) {
                                        continue;
                                    } else if (compareAndSet(j, 0L)) {
                                        return;
                                    } else {
                                        j = get();
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            subscriber.onError(th);
                            this.cancelled = true;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    subscriber.onError(th2);
                    this.cancelled = true;
                }
            }
            clear();
        }
    }

    public FlowableFromStream(Stream<T> stream) {
        this.stream = stream;
    }

    static void closeSafely(AutoCloseable c) {
        try {
            c.close();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    public static <T> void subscribeStream(Subscriber<? super T> s, Stream<T> stream) {
        try {
            Iterator<T> it2 = stream.iterator();
            if (!it2.hasNext()) {
                EmptySubscription.complete(s);
                closeSafely(stream);
            } else if (s instanceof ConditionalSubscriber) {
                s.onSubscribe(new StreamConditionalSubscription((ConditionalSubscriber) s, it2, stream));
            } else {
                s.onSubscribe(new StreamSubscription(s, it2, stream));
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, s);
            closeSafely(stream);
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(Subscriber<? super T> s) {
        subscribeStream(s, this.stream);
    }
}

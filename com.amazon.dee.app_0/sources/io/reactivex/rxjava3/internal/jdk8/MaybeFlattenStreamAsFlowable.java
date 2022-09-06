package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import org.reactivestreams.Subscriber;
/* loaded from: classes3.dex */
public final class MaybeFlattenStreamAsFlowable<T, R> extends Flowable<R> {
    final Function<? super T, ? extends Stream<? extends R>> mapper;
    final Maybe<T> source;

    /* loaded from: classes3.dex */
    static final class FlattenStreamMultiObserver<T, R> extends BasicIntQueueSubscription<R> implements MaybeObserver<T>, SingleObserver<T> {
        private static final long serialVersionUID = 7363336003027148283L;
        volatile boolean cancelled;
        AutoCloseable close;
        final Subscriber<? super R> downstream;
        long emitted;
        volatile Iterator<? extends R> iterator;
        final Function<? super T, ? extends Stream<? extends R>> mapper;
        boolean once;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();
        Disposable upstream;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FlattenStreamMultiObserver(Subscriber<? super R> downstream, Function<? super T, ? extends Stream<? extends R>> mapper) {
            this.downstream = downstream;
            this.mapper = mapper;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.upstream.dispose();
            if (!this.outputFused) {
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.iterator = null;
            AutoCloseable autoCloseable = this.close;
            this.close = null;
            close(autoCloseable);
        }

        void close(AutoCloseable c) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }

        void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super R> subscriber = this.downstream;
            long j = this.emitted;
            long j2 = this.requested.get();
            Iterator<? extends R> it2 = this.iterator;
            int i = 1;
            while (true) {
                if (this.cancelled) {
                    clear();
                } else if (this.outputFused) {
                    if (it2 != null) {
                        subscriber.onNext(null);
                        subscriber.onComplete();
                    }
                } else if (it2 != null && j != j2) {
                    try {
                        Object obj = (R) it2.next();
                        if (!this.cancelled) {
                            subscriber.onNext(obj);
                            j++;
                            if (!this.cancelled) {
                                try {
                                    boolean hasNext = it2.hasNext();
                                    if (!this.cancelled && !hasNext) {
                                        subscriber.onComplete();
                                        this.cancelled = true;
                                    }
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    subscriber.onError(th);
                                    this.cancelled = true;
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        subscriber.onError(th2);
                        this.cancelled = true;
                    }
                }
                this.emitted = j;
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
                j2 = this.requested.get();
                if (it2 == null) {
                    it2 = this.iterator;
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            Iterator<? extends R> it2 = this.iterator;
            if (it2 != null) {
                if (!this.once || it2.hasNext()) {
                    return false;
                }
                clear();
                return true;
            }
            return true;
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onError(@NonNull Throwable e) {
            this.downstream.onError(e);
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onSubscribe(@NonNull Disposable d) {
            if (DisposableHelper.validate(this.upstream, d)) {
                this.upstream = d;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(@NonNull T t) {
            try {
                Stream stream = (Stream) Objects.requireNonNull(this.mapper.mo10358apply(t), "The mapper returned a null Stream");
                Iterator<? extends R> it2 = stream.iterator();
                if (!it2.hasNext()) {
                    this.downstream.onComplete();
                    close(stream);
                    return;
                }
                this.iterator = it2;
                this.close = stream;
                drain();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public R mo10355poll() throws Throwable {
            Iterator<? extends R> it2 = this.iterator;
            if (it2 != null) {
                if (this.once) {
                    if (!it2.hasNext()) {
                        clear();
                        return null;
                    }
                } else {
                    this.once = true;
                }
                return it2.next();
            }
            return null;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 2) != 0) {
                this.outputFused = true;
                return 2;
            }
            return 0;
        }
    }

    public MaybeFlattenStreamAsFlowable(Maybe<T> source, Function<? super T, ? extends Stream<? extends R>> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    protected void subscribeActual(@NonNull Subscriber<? super R> s) {
        this.source.subscribe(new FlattenStreamMultiObserver(s, this.mapper));
    }
}

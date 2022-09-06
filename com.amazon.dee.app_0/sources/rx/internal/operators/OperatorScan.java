package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes5.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    private static final Object NO_INITIAL_VALUE = new Object();
    final Func2<R, ? super T, R> accumulator;
    private final Func0<R> initialValueFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class InitialProducer<R> implements Producer, Observer<R> {
        final Subscriber<? super R> child;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        long missedRequested;
        volatile Producer producer;
        final Queue<Object> queue;
        final AtomicLong requested;

        public InitialProducer(R r, Subscriber<? super R> subscriber) {
            Queue<Object> spscLinkedAtomicQueue;
            this.child = subscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscLinkedAtomicQueue = new SpscLinkedQueue<>();
            } else {
                spscLinkedAtomicQueue = new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedAtomicQueue;
            spscLinkedAtomicQueue.offer(NotificationLite.instance().next(r));
            this.requested = new AtomicLong();
        }

        boolean checkTerminated(boolean z, boolean z2, Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
                return true;
            } else if (!z2) {
                return false;
            } else {
                subscriber.onCompleted();
                return true;
            }
        }

        void emit() {
            synchronized (this) {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                emitLoop();
            }
        }

        void emitLoop() {
            Subscriber<? super R> subscriber = this.child;
            Queue<Object> queue = this.queue;
            NotificationLite instance = NotificationLite.instance();
            AtomicLong atomicLong = this.requested;
            long j = atomicLong.get();
            while (!checkTerminated(this.done, queue.isEmpty(), subscriber)) {
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (checkTerminated(z, z2, subscriber)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    Object obj = (Object) instance.getValue(poll);
                    try {
                        subscriber.onNext(obj);
                        j2++;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, obj);
                        return;
                    }
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    j = BackpressureUtils.produced(atomicLong, j2);
                }
                synchronized (this) {
                    if (!this.missed) {
                        this.emitting = false;
                        return;
                    }
                    this.missed = false;
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            emit();
        }

        @Override // rx.Observer
        public void onNext(R r) {
            this.queue.offer(NotificationLite.instance().next(r));
            emit();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i == 0) {
                    return;
                }
                BackpressureUtils.getAndAddRequest(this.requested, j);
                Producer producer = this.producer;
                if (producer == null) {
                    synchronized (this.requested) {
                        producer = this.producer;
                        if (producer == null) {
                            this.missedRequested = BackpressureUtils.addCap(this.missedRequested, j);
                        }
                    }
                }
                if (producer != null) {
                    producer.request(j);
                }
                emit();
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= required but it was ", j));
        }

        public void setProducer(Producer producer) {
            long j;
            if (producer != null) {
                synchronized (this.requested) {
                    if (this.producer == null) {
                        j = this.missedRequested;
                        if (j != Long.MAX_VALUE) {
                            j--;
                        }
                        this.missedRequested = 0L;
                        this.producer = producer;
                    } else {
                        throw new IllegalStateException("Can't set more than one Producer!");
                    }
                }
                if (j > 0) {
                    producer.request(j);
                }
                emit();
                return;
            }
            throw new NullPointerException();
        }
    }

    public OperatorScan(final R r, Func2<R, ? super T, R> func2) {
        this((Func0) new Func0<R>() { // from class: rx.internal.operators.OperatorScan.1
            @Override // rx.functions.Func0, java.util.concurrent.Callable
            /* renamed from: call */
            public R mo13098call() {
                return (R) r;
            }
        }, (Func2) func2);
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorScan(Func0<R> func0, Func2<R, ? super T, R> func2) {
        this.initialValueFactory = func0;
        this.accumulator = func2;
    }

    public Subscriber<? super T> call(final Subscriber<? super R> subscriber) {
        final R mo13098call = this.initialValueFactory.mo13098call();
        if (mo13098call == NO_INITIAL_VALUE) {
            return (Subscriber<T>) new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorScan.2
                boolean once;
                R value;

                @Override // rx.Observer
                public void onCompleted() {
                    subscriber.onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    subscriber.onError(th);
                }

                @Override // rx.Observer
                public void onNext(T t) {
                    R r;
                    if (!this.once) {
                        this.once = true;
                        r = t;
                    } else {
                        try {
                            r = OperatorScan.this.accumulator.mo13094call(this.value, t);
                        } catch (Throwable th) {
                            Exceptions.throwOrReport(th, subscriber, t);
                            return;
                        }
                    }
                    this.value = r;
                    subscriber.onNext(r);
                }
            };
        }
        final InitialProducer initialProducer = new InitialProducer(mo13098call, subscriber);
        Subscriber subscriber2 = (Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.OperatorScan.3
            private R value;

            {
                this.value = (R) mo13098call;
            }

            @Override // rx.Observer
            public void onCompleted() {
                initialProducer.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                initialProducer.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                try {
                    R mo13094call = OperatorScan.this.accumulator.mo13094call(this.value, t);
                    this.value = mo13094call;
                    initialProducer.onNext(mo13094call);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this, t);
                }
            }

            @Override // rx.Subscriber
            public void setProducer(Producer producer) {
                initialProducer.setProducer(producer);
            }
        };
        subscriber.add(subscriber2);
        subscriber.setProducer(initialProducer);
        return subscriber2;
    }

    public OperatorScan(Func2<R, ? super T, R> func2) {
        this(NO_INITIAL_VALUE, func2);
    }
}

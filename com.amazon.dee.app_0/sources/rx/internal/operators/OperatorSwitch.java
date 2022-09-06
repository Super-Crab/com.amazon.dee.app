package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes5.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    final boolean delayError;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class Holder {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(false);

        Holder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class HolderDelayError {
        static final OperatorSwitch<Object> INSTANCE = new OperatorSwitch<>(true);

        HolderDelayError() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class InnerSubscriber<T> extends Subscriber<T> {
        private final long id;
        private final SwitchSubscriber<T> parent;

        InnerSubscriber(long j, SwitchSubscriber<T> switchSubscriber) {
            this.id = j;
            this.parent = switchSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.parent.complete(this.id);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.parent.error(th, this.id);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.parent.emit(t, this);
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            this.parent.innerProducer(producer, this.id);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class SwitchSubscriber<T> extends Subscriber<Observable<? extends T>> {
        static final Throwable TERMINAL_ERROR = new Throwable("Terminal error");
        final Subscriber<? super T> child;
        final boolean delayError;
        boolean emitting;
        Throwable error;
        boolean innerActive;
        volatile boolean mainDone;
        boolean missed;
        Producer producer;
        long requested;
        final SerialSubscription ssub = new SerialSubscription();
        final AtomicLong index = new AtomicLong();
        final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);
        final NotificationLite<T> nl = NotificationLite.instance();

        SwitchSubscriber(Subscriber<? super T> subscriber, boolean z) {
            this.child = subscriber;
            this.delayError = z;
        }

        protected boolean checkTerminated(boolean z, boolean z2, Throwable th, SpscLinkedArrayQueue<Object> spscLinkedArrayQueue, Subscriber<? super T> subscriber, boolean z3) {
            if (this.delayError) {
                if (!z || z2 || !z3) {
                    return false;
                }
                if (th != null) {
                    subscriber.onError(th);
                } else {
                    subscriber.onCompleted();
                }
                return true;
            } else if (th != null) {
                spscLinkedArrayQueue.clear();
                subscriber.onError(th);
                return true;
            } else if (!z || z2 || !z3) {
                return false;
            } else {
                subscriber.onCompleted();
                return true;
            }
        }

        void childRequested(long j) {
            Producer producer;
            synchronized (this) {
                producer = this.producer;
                this.requested = BackpressureUtils.addCap(this.requested, j);
            }
            if (producer != null) {
                producer.request(j);
            }
            drain();
        }

        void clearProducer() {
            synchronized (this) {
                this.producer = null;
            }
        }

        void complete(long j) {
            synchronized (this) {
                if (this.index.get() != j) {
                    return;
                }
                this.innerActive = false;
                this.producer = null;
                drain();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
            if (r18 != 0) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007e, code lost:
            if (r11.isUnsubscribed() == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0080, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0091, code lost:
            if (checkTerminated(r20.mainDone, r0, r14, r9, r11, r9.isEmpty()) == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0093, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0094, code lost:
            monitor-enter(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0095, code lost:
            r0 = r20.requested;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x009e, code lost:
            if (r0 == Long.MAX_VALUE) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00a0, code lost:
            r0 = r0 - r16;
            r20.requested = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00a4, code lost:
            r12 = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00a8, code lost:
            if (r20.missed != false) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00aa, code lost:
            r20.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00ac, code lost:
            monitor-exit(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00ad, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00ae, code lost:
            r20.missed = false;
            r15 = r20.mainDone;
            r0 = r20.innerActive;
            r14 = r20.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00b6, code lost:
            if (r14 == null) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00ba, code lost:
            if (r14 == rx.internal.operators.OperatorSwitch.SwitchSubscriber.TERMINAL_ERROR) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00be, code lost:
            if (r20.delayError != false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00c0, code lost:
            r20.error = rx.internal.operators.OperatorSwitch.SwitchSubscriber.TERMINAL_ERROR;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00c4, code lost:
            monitor-exit(r20);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        void drain() {
            /*
                Method dump skipped, instructions count: 205
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSwitch.SwitchSubscriber.drain():void");
        }

        void emit(T t, InnerSubscriber<T> innerSubscriber) {
            synchronized (this) {
                if (this.index.get() != ((InnerSubscriber) innerSubscriber).id) {
                    return;
                }
                this.queue.offer(innerSubscriber, this.nl.next(t));
                drain();
            }
        }

        void error(Throwable th, long j) {
            boolean z;
            synchronized (this) {
                if (this.index.get() == j) {
                    z = updateError(th);
                    this.innerActive = false;
                    this.producer = null;
                } else {
                    z = true;
                }
            }
            if (z) {
                drain();
            } else {
                pluginError(th);
            }
        }

        void init() {
            this.child.add(this.ssub);
            this.child.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.1
                @Override // rx.functions.Action0
                public void call() {
                    SwitchSubscriber.this.clearProducer();
                }
            }));
            this.child.setProducer(new Producer() { // from class: rx.internal.operators.OperatorSwitch.SwitchSubscriber.2
                @Override // rx.Producer
                public void request(long j) {
                    int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                    if (i > 0) {
                        SwitchSubscriber.this.childRequested(j);
                    } else if (i < 0) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= 0 expected but it was ", j));
                    }
                }
            });
        }

        void innerProducer(Producer producer, long j) {
            synchronized (this) {
                if (this.index.get() != j) {
                    return;
                }
                long j2 = this.requested;
                this.producer = producer;
                producer.request(j2);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.mainDone = true;
            drain();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            boolean updateError;
            synchronized (this) {
                updateError = updateError(th);
            }
            if (updateError) {
                this.mainDone = true;
                drain();
                return;
            }
            pluginError(th);
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Observable) ((Observable) obj));
        }

        void pluginError(Throwable th) {
            RxJavaHooks.onError(th);
        }

        boolean updateError(Throwable th) {
            Throwable th2 = this.error;
            if (th2 == TERMINAL_ERROR) {
                return false;
            }
            if (th2 == null) {
                this.error = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                arrayList.add(th);
                this.error = new CompositeException(arrayList);
            } else {
                this.error = new CompositeException(th2, th);
            }
            return true;
        }

        public void onNext(Observable<? extends T> observable) {
            InnerSubscriber innerSubscriber;
            long incrementAndGet = this.index.incrementAndGet();
            Subscription subscription = this.ssub.get();
            if (subscription != null) {
                subscription.unsubscribe();
            }
            synchronized (this) {
                innerSubscriber = new InnerSubscriber(incrementAndGet, this);
                this.innerActive = true;
                this.producer = null;
            }
            this.ssub.set(innerSubscriber);
            observable.unsafeSubscribe(innerSubscriber);
        }
    }

    OperatorSwitch(boolean z) {
        this.delayError = z;
    }

    public static <T> OperatorSwitch<T> instance(boolean z) {
        if (z) {
            return (OperatorSwitch<T>) HolderDelayError.INSTANCE;
        }
        return (OperatorSwitch<T>) Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        SwitchSubscriber switchSubscriber = new SwitchSubscriber(subscriber, this.delayError);
        subscriber.add(switchSubscriber);
        switchSubscriber.init();
        return switchSubscriber;
    }
}

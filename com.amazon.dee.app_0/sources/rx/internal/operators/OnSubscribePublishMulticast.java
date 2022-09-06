package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes5.dex */
public final class OnSubscribePublishMulticast<T> extends AtomicInteger implements Observable.OnSubscribe<T>, Observer<T>, Subscription {
    static final PublishProducer<?>[] EMPTY = new PublishProducer[0];
    static final PublishProducer<?>[] TERMINATED = new PublishProducer[0];
    private static final long serialVersionUID = -3741892510772238743L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final ParentSubscriber<T> parent;
    final int prefetch;
    volatile Producer producer;
    final Queue<T> queue;
    volatile PublishProducer<T>[] subscribers;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class ParentSubscriber<T> extends Subscriber<T> {
        final OnSubscribePublishMulticast<T> state;

        public ParentSubscriber(OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.state = onSubscribePublishMulticast;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.state.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.state.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.state.onNext(t);
        }

        @Override // rx.Subscriber
        public void setProducer(Producer producer) {
            this.state.setProducer(producer);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class PublishProducer<T> extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = 960704844171597367L;
        final Subscriber<? super T> actual;
        final AtomicBoolean once = new AtomicBoolean();
        final OnSubscribePublishMulticast<T> parent;

        public PublishProducer(Subscriber<? super T> subscriber, OnSubscribePublishMulticast<T> onSubscribePublishMulticast) {
            this.actual = subscriber;
            this.parent = onSubscribePublishMulticast;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.once.get();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i == 0) {
                    return;
                }
                BackpressureUtils.getAndAddRequest(this, j);
                this.parent.drain();
                return;
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline56("n >= 0 required but it was ", j));
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }
    }

    public OnSubscribePublishMulticast(int i, boolean z) {
        if (i > 0) {
            this.prefetch = i;
            this.delayError = z;
            if (UnsafeAccess.isUnsafeAvailable()) {
                this.queue = new SpscArrayQueue(i);
            } else {
                this.queue = new SpscAtomicArrayQueue(i);
            }
            this.subscribers = (PublishProducer<T>[]) EMPTY;
            this.parent = new ParentSubscriber<>(this);
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("prefetch > 0 required but it was ", i));
    }

    boolean add(PublishProducer<T> publishProducer) {
        if (this.subscribers == TERMINATED) {
            return false;
        }
        synchronized (this) {
            PublishProducer<T>[] publishProducerArr = this.subscribers;
            if (publishProducerArr == TERMINATED) {
                return false;
            }
            int length = publishProducerArr.length;
            PublishProducer<T>[] publishProducerArr2 = new PublishProducer[length + 1];
            System.arraycopy(publishProducerArr, 0, publishProducerArr2, 0, length);
            publishProducerArr2[length] = publishProducer;
            this.subscribers = publishProducerArr2;
            return true;
        }
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    boolean checkTerminated(boolean z, boolean z2) {
        int i = 0;
        if (z) {
            if (!this.delayError) {
                Throwable th = this.error;
                if (th != null) {
                    this.queue.clear();
                    PublishProducer<T>[] terminate = terminate();
                    int length = terminate.length;
                    while (i < length) {
                        terminate[i].actual.onError(th);
                        i++;
                    }
                    return true;
                } else if (z2) {
                    PublishProducer<T>[] terminate2 = terminate();
                    int length2 = terminate2.length;
                    while (i < length2) {
                        terminate2[i].actual.onCompleted();
                        i++;
                    }
                    return true;
                }
            } else if (z2) {
                PublishProducer<T>[] terminate3 = terminate();
                Throwable th2 = this.error;
                if (th2 != null) {
                    int length3 = terminate3.length;
                    while (i < length3) {
                        terminate3[i].actual.onError(th2);
                        i++;
                    }
                } else {
                    int length4 = terminate3.length;
                    while (i < length4) {
                        terminate3[i].actual.onCompleted();
                        i++;
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r10 != 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
        if (checkTerminated(r14.done, r0.isEmpty()) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0066, code lost:
        if (r6 == 0) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
        r3 = r14.producer;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x006a, code lost:
        if (r3 == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x006c, code lost:
        r3.request(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006f, code lost:
        r3 = r5.length;
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
        if (r4 >= r3) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
        rx.internal.operators.BackpressureUtils.produced(r5[r4], r6);
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void drain() {
        /*
            r14 = this;
            int r0 = r14.getAndIncrement()
            if (r0 == 0) goto L7
            return
        L7:
            java.util.Queue<T> r0 = r14.queue
            r1 = 0
            r2 = r1
        Lb:
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            rx.internal.operators.OnSubscribePublishMulticast$PublishProducer<T>[] r5 = r14.subscribers
            int r6 = r5.length
            int r7 = r5.length
            r8 = r3
            r3 = r1
        L16:
            if (r3 >= r7) goto L25
            r4 = r5[r3]
            long r10 = r4.get()
            long r8 = java.lang.Math.min(r8, r10)
            int r3 = r3 + 1
            goto L16
        L25:
            if (r6 == 0) goto L7b
            r3 = 0
            r6 = r3
        L2a:
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 == 0) goto L55
            boolean r11 = r14.done
            java.lang.Object r12 = r0.poll()
            if (r12 != 0) goto L38
            r13 = 1
            goto L39
        L38:
            r13 = r1
        L39:
            boolean r11 = r14.checkTerminated(r11, r13)
            if (r11 == 0) goto L40
            return
        L40:
            if (r13 == 0) goto L43
            goto L55
        L43:
            int r10 = r5.length
            r11 = r1
        L45:
            if (r11 >= r10) goto L51
            r13 = r5[r11]
            rx.Subscriber<? super T> r13 = r13.actual
            r13.onNext(r12)
            int r11 = r11 + 1
            goto L45
        L51:
            r10 = 1
            long r6 = r6 + r10
            goto L2a
        L55:
            if (r10 != 0) goto L64
            boolean r8 = r14.done
            boolean r9 = r0.isEmpty()
            boolean r8 = r14.checkTerminated(r8, r9)
            if (r8 == 0) goto L64
            return
        L64:
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L7b
            rx.Producer r3 = r14.producer
            if (r3 == 0) goto L6f
            r3.request(r6)
        L6f:
            int r3 = r5.length
            r4 = r1
        L71:
            if (r4 >= r3) goto L7b
            r8 = r5[r4]
            rx.internal.operators.BackpressureUtils.produced(r8, r6)
            int r4 = r4 + 1
            goto L71
        L7b:
            int r2 = -r2
            int r2 = r14.addAndGet(r2)
            if (r2 != 0) goto Lb
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribePublishMulticast.drain():void");
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.parent.isUnsubscribed();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // rx.Observer
    public void onNext(T t) {
        if (!this.queue.offer(t)) {
            this.parent.unsubscribe();
            this.error = new MissingBackpressureException("Queue full?!");
            this.done = true;
        }
        drain();
    }

    void remove(PublishProducer<T> publishProducer) {
        PublishProducer[] publishProducerArr;
        PublishProducer<T>[] publishProducerArr2 = this.subscribers;
        if (publishProducerArr2 == TERMINATED || publishProducerArr2 == EMPTY) {
            return;
        }
        synchronized (this) {
            PublishProducer<T>[] publishProducerArr3 = this.subscribers;
            if (publishProducerArr3 != TERMINATED && publishProducerArr3 != EMPTY) {
                int i = -1;
                int length = publishProducerArr3.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (publishProducerArr3[i2] == publishProducer) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    publishProducerArr = EMPTY;
                } else {
                    PublishProducer[] publishProducerArr4 = new PublishProducer[length - 1];
                    System.arraycopy(publishProducerArr3, 0, publishProducerArr4, 0, i);
                    System.arraycopy(publishProducerArr3, i + 1, publishProducerArr4, i, (length - i) - 1);
                    publishProducerArr = publishProducerArr4;
                }
                this.subscribers = publishProducerArr;
            }
        }
    }

    void setProducer(Producer producer) {
        this.producer = producer;
        producer.request(this.prefetch);
    }

    public Subscriber<T> subscriber() {
        return this.parent;
    }

    PublishProducer<T>[] terminate() {
        PublishProducer<T>[] publishProducerArr = this.subscribers;
        if (publishProducerArr != TERMINATED) {
            synchronized (this) {
                publishProducerArr = this.subscribers;
                if (publishProducerArr != TERMINATED) {
                    this.subscribers = (PublishProducer<T>[]) TERMINATED;
                }
            }
        }
        return publishProducerArr;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.parent.unsubscribe();
    }

    public void call(Subscriber<? super T> subscriber) {
        PublishProducer<T> publishProducer = new PublishProducer<>(subscriber, this);
        subscriber.add(publishProducer);
        subscriber.setProducer(publishProducer);
        if (add(publishProducer)) {
            if (publishProducer.isUnsubscribed()) {
                remove(publishProducer);
                return;
            } else {
                drain();
                return;
            }
        }
        Throwable th = this.error;
        if (th != null) {
            subscriber.onError(th);
        } else {
            subscriber.onCompleted();
        }
    }
}

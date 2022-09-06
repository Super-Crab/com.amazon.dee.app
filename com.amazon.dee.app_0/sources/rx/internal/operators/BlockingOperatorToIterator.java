package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.util.RxRingBuffer;
/* loaded from: classes5.dex */
public final class BlockingOperatorToIterator {

    /* loaded from: classes5.dex */
    public static final class SubscriberIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        static final int LIMIT = (RxRingBuffer.SIZE * 3) / 4;
        private Notification<? extends T> buf;
        private final BlockingQueue<Notification<? extends T>> notifications = new LinkedBlockingQueue();
        private int received;

        private Notification<? extends T> take() {
            try {
                Notification<? extends T> poll = this.notifications.poll();
                return poll != null ? poll : this.notifications.take();
            } catch (InterruptedException e) {
                unsubscribe();
                throw Exceptions.propagate(e);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.buf == null) {
                this.buf = take();
                this.received++;
                int i = this.received;
                if (i >= LIMIT) {
                    request(i);
                    this.received = 0;
                }
            }
            if (!this.buf.isOnError()) {
                return !this.buf.isOnCompleted();
            }
            throw Exceptions.propagate(this.buf.getThrowable());
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T value = this.buf.getValue();
                this.buf = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.notifications.offer(Notification.createOnError(th));
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        @Override // rx.Subscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator");
        }

        public void onNext(Notification<? extends T> notification) {
            this.notifications.offer(notification);
        }
    }

    private BlockingOperatorToIterator() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterator<T> toIterator(Observable<? extends T> observable) {
        SubscriberIterator subscriberIterator = new SubscriberIterator();
        observable.materialize().subscribe((Subscriber<? super Notification<? extends T>>) subscriberIterator);
        return subscriberIterator;
    }
}

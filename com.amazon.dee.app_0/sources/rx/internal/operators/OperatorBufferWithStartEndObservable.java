package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes5.dex */
public final class OperatorBufferWithStartEndObservable<T, TOpening, TClosing> implements Observable.Operator<List<T>, T> {
    final Func1<? super TOpening, ? extends Observable<? extends TClosing>> bufferClosing;
    final Observable<? extends TOpening> bufferOpening;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public final class BufferingSubscriber extends Subscriber<T> {
        final Subscriber<? super List<T>> child;
        final List<List<T>> chunks = new LinkedList();
        final CompositeSubscription closingSubscriptions = new CompositeSubscription();
        boolean done;

        public BufferingSubscriber(Subscriber<? super List<T>> subscriber) {
            this.child = subscriber;
            add(this.closingSubscriptions);
        }

        void endBuffer(List<T> list) {
            boolean z;
            synchronized (this) {
                if (this.done) {
                    return;
                }
                Iterator<List<T>> it2 = this.chunks.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z = false;
                        break;
                    } else if (it2.next() == list) {
                        z = true;
                        it2.remove();
                        break;
                    }
                }
                if (!z) {
                    return;
                }
                this.child.onNext(list);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.done) {
                        return;
                    }
                    this.done = true;
                    LinkedList<List> linkedList = new LinkedList(this.chunks);
                    this.chunks.clear();
                    for (List list : linkedList) {
                        this.child.onNext(list);
                    }
                    this.child.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.child);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.done = true;
                this.chunks.clear();
                this.child.onError(th);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (List<T> list : this.chunks) {
                    list.add(t);
                }
            }
        }

        void startBuffer(TOpening topening) {
            final ArrayList arrayList = new ArrayList();
            synchronized (this) {
                if (this.done) {
                    return;
                }
                this.chunks.add(arrayList);
                try {
                    Observable<? extends TClosing> mo13102call = OperatorBufferWithStartEndObservable.this.bufferClosing.mo13102call(topening);
                    Subscriber<TClosing> subscriber = new Subscriber<TClosing>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.BufferingSubscriber.1
                        @Override // rx.Observer
                        public void onCompleted() {
                            BufferingSubscriber.this.closingSubscriptions.remove(this);
                            BufferingSubscriber.this.endBuffer(arrayList);
                        }

                        @Override // rx.Observer
                        public void onError(Throwable th) {
                            BufferingSubscriber.this.onError(th);
                        }

                        @Override // rx.Observer
                        public void onNext(TClosing tclosing) {
                            BufferingSubscriber.this.closingSubscriptions.remove(this);
                            BufferingSubscriber.this.endBuffer(arrayList);
                        }
                    };
                    this.closingSubscriptions.add(subscriber);
                    mo13102call.unsafeSubscribe(subscriber);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }
    }

    public OperatorBufferWithStartEndObservable(Observable<? extends TOpening> observable, Func1<? super TOpening, ? extends Observable<? extends TClosing>> func1) {
        this.bufferOpening = observable;
        this.bufferClosing = func1;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        final BufferingSubscriber bufferingSubscriber = new BufferingSubscriber(new SerializedSubscriber(subscriber));
        Subscriber<TOpening> subscriber2 = new Subscriber<TOpening>() { // from class: rx.internal.operators.OperatorBufferWithStartEndObservable.1
            @Override // rx.Observer
            public void onCompleted() {
                bufferingSubscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                bufferingSubscriber.onError(th);
            }

            @Override // rx.Observer
            public void onNext(TOpening topening) {
                bufferingSubscriber.startBuffer(topening);
            }
        };
        subscriber.add(subscriber2);
        subscriber.add(bufferingSubscriber);
        this.bufferOpening.unsafeSubscribe(subscriber2);
        return bufferingSubscriber;
    }
}

package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func0;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes5.dex */
public final class OperatorWindowWithObservableFactory<T, U> implements Observable.Operator<Observable<T>, T> {
    static final Object NEXT_SUBJECT = new Object();
    static final NotificationLite<Object> NL = NotificationLite.instance();
    final Func0<? extends Observable<? extends U>> otherFactory;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class BoundarySubscriber<T, U> extends Subscriber<U> {
        boolean done;
        final SourceSubscriber<T, U> sub;

        public BoundarySubscriber(SourceSubscriber<T, U> sourceSubscriber) {
            this.sub = sourceSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                this.sub.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.sub.onError(th);
        }

        @Override // rx.Observer
        public void onNext(U u) {
            if (!this.done) {
                this.done = true;
                this.sub.replaceWindow();
            }
        }

        @Override // rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class SourceSubscriber<T, U> extends Subscriber<T> {
        final Subscriber<? super Observable<T>> child;
        Observer<T> consumer;
        boolean emitting;
        final Func0<? extends Observable<? extends U>> otherFactory;
        Observable<T> producer;
        List<Object> queue;
        final Object guard = new Object();
        final SerialSubscription ssub = new SerialSubscription();

        public SourceSubscriber(Subscriber<? super Observable<T>> subscriber, Func0<? extends Observable<? extends U>> func0) {
            this.child = new SerializedSubscriber(subscriber);
            this.otherFactory = func0;
            add(this.ssub);
        }

        void complete() {
            Observer<T> observer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (observer != null) {
                observer.onCompleted();
            }
            this.child.onCompleted();
            unsubscribe();
        }

        void createNewWindow() {
            UnicastSubject create = UnicastSubject.create();
            this.consumer = create;
            this.producer = create;
            try {
                Observable<? extends U> mo13098call = this.otherFactory.mo13098call();
                BoundarySubscriber boundarySubscriber = new BoundarySubscriber(this);
                this.ssub.set(boundarySubscriber);
                mo13098call.unsafeSubscribe(boundarySubscriber);
            } catch (Throwable th) {
                this.child.onError(th);
                unsubscribe();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void drain(List<Object> list) {
            if (list == null) {
                return;
            }
            for (Object obj : list) {
                if (obj == OperatorWindowWithObservableFactory.NEXT_SUBJECT) {
                    replaceSubject();
                } else if (OperatorWindowWithObservableFactory.NL.isError(obj)) {
                    error(OperatorWindowWithObservableFactory.NL.getError(obj));
                    return;
                } else if (OperatorWindowWithObservableFactory.NL.isCompleted(obj)) {
                    complete();
                    return;
                } else {
                    emitValue(obj);
                }
            }
        }

        void emitValue(T t) {
            Observer<T> observer = this.consumer;
            if (observer != null) {
                observer.onNext(t);
            }
        }

        void error(Throwable th) {
            Observer<T> observer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (observer != null) {
                observer.onError(th);
            }
            this.child.onError(th);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onCompleted() {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithObservableFactory.NL.completed());
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                this.emitting = true;
                try {
                    drain(list);
                    complete();
                } catch (Throwable th) {
                    error(th);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(OperatorWindowWithObservableFactory.NL.error(th));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                error(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(t);
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                boolean z = true;
                this.emitting = true;
                boolean z2 = true;
                while (true) {
                    try {
                        drain(list);
                        if (z2) {
                            emitValue(t);
                            z2 = false;
                        }
                        try {
                            synchronized (this.guard) {
                                try {
                                    List<Object> list2 = this.queue;
                                    this.queue = null;
                                    if (list2 == null) {
                                        this.emitting = false;
                                        return;
                                    } else if (this.child.isUnsubscribed()) {
                                        synchronized (this.guard) {
                                            this.emitting = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    z = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.guard) {
                                                this.emitting = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }

        @Override // rx.Subscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }

        void replaceSubject() {
            Observer<T> observer = this.consumer;
            if (observer != null) {
                observer.onCompleted();
            }
            createNewWindow();
            this.child.onNext(this.producer);
        }

        void replaceWindow() {
            synchronized (this.guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList();
                    }
                    this.queue.add(OperatorWindowWithObservableFactory.NEXT_SUBJECT);
                    return;
                }
                List<Object> list = this.queue;
                this.queue = null;
                boolean z = true;
                this.emitting = true;
                boolean z2 = true;
                while (true) {
                    try {
                        drain(list);
                        if (z2) {
                            replaceSubject();
                            z2 = false;
                        }
                        try {
                            synchronized (this.guard) {
                                try {
                                    List<Object> list2 = this.queue;
                                    this.queue = null;
                                    if (list2 == null) {
                                        this.emitting = false;
                                        return;
                                    } else if (this.child.isUnsubscribed()) {
                                        synchronized (this.guard) {
                                            this.emitting = false;
                                        }
                                        return;
                                    } else {
                                        list = list2;
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    z = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.guard) {
                                                this.emitting = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        z = false;
                    }
                }
            }
        }
    }

    public OperatorWindowWithObservableFactory(Func0<? extends Observable<? extends U>> func0) {
        this.otherFactory = func0;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, this.otherFactory);
        subscriber.add(sourceSubscriber);
        sourceSubscriber.replaceWindow();
        return sourceSubscriber;
    }
}

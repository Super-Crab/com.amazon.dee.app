package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes3.dex */
public final class UnicastSubject<T> extends Subject<T> {
    final boolean delayError;
    volatile boolean disposed;
    volatile boolean done;
    boolean enableOperatorFusion;
    Throwable error;
    final AtomicReference<Runnable> onTerminate;
    final SpscLinkedArrayQueue<T> queue;
    final AtomicReference<Observer<? super T>> downstream = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();
    final BasicIntQueueDisposable<T> wip = new UnicastQueueDisposable();

    /* loaded from: classes3.dex */
    final class UnicastQueueDisposable extends BasicIntQueueDisposable<T> {
        private static final long serialVersionUID = 7926949470189395511L;

        UnicastQueueDisposable() {
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            UnicastSubject.this.queue.clear();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (!UnicastSubject.this.disposed) {
                UnicastSubject unicastSubject = UnicastSubject.this;
                unicastSubject.disposed = true;
                unicastSubject.doTerminate();
                UnicastSubject.this.downstream.lazySet(null);
                if (UnicastSubject.this.wip.getAndIncrement() != 0) {
                    return;
                }
                UnicastSubject.this.downstream.lazySet(null);
                UnicastSubject unicastSubject2 = UnicastSubject.this;
                if (unicastSubject2.enableOperatorFusion) {
                    return;
                }
                unicastSubject2.queue.clear();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return UnicastSubject.this.disposed;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return UnicastSubject.this.queue.isEmpty();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        /* renamed from: poll */
        public T mo10355poll() {
            return UnicastSubject.this.queue.mo10355poll();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int mode) {
            if ((mode & 2) != 0) {
                UnicastSubject.this.enableOperatorFusion = true;
                return 2;
            }
            return 0;
        }
    }

    UnicastSubject(int capacityHint, Runnable onTerminate, boolean delayError) {
        this.queue = new SpscLinkedArrayQueue<>(capacityHint);
        this.onTerminate = new AtomicReference<>(onTerminate);
        this.delayError = delayError;
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create() {
        return new UnicastSubject<>(Observable.bufferSize(), null, true);
    }

    void doTerminate() {
        Runnable runnable = this.onTerminate.get();
        if (runnable == null || !this.onTerminate.compareAndSet(runnable, null)) {
            return;
        }
        runnable.run();
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Observer<? super T> observer = this.downstream.get();
        int i = 1;
        while (observer == null) {
            i = this.wip.addAndGet(-i);
            if (i == 0) {
                return;
            }
            observer = this.downstream.get();
        }
        if (this.enableOperatorFusion) {
            drainFused(observer);
        } else {
            drainNormal(observer);
        }
    }

    void drainFused(Observer<? super T> a) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        int i = 1;
        boolean z = !this.delayError;
        while (!this.disposed) {
            boolean z2 = this.done;
            if (z && z2 && failedFast(spscLinkedArrayQueue, a)) {
                return;
            }
            a.onNext(null);
            if (z2) {
                errorOrComplete(a);
                return;
            }
            i = this.wip.addAndGet(-i);
            if (i == 0) {
                return;
            }
        }
        this.downstream.lazySet(null);
    }

    void drainNormal(Observer<? super T> a) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.queue;
        boolean z = !this.delayError;
        boolean z2 = true;
        int i = 1;
        while (!this.disposed) {
            boolean z3 = this.done;
            Object obj = (T) this.queue.mo10355poll();
            boolean z4 = obj == null;
            if (z3) {
                if (z && z2) {
                    if (failedFast(spscLinkedArrayQueue, a)) {
                        return;
                    }
                    z2 = false;
                }
                if (z4) {
                    errorOrComplete(a);
                    return;
                }
            }
            if (z4) {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            } else {
                a.onNext(obj);
            }
        }
        this.downstream.lazySet(null);
        spscLinkedArrayQueue.clear();
    }

    void errorOrComplete(Observer<? super T> a) {
        this.downstream.lazySet(null);
        Throwable th = this.error;
        if (th != null) {
            a.onError(th);
        } else {
            a.onComplete();
        }
    }

    boolean failedFast(final SimpleQueue<T> q, Observer<? super T> a) {
        Throwable th = this.error;
        if (th != null) {
            this.downstream.lazySet(null);
            q.clear();
            a.onError(th);
            return true;
        }
        return false;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    @Nullable
    public Throwable getThrowable() {
        if (this.done) {
            return this.error;
        }
        return null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasComplete() {
        return this.done && this.error == null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasObservers() {
        return this.downstream.get() != null;
    }

    @Override // io.reactivex.rxjava3.subjects.Subject
    @CheckReturnValue
    public boolean hasThrowable() {
        return this.done && this.error != null;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onComplete() {
        if (this.done || this.disposed) {
            return;
        }
        this.done = true;
        doTerminate();
        drain();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onError(Throwable t) {
        ExceptionHelper.nullCheck(t, "onError called with a null Throwable.");
        if (!this.done && !this.disposed) {
            this.error = t;
            this.done = true;
            doTerminate();
            drain();
            return;
        }
        RxJavaPlugins.onError(t);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onNext(T t) {
        ExceptionHelper.nullCheck(t, "onNext called with a null value.");
        if (this.done || this.disposed) {
            return;
        }
        this.queue.offer(t);
        drain();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public void onSubscribe(Disposable d) {
        if (this.done || this.disposed) {
            d.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    protected void subscribeActual(Observer<? super T> observer) {
        if (!this.once.get() && this.once.compareAndSet(false, true)) {
            observer.onSubscribe(this.wip);
            this.downstream.lazySet(observer);
            if (this.disposed) {
                this.downstream.lazySet(null);
                return;
            } else {
                drain();
                return;
            }
        }
        EmptyDisposable.error(new IllegalStateException("Only a single observer allowed."), observer);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int capacityHint) {
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        return new UnicastSubject<>(capacityHint, null, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int capacityHint, @NonNull Runnable onTerminate) {
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        Objects.requireNonNull(onTerminate, "onTerminate");
        return new UnicastSubject<>(capacityHint, onTerminate, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(int capacityHint, @NonNull Runnable onTerminate, boolean delayError) {
        ObjectHelper.verifyPositive(capacityHint, "capacityHint");
        Objects.requireNonNull(onTerminate, "onTerminate");
        return new UnicastSubject<>(capacityHint, onTerminate, delayError);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastSubject<T> create(boolean delayError) {
        return new UnicastSubject<>(Observable.bufferSize(), null, delayError);
    }
}

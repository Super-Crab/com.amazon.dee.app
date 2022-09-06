package rx.internal.operators;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
/* loaded from: classes5.dex */
public final class BlockingOperatorToFuture {
    private BlockingOperatorToFuture() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Future<T> toFuture(Observable<? extends T> observable) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        final Subscription subscribe = observable.single().subscribe((Subscriber<? super Object>) ((Subscriber<T>) new Subscriber<T>() { // from class: rx.internal.operators.BlockingOperatorToFuture.1
            @Override // rx.Observer
            public void onCompleted() {
                countDownLatch.countDown();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                atomicReference2.compareAndSet(null, th);
                countDownLatch.countDown();
            }

            @Override // rx.Observer
            public void onNext(T t) {
                atomicReference.set(t);
            }
        }));
        return new Future<T>() { // from class: rx.internal.operators.BlockingOperatorToFuture.2
            private volatile boolean cancelled;

            /* JADX WARN: Type inference failed for: r0v6, types: [T, java.lang.Object] */
            private T getValue() throws ExecutionException {
                Throwable th = (Throwable) atomicReference2.get();
                if (th == null) {
                    if (!this.cancelled) {
                        return atomicReference.get();
                    }
                    throw new CancellationException("Subscription unsubscribed");
                }
                throw new ExecutionException("Observable onError", th);
            }

            @Override // java.util.concurrent.Future
            public boolean cancel(boolean z) {
                if (countDownLatch.getCount() > 0) {
                    this.cancelled = true;
                    subscribe.unsubscribe();
                    countDownLatch.countDown();
                    return true;
                }
                return false;
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // java.util.concurrent.Future
            public T get() throws InterruptedException, ExecutionException {
                countDownLatch.await();
                return getValue();
            }

            @Override // java.util.concurrent.Future
            public boolean isCancelled() {
                return this.cancelled;
            }

            @Override // java.util.concurrent.Future
            public boolean isDone() {
                return countDownLatch.getCount() == 0;
            }

            /* JADX WARN: Type inference failed for: r3v4, types: [T, java.lang.Object] */
            @Override // java.util.concurrent.Future
            public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                if (countDownLatch.await(j, timeUnit)) {
                    return getValue();
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Timed out after ");
                outline107.append(timeUnit.toMillis(j));
                outline107.append("ms waiting for underlying Observable.");
                throw new TimeoutException(outline107.toString());
            }
        };
    }
}

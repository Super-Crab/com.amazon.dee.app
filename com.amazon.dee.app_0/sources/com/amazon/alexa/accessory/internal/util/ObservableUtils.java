package com.amazon.alexa.accessory.internal.util;

import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.SingleSubject;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes.dex */
public final class ObservableUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.accessory.internal.util.ObservableUtils$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass1 implements Function<Flowable<Throwable>, Publisher<?>> {
        private volatile int retry;
        final /* synthetic */ long val$delayMillis;
        final /* synthetic */ int val$numberOfRetry;

        AnonymousClass1(int i, long j) {
            this.val$numberOfRetry = i;
            this.val$delayMillis = j;
        }

        public /* synthetic */ Publisher lambda$apply$0$ObservableUtils$1(int i, long j, Throwable th) throws Throwable {
            int i2 = this.retry + 1;
            this.retry = i2;
            return i2 >= i ? Flowable.error(th) : Flowable.timer(j * this.retry, TimeUnit.MILLISECONDS);
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public Publisher<?> mo10358apply(@NonNull Flowable<Throwable> flowable) throws Exception {
            final int i = this.val$numberOfRetry;
            final long j = this.val$delayMillis;
            return flowable.flatMap(new Function() { // from class: com.amazon.alexa.accessory.internal.util.-$$Lambda$ObservableUtils$1$wa0cTiKurGUQSuWgYCryGHfZPto
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return ObservableUtils.AnonymousClass1.this.lambda$apply$0$ObservableUtils$1(i, j, (Throwable) obj);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    public static final class StreamReleasedException extends IllegalStateException {
    }

    private ObservableUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static void dispose(Disposable disposable) {
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        disposable.dispose();
    }

    public static <T> Function<Throwable, Publisher<T>> errorIfNotReleased() {
        return $$Lambda$ObservableUtils$Npal9jdAKUGFbK7Fufrvzy0esKY.INSTANCE;
    }

    public static boolean isDisposed(Disposable disposable) {
        return disposable == null || disposable.isDisposed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Publisher lambda$errorIfNotReleased$0(Throwable th) throws Throwable {
        return th instanceof StreamReleasedException ? Flowable.empty() : Flowable.error(th);
    }

    public static <T> void release(Observer<T> observer) {
        if (observer == null) {
            return;
        }
        observer.onComplete();
    }

    public static Function<Flowable<Throwable>, Publisher<?>> retryBackoff(int i, long j) {
        return new AnonymousClass1(i, j);
    }

    public static void release(Subscriber... subscriberArr) {
        for (Subscriber subscriber : subscriberArr) {
            if (subscriber != null) {
                subscriber.onComplete();
            }
        }
    }

    public static void release(Subscriber subscriber) {
        if (subscriber == null) {
            return;
        }
        subscriber.onComplete();
    }

    public static <T> void release(SingleSubject<T> singleSubject) {
        if (singleSubject == null || singleSubject.hasValue() || singleSubject.hasThrowable()) {
            return;
        }
        singleSubject.onError(new InterruptedException("Observable was released"));
    }
}

package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes5.dex */
public final class CompletableOnSubscribeMergeIterable implements Completable.CompletableOnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeIterable(Iterable<? extends Completable> iterable) {
        this.sources = iterable;
    }

    @Override // rx.functions.Action1
    public void call(final Completable.CompletableSubscriber completableSubscriber) {
        final CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        try {
            Iterator<? extends Completable> it2 = this.sources.iterator();
            if (it2 == null) {
                completableSubscriber.onError(new NullPointerException("The source iterator returned is null"));
                return;
            }
            final AtomicInteger atomicInteger = new AtomicInteger(1);
            final AtomicBoolean atomicBoolean = new AtomicBoolean();
            while (!compositeSubscription.isUnsubscribed()) {
                try {
                    if (!it2.hasNext()) {
                        if (atomicInteger.decrementAndGet() != 0 || !atomicBoolean.compareAndSet(false, true)) {
                            return;
                        }
                        completableSubscriber.onCompleted();
                        return;
                    } else if (compositeSubscription.isUnsubscribed()) {
                        return;
                    } else {
                        try {
                            Completable next = it2.next();
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (next == null) {
                                compositeSubscription.unsubscribe();
                                Throwable nullPointerException = new NullPointerException("A completable source is null");
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    completableSubscriber.onError(nullPointerException);
                                    return;
                                } else {
                                    RxJavaHooks.onError(nullPointerException);
                                    return;
                                }
                            }
                            atomicInteger.getAndIncrement();
                            next.unsafeSubscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeIterable.1
                                @Override // rx.Completable.CompletableSubscriber
                                public void onCompleted() {
                                    if (atomicInteger.decrementAndGet() != 0 || !atomicBoolean.compareAndSet(false, true)) {
                                        return;
                                    }
                                    completableSubscriber.onCompleted();
                                }

                                @Override // rx.Completable.CompletableSubscriber
                                public void onError(Throwable th) {
                                    compositeSubscription.unsubscribe();
                                    if (atomicBoolean.compareAndSet(false, true)) {
                                        completableSubscriber.onError(th);
                                    } else {
                                        RxJavaHooks.onError(th);
                                    }
                                }

                                @Override // rx.Completable.CompletableSubscriber
                                public void onSubscribe(Subscription subscription) {
                                    compositeSubscription.add(subscription);
                                }
                            });
                        } catch (Throwable th) {
                            compositeSubscription.unsubscribe();
                            if (atomicBoolean.compareAndSet(false, true)) {
                                completableSubscriber.onError(th);
                                return;
                            } else {
                                RxJavaHooks.onError(th);
                                return;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    compositeSubscription.unsubscribe();
                    if (atomicBoolean.compareAndSet(false, true)) {
                        completableSubscriber.onError(th2);
                        return;
                    } else {
                        RxJavaHooks.onError(th2);
                        return;
                    }
                }
            }
        } catch (Throwable th3) {
            completableSubscriber.onError(th3);
        }
    }
}

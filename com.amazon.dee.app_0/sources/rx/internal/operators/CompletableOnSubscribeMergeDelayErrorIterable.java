package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.Subscription;
import rx.internal.util.unsafe.MpscLinkedQueue;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes5.dex */
public final class CompletableOnSubscribeMergeDelayErrorIterable implements Completable.CompletableOnSubscribe {
    final Iterable<? extends Completable> sources;

    public CompletableOnSubscribeMergeDelayErrorIterable(Iterable<? extends Completable> iterable) {
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
            final MpscLinkedQueue mpscLinkedQueue = new MpscLinkedQueue();
            while (!compositeSubscription.isUnsubscribed()) {
                try {
                    if (!it2.hasNext()) {
                        if (atomicInteger.decrementAndGet() != 0) {
                            return;
                        }
                        if (mpscLinkedQueue.isEmpty()) {
                            completableSubscriber.onCompleted();
                            return;
                        } else {
                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                            return;
                        }
                    } else if (compositeSubscription.isUnsubscribed()) {
                        return;
                    } else {
                        try {
                            Completable next = it2.next();
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (next == null) {
                                mpscLinkedQueue.offer(new NullPointerException("A completable source is null"));
                                if (atomicInteger.decrementAndGet() != 0) {
                                    return;
                                }
                                if (mpscLinkedQueue.isEmpty()) {
                                    completableSubscriber.onCompleted();
                                    return;
                                } else {
                                    completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                                    return;
                                }
                            }
                            atomicInteger.getAndIncrement();
                            next.unsafeSubscribe(new Completable.CompletableSubscriber() { // from class: rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable.1
                                @Override // rx.Completable.CompletableSubscriber
                                public void onCompleted() {
                                    tryTerminate();
                                }

                                @Override // rx.Completable.CompletableSubscriber
                                public void onError(Throwable th) {
                                    mpscLinkedQueue.offer(th);
                                    tryTerminate();
                                }

                                @Override // rx.Completable.CompletableSubscriber
                                public void onSubscribe(Subscription subscription) {
                                    compositeSubscription.add(subscription);
                                }

                                void tryTerminate() {
                                    if (atomicInteger.decrementAndGet() == 0) {
                                        if (mpscLinkedQueue.isEmpty()) {
                                            completableSubscriber.onCompleted();
                                        } else {
                                            completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                                        }
                                    }
                                }
                            });
                        } catch (Throwable th) {
                            mpscLinkedQueue.offer(th);
                            if (atomicInteger.decrementAndGet() != 0) {
                                return;
                            }
                            if (mpscLinkedQueue.isEmpty()) {
                                completableSubscriber.onCompleted();
                                return;
                            } else {
                                completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                                return;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    mpscLinkedQueue.offer(th2);
                    if (atomicInteger.decrementAndGet() != 0) {
                        return;
                    }
                    if (mpscLinkedQueue.isEmpty()) {
                        completableSubscriber.onCompleted();
                        return;
                    } else {
                        completableSubscriber.onError(CompletableOnSubscribeMerge.collectErrors(mpscLinkedQueue));
                        return;
                    }
                }
            }
        } catch (Throwable th3) {
            completableSubscriber.onError(th3);
        }
    }
}

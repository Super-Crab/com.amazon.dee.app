package io.reactivex.rxjava3.internal.subscribers;
/* loaded from: classes3.dex */
public final class BlockingLastSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable t) {
        this.value = null;
        this.error = t;
        countDown();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        this.value = t;
    }
}

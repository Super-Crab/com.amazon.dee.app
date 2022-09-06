package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
abstract class FlowableStageSubscriber<T> extends CompletableFuture<T> implements FlowableSubscriber<T> {
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    T value;

    protected abstract void afterSubscribe(Subscription s);

    @Override // java.util.concurrent.CompletableFuture, java.util.concurrent.Future
    public final boolean cancel(boolean mayInterruptIfRunning) {
        cancelUpstream();
        return super.cancel(mayInterruptIfRunning);
    }

    protected final void cancelUpstream() {
        SubscriptionHelper.cancel(this.upstream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clear() {
        this.value = null;
        this.upstream.lazySet(SubscriptionHelper.CANCELLED);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean complete(T value) {
        cancelUpstream();
        return super.complete(value);
    }

    @Override // java.util.concurrent.CompletableFuture
    public final boolean completeExceptionally(Throwable ex) {
        cancelUpstream();
        return super.completeExceptionally(ex);
    }

    @Override // org.reactivestreams.Subscriber
    public final void onError(Throwable t) {
        clear();
        if (!completeExceptionally(t)) {
            RxJavaPlugins.onError(t);
        }
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(@NonNull Subscription s) {
        if (SubscriptionHelper.setOnce(this.upstream, s)) {
            afterSubscribe(s);
        }
    }
}

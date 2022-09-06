package rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.Subscription;
import rx.annotations.Experimental;
import rx.plugins.RxJavaHooks;
@Experimental
/* loaded from: classes5.dex */
public abstract class AsyncCompletableSubscriber implements Completable.CompletableSubscriber, Subscription {
    static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private final AtomicReference<Subscription> upstream = new AtomicReference<>();

    /* loaded from: classes5.dex */
    static final class Unsubscribed implements Subscription {
        Unsubscribed() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }
    }

    protected final void clear() {
        this.upstream.set(UNSUBSCRIBED);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.upstream.get() == UNSUBSCRIBED;
    }

    protected void onStart() {
    }

    @Override // rx.Completable.CompletableSubscriber
    public final void onSubscribe(Subscription subscription) {
        if (!this.upstream.compareAndSet(null, subscription)) {
            subscription.unsubscribe();
            if (this.upstream.get() == UNSUBSCRIBED) {
                return;
            }
            RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
            return;
        }
        onStart();
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        Subscription andSet;
        Subscription subscription = this.upstream.get();
        Unsubscribed unsubscribed = UNSUBSCRIBED;
        if (subscription == unsubscribed || (andSet = this.upstream.getAndSet(unsubscribed)) == null || andSet == UNSUBSCRIBED) {
            return;
        }
        andSet.unsubscribe();
    }
}

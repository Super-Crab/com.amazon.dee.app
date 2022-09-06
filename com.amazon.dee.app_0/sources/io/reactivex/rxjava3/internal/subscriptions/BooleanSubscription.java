package io.reactivex.rxjava3.internal.subscriptions;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public final class BooleanSubscription extends AtomicBoolean implements Subscription {
    private static final long serialVersionUID = -8127758972444290902L;

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long n) {
        SubscriptionHelper.validate(n);
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("BooleanSubscription(cancelled=");
        outline107.append(get());
        outline107.append(")");
        return outline107.toString();
    }
}

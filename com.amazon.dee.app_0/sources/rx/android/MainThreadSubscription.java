package rx.android;

import android.os.Looper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
/* loaded from: classes5.dex */
public abstract class MainThreadSubscription implements Subscription {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    public static void verifyMainThread() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Expected to be called on the main thread but was ");
        outline107.append(Thread.currentThread().getName());
        throw new IllegalStateException(outline107.toString());
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.unsubscribed.get();
    }

    protected abstract void onUnsubscribe();

    @Override // rx.Subscription
    public final void unsubscribe() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onUnsubscribe();
            } else {
                AndroidSchedulers.mainThread().createWorker().schedule(new Action0() { // from class: rx.android.MainThreadSubscription.1
                    @Override // rx.functions.Action0
                    public void call() {
                        MainThreadSubscription.this.onUnsubscribe();
                    }
                });
            }
        }
    }
}

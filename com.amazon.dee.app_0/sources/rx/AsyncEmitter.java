package rx;

import rx.annotations.Experimental;
@Experimental
/* loaded from: classes5.dex */
public interface AsyncEmitter<T> extends Observer<T> {

    /* loaded from: classes5.dex */
    public enum BackpressureMode {
        NONE,
        ERROR,
        BUFFER,
        DROP,
        LATEST
    }

    /* loaded from: classes5.dex */
    public interface Cancellable {
        void cancel() throws Exception;
    }

    long requested();

    void setCancellation(Cancellable cancellable);

    void setSubscription(Subscription subscription);
}

package io.reactivex.rxjava3.internal.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.Serializable;
import java.util.Objects;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes3.dex */
public enum NotificationLite {
    COMPLETE;

    /* loaded from: classes3.dex */
    static final class DisposableNotification implements Serializable {
        private static final long serialVersionUID = -7482590109178395495L;
        final Disposable upstream;

        DisposableNotification(Disposable d) {
            this.upstream = d;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationLite.Disposable[");
            outline107.append(this.upstream);
            outline107.append("]");
            return outline107.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class ErrorNotification implements Serializable {
        private static final long serialVersionUID = -8759979445933046293L;
        final Throwable e;

        ErrorNotification(Throwable e) {
            this.e = e;
        }

        public boolean equals(Object obj) {
            if (obj instanceof ErrorNotification) {
                return Objects.equals(this.e, ((ErrorNotification) obj).e);
            }
            return false;
        }

        public int hashCode() {
            return this.e.hashCode();
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationLite.Error[");
            outline107.append(this.e);
            outline107.append("]");
            return outline107.toString();
        }
    }

    /* loaded from: classes3.dex */
    static final class SubscriptionNotification implements Serializable {
        private static final long serialVersionUID = -1322257508628817540L;
        final Subscription upstream;

        SubscriptionNotification(Subscription s) {
            this.upstream = s;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NotificationLite.Subscription[");
            outline107.append(this.upstream);
            outline107.append("]");
            return outline107.toString();
        }
    }

    public static <T> boolean accept(Object o, Subscriber<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).e);
            return true;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object o, Subscriber<? super T> s) {
        if (o == COMPLETE) {
            s.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            s.onError(((ErrorNotification) o).e);
            return true;
        } else if (o instanceof SubscriptionNotification) {
            s.onSubscribe(((SubscriptionNotification) o).upstream);
            return false;
        } else {
            s.onNext(o);
            return false;
        }
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object disposable(Disposable d) {
        return new DisposableNotification(d);
    }

    public static Object error(Throwable e) {
        return new ErrorNotification(e);
    }

    public static Disposable getDisposable(Object o) {
        return ((DisposableNotification) o).upstream;
    }

    public static Throwable getError(Object o) {
        return ((ErrorNotification) o).e;
    }

    public static Subscription getSubscription(Object o) {
        return ((SubscriptionNotification) o).upstream;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object o) {
        return o;
    }

    public static boolean isComplete(Object o) {
        return o == COMPLETE;
    }

    public static boolean isDisposable(Object o) {
        return o instanceof DisposableNotification;
    }

    public static boolean isError(Object o) {
        return o instanceof ErrorNotification;
    }

    public static boolean isSubscription(Object o) {
        return o instanceof SubscriptionNotification;
    }

    public static <T> Object next(T value) {
        return value;
    }

    public static Object subscription(Subscription s) {
        return new SubscriptionNotification(s);
    }

    @Override // java.lang.Enum
    public String toString() {
        return "NotificationLite.Complete";
    }

    public static <T> boolean accept(Object o, Observer<? super T> observer) {
        if (o == COMPLETE) {
            observer.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) o).e);
            return true;
        } else {
            observer.onNext(o);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object o, Observer<? super T> observer) {
        if (o == COMPLETE) {
            observer.onComplete();
            return true;
        } else if (o instanceof ErrorNotification) {
            observer.onError(((ErrorNotification) o).e);
            return true;
        } else if (o instanceof DisposableNotification) {
            observer.onSubscribe(((DisposableNotification) o).upstream);
            return false;
        } else {
            observer.onNext(o);
            return false;
        }
    }
}

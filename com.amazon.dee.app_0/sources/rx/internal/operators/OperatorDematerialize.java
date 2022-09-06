package rx.internal.operators;

import rx.Notification;
import rx.Observable;
import rx.Subscriber;
/* loaded from: classes5.dex */
public final class OperatorDematerialize<T> implements Observable.Operator<T, Notification<T>> {

    /* renamed from: rx.internal.operators.OperatorDematerialize$2  reason: invalid class name */
    /* loaded from: classes5.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$rx$Notification$Kind = new int[Notification.Kind.values().length];

        static {
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnNext.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnError.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$rx$Notification$Kind[Notification.Kind.OnCompleted.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static final class Holder {
        static final OperatorDematerialize<Object> INSTANCE = new OperatorDematerialize<>();

        Holder() {
        }
    }

    OperatorDematerialize() {
    }

    public static OperatorDematerialize instance() {
        return Holder.INSTANCE;
    }

    @Override // rx.functions.Func1
    /* renamed from: call */
    public /* bridge */ /* synthetic */ Object mo13102call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Notification<T>> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<Notification<T>>(subscriber) { // from class: rx.internal.operators.OperatorDematerialize.1
            boolean terminated;

            @Override // rx.Observer
            public void onCompleted() {
                if (!this.terminated) {
                    this.terminated = true;
                    subscriber.onCompleted();
                }
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                if (!this.terminated) {
                    this.terminated = true;
                    subscriber.onError(th);
                }
            }

            @Override // rx.Observer
            public /* bridge */ /* synthetic */ void onNext(Object obj) {
                onNext((Notification) ((Notification) obj));
            }

            public void onNext(Notification<T> notification) {
                int ordinal = notification.getKind().ordinal();
                if (ordinal == 0) {
                    if (this.terminated) {
                        return;
                    }
                    subscriber.onNext(notification.getValue());
                } else if (ordinal == 1) {
                    onError(notification.getThrowable());
                } else if (ordinal != 2) {
                    onError(new IllegalArgumentException("Unsupported notification type: " + notification));
                } else {
                    onCompleted();
                }
            }
        };
    }
}

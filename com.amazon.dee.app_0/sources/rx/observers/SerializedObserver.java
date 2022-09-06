package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;
/* loaded from: classes5.dex */
public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private final NotificationLite<T> nl = NotificationLite.instance();
    private FastList queue;
    private volatile boolean terminated;

    /* loaded from: classes5.dex */
    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object obj) {
            int i = this.size;
            Object[] objArr = this.array;
            if (objArr == null) {
                objArr = new Object[16];
                this.array = objArr;
            } else if (i == objArr.length) {
                Object[] objArr2 = new Object[(i >> 2) + i];
                System.arraycopy(objArr, 0, objArr2, 0, i);
                this.array = objArr2;
                objArr = objArr2;
            }
            objArr[i] = obj;
            this.size = i + 1;
        }
    }

    public SerializedObserver(Observer<? super T> observer) {
        this.actual = observer;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
            this.terminated = true;
            if (this.emitting) {
                FastList fastList = this.queue;
                if (fastList == null) {
                    fastList = new FastList();
                    this.queue = fastList;
                }
                fastList.add(this.nl.completed());
                return;
            }
            this.emitting = true;
            this.actual.onCompleted();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            if (this.terminated) {
                return;
            }
            this.terminated = true;
            if (this.emitting) {
                FastList fastList = this.queue;
                if (fastList == null) {
                    fastList = new FastList();
                    this.queue = fastList;
                }
                fastList.add(this.nl.error(th));
                return;
            }
            this.emitting = true;
            this.actual.onError(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x002f, code lost:
        continue;
     */
    @Override // rx.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onNext(T r8) {
        /*
            r7 = this;
            boolean r0 = r7.terminated
            if (r0 == 0) goto L5
            return
        L5:
            monitor-enter(r7)
            boolean r0 = r7.terminated     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto Lc
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L73
            return
        Lc:
            boolean r0 = r7.emitting     // Catch: java.lang.Throwable -> L73
            if (r0 == 0) goto L26
            rx.observers.SerializedObserver$FastList r0 = r7.queue     // Catch: java.lang.Throwable -> L73
            if (r0 != 0) goto L1b
            rx.observers.SerializedObserver$FastList r0 = new rx.observers.SerializedObserver$FastList     // Catch: java.lang.Throwable -> L73
            r0.<init>()     // Catch: java.lang.Throwable -> L73
            r7.queue = r0     // Catch: java.lang.Throwable -> L73
        L1b:
            rx.internal.operators.NotificationLite<T> r1 = r7.nl     // Catch: java.lang.Throwable -> L73
            java.lang.Object r8 = r1.next(r8)     // Catch: java.lang.Throwable -> L73
            r0.add(r8)     // Catch: java.lang.Throwable -> L73
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L73
            return
        L26:
            r0 = 1
            r7.emitting = r0     // Catch: java.lang.Throwable -> L73
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L73
            rx.Observer<? super T> r1 = r7.actual     // Catch: java.lang.Throwable -> L6a
            r1.onNext(r8)     // Catch: java.lang.Throwable -> L6a
        L2f:
            monitor-enter(r7)
            rx.observers.SerializedObserver$FastList r1 = r7.queue     // Catch: java.lang.Throwable -> L67
            r2 = 0
            if (r1 != 0) goto L39
            r7.emitting = r2     // Catch: java.lang.Throwable -> L67
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L67
            return
        L39:
            r3 = 0
            r7.queue = r3     // Catch: java.lang.Throwable -> L67
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L67
            java.lang.Object[] r1 = r1.array
            int r3 = r1.length
        L40:
            if (r2 >= r3) goto L2f
            r4 = r1[r2]
            if (r4 != 0) goto L47
            goto L2f
        L47:
            rx.internal.operators.NotificationLite<T> r5 = r7.nl     // Catch: java.lang.Throwable -> L57
            rx.Observer<? super T> r6 = r7.actual     // Catch: java.lang.Throwable -> L57
            boolean r4 = r5.accept(r6, r4)     // Catch: java.lang.Throwable -> L57
            if (r4 == 0) goto L54
            r7.terminated = r0     // Catch: java.lang.Throwable -> L57
            return
        L54:
            int r2 = r2 + 1
            goto L40
        L57:
            r1 = move-exception
            r7.terminated = r0
            rx.exceptions.Exceptions.throwIfFatal(r1)
            rx.Observer<? super T> r0 = r7.actual
            java.lang.Throwable r8 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r8)
            r0.onError(r8)
            return
        L67:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L67
            throw r8
        L6a:
            r1 = move-exception
            r7.terminated = r0
            rx.Observer<? super T> r0 = r7.actual
            rx.exceptions.Exceptions.throwOrReport(r1, r0, r8)
            return
        L73:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L73
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }
}

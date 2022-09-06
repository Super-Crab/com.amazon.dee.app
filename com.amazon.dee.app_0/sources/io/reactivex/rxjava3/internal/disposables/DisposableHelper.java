package io.reactivex.rxjava3.internal.disposables;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes3.dex */
public enum DisposableHelper implements Disposable {
    DISPOSED;

    public static boolean dispose(AtomicReference<Disposable> field) {
        Disposable andSet;
        Disposable disposable = field.get();
        DisposableHelper disposableHelper = DISPOSED;
        if (disposable == disposableHelper || (andSet = field.getAndSet(disposableHelper)) == disposableHelper) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean isDisposed(Disposable d) {
        return d == DISPOSED;
    }

    public static boolean replace(AtomicReference<Disposable> field, Disposable d) {
        Disposable disposable;
        do {
            disposable = field.get();
            if (disposable == DISPOSED) {
                if (d == null) {
                    return false;
                }
                d.dispose();
                return false;
            }
        } while (!field.compareAndSet(disposable, d));
        return true;
    }

    public static void reportDisposableSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Disposable already set!"));
    }

    public static boolean set(AtomicReference<Disposable> field, Disposable d) {
        Disposable disposable;
        do {
            disposable = field.get();
            if (disposable == DISPOSED) {
                if (d == null) {
                    return false;
                }
                d.dispose();
                return false;
            }
        } while (!field.compareAndSet(disposable, d));
        if (disposable != null) {
            disposable.dispose();
            return true;
        }
        return true;
    }

    public static boolean setOnce(AtomicReference<Disposable> field, Disposable d) {
        Objects.requireNonNull(d, "d is null");
        if (!field.compareAndSet(null, d)) {
            d.dispose();
            if (field.get() == DISPOSED) {
                return false;
            }
            reportDisposableSet();
            return false;
        }
        return true;
    }

    public static boolean trySet(AtomicReference<Disposable> field, Disposable d) {
        if (!field.compareAndSet(null, d)) {
            if (field.get() != DISPOSED) {
                return false;
            }
            d.dispose();
            return false;
        }
        return true;
    }

    public static boolean validate(Disposable current, Disposable next) {
        if (next == null) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        } else if (current == null) {
            return true;
        } else {
            next.dispose();
            reportDisposableSet();
            return false;
        }
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return true;
    }
}

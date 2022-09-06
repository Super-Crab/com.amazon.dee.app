package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeToObservable;
import io.reactivex.rxjava3.internal.operators.single.SingleToObservable;
import java.util.Objects;
/* loaded from: classes3.dex */
final class ScalarXMapZHelper {
    private ScalarXMapZHelper() {
        throw new IllegalStateException("No instances!");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> boolean tryAsCompletable(Object source, Function<? super T, ? extends CompletableSource> mapper, CompletableObserver observer) {
        if (source instanceof Supplier) {
            CompletableSource completableSource = null;
            try {
                Object obj = (Object) ((Supplier) source).mo10365get();
                if (obj != 0) {
                    completableSource = (CompletableSource) Objects.requireNonNull(mapper.mo10358apply(obj), "The mapper returned a null CompletableSource");
                }
                if (completableSource == null) {
                    EmptyDisposable.complete(observer);
                } else {
                    completableSource.subscribe(observer);
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R> boolean tryAsMaybe(Object source, Function<? super T, ? extends MaybeSource<? extends R>> mapper, Observer<? super R> observer) {
        if (source instanceof Supplier) {
            MaybeSource maybeSource = null;
            try {
                Object obj = (Object) ((Supplier) source).mo10365get();
                if (obj != 0) {
                    maybeSource = (MaybeSource) Objects.requireNonNull(mapper.mo10358apply(obj), "The mapper returned a null MaybeSource");
                }
                if (maybeSource == null) {
                    EmptyDisposable.complete(observer);
                } else {
                    maybeSource.subscribe(MaybeToObservable.create(observer));
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, R> boolean tryAsSingle(Object source, Function<? super T, ? extends SingleSource<? extends R>> mapper, Observer<? super R> observer) {
        if (source instanceof Supplier) {
            SingleSource singleSource = null;
            try {
                Object obj = (Object) ((Supplier) source).mo10365get();
                if (obj != 0) {
                    singleSource = (SingleSource) Objects.requireNonNull(mapper.mo10358apply(obj), "The mapper returned a null SingleSource");
                }
                if (singleSource == null) {
                    EmptyDisposable.complete(observer);
                } else {
                    singleSource.subscribe(SingleToObservable.create(observer));
                }
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return true;
            }
        }
        return false;
    }
}

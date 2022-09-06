package io.reactivex.rxjava3.disposables;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
/* loaded from: classes3.dex */
final class AutoCloseableDisposable extends ReferenceDisposable<AutoCloseable> {
    private static final long serialVersionUID = -6646144244598696847L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoCloseableDisposable(AutoCloseable value) {
        super(value);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AutoCloseableDisposable(disposed=");
        outline107.append(isDisposed());
        outline107.append(", ");
        outline107.append(get());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(@NonNull AutoCloseable value) {
        try {
            value.close();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }
}

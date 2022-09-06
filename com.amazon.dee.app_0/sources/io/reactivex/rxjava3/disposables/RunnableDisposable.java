package io.reactivex.rxjava3.disposables;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableDisposable(Runnable value) {
        super(value);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("RunnableDisposable(disposed=");
        outline107.append(isDisposed());
        outline107.append(", ");
        outline107.append(get());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(@NonNull Runnable value) {
        value.run();
    }
}

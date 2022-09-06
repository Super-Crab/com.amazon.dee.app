package io.reactivex.rxjava3.disposables;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ActionDisposable extends ReferenceDisposable<Action> {
    private static final long serialVersionUID = -8219729196779211169L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionDisposable(Action value) {
        super(value);
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ActionDisposable(disposed=");
        outline107.append(isDisposed());
        outline107.append(", ");
        outline107.append(get());
        outline107.append(")");
        return outline107.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(@NonNull Action value) {
        try {
            value.run();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }
}

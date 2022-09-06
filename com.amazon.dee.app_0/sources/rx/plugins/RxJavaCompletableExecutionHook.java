package rx.plugins;

import rx.Completable;
import rx.annotations.Experimental;
@Experimental
/* loaded from: classes5.dex */
public abstract class RxJavaCompletableExecutionHook {
    @Deprecated
    public Completable.CompletableOnSubscribe onCreate(Completable.CompletableOnSubscribe completableOnSubscribe) {
        return completableOnSubscribe;
    }

    @Deprecated
    public Completable.CompletableOperator onLift(Completable.CompletableOperator completableOperator) {
        return completableOperator;
    }

    @Deprecated
    public Throwable onSubscribeError(Throwable th) {
        return th;
    }

    @Deprecated
    public Completable.CompletableOnSubscribe onSubscribeStart(Completable completable, Completable.CompletableOnSubscribe completableOnSubscribe) {
        return completableOnSubscribe;
    }
}

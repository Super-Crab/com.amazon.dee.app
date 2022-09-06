package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.repositories.Producer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
/* loaded from: classes.dex */
public final class SingleResult {
    private SingleResult() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Single<T> create(final ResultCallable<T> resultCallable) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessory.internal.repositories.-$$Lambda$SingleResult$SXsDiwzoh2cxyW_H8K_PZq9yq4A
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                ResultCallable.this.call(new Producer.Result<T>() { // from class: com.amazon.alexa.accessory.internal.repositories.SingleResult.1
                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void complete(T t) {
                        if (SingleEmitter.this.isDisposed()) {
                            return;
                        }
                        SingleEmitter.this.onSuccess(t);
                    }

                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void completeWithError(Throwable th) {
                        if (SingleEmitter.this.isDisposed()) {
                            return;
                        }
                        SingleEmitter.this.onError(th);
                    }
                });
            }
        });
    }
}

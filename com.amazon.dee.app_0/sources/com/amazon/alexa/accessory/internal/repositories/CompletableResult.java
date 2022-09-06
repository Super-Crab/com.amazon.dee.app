package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.repositories.Producer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
/* loaded from: classes.dex */
public final class CompletableResult {

    /* loaded from: classes.dex */
    public static final class Value {
        private Value() {
        }

        public static Value complete() {
            return new Value();
        }
    }

    private CompletableResult() {
        throw new IllegalStateException("No instances!");
    }

    public static Completable create(final ResultCallable<Value> resultCallable) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amazon.alexa.accessory.internal.repositories.-$$Lambda$CompletableResult$Q5yAd1X6YpFnEg-01JOUfYnvRNs
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) {
                ResultCallable.this.call(new Producer.Result<CompletableResult.Value>() { // from class: com.amazon.alexa.accessory.internal.repositories.CompletableResult.1
                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void completeWithError(Throwable th) {
                        CompletableEmitter.this.onError(th);
                    }

                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void complete(Value value) {
                        CompletableEmitter.this.onComplete();
                    }
                });
            }
        });
    }
}

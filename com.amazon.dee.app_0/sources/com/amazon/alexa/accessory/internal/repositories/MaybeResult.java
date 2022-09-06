package com.amazon.alexa.accessory.internal.repositories;

import com.amazon.alexa.accessory.internal.repositories.MaybeResult;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.repositories.Producer;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeEmitter;
import io.reactivex.rxjava3.core.MaybeOnSubscribe;
/* loaded from: classes.dex */
public final class MaybeResult {

    /* loaded from: classes.dex */
    public static final class MaybeValue<T> {
        private final T value;

        private MaybeValue(T t) {
            this.value = t;
        }

        public static <T> MaybeValue<T> empty() {
            return new MaybeValue<>(null);
        }

        public static <T> MaybeValue<T> of(T t) {
            Preconditions.notNull(t, "value");
            return new MaybeValue<>(t);
        }

        public T getValue() {
            return this.value;
        }

        public boolean hasValue() {
            return this.value != null;
        }
    }

    private MaybeResult() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Maybe<T> create(final ResultCallable<MaybeValue<T>> resultCallable) {
        return Maybe.create(new MaybeOnSubscribe() { // from class: com.amazon.alexa.accessory.internal.repositories.-$$Lambda$MaybeResult$SO5W0wNzlz7uMwT0gusEheXDBEg
            @Override // io.reactivex.rxjava3.core.MaybeOnSubscribe
            public final void subscribe(MaybeEmitter maybeEmitter) {
                ResultCallable.this.call(new Producer.Result<MaybeResult.MaybeValue<T>>() { // from class: com.amazon.alexa.accessory.internal.repositories.MaybeResult.1
                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void completeWithError(Throwable th) {
                        MaybeEmitter.this.onError(th);
                    }

                    @Override // com.amazon.alexa.accessory.repositories.Producer.Result
                    public void complete(MaybeValue<T> maybeValue) {
                        if (maybeValue.hasValue()) {
                            MaybeEmitter.this.onSuccess(maybeValue.getValue());
                        } else {
                            MaybeEmitter.this.onComplete();
                        }
                    }
                });
            }
        });
    }
}

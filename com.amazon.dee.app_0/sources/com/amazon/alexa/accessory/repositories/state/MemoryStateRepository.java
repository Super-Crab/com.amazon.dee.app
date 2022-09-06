package com.amazon.alexa.accessory.repositories.state;

import android.util.SparseArray;
import com.amazon.alexa.accessory.internal.repositories.BaseProducer;
import com.amazon.alexa.accessory.internal.repositories.CompletableResult;
import com.amazon.alexa.accessory.internal.repositories.ResultCallable;
import com.amazon.alexa.accessory.internal.repositories.SingleResult;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.LoggerUtils;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.Producer;
import com.amazon.alexa.accessory.repositories.state.StateProducer;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import io.reactivex.rxjava3.processors.PublishProcessor;
import java.util.Arrays;
/* loaded from: classes6.dex */
public final class MemoryStateRepository extends BaseProducer<StateProducer.ActionHandler> implements StateRepository, StateProvider, StateProducer {
    private final SparseArray<StateOuterClass.State> states = new SparseArray<>();
    private final FlowableProcessor<StateOuterClass.State> statePublisher = PublishProcessor.create().toSerialized();

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheStateValue(StateOuterClass.State state) {
        Preconditions.notNull(state, "state");
        Logger.d("Device State added to state repository: %s", LoggerUtils.stateToString(state));
        synchronized (this.states) {
            this.states.put(state.getFeature(), state);
        }
    }

    private Maybe<StateOuterClass.State> getCached(final StateFeature stateFeature) {
        return Maybe.defer(new Supplier() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryStateRepository$Tmt86Dot39mtOWHdKAuBewYS_fI
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public final Object mo10365get() {
                return MemoryStateRepository.this.lambda$getCached$3$MemoryStateRepository(stateFeature);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$query$2(StateFeature stateFeature, StateOuterClass.State state) throws Throwable {
        return stateFeature.toInteger() == state.getFeature();
    }

    public /* synthetic */ MaybeSource lambda$getCached$3$MemoryStateRepository(StateFeature stateFeature) throws Throwable {
        Maybe empty;
        if (stateFeature.toInteger() == StateFeature.WAKEWORD_DETECTION_ENABLED.toInteger()) {
            return Maybe.empty();
        }
        synchronized (this.states) {
            StateOuterClass.State state = this.states.get(stateFeature.toInteger());
            empty = state == null ? Maybe.empty() : Maybe.just(state);
        }
        return empty;
    }

    public /* synthetic */ void lambda$query$1$MemoryStateRepository(StateFeature stateFeature, Producer.Result result) {
        getHandler().handleGetState(stateFeature, result);
    }

    public /* synthetic */ void lambda$set$0$MemoryStateRepository(StateOuterClass.State state, Producer.Result result) {
        getHandler().handleSetState(state, result);
    }

    @Override // com.amazon.alexa.accessory.repositories.state.StateProvider
    public void provideState(StateOuterClass.State state) {
        synchronized (this.states) {
            cacheStateValue(state);
            this.statePublisher.onNext(state);
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.state.StateRepository
    public Flowable<StateOuterClass.State> query(final StateFeature stateFeature) {
        Preconditions.notNull(stateFeature, "feature");
        return Flowable.concatEager(Arrays.asList(getCached(stateFeature).switchIfEmpty(SingleResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryStateRepository$ryD2W3nUeZVq7kh9uDpUbQ6QL9I
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryStateRepository.this.lambda$query$1$MemoryStateRepository(stateFeature, result);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryStateRepository$_DLh8xTDrqzFk3nk5HSCfz_c0wQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MemoryStateRepository.this.cacheStateValue((StateOuterClass.State) obj);
            }
        }).toMaybe()).toFlowable(), this.statePublisher.filter(new Predicate() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryStateRepository$wvxL-knjj7w__z2cYl4WwJMj97o
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return MemoryStateRepository.lambda$query$2(StateFeature.this, (StateOuterClass.State) obj);
            }
        }))).onErrorResumeNext(ObservableUtils.errorIfNotReleased()).distinctUntilChanged();
    }

    public void release() {
        synchronized (this.states) {
            if (!this.statePublisher.hasComplete() && !this.statePublisher.hasThrowable()) {
                this.statePublisher.onError(new ObservableUtils.StreamReleasedException());
            }
        }
    }

    @Override // com.amazon.alexa.accessory.repositories.state.StateRepository
    public Completable set(final StateOuterClass.State state) {
        Preconditions.notNull(state, "state");
        return CompletableResult.create(new ResultCallable() { // from class: com.amazon.alexa.accessory.repositories.state.-$$Lambda$MemoryStateRepository$IetCKnRU0e9R_usm8iuHv5uuRus
            @Override // com.amazon.alexa.accessory.internal.repositories.ResultCallable
            public final void call(Producer.Result result) {
                MemoryStateRepository.this.lambda$set$0$MemoryStateRepository(state, result);
            }
        });
    }
}

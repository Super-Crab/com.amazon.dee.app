package androidx.room.rxjava3;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class RxRoom {
    @NonNull
    public static final Object NOTHING = new Object();

    private RxRoom() {
    }

    @NonNull
    public static Flowable<Object> createFlowable(@NonNull final RoomDatabase roomDatabase, @NonNull final String... strArr) {
        return Flowable.create(new FlowableOnSubscribe() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$W60v4I_r7gppEeohbhuDi73SLIY
            @Override // io.reactivex.rxjava3.core.FlowableOnSubscribe
            public final void subscribe(FlowableEmitter flowableEmitter) {
                RxRoom.lambda$createFlowable$1(strArr, roomDatabase, flowableEmitter);
            }
        }, BackpressureStrategy.LATEST);
    }

    @NonNull
    public static Observable<Object> createObservable(@NonNull final RoomDatabase roomDatabase, @NonNull final String... strArr) {
        return Observable.create(new ObservableOnSubscribe() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$Tk0BPH97c2QPSUqHOfdYtvDP4ts
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                RxRoom.lambda$createObservable$4(strArr, roomDatabase, observableEmitter);
            }
        });
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static <T> Single<T> createSingle(@NonNull final Callable<T> callable) {
        return Single.create(new SingleOnSubscribe() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$76uKsy7HwXfRzvGBqXgJU6gL1uQ
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                RxRoom.lambda$createSingle$6(callable, singleEmitter);
            }
        });
    }

    private static Executor getExecutor(@NonNull RoomDatabase roomDatabase, boolean z) {
        if (z) {
            return roomDatabase.getTransactionExecutor();
        }
        return roomDatabase.getQueryExecutor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createFlowable$1(String[] strArr, final RoomDatabase roomDatabase, final FlowableEmitter flowableEmitter) throws Throwable {
        final InvalidationTracker.Observer observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.rxjava3.RxRoom.1
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                if (!flowableEmitter.isCancelled()) {
                    flowableEmitter.onNext(RxRoom.NOTHING);
                }
            }
        };
        if (!flowableEmitter.isCancelled()) {
            roomDatabase.getInvalidationTracker().addObserver(observer);
            flowableEmitter.setDisposable(Disposable.fromAction(new Action() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$tByyYD1-qvdkSfip77bPQE_SM8k
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    RoomDatabase.this.getInvalidationTracker().removeObserver(observer);
                }
            }));
        }
        if (!flowableEmitter.isCancelled()) {
            flowableEmitter.onNext(NOTHING);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$createFlowable$2(Maybe maybe, Object obj) throws Throwable {
        return maybe;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createObservable$4(String[] strArr, final RoomDatabase roomDatabase, final ObservableEmitter observableEmitter) throws Throwable {
        final InvalidationTracker.Observer observer = new InvalidationTracker.Observer(strArr) { // from class: androidx.room.rxjava3.RxRoom.2
            @Override // androidx.room.InvalidationTracker.Observer
            public void onInvalidated(@NonNull Set<String> set) {
                observableEmitter.onNext(RxRoom.NOTHING);
            }
        };
        roomDatabase.getInvalidationTracker().addObserver(observer);
        observableEmitter.setDisposable(Disposable.fromAction(new Action() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$UpzqOTfFjfkoAg5I_Q8oCvoJbTw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                RoomDatabase.this.getInvalidationTracker().removeObserver(observer);
            }
        }));
        observableEmitter.onNext(NOTHING);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ MaybeSource lambda$createObservable$5(Maybe maybe, Object obj) throws Throwable {
        return maybe;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$createSingle$6(Callable callable, SingleEmitter singleEmitter) throws Throwable {
        try {
            singleEmitter.onSuccess(callable.call());
        } catch (EmptyResultSetException e) {
            singleEmitter.tryOnError(e);
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static <T> Flowable<T> createFlowable(@NonNull RoomDatabase roomDatabase, boolean z, @NonNull String[] strArr, @NonNull Callable<T> callable) {
        Scheduler from = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe fromCallable = Maybe.fromCallable(callable);
        return (Flowable<T>) createFlowable(roomDatabase, strArr).subscribeOn(from).unsubscribeOn(from).observeOn(from).flatMapMaybe(new Function() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$evSoRU_fJAOvbxRgbGkMMM6Lke8
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Maybe maybe = Maybe.this;
                RxRoom.lambda$createFlowable$2(maybe, obj);
                return maybe;
            }
        });
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static <T> Observable<T> createObservable(@NonNull RoomDatabase roomDatabase, boolean z, @NonNull String[] strArr, @NonNull Callable<T> callable) {
        Scheduler from = Schedulers.from(getExecutor(roomDatabase, z));
        final Maybe fromCallable = Maybe.fromCallable(callable);
        return (Observable<T>) createObservable(roomDatabase, strArr).subscribeOn(from).unsubscribeOn(from).observeOn(from).flatMapMaybe(new Function() { // from class: androidx.room.rxjava3.-$$Lambda$RxRoom$mhGvxbSorHsUa2TVvm1EHcA9zuc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                Maybe maybe = Maybe.this;
                RxRoom.lambda$createObservable$5(maybe, obj);
                return maybe;
            }
        });
    }
}

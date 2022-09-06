package retrofit2.adapter.rxjava3;

import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import retrofit2.Call;
import retrofit2.CallAdapter;
/* loaded from: classes5.dex */
final class RxJava3CallAdapter<R> implements CallAdapter<R, Object> {
    private final boolean isAsync;
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    @Nullable
    private final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxJava3CallAdapter(Type type, @Nullable Scheduler scheduler, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
        this.responseType = type;
        this.scheduler = scheduler;
        this.isAsync = z;
        this.isResult = z2;
        this.isBody = z3;
        this.isFlowable = z4;
        this.isSingle = z5;
        this.isMaybe = z6;
        this.isCompletable = z7;
    }

    @Override // retrofit2.CallAdapter
    /* renamed from: adapt */
    public Object mo13080adapt(Call<R> call) {
        Observable bodyObservable;
        Observable callEnqueueObservable = this.isAsync ? new CallEnqueueObservable(call) : new CallExecuteObservable(call);
        if (this.isResult) {
            bodyObservable = new ResultObservable(callEnqueueObservable);
        } else {
            bodyObservable = this.isBody ? new BodyObservable(callEnqueueObservable) : callEnqueueObservable;
        }
        Scheduler scheduler = this.scheduler;
        if (scheduler != null) {
            bodyObservable = bodyObservable.subscribeOn(scheduler);
        }
        if (this.isFlowable) {
            return bodyObservable.toFlowable(BackpressureStrategy.LATEST);
        }
        if (this.isSingle) {
            return bodyObservable.singleOrError();
        }
        if (this.isMaybe) {
            return bodyObservable.singleElement();
        }
        if (this.isCompletable) {
            return bodyObservable.ignoreElements();
        }
        return RxJavaPlugins.onAssembly(bodyObservable);
    }

    @Override // retrofit2.CallAdapter
    public Type responseType() {
        return this.responseType;
    }
}

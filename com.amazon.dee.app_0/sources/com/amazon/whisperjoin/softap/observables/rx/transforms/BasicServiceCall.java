package com.amazon.whisperjoin.softap.observables.rx.transforms;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
/* loaded from: classes13.dex */
public abstract class BasicServiceCall implements ServiceCall {
    protected final Scheduler observeScheduler;
    protected final int retryCount;
    protected final Scheduler serialWorkScheduler;
    protected final long timeout;

    public BasicServiceCall(Scheduler scheduler, Scheduler scheduler2, long j, int i) {
        this.serialWorkScheduler = scheduler;
        this.observeScheduler = scheduler2;
        this.timeout = j;
        this.retryCount = i;
    }

    @Override // com.amazon.whisperjoin.softap.observables.rx.transforms.ServiceCall
    public <T> Observable.Transformer<T, T> observable() {
        return new Observable.Transformer<T, T>() { // from class: com.amazon.whisperjoin.softap.observables.rx.transforms.BasicServiceCall.2
            @Override // rx.functions.Func1
            /* renamed from: call */
            public Observable<T> mo13102call(Observable<T> observable) {
                return observable.timeout(BasicServiceCall.this.timeout, TimeUnit.MILLISECONDS).retry(BasicServiceCall.this.retryCount).subscribeOn(BasicServiceCall.this.serialWorkScheduler).observeOn(BasicServiceCall.this.observeScheduler);
            }
        };
    }

    @Override // com.amazon.whisperjoin.softap.observables.rx.transforms.ServiceCall
    public <T> Single.Transformer<T, T> single() {
        return new Single.Transformer<T, T>() { // from class: com.amazon.whisperjoin.softap.observables.rx.transforms.BasicServiceCall.1
            @Override // rx.functions.Func1
            /* renamed from: call */
            public Single<T> mo13102call(Single<T> single) {
                return single.toObservable().timeout(BasicServiceCall.this.timeout, TimeUnit.MILLISECONDS).retry(BasicServiceCall.this.retryCount).toSingle().subscribeOn(BasicServiceCall.this.serialWorkScheduler).observeOn(BasicServiceCall.this.observeScheduler);
            }
        };
    }
}

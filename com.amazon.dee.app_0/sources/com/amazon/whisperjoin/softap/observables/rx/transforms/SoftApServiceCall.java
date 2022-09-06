package com.amazon.whisperjoin.softap.observables.rx.transforms;

import com.amazon.whisperjoin.softap.wifi.requests.RequestException;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.functions.Func2;
/* loaded from: classes13.dex */
public class SoftApServiceCall extends BasicServiceCall {
    private static final int SOFT_AP_OPERATION_RETRY_COUNT = 5;
    private static final long SOFT_AP_OPERATION_TIMEOUT = 6000;
    private final Func2<Integer, Throwable, Boolean> retryUnlessRequestError;

    public SoftApServiceCall(Scheduler scheduler, Scheduler scheduler2) {
        super(scheduler, scheduler2, SOFT_AP_OPERATION_TIMEOUT, 5);
        this.retryUnlessRequestError = new Func2<Integer, Throwable, Boolean>() { // from class: com.amazon.whisperjoin.softap.observables.rx.transforms.SoftApServiceCall.1
            @Override // rx.functions.Func2
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Boolean mo13094call(Integer num, Throwable th) {
                return !(th instanceof RequestException) && num.intValue() < 5;
            }
        };
    }

    @Override // com.amazon.whisperjoin.softap.observables.rx.transforms.BasicServiceCall, com.amazon.whisperjoin.softap.observables.rx.transforms.ServiceCall
    public <T> Observable.Transformer<T, T> observable() {
        return new Observable.Transformer<T, T>() { // from class: com.amazon.whisperjoin.softap.observables.rx.transforms.SoftApServiceCall.3
            @Override // rx.functions.Func1
            /* renamed from: call */
            public Observable<T> mo13102call(Observable<T> observable) {
                return observable.timeout(SoftApServiceCall.this.timeout, TimeUnit.MILLISECONDS).retry(SoftApServiceCall.this.retryUnlessRequestError).subscribeOn(SoftApServiceCall.this.serialWorkScheduler).observeOn(SoftApServiceCall.this.observeScheduler);
            }
        };
    }

    @Override // com.amazon.whisperjoin.softap.observables.rx.transforms.BasicServiceCall, com.amazon.whisperjoin.softap.observables.rx.transforms.ServiceCall
    public <T> Single.Transformer<T, T> single() {
        return new Single.Transformer<T, T>() { // from class: com.amazon.whisperjoin.softap.observables.rx.transforms.SoftApServiceCall.2
            @Override // rx.functions.Func1
            /* renamed from: call */
            public Single<T> mo13102call(Single<T> single) {
                return single.toObservable().timeout(SoftApServiceCall.this.timeout, TimeUnit.MILLISECONDS).retry(SoftApServiceCall.this.retryUnlessRequestError).toSingle().subscribeOn(SoftApServiceCall.this.serialWorkScheduler).observeOn(SoftApServiceCall.this.observeScheduler);
            }
        };
    }
}

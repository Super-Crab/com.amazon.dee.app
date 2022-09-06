package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class ObserveForSingleEmissionWithShortBufferToAllowMultipleEmissions<T> implements ObservableTransformer<T, T> {
    private final long mBufferSec;
    private final long mTimeoutSec;

    public ObserveForSingleEmissionWithShortBufferToAllowMultipleEmissions(long j, long j2) {
        this.mTimeoutSec = j;
        this.mBufferSec = j2;
    }

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public ObservableSource<T> apply(@NonNull Observable<T> observable) {
        return observable.publish(new Function<Observable<T>, ObservableSource<T>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util.ObserveForSingleEmissionWithShortBufferToAllowMultipleEmissions.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public /* bridge */ /* synthetic */ Object mo10358apply(@NonNull Object obj) throws Throwable {
                return apply((Observable) ((Observable) obj));
            }

            public ObservableSource<T> apply(@NonNull Observable<T> observable2) throws Exception {
                return observable2.takeUntil(Observable.merge(observable2.delay(ObserveForSingleEmissionWithShortBufferToAllowMultipleEmissions.this.mBufferSec, TimeUnit.SECONDS), Observable.timer(ObserveForSingleEmissionWithShortBufferToAllowMultipleEmissions.this.mTimeoutSec, TimeUnit.SECONDS)));
            }
        });
    }
}

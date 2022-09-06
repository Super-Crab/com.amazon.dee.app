package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util;

import android.util.Pair;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class RetryAfterExponentialBackOffIfKnownError implements Function<Observable<Throwable>, ObservableSource<?>> {
    private static final String TAG = "RetryAfterExponentialBackOffIfKnownError";
    private static final int UNCHECKED_ERROR = -100;
    private final Class<? extends Throwable> mKnownError;
    private final int mRetryBackoffAttemptCount;
    private final long mRetryBackoffBaseDurationSeconds;

    public RetryAfterExponentialBackOffIfKnownError(Class<? extends Throwable> cls, int i, long j) {
        if (cls != null) {
            this.mKnownError = cls;
            this.mRetryBackoffAttemptCount = i;
            this.mRetryBackoffBaseDurationSeconds = j;
            return;
        }
        throw new IllegalArgumentException("knownError can not be null");
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public ObservableSource<?> mo10358apply(@NonNull Observable<Throwable> observable) throws Exception {
        return observable.zipWith(Observable.range(0, this.mRetryBackoffAttemptCount + 1), new BiFunction<Throwable, Integer, Pair<Throwable, Integer>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util.RetryAfterExponentialBackOffIfKnownError.2
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public Pair<Throwable, Integer> apply(@NonNull Throwable th, @NonNull Integer num) throws Exception {
                if (num.intValue() != RetryAfterExponentialBackOffIfKnownError.this.mRetryBackoffAttemptCount + 1) {
                    if (th.getClass().equals(RetryAfterExponentialBackOffIfKnownError.this.mKnownError)) {
                        return new Pair<>(th, num);
                    }
                    return new Pair<>(th, -100);
                }
                return new Pair<>(th, -100);
            }
        }).flatMap(new Function<Pair<Throwable, Integer>, ObservableSource<?>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.util.RetryAfterExponentialBackOffIfKnownError.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public ObservableSource<?> mo10358apply(@NonNull Pair<Throwable, Integer> pair) throws Exception {
                if (((Integer) pair.second).intValue() != -100) {
                    long round = Math.round(Math.pow(2.0d, ((Integer) pair.second).intValue()) * RetryAfterExponentialBackOffIfKnownError.this.mRetryBackoffBaseDurationSeconds);
                    WJLog.d(RetryAfterExponentialBackOffIfKnownError.TAG, String.format(Locale.ENGLISH, "Backing off workflow for %d seconds", Long.valueOf(round)));
                    return Observable.timer(round, TimeUnit.SECONDS);
                }
                return Observable.error((Throwable) pair.first);
            }
        });
    }
}

package com.amazon.whisperjoin.devicesetupserviceandroidclient.util;

import com.amazon.whisperjoin.devicesetupserviceandroidclient.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Function;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
/* loaded from: classes13.dex */
public class RetryWithDelay implements Function<Flowable<Throwable>, Publisher<?>> {
    private static final String TAG = GeneratedOutlineSupport1.outline39(RetryWithDelay.class, GeneratedOutlineSupport1.outline107(Constants.LOG_PREFIX));
    private final int mMaxRetries;
    private int mRetryCount = 0;
    private final long mRetryDelaySec;
    private TimeUnit mTimeUnit;

    public RetryWithDelay(int i, int i2, TimeUnit timeUnit) {
        this.mMaxRetries = i;
        this.mRetryDelaySec = i2;
        this.mTimeUnit = timeUnit;
    }

    static /* synthetic */ int access$104(RetryWithDelay retryWithDelay) {
        int i = retryWithDelay.mRetryCount + 1;
        retryWithDelay.mRetryCount = i;
        return i;
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply  reason: avoid collision after fix types in other method */
    public Publisher<?> mo10358apply(Flowable<Throwable> flowable) throws Exception {
        return flowable.flatMap(new Function<Throwable, Publisher<?>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.util.RetryWithDelay.1
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public Publisher<?> mo10358apply(Throwable th) throws Exception {
                String unused = RetryWithDelay.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Retry count: ");
                outline107.append(RetryWithDelay.this.mRetryCount);
                outline107.toString();
                if (RetryWithDelay.access$104(RetryWithDelay.this) < RetryWithDelay.this.mMaxRetries) {
                    return Flowable.timer(RetryWithDelay.this.mRetryDelaySec, RetryWithDelay.this.mTimeUnit);
                }
                String unused2 = RetryWithDelay.TAG;
                return Flowable.error(th);
            }
        });
    }
}

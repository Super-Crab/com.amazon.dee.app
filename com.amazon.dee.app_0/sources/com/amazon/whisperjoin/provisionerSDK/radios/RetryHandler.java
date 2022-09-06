package com.amazon.whisperjoin.provisionerSDK.radios;

import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class RetryHandler<TResult> {
    private static final String TAG = "com.amazon.whisperjoin.provisionerSDK.radios.RetryHandler";
    private final PromiseFactory<TResult> mAction;
    private final ExecutorService mExectuorService;

    public RetryHandler(PromiseFactory<TResult> promiseFactory, ExecutorService executorService) {
        if (promiseFactory != null) {
            if (executorService != null) {
                this.mAction = promiseFactory;
                this.mExectuorService = executorService;
                return;
            }
            throw new IllegalArgumentException("executorService can not be null");
        }
        throw new IllegalArgumentException("action can not be null");
    }

    public Future<TResult> startAttempts(final long j, final TimeUnit timeUnit, final long j2, final String str) {
        if (j >= 0) {
            if (j2 >= 0) {
                if (str != null && str.length() != 0) {
                    return this.mExectuorService.submit(new Callable<TResult>() { // from class: com.amazon.whisperjoin.provisionerSDK.radios.RetryHandler.1
                        @Override // java.util.concurrent.Callable
                        public TResult call() throws Exception {
                            Exception e = null;
                            Future<TResult> future = null;
                            for (long j3 = j2; j3 >= 0; j3--) {
                                try {
                                    Future<TResult> future2 = RetryHandler.this.mAction.getFuture();
                                    try {
                                        return future2.get(j, timeUnit);
                                    } catch (Exception e2) {
                                        future = future2;
                                        e = e2;
                                        if (future != null) {
                                            future.cancel(true);
                                        }
                                        String str2 = RetryHandler.TAG;
                                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Attempt failed for Action:");
                                        outline107.append(str);
                                        outline107.append(". Retry count: ");
                                        outline107.append(j3);
                                        WJLog.e(str2, outline107.toString(), e);
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                }
                            }
                            String str3 = RetryHandler.TAG;
                            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("All attempts failed for Action: ");
                            outline1072.append(str);
                            outline1072.append(" no more retrying.");
                            WJLog.e(str3, outline1072.toString());
                            throw e;
                        }
                    });
                }
                throw new IllegalArgumentException("actionName must not be null or empty");
            }
            throw new IllegalArgumentException("maxRetry must be >= 0");
        }
        throw new IllegalArgumentException("timeout must be >= 0");
    }
}

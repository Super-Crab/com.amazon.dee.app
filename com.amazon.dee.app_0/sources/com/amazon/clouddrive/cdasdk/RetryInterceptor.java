package com.amazon.clouddrive.cdasdk;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.retry.RetryContext;
import com.amazon.clouddrive.cdasdk.retry.RetryPolicy;
import com.amazon.clouddrive.cdasdk.util.Logger;
import com.amazon.clouddrive.cdasdk.util.SystemUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: classes11.dex */
public class RetryInterceptor implements Interceptor {
    private static final String TAG = "RetryInterceptor";
    @NonNull
    private final Logger logger;
    @NonNull
    private final RetryPolicy retryPolicy;
    @NonNull
    private final SystemUtil systemUtil;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RetryInterceptor(@NonNull Logger logger, @NonNull SystemUtil systemUtil, @NonNull RetryPolicy retryPolicy) {
        this.logger = logger;
        this.systemUtil = systemUtil;
        this.retryPolicy = retryPolicy;
    }

    @Override // okhttp3.Interceptor
    @NonNull
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        RetryContext retryContext = new RetryContext();
        boolean z = true;
        do {
            try {
                retryContext.setResponse(chain.proceed(request));
            } catch (IOException e) {
                retryContext.setException(e);
            }
            if (this.retryPolicy.shouldRetry(new RetryContext(retryContext))) {
                long computeDelayBeforeNextRetry = this.retryPolicy.getBackoffStrategy().computeDelayBeforeNextRetry(retryContext);
                Logger logger = this.logger;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Call was not successful - ");
                outline107.append(retryContext.getNumRetriesAttempted());
                outline107.append(" retries, backing off - ");
                outline107.append(computeDelayBeforeNextRetry);
                outline107.append("ms");
                logger.v(TAG, outline107.toString());
                retryContext.setNumRetriesAttempted(retryContext.getNumRetriesAttempted() + 1);
                retryContext.setException(null);
                retryContext.setResponse(null);
                try {
                    this.systemUtil.sleep(computeDelayBeforeNextRetry);
                    continue;
                } catch (InterruptedException unused) {
                    this.systemUtil.interrupt();
                    continue;
                }
            } else {
                z = false;
                continue;
            }
        } while (z);
        if (retryContext.getException() != null) {
            if (retryContext.getResponse() != null) {
                retryContext.getResponse().close();
            }
            throw retryContext.getException();
        } else if (retryContext.getResponse() != null) {
            return retryContext.getResponse();
        } else {
            throw new IllegalStateException("Call finished without either an exception or a response");
        }
    }
}

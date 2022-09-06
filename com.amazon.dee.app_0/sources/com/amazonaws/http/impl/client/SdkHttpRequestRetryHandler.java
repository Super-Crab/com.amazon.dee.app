package com.amazonaws.http.impl.client;

import com.amazonaws.util.AWSRequestMetrics;
import java.io.IOException;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
/* loaded from: classes13.dex */
public final class SdkHttpRequestRetryHandler extends DefaultHttpRequestRetryHandler {
    public static final SdkHttpRequestRetryHandler Singleton = new SdkHttpRequestRetryHandler();

    private SdkHttpRequestRetryHandler() {
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        AWSRequestMetrics aWSRequestMetrics;
        boolean retryRequest = super.retryRequest(iOException, i, httpContext);
        if (retryRequest && (aWSRequestMetrics = (AWSRequestMetrics) httpContext.getAttribute(AWSRequestMetrics.class.getSimpleName())) != null) {
            aWSRequestMetrics.incrementCounter(AWSRequestMetrics.Field.HttpClientRetryCount);
        }
        return retryRequest;
    }
}

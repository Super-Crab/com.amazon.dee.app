package com.amazonaws.http.protocol;

import com.amazonaws.util.AWSRequestMetrics;
import java.io.IOException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestExecutor;
/* loaded from: classes13.dex */
public class SdkHttpRequestExecutor extends HttpRequestExecutor {
    protected HttpResponse doReceiveResponse(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        AWSRequestMetrics aWSRequestMetrics = (AWSRequestMetrics) httpContext.getAttribute(AWSRequestMetrics.class.getSimpleName());
        if (aWSRequestMetrics == null) {
            return super.doReceiveResponse(httpRequest, httpClientConnection, httpContext);
        }
        aWSRequestMetrics.startEvent(AWSRequestMetrics.Field.HttpClientReceiveResponseTime);
        try {
            return super.doReceiveResponse(httpRequest, httpClientConnection, httpContext);
        } finally {
            aWSRequestMetrics.endEvent(AWSRequestMetrics.Field.HttpClientReceiveResponseTime);
        }
    }

    protected HttpResponse doSendRequest(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws IOException, HttpException {
        AWSRequestMetrics aWSRequestMetrics = (AWSRequestMetrics) httpContext.getAttribute(AWSRequestMetrics.class.getSimpleName());
        if (aWSRequestMetrics == null) {
            return super.doSendRequest(httpRequest, httpClientConnection, httpContext);
        }
        aWSRequestMetrics.startEvent(AWSRequestMetrics.Field.HttpClientSendRequestTime);
        try {
            return super.doSendRequest(httpRequest, httpClientConnection, httpContext);
        } finally {
            aWSRequestMetrics.endEvent(AWSRequestMetrics.Field.HttpClientSendRequestTime);
        }
    }
}

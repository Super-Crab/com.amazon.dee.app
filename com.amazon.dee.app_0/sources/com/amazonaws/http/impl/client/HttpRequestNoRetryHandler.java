package com.amazonaws.http.impl.client;

import java.io.IOException;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;
/* loaded from: classes13.dex */
public final class HttpRequestNoRetryHandler extends DefaultHttpRequestRetryHandler {
    public static final HttpRequestNoRetryHandler Singleton = new HttpRequestNoRetryHandler();

    private HttpRequestNoRetryHandler() {
    }

    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        return false;
    }
}

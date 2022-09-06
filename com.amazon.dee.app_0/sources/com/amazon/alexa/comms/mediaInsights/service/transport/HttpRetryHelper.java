package com.amazon.alexa.comms.mediaInsights.service.transport;

import java.net.HttpRetryException;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes6.dex */
public class HttpRetryHelper {
    private static final List<Integer> RETRIABLE_HTTP_RESPONSE_CODES = Arrays.asList(502, 503, 504, 500, 408);
    public static final int UHE_HTTP_CODE = 431;

    public static HttpRetryException getRetriableException(Throwable th) {
        return new HttpRetryException(th.toString(), UHE_HTTP_CODE);
    }

    public static boolean isRetriable(int i) {
        return RETRIABLE_HTTP_RESPONSE_CODES.contains(Integer.valueOf(i));
    }
}

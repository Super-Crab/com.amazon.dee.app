package com.amazon.clouddrive.cdasdk.retry;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Set;
import okhttp3.Response;
/* loaded from: classes11.dex */
final class RetryUtil {
    static final int ERROR_CODE_BAD_GATEWAY = 502;
    static final int ERROR_CODE_GATEWAY_TIMEOUT = 504;
    static final int ERROR_CODE_INTERNAL_SERVICE_ERROR = 500;
    static final int ERROR_CODE_REQUEST_TIMEOUT = 408;
    static final int ERROR_CODE_SERVICE_UNAVAILABLE = 503;
    static final int ERROR_CODE_TOO_MANY_REQUESTS = 429;
    private static final Set<Integer> RETRYABLE_STATUS_CODES;
    private static final Set<Integer> THROTTLING_ERROR_CODES = new HashSet();

    static {
        THROTTLING_ERROR_CODES.add(408);
        THROTTLING_ERROR_CODES.add(429);
        RETRYABLE_STATUS_CODES = new HashSet();
        RETRYABLE_STATUS_CODES.add(500);
        RETRYABLE_STATUS_CODES.add(502);
        RETRYABLE_STATUS_CODES.add(503);
        RETRYABLE_STATUS_CODES.add(504);
    }

    private RetryUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isRetryableError(@NonNull Response response) {
        return RETRYABLE_STATUS_CODES.contains(Integer.valueOf(response.code()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isThrottlingError(@NonNull Response response) {
        return THROTTLING_ERROR_CODES.contains(Integer.valueOf(response.code()));
    }
}

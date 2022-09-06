package com.here.sdk.search;
/* loaded from: classes3.dex */
public enum SearchError {
    AUTHENTICATION_FAILED(1),
    MAX_ITEMS_OUT_OF_RANGE(2),
    POLYLINE_TOO_LONG(3),
    PARSING_ERROR(4),
    NO_RESULTS_FOUND(5),
    HTTP_ERROR(6),
    SERVER_UNREACHABLE(7),
    INVALID_PARAMETER(8),
    FORBIDDEN(9),
    EXCEEDED_USAGE_LIMIT(10),
    OPERATION_FAILED(11),
    OPERATION_CANCELLED(12),
    OPTION_NOT_AVAILABLE(13),
    TIMED_OUT(14),
    OFFLINE(15),
    QUERY_TOO_LONG(16),
    FILTER_TOO_LONG(17);
    
    public final int value;

    SearchError(int i) {
        this.value = i;
    }
}

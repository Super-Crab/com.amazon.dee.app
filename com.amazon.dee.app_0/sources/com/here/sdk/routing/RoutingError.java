package com.here.sdk.routing;
/* loaded from: classes3.dex */
public enum RoutingError {
    INTERNAL_ERROR(1),
    INVALID_PARAMETER(2),
    SERVER_UNREACHABLE(3),
    HTTP_ERROR(4),
    AUTHENTICATION_FAILED(5),
    FORBIDDEN(6),
    EXCEEDED_USAGE_LIMIT(7),
    PARSING_ERROR(8),
    NO_ROUTE_FOUND(9),
    TIMED_OUT(10),
    OFFLINE(11),
    NO_ISOLINE_FOUND(12);
    
    public final int value;

    RoutingError(int i) {
        this.value = i;
    }
}

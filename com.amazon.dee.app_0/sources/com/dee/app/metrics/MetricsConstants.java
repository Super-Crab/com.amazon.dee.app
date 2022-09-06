package com.dee.app.metrics;
/* loaded from: classes2.dex */
public final class MetricsConstants {
    public static final char METRIC_NAME_DELIMITER = '.';

    /* loaded from: classes2.dex */
    public static final class CategoryId {
        public static final String CACHE_SERVICE = "cache-service";
        public static final String UNKNOWN = "unknown";

        private CategoryId() {
        }
    }

    /* loaded from: classes2.dex */
    public final class Format {
        public static final String LOADING_TIMEOUT = "%S_TO_%S_TIMEOUT_%d_SECONDS";
        public static final String NAVIGATION = "%S_%S_TO_%S_DURATION";
        public static final String NAVIGATION_ENDPOINT = "_TO_%S_DURATION";

        private Format() {
        }
    }

    /* loaded from: classes2.dex */
    public final class Id {
        public static final String BUNDLE_INITIALIZE = "bundle_init";
        public static final String CACHE_CLEAR_ERROR = "cache_clear_error";
        public static final String CACHE_GET_ERROR = "cache_get_error";
        public static final String CACHE_MISS = "cache_miss";
        public static final String CACHE_PUT_ERROR = "cache_put_error";
        public static final String HTTP_CLIENT_FETCH = "fetch";
        public static final String LOAD = "load";
        public static final String LOAD_FAILURE = "load_failure";
        public static final String PING = "ping";
        public static final String PING_DELAYED = "ping_delayed";
        public static final String PING_FAILURE = "ping_failure";
        public static final String PING_TIMEOUT = "ping_timeout";

        private Id() {
        }
    }

    /* loaded from: classes2.dex */
    public static final class Method {
        public static final String CACHE_CLEAR = "clear";
        public static final String CACHE_GET = "get";
        public static final String CACHE_PUT = "put";
        public static final String LOAD = "load";
        public static final String PING = "ping";

        private Method() {
        }
    }

    /* loaded from: classes2.dex */
    public final class MetricsComponents {
        public static final String ALEXA_APPLICATION = "Application";

        private MetricsComponents() {
        }
    }

    /* loaded from: classes2.dex */
    public final class Module {
        public static final String CORE = "core";
        public static final String UNKNOWN = "unknown";

        private Module() {
        }
    }

    /* loaded from: classes2.dex */
    public final class NativeFetch {
        public static final String METHOD = "method";
        public static final String URL = "url";

        private NativeFetch() {
        }
    }

    /* loaded from: classes2.dex */
    public final class Source {
        public static final String NATIVE = "native";
        public static final String NATIVE_CORAL = "native.http";
        public static final String NATIVE_ELEMENTS = "native.lmnt";

        private Source() {
        }
    }

    private MetricsConstants() {
    }
}

package com.amazon.alexa.comms.mediaInsights.service.configuration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import lombok.NonNull;
/* loaded from: classes6.dex */
public class TracePublisherServiceConfigImpl implements TracePublisherServiceConfiguration {
    public static final TracePublisherServiceConfiguration DEFAULT = builder().build();
    public static final int DEVICE_DETAILS_JOB_INTERVAL_MILLIS = 120000;
    public static final int EXPONENT_FOR_HTTP_REQUEST = 2;
    public static final long FLUSH_JOB_INTERVAL_MILLIS = 60000;
    public static final int FLUSH_THREAD_POOL_SIZE = 8;
    public static final int HTTPS_CONNECT_TIMEOUT = 10000;
    public static final int HTTPS_READ_TIMEOUT = 10000;
    public static final int MAX_HTTPS_RETRY_COUNT = 2;
    public static final int MAX_TRACE_TTL_MILLIS = 60000;
    public static final int MIN_DELAY_FOR_HTTP_REQUEST = 1000;
    public static final int TOTAL_TRACES_CAPACITY_IN_BYTES = 1000000;
    public static final String TRACE_HTTPS_ENDPOINT = "https://prod.insights.comms.alexa.a2z.com/traces";
    public static final long WAKE_LOCK_TIMEOUT = 11000;
    @NonNull
    private Integer deviceDetailsJobIntervalInMillis;
    @NonNull
    private Integer exponentForHttpRetries;
    @NonNull
    private Long flushJobIntervalInMillis;
    @NonNull
    private Integer flushThreadPoolSize;
    @NonNull
    private Integer httpConnectTimeout;
    @NonNull
    private Integer httpReadTimeout;
    @NonNull
    private Integer maxNumberOfHttpRetries;
    @NonNull
    private Integer maxTraceTTLInMillis;
    @NonNull
    private Integer minimumDelayForHttpRetries;
    @NonNull
    private Integer totalTracesCapacityInBytes;
    @NonNull
    private String traceServiceHttpsEndpoint;
    @NonNull
    private Long wakeLockTimeout;

    /* loaded from: classes6.dex */
    public static class TracePublisherServiceConfigImplBuilder {
        private Integer deviceDetailsJobIntervalInMillis;
        private boolean deviceDetailsJobIntervalInMillis$set;
        private Integer exponentForHttpRetries;
        private boolean exponentForHttpRetries$set;
        private Long flushJobIntervalInMillis;
        private boolean flushJobIntervalInMillis$set;
        private Integer flushThreadPoolSize;
        private boolean flushThreadPoolSize$set;
        private Integer httpConnectTimeout;
        private boolean httpConnectTimeout$set;
        private Integer httpReadTimeout;
        private boolean httpReadTimeout$set;
        private Integer maxNumberOfHttpRetries;
        private boolean maxNumberOfHttpRetries$set;
        private Integer maxTraceTTLInMillis;
        private boolean maxTraceTTLInMillis$set;
        private Integer minimumDelayForHttpRetries;
        private boolean minimumDelayForHttpRetries$set;
        private Integer totalTracesCapacityInBytes;
        private boolean totalTracesCapacityInBytes$set;
        private String traceServiceHttpsEndpoint;
        private boolean traceServiceHttpsEndpoint$set;
        private Long wakeLockTimeout;
        private boolean wakeLockTimeout$set;

        TracePublisherServiceConfigImplBuilder() {
        }

        public TracePublisherServiceConfigImpl build() {
            return new TracePublisherServiceConfigImpl(this.totalTracesCapacityInBytes$set ? this.totalTracesCapacityInBytes : TracePublisherServiceConfigImpl.$default$totalTracesCapacityInBytes(), this.maxTraceTTLInMillis$set ? this.maxTraceTTLInMillis : TracePublisherServiceConfigImpl.$default$maxTraceTTLInMillis(), this.flushJobIntervalInMillis$set ? this.flushJobIntervalInMillis : TracePublisherServiceConfigImpl.$default$flushJobIntervalInMillis(), this.deviceDetailsJobIntervalInMillis$set ? this.deviceDetailsJobIntervalInMillis : TracePublisherServiceConfigImpl.$default$deviceDetailsJobIntervalInMillis(), this.flushThreadPoolSize$set ? this.flushThreadPoolSize : TracePublisherServiceConfigImpl.$default$flushThreadPoolSize(), this.traceServiceHttpsEndpoint$set ? this.traceServiceHttpsEndpoint : TracePublisherServiceConfigImpl.$default$traceServiceHttpsEndpoint(), this.httpConnectTimeout$set ? this.httpConnectTimeout : TracePublisherServiceConfigImpl.$default$httpConnectTimeout(), this.httpReadTimeout$set ? this.httpReadTimeout : TracePublisherServiceConfigImpl.$default$httpReadTimeout(), this.wakeLockTimeout$set ? this.wakeLockTimeout : TracePublisherServiceConfigImpl.$default$wakeLockTimeout(), this.maxNumberOfHttpRetries$set ? this.maxNumberOfHttpRetries : TracePublisherServiceConfigImpl.$default$maxNumberOfHttpRetries(), this.minimumDelayForHttpRetries$set ? this.minimumDelayForHttpRetries : TracePublisherServiceConfigImpl.$default$minimumDelayForHttpRetries(), this.exponentForHttpRetries$set ? this.exponentForHttpRetries : TracePublisherServiceConfigImpl.$default$exponentForHttpRetries());
        }

        public TracePublisherServiceConfigImplBuilder deviceDetailsJobIntervalInMillis(Integer num) {
            this.deviceDetailsJobIntervalInMillis = num;
            this.deviceDetailsJobIntervalInMillis$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder exponentForHttpRetries(Integer num) {
            this.exponentForHttpRetries = num;
            this.exponentForHttpRetries$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder flushJobIntervalInMillis(Long l) {
            this.flushJobIntervalInMillis = l;
            this.flushJobIntervalInMillis$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder flushThreadPoolSize(Integer num) {
            this.flushThreadPoolSize = num;
            this.flushThreadPoolSize$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder httpConnectTimeout(Integer num) {
            this.httpConnectTimeout = num;
            this.httpConnectTimeout$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder httpReadTimeout(Integer num) {
            this.httpReadTimeout = num;
            this.httpReadTimeout$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder maxNumberOfHttpRetries(Integer num) {
            this.maxNumberOfHttpRetries = num;
            this.maxNumberOfHttpRetries$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder maxTraceTTLInMillis(Integer num) {
            this.maxTraceTTLInMillis = num;
            this.maxTraceTTLInMillis$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder minimumDelayForHttpRetries(Integer num) {
            this.minimumDelayForHttpRetries = num;
            this.minimumDelayForHttpRetries$set = true;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TracePublisherServiceConfigImpl.TracePublisherServiceConfigImplBuilder(totalTracesCapacityInBytes=");
            outline107.append(this.totalTracesCapacityInBytes);
            outline107.append(", maxTraceTTLInMillis=");
            outline107.append(this.maxTraceTTLInMillis);
            outline107.append(", flushJobIntervalInMillis=");
            outline107.append(this.flushJobIntervalInMillis);
            outline107.append(", deviceDetailsJobIntervalInMillis=");
            outline107.append(this.deviceDetailsJobIntervalInMillis);
            outline107.append(", flushThreadPoolSize=");
            outline107.append(this.flushThreadPoolSize);
            outline107.append(", traceServiceHttpsEndpoint=");
            outline107.append(this.traceServiceHttpsEndpoint);
            outline107.append(", httpConnectTimeout=");
            outline107.append(this.httpConnectTimeout);
            outline107.append(", httpReadTimeout=");
            outline107.append(this.httpReadTimeout);
            outline107.append(", wakeLockTimeout=");
            outline107.append(this.wakeLockTimeout);
            outline107.append(", maxNumberOfHttpRetries=");
            outline107.append(this.maxNumberOfHttpRetries);
            outline107.append(", minimumDelayForHttpRetries=");
            outline107.append(this.minimumDelayForHttpRetries);
            outline107.append(", exponentForHttpRetries=");
            outline107.append(this.exponentForHttpRetries);
            outline107.append(")");
            return outline107.toString();
        }

        public TracePublisherServiceConfigImplBuilder totalTracesCapacityInBytes(Integer num) {
            this.totalTracesCapacityInBytes = num;
            this.totalTracesCapacityInBytes$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder traceServiceHttpsEndpoint(String str) {
            this.traceServiceHttpsEndpoint = str;
            this.traceServiceHttpsEndpoint$set = true;
            return this;
        }

        public TracePublisherServiceConfigImplBuilder wakeLockTimeout(Long l) {
            this.wakeLockTimeout = l;
            this.wakeLockTimeout$set = true;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$deviceDetailsJobIntervalInMillis() {
        return Integer.valueOf((int) DEVICE_DETAILS_JOB_INTERVAL_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$exponentForHttpRetries() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long $default$flushJobIntervalInMillis() {
        return 60000L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$flushThreadPoolSize() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$httpConnectTimeout() {
        return 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$httpReadTimeout() {
        return 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$maxNumberOfHttpRetries() {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$maxTraceTTLInMillis() {
        return 60000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$minimumDelayForHttpRetries() {
        return 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Integer $default$totalTracesCapacityInBytes() {
        return Integer.valueOf((int) TOTAL_TRACES_CAPACITY_IN_BYTES);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String $default$traceServiceHttpsEndpoint() {
        return TRACE_HTTPS_ENDPOINT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Long $default$wakeLockTimeout() {
        return Long.valueOf((long) WAKE_LOCK_TIMEOUT);
    }

    TracePublisherServiceConfigImpl(@NonNull Integer num, @NonNull Integer num2, @NonNull Long l, @NonNull Integer num3, @NonNull Integer num4, @NonNull String str, @NonNull Integer num5, @NonNull Integer num6, @NonNull Long l2, @NonNull Integer num7, @NonNull Integer num8, @NonNull Integer num9) {
        if (num != null) {
            if (num2 == null) {
                throw new IllegalArgumentException("maxTraceTTLInMillis is null");
            }
            if (l == null) {
                throw new IllegalArgumentException("flushJobIntervalInMillis is null");
            }
            if (num3 == null) {
                throw new IllegalArgumentException("deviceDetailsJobIntervalInMillis is null");
            }
            if (num4 == null) {
                throw new IllegalArgumentException("flushThreadPoolSize is null");
            }
            if (str == null) {
                throw new IllegalArgumentException("traceServiceHttpsEndpoint is null");
            }
            if (num5 == null) {
                throw new IllegalArgumentException("httpConnectTimeout is null");
            }
            if (num6 == null) {
                throw new IllegalArgumentException("httpReadTimeout is null");
            }
            if (l2 == null) {
                throw new IllegalArgumentException("wakeLockTimeout is null");
            }
            if (num7 == null) {
                throw new IllegalArgumentException("maxNumberOfHttpRetries is null");
            }
            if (num8 == null) {
                throw new IllegalArgumentException("minimumDelayForHttpRetries is null");
            }
            if (num9 == null) {
                throw new IllegalArgumentException("exponentForHttpRetries is null");
            }
            this.totalTracesCapacityInBytes = num;
            this.maxTraceTTLInMillis = num2;
            this.flushJobIntervalInMillis = l;
            this.deviceDetailsJobIntervalInMillis = num3;
            this.flushThreadPoolSize = num4;
            this.traceServiceHttpsEndpoint = str;
            this.httpConnectTimeout = num5;
            this.httpReadTimeout = num6;
            this.wakeLockTimeout = l2;
            this.maxNumberOfHttpRetries = num7;
            this.minimumDelayForHttpRetries = num8;
            this.exponentForHttpRetries = num9;
            return;
        }
        throw new IllegalArgumentException("totalTracesCapacityInBytes is null");
    }

    public static TracePublisherServiceConfigImplBuilder builder() {
        return new TracePublisherServiceConfigImplBuilder();
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getDeviceDetailsJobIntervalInMillis() {
        return this.deviceDetailsJobIntervalInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getExponentForHttpRetries() {
        return this.exponentForHttpRetries;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Long getFlushJobIntervalInMillis() {
        return this.flushJobIntervalInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getFlushThreadPoolSize() {
        return this.flushThreadPoolSize;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getHttpConnectTimeout() {
        return this.httpConnectTimeout;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getHttpReadTimeout() {
        return this.httpReadTimeout;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getMaxNumberOfHttpRetries() {
        return this.maxNumberOfHttpRetries;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getMaxTraceTTLInMillis() {
        return this.maxTraceTTLInMillis;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getMinimumDelayForHttpRetries() {
        return this.minimumDelayForHttpRetries;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Integer getTotalTracesCapacityInBytes() {
        return this.totalTracesCapacityInBytes;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public String getTraceServiceHttpsEndpoint() {
        return this.traceServiceHttpsEndpoint;
    }

    @Override // com.amazon.alexa.comms.mediaInsights.service.configuration.TracePublisherServiceConfiguration
    @NonNull
    public Long getWakeLockTimeout() {
        return this.wakeLockTimeout;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TracePublisherServiceConfigImpl(totalTracesCapacityInBytes=");
        outline107.append(getTotalTracesCapacityInBytes());
        outline107.append(", maxTraceTTLInMillis=");
        outline107.append(getMaxTraceTTLInMillis());
        outline107.append(", flushJobIntervalInMillis=");
        outline107.append(getFlushJobIntervalInMillis());
        outline107.append(", deviceDetailsJobIntervalInMillis=");
        outline107.append(getDeviceDetailsJobIntervalInMillis());
        outline107.append(", flushThreadPoolSize=");
        outline107.append(getFlushThreadPoolSize());
        outline107.append(", traceServiceHttpsEndpoint=");
        outline107.append(getTraceServiceHttpsEndpoint());
        outline107.append(", httpConnectTimeout=");
        outline107.append(getHttpConnectTimeout());
        outline107.append(", httpReadTimeout=");
        outline107.append(getHttpReadTimeout());
        outline107.append(", wakeLockTimeout=");
        outline107.append(getWakeLockTimeout());
        outline107.append(", maxNumberOfHttpRetries=");
        outline107.append(getMaxNumberOfHttpRetries());
        outline107.append(", minimumDelayForHttpRetries=");
        outline107.append(getMinimumDelayForHttpRetries());
        outline107.append(", exponentForHttpRetries=");
        outline107.append(getExponentForHttpRetries());
        outline107.append(")");
        return outline107.toString();
    }
}

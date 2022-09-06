package com.amazon.alexa.crashreporting;
/* loaded from: classes6.dex */
public final class CrashReportingMetrics {

    /* loaded from: classes6.dex */
    public static final class Component {
        public static final String CRASH_HANDLER = "CrashHandler";

        private Component() {
            throw new UnsupportedOperationException("No instances!");
        }
    }

    /* loaded from: classes6.dex */
    public static final class ExtraData {
        public static final String ERROR_SOURCE = "errorSource";
        public static final String SEND_PRIORITY = "sendPriority";

        private ExtraData() {
            throw new UnsupportedOperationException("No instances!");
        }
    }

    /* loaded from: classes6.dex */
    public static final class MetricKey {
        public static final String APP_CRASH = "APP_CRASH";
        public static final String APP_CRASH_COUNT = "APP_CRASH_COUNT";
        public static final String APP_CRASH_USING_BUGSNAG = "APP_CRASH_USING_BUGSNAG";

        private MetricKey() {
            throw new UnsupportedOperationException("No instances!");
        }
    }

    private CrashReportingMetrics() {
        throw new UnsupportedOperationException("No instances!");
    }
}

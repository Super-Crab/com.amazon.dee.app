package com.amazon.alexa.logupload;
/* loaded from: classes9.dex */
public final class LogUploadMetricsConstants {

    /* loaded from: classes9.dex */
    public static final class ComponentName {
        public static final String COMPONENT = "LogUpload";
        public static final String SUB_COMPONENT = "LogUpload_Session";

        private ComponentName() {
        }
    }

    /* loaded from: classes9.dex */
    public static final class EventName {
        public static final String UPLOAD_ATTEMPT_SUFFIX = ".log.upload.attempt";
        public static final String UPLOAD_DEFAULT_NAME = "default";
        public static final String UPLOAD_HTTP_ERROR_INFIX = ".log.upload.error.http.";
        public static final String UPLOAD_OTHER_ERROR_SUFFIX = ".log.upload.error.other";
        public static final String UPLOAD_RETRIEVING_ERROR_SUFFIX = ".log.upload.error.retrieving";
        public static final String UPLOAD_SUCCESS_SUFFIX = ".log.upload.success";

        private EventName() {
        }
    }

    private LogUploadMetricsConstants() {
    }
}

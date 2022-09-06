package com.amazon.android.codahalemetricreporter.impl;

import java.util.UUID;
/* loaded from: classes11.dex */
public final class MetricIntent {
    public static final String ENUM_ACTION = "com.amazon.android.codahalemetricreporter.impl.ENUM_ACTION";
    public static final String REPLY_ACTION = "com.amazon.android.codahalemetricreporter.impl.REPLY_ACTION";
    public static final String REPLY_ACTION_EXTRA = "REPLY_ACTION";
    public static final String REPLY_COOKIE_EXTRA = "REPLY_COOKIE";
    public static final String REPLY_PACKAGE_EXTRA = "REPLY_PACKAGE";
    public static final String REPORTER_FORMATS_EXTRA = "REPORTER_FORMATS";
    public static final String REPORTER_PACKAGE_EXTRA = "REPORTER_PACKAGE";
    public static final String REPORTER_UUID_EXTRA = "REPORTER_UUID";
    public static final String REPORT_ACTION = "com.amazon.android.codahalemetricreporter.impl.REPORT_ACTION";
    public static final String REPORT_ERROR_EXTRA = "REPORT_ERROR";
    public static final String REPORT_FILE_EXTRA = "REPORT_CONTENT";
    public static final String REPORT_FILTER_EXTRA = "REPORT_FILTER";
    public static final String REPORT_FORMAT_EXTRA = "REPORT_FORMAT";

    private MetricIntent() {
    }

    public static String genReplyAction() {
        return getInstanceAction(UUID.randomUUID().toString(), REPLY_ACTION);
    }

    public static String getInstanceAction(String str, String str2) {
        return String.format("%s_%s", str2, str.replace('-', '_'));
    }

    public static String getReportAction(String str) {
        return getInstanceAction(str, REPORT_ACTION);
    }
}

package com.amazon.alexa.handsfree.protocols.metricsprovider;

import android.net.Uri;
import android.provider.BaseColumns;
/* loaded from: classes8.dex */
public final class AlexaMetricsContract {
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://com.amazon.alexa.handsfree.metrics.metricsprovider.AlexaMetricsContentProvider");
    public static final String CONTENT_AUTHORITY = "com.amazon.alexa.handsfree.metrics.metricsprovider.AlexaMetricsContentProvider";

    /* loaded from: classes8.dex */
    public static final class AlexaMetricsTable implements BaseColumns {
        public static final String COLUMN_CLICK_ACTION = "COLUMN_CLICK_ACTION";
        public static final String COLUMN_CLICK_COMPONENT = "COLUMN_CLICK_COMPONENT";
        public static final String COLUMN_CLICK_PAGE_TYPE = "COLUMN_CLICK_PAGE_TYPE";
        public static final String COLUMN_COMPONENT = "COLUMN_COMPONENT";
        public static final String COLUMN_METRIC_DATE = "METRIC_DATE";
        public static final String COLUMN_METRIC_TYPE = "METRIC_TYPE";
        public static final String COLUMN_NOTIFICATION_TEXT = "COLUMN_NOTIFICATION_TEXT";
        public static final String COLUMN_NOTIFICATION_TITLE = "COLUMN_NOTIFICATION_TITLE";
        public static final String COLUMN_PAGE_TYPE = "COLUMN_PAGE_TYPE";
        public static final String COLUMN_SETUP_ACTION_TYPE = "COLUMN_SETUP_ACTION_TYPE";
        public static final String COLUMN_SETUP_COMPONENT = "COLUMN_SETUP_COMPONENT";
        public static final String COLUMN_SETUP_EVENT_TYPE = "COLUMN_SETUP_EVENT_TYPE";
        public static final String COLUMN_SETUP_PAGE_TYPE = "COLUMN_SETUP_PAGE_TYPE";
        public static final String COLUMN_SUB_PAGE_TYPE = "COLUMN_SUB_PAGE_TYPE";
        public static final String COLUMN_VENDOR_EVENT = "COLUMN_VENDOR_EVENT";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd.com.amazon.alexa.handsfree.metrics.metricsprovider.AlexaMetricsContentProvider.alexa_metrics";
        public static final String DATABASE_NAME = "alexa_metrics.db";
        public static final String METRIC_TYPE_CLICK = "CLICK_METRIC";
        public static final String METRIC_TYPE_FRO = "FRO_METRIC";
        public static final String METRIC_TYPE_NOTIFICATION_CLICK = "NOTIFICATION_CLICK_METRIC";
        public static final String METRIC_TYPE_NOTIFICATION_EVENT = "NOTIFICATION_EVENT_METRIC";
        public static final String METRIC_TYPE_SETUP = "SETUP_METRIC";
        public static final String METRIC_TYPE_VENDOR = "VENDOR_METRIC";
        public static final String TABLE_NAME = "alexaMetricsTable";
        public static final String ALEXA_METRICS_DIRECTORY = "alexa_metrics";
        public static final Uri CONTENT_URI = AlexaMetricsContract.BASE_CONTENT_URI.buildUpon().appendPath(ALEXA_METRICS_DIRECTORY).build();
    }

    private AlexaMetricsContract() {
    }
}

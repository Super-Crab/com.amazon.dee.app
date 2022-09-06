package com.amazon.alexa.hho.metrics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes8.dex */
public final class HHOMetricsConstants {

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface Component {
        public static final String STICKY_NOTES = "STICKY_NOTES";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface Event {
        public static final String CACHE_EVICTED = "CACHE_EVICTED";
        public static final String CLEAN_UP_MEDIA = "CLEAN_UP_MEDIA";
        public static final String EXCEPTION = "EXCEPTION";
        public static final String GET_MEDIA = "GET_MEDIA";
        public static final String GET_MEDIA_FAILURE = "GET_MEDIA_FAILURE";
        public static final String GET_MEDIA_SUCCESS = "GET_MEDIA_SUCCESS";
        public static final String GET_THUMBNAIL = "GET_THUMBNAIL";
        public static final String GET_VIDEO = "GET_VIDEO";
        public static final String PREFETCH_MEDIA = "PREFETCH_MEDIA";
        public static final String REMOVE_ALL_MEDIA = "REMOVE_ALL_MEDIA";
        public static final String REMOVE_MEDIA = "REMOVE_MEDIA";
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes8.dex */
    public @interface Subcomponent {
        public static final String DOWNLOADER = "DOWNLOADER";
        public static final String PROVIDER = "PROVIDER";
    }
}

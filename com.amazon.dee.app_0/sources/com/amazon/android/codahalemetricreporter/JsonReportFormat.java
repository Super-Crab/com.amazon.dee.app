package com.amazon.android.codahalemetricreporter;
/* loaded from: classes11.dex */
public final class JsonReportFormat {
    public static final String COUNT = "n";
    public static final String COUNTERS = "counters";
    public static final String ENCODING = "UTF-8";
    public static final String GAUGES = "gauges";
    public static final String HISTOGRAMS = "histograms";
    public static final String METERS = "meters";
    public static final String NAME = ReportFormat.name("metrics", ReportFormat.type("text", "json", "metrics"));
    public static final String RATE_15M = "r15m";
    public static final String RATE_1M = "r1m";
    public static final String RATE_5M = "r5m";
    public static final String RATE_AVG = "rAvg";
    public static final String SAMPLING_75P = "s75p";
    public static final String SAMPLING_95P = "s95p";
    public static final String SAMPLING_99P = "s99p";
    public static final String SAMPLING_AVG = "sAvg";
    public static final String SAMPLING_MAX = "sMax";
    public static final String SAMPLING_MEDIAN = "sMed";
    public static final String SAMPLING_MIN = "sMin";
    public static final String SAMPLING_STDDEV = "sDev";
    public static final String TIMERS = "timers";

    private JsonReportFormat() {
    }
}

package com.amazon.android.codahalemetricreporter.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.codahale.metrics.Clock;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes11.dex */
public class TextReportGenerator implements ReportGenerator {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private final int mBannerIndent;
    private final int mBannerWidth;
    private final Clock mClock = Clock.defaultClock();
    private final DateFormat mDateFormat = DateFormat.getDateTimeInstance(3, 2);
    private final double mDurationFactor;
    private final String mDurationUnit;
    private final double mRateFactor;
    private final String mRateUnit;

    /* loaded from: classes11.dex */
    public static final class Builder {
        private int mBannerIndent;
        private int mBannerWidth;
        private TimeUnit mDurationUnit;
        private TimeUnit mRateUnit;

        public TextReportGenerator build() {
            return new TextReportGenerator(this);
        }

        public Builder convertDurationsTo(TimeUnit timeUnit) {
            this.mDurationUnit = timeUnit;
            return this;
        }

        public Builder convertRatesTo(TimeUnit timeUnit) {
            this.mRateUnit = timeUnit;
            return this;
        }

        public Builder withBannerIndent(int i) {
            this.mBannerIndent = i;
            return this;
        }

        public Builder withBannerWidth(int i) {
            this.mBannerWidth = i;
            return this;
        }

        private Builder() {
            this.mRateUnit = TimeUnit.SECONDS;
            this.mDurationUnit = TimeUnit.MILLISECONDS;
        }
    }

    protected TextReportGenerator(Builder builder) {
        this.mRateFactor = builder.mRateUnit.toSeconds(1L);
        this.mRateUnit = calculateRateUnit(builder.mRateUnit);
        this.mDurationFactor = 1.0d / builder.mDurationUnit.toNanos(1L);
        this.mDurationUnit = builder.mDurationUnit.toString().toLowerCase(Locale.US);
        this.mBannerIndent = builder.mBannerIndent;
        this.mBannerWidth = builder.mBannerWidth;
    }

    public static Builder builder() {
        return new Builder();
    }

    private static String calculateRateUnit(TimeUnit timeUnit) {
        return GeneratedOutlineSupport1.outline50(timeUnit.toString().toLowerCase(Locale.US), -1, 0);
    }

    private double convertDuration(double d) {
        return d * this.mDurationFactor;
    }

    private double convertRate(double d) {
        return d * this.mRateFactor;
    }

    private void formatBanner(Formatter formatter, String str, char c) {
        int length = (this.mBannerWidth - this.mBannerIndent) - str.length();
        char[] cArr = new char[this.mBannerIndent];
        if (length <= 0) {
            length = 0;
        }
        char[] cArr2 = new char[length];
        if (cArr.length > 0) {
            Arrays.fill(cArr, c);
            cArr[cArr.length - 1] = Chars.SPACE;
        }
        if (cArr2.length > 0) {
            Arrays.fill(cArr2, c);
            cArr2[0] = Chars.SPACE;
        }
        formatter.format("%s%s%s%n", String.valueOf(cArr), str, String.valueOf(cArr2));
    }

    private void formatCounter(Formatter formatter, Map.Entry<String, Counter> entry) {
        formatter.format("             count = %d%n", Long.valueOf(entry.getValue().getCount()));
    }

    private void formatGauge(Formatter formatter, Map.Entry<String, Gauge> entry) {
        formatter.format("             value = %s%n", entry.getValue().mo6796getValue());
    }

    private void formatHistogram(Formatter formatter, Histogram histogram) {
        formatter.format("             count = %d%n", Long.valueOf(histogram.getCount()));
        Snapshot snapshot = histogram.getSnapshot();
        formatter.format("               min = %d%n", Long.valueOf(snapshot.getMin()));
        formatter.format("               max = %d%n", Long.valueOf(snapshot.getMax()));
        formatter.format("              mean = %2.2f%n", Double.valueOf(snapshot.getMean()));
        formatter.format("            stddev = %2.2f%n", Double.valueOf(snapshot.getStdDev()));
        formatter.format("            median = %2.2f%n", Double.valueOf(snapshot.getMedian()));
        formatter.format("              75%% <= %2.2f%n", Double.valueOf(snapshot.get75thPercentile()));
        formatter.format("              95%% <= %2.2f%n", Double.valueOf(snapshot.get95thPercentile()));
        formatter.format("              98%% <= %2.2f%n", Double.valueOf(snapshot.get98thPercentile()));
        formatter.format("              99%% <= %2.2f%n", Double.valueOf(snapshot.get99thPercentile()));
        formatter.format("            99.9%% <= %2.2f%n", Double.valueOf(snapshot.get999thPercentile()));
    }

    private void formatMeter(Formatter formatter, Meter meter) {
        formatter.format("             count = %d%n", Long.valueOf(meter.getCount()));
        formatter.format("         mean rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getMeanRate())), getRateUnit());
        formatter.format("     1-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getOneMinuteRate())), getRateUnit());
        formatter.format("     5-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getFiveMinuteRate())), getRateUnit());
        formatter.format("    15-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getFifteenMinuteRate())), getRateUnit());
    }

    private void formatMetrics(Writer writer, MetricRegistry metricRegistry, MetricFilter metricFilter) {
        Formatter formatter = new Formatter(writer);
        formatMetrics(formatter, metricRegistry, metricFilter);
        formatter.flush();
    }

    private void formatTimer(Formatter formatter, Timer timer) {
        Snapshot snapshot = timer.getSnapshot();
        formatter.format("             count = %d%n", Long.valueOf(timer.getCount()));
        formatter.format("         mean rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getMeanRate())), getRateUnit());
        formatter.format("     1-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getOneMinuteRate())), getRateUnit());
        formatter.format("     5-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getFiveMinuteRate())), getRateUnit());
        formatter.format("    15-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getFifteenMinuteRate())), getRateUnit());
        formatter.format("               min = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMin())), getDurationUnit());
        formatter.format("               max = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMax())), getDurationUnit());
        formatter.format("              mean = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMean())), getDurationUnit());
        formatter.format("            stddev = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getStdDev())), getDurationUnit());
        formatter.format("            median = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMedian())), getDurationUnit());
        formatter.format("              75%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get75thPercentile())), getDurationUnit());
        formatter.format("              95%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get95thPercentile())), getDurationUnit());
        formatter.format("              98%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get98thPercentile())), getDurationUnit());
        formatter.format("              99%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get99thPercentile())), getDurationUnit());
        formatter.format("            99.9%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get999thPercentile())), getDurationUnit());
    }

    private String getDurationUnit() {
        return this.mDurationUnit;
    }

    private String getRateUnit() {
        return this.mRateUnit;
    }

    @Override // com.amazon.android.codahalemetricreporter.impl.ReportGenerator
    public void generate(MetricRegistry metricRegistry, MetricFilter metricFilter, OutputStream outputStream) throws IOException {
        formatMetrics(new OutputStreamWriter(outputStream, CHARSET), metricRegistry, metricFilter);
    }

    private void formatMetrics(Formatter formatter, MetricRegistry metricRegistry, MetricFilter metricFilter) {
        formatBanner(formatter, this.mDateFormat.format(new Date(this.mClock.getTime())), Chars.EQ);
        formatter.format("%n", new Object[0]);
        SortedMap<String, Gauge> gauges = metricRegistry.getGauges(metricFilter);
        if (!gauges.isEmpty()) {
            formatBanner(formatter, "Gauges", '-');
            for (Map.Entry<String, Gauge> entry : gauges.entrySet()) {
                formatter.format("%s%n", entry.getKey());
                formatGauge(formatter, entry);
            }
            formatter.format("%n", new Object[0]);
        }
        SortedMap<String, Counter> counters = metricRegistry.getCounters(metricFilter);
        if (!counters.isEmpty()) {
            formatBanner(formatter, "Counters", '-');
            for (Map.Entry<String, Counter> entry2 : counters.entrySet()) {
                formatter.format("%s%n", entry2.getKey());
                formatCounter(formatter, entry2);
            }
            formatter.format("%n", new Object[0]);
        }
        SortedMap<String, Histogram> histograms = metricRegistry.getHistograms(metricFilter);
        if (!histograms.isEmpty()) {
            formatBanner(formatter, "Histograms", '-');
            for (Map.Entry<String, Histogram> entry3 : histograms.entrySet()) {
                formatter.format("%s%n", entry3.getKey());
                formatHistogram(formatter, entry3.getValue());
            }
            formatter.format("%n", new Object[0]);
        }
        SortedMap<String, Meter> meters = metricRegistry.getMeters(metricFilter);
        if (!meters.isEmpty()) {
            formatBanner(formatter, "Meters", '-');
            for (Map.Entry<String, Meter> entry4 : meters.entrySet()) {
                formatter.format("%s%n", entry4.getKey());
                formatMeter(formatter, entry4.getValue());
            }
            formatter.format("%n", new Object[0]);
        }
        SortedMap<String, Timer> timers = metricRegistry.getTimers(metricFilter);
        if (!timers.isEmpty()) {
            formatBanner(formatter, "Timers", '-');
            for (Map.Entry<String, Timer> entry5 : timers.entrySet()) {
                formatter.format("%s%n", entry5.getKey());
                formatTimer(formatter, entry5.getValue());
            }
            formatter.format("%n", new Object[0]);
        }
    }
}
